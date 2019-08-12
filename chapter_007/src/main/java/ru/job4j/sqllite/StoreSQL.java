package ru.job4j.sqllite;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.*;
import java.util.function.Consumer;

/**
 * Класс подключается к БД SQLite.
 * Генерирует N записей в БД.
 * Создает структуру БД, если ее нет.
 * Загружает все записи из таблицы entry в List.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public class StoreSQL implements IStoreSQL, AutoCloseable {
    /**
     * Логер.
     */
    private static final Logger LOG = LogManager.getLogger(StoreSQL.class.getName());
    /**
     * Конфиг для подключения к БД.
     */
    private final Config config;
    /**
     * Соединение.
     */
    private Connection connection;
    /**
     * Словарь с действиями для создания таблиц в БД.
     */
    private final Map<String, Consumer<String>> actions = new HashMap<>();

    /**
     * В конструктор передается конфиг с данными для соединения с БД.
     * @param config - конфиг.
     */
    public StoreSQL(Config config) {
        this.config = config;
    }

    /**
     * Заполняет словарь actions действиями.
     */
    private void initActions() {
        this.actions.put("entry", this::createTableEntry);
        this.actions.put("account", this::createTableAccount);
    }

    /**
     * Устанавливает соединение с БД. Инициализирует actions.
     */
    public void init() {
        initActions();
        try {
            Class.forName(this.config.get("driver-class-name"));
            this.connection = DriverManager.getConnection(this.config.get("url"));
            this.connection.setAutoCommit(false);
            initDB();
        } catch (Exception e) {
            LOG.error("In init", e);
        }
    }

    /**
     * Инициализирует структуру БД, если ее нет. Или добавляет не хватающие таблицы.
     */
    private void initDB() {
        try {
            DatabaseMetaData metaData = this.connection.getMetaData();
            String[] types = {"table"};
            try (final ResultSet tables = metaData.getTables(null, null, "%", types)) {
                List<String> tablesNames = new ArrayList<>(Arrays.asList("entry", "account"));
                while (tables.next()) {
                    String tableName = tables.getString("TABLE_NAME");
                    tablesNames.removeIf(tableName::equals);
                }
                if (!tablesNames.isEmpty()) {
                    tablesNames.forEach(name -> this.actions.get(name).accept(name));
                }
            }
        } catch (SQLException e) {
            LOG.error("In initDB {}", e);
        }
    }

    /**
     * Создает таблицу со столбцом field типа integer по заданному имени.
     * @param name - имя таблицы.
     */
    private void createTableEntry(String name) {
        reqUpdate(
                new String[] {
                        String.format(
                                "%s %s(%s, %s);", "create table", name,
                                "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL",
                                "field integer"
                        )
                }
        );
    }

    /**
     * Создает таблицу со столбцом name типа varchar(255) по заданному имени.
     * @param name - имя таблицы.
     */
    private void createTableAccount(String name) {
        reqUpdate(
                new String[] {
                        String.format(
                                "%s %s(%s, %s);", "create table", name,
                                "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL",
                                "name varchar(255)"
                        )
                }
        );
    }

    /**
     * Выполняет executeBatch() переданные запросы.
     * @param requests - SQL запросы.
     */
    public void reqUpdate(String[] requests) {
        try (final Statement stmt = this.connection.createStatement()) {
            for (String req : requests) {
                stmt.addBatch(req);
            }
            stmt.executeBatch();
            this.connection.commit();
        } catch (SQLException e) {
            try {
                this.connection.rollback();
            } catch (SQLException e1) {
                LOG.error("reqUpdate is rollback", e1);
            }
            LOG.error("In reqUpdate ", e);
        }
    }

    @Override
    public void close() throws Exception {
        if (this.connection != null) {
            connection.close();
        }
    }

    /**
     * Возвращает количесвто цифр в числе.
     * @param number - число.
     * @return - количесвто цифр в числе.
     */
    public int countDigits(long number){
        int result = 0;
        for (long match = 0L; number > match; ++result) {
            match = (match << 3) + (match << 1) + 9L;
        }
        return result;
    }

    /**
     * Вставляет в таблицу entry n записей со значениями 1..N. Если в таблице account
     * находились записи, то они удаляются перед вставкой.
     * @param size - число записей.
     */
    @Override
    public void generate(int size) {
        reqUpdate(new String[]{"DELETE FROM entry;"});
        String req = "INSERT INTO entry (field) VALUES (?);";
        try (final PreparedStatement statement = this.connection.prepareStatement(req)) {
            if (size < 1000000) {
                upToMillion(statement, size);
            } else {
                int countDigits = countDigits(size);
                int numberOrder = (int) Math.pow(10, countDigits - 2);
                int intInsert = 1;
                for (int order = 0; order < size / numberOrder; ++order) {
                    afterMillion(statement, size, numberOrder, intInsert);
                    intInsert = intInsert + numberOrder;
                }
            }
        } catch (SQLException e) {
            try {
                this.connection.rollback();
            } catch (SQLException e1) {
                LOG.error("In generate {}", e1);
            }
            LOG.error("In generate {}", e);
        }
    }

    /**
     * Способ вставки в таблицу entry n записей со значениями 1..N, при числе записей до 1'000'000.
     * @param statement - PreparedStatement.
     * @param size - число записей.
     * @throws SQLException -
     */
    private void upToMillion(final PreparedStatement statement, int size) throws SQLException {
        for (int iteration = 1; iteration <= size; ++iteration) {
            statement.setInt(1, iteration);
            statement.addBatch();
        }
        statement.executeBatch();
        this.connection.commit();
    }

    /**
     * Способ вставки в таблицу entry n записей со значениями 1..N, при числе записей от 1'000'000.
     * @param statement - PreparedStatement.
     * @param size - число записей.
     * @param numberOrder -
     * @param intInsert - вставляемое в таблицу entry число (запись).
     * @throws SQLException -
     */
    private void afterMillion(final PreparedStatement statement,
                              int size, int numberOrder, int intInsert) throws SQLException {
        for (int countStatement = 0; countStatement < numberOrder && intInsert <= size; ++countStatement) {
            statement.setInt(1, intInsert);
            statement.addBatch();
            ++intInsert;
        }
        statement.executeBatch();
        this.connection.commit();
    }

    /**
     * Загружает все записи из таблицы entry и сохраняет их в List<Entry>.
     * @return - List<Entry>.
     */
    @Override
    public List<Entry> load() {
        List<Entry> entries = new ArrayList<>();
        try (Statement stmt = this.connection.createStatement()) {
            ResultSet result = stmt.executeQuery("SELECT * FROM entry;");
            Entry.EntryBuilder builder = new Entry.EntryBuilder();
            while (result.next()) {
                entries.add(builder
                                .setId(result.getInt("id"))
                                .setField(result.getInt("field"))
                                .build()
                );
            }
        } catch (SQLException e) {
            LOG.error("In load() {}", e);
        }
        return entries;
    }
}
