package ru.job4j.tracker;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.trackerrefactor.ITracker;
import ru.job4j.trackerrefactor.Item;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Реализация интерфейса ITracker для хранения зявок в базе данных.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public class TrackerSQL implements ITracker, AutoCloseable {
    /**
     * Логер.
     */
    private static final Logger LOG = LogManager.getLogger(TrackerSQL.class.getName());
    /**
     * Соединение с базод данных.
     */
    private Connection connection;
    /**
     * SQL запрос, создающий структуру БД.
     */
    private StringBuilder structure = new StringBuilder()
            .append("CREATE TABLE items (id SERIAL PRIMARY KEY,")
            .append("name VARCHAR(50) NOT NULL, description VARCHAR(50) NOT NULL, create_date BIGINT NOT NULL);");

    /**
     * Инициализация класса TrackerSQL.
     * Читает конфиг из trackerSQL.properties и создает на основе конфига соединение с БД.
     * Далее вызывается метод private void initDB(), если в БД нет структуры, он ее создаст.
     * @return true если соединение создано.
     */
    public boolean init() {
        try (final InputStream in = TrackerSQL.class.getClassLoader().getResourceAsStream("trackerSQL.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            this.connection = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
            this.connection.setAutoCommit(false);
            initDB(this.structure);
        } catch (Exception e) {
            LOG.error("trace message {}", e);
            throw new IllegalStateException(e);
        }
        return this.connection != null;
    }

    /**
     * Создает Структуру в БД, если в БД нет ни одной таблицы.
     * @param request - SQL запрос, создающий структуру БД.
     */
    private void initDB(final StringBuilder request) {
        try {
            DatabaseMetaData metaData = this.connection.getMetaData();
            String[] types = {"TABLE"};
            try (final ResultSet tables = metaData.getTables(null, null, "items", types)) {
                if (!tables.next()) {
                    reqUpdate(request.toString());
                }
            }
        } catch (SQLException e) {
            LOG.error("trace message {}", e);
        }
    }

    @Override
    public void close() throws SQLException {
        if (this.connection != null) {
            this.connection.close();
        }
    }

    /**
     * Создает PreparedStatement для this.connection и выполняет executeUpdate() SQL запрос,
     * переданный в качестве параметра метода.
     * @param req - SQL запрос.
     */
    private void reqUpdate(String req) {
        try (final PreparedStatement statement = this.connection.prepareStatement(req)) {
            statement.executeUpdate();
            this.connection.commit();
        } catch (SQLException e) {
            try {
                this.connection.rollback();
            } catch (SQLException e1) {
                LOG.error("trace message {}", e1);
            }
            LOG.error("trace message {}", e);
        }
    }

    /**
     * Создает PreparedStatement для this.connection и выполняет executeUpdate() SQL запрос,
     * переданный в качестве первого параметра метода.
     * Добавляет в запрос первым параметром заданный id.
     * @param req - SQL запрос.
     * @param id - id в БД.
     */
    private void reqUpdate(String req, Integer id) {
        try (final PreparedStatement statement = this.connection.prepareStatement(req)) {
            if (id != null) {
                statement.setInt(1, id);
                statement.executeUpdate();
                this.connection.commit();
            }
        } catch (SQLException e) {
            try {
                this.connection.rollback();
            } catch (SQLException e1) {
                LOG.error("trace message {}", e1);
            }
            LOG.error("trace message {}", e);
        }
    }

    /**
     * Создает PreparedStatement для this.connection и выполняет executeUpdate() SQL запрос,
     * переданный в качестве первого параметра метода.
     * Добавляет в запрос имя, описание и дату создания из item.
     * А так же добавляет четвертым параметром id.
     * @param req - SQL запрос.
     * @param item - объект типа Item.
     * @param id - id в БД.
     */
    private void reqUpdate(String req, Item item, Integer id) {
        try (final PreparedStatement statement = this.connection.prepareStatement(req)) {
            if (id != null && item != null) {
                statement.setString(1, item.getName());
                statement.setString(2, item.getDescription());
                statement.setLong(3, item.getCreate());
                statement.setInt(4, id);
                statement.executeUpdate();
                this.connection.commit();
            }
        } catch (SQLException e) {
            try {
                this.connection.rollback();
            } catch (SQLException e1) {
                LOG.error("trace message {}", e1);
            }
            LOG.error("trace message {}", e);
        }
    }

    /**
     * Создает PreparedStatement для this.connection и выполняет executeUpdate() SQL запрос,
     * переданный в качестве первого параметра метода.
     * Добавляет в запрос имя, описание и дату создания из item.
     * @param req - SQL запрос.
     * @param item - объект типа Item.
     */
    private void reqUpdate(String req, Item item) {
        try (final PreparedStatement statement = this.connection.prepareStatement(req)) {
            if (item != null) {
                statement.setString(1, item.getName());
                statement.setString(2, item.getDescription());
                statement.setLong(3, item.getCreate());
                statement.executeUpdate();
                this.connection.commit();
            }
        } catch (SQLException e) {
            try {
                this.connection.rollback();
            } catch (SQLException e1) {
                LOG.error("trace message {}", e1);
            }
            LOG.error("trace message {}", e);
        }
    }

    /**
     * Добавляет все заявки из resultSet в переданный, в качестве параметра, list.
     * @param list - коллекция типа List.
     * @param resultSet - ResultSet
     * @throws SQLException ResultSet может выкинуть.
     */
    private void addItem(List<Item> list, ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            Item item = new Item(resultSet.getString("name"),
                    resultSet.getString("description"),
                    resultSet.getLong("create_date")
            );
            item.setId(String.valueOf(resultSet.getInt("id")));
            list.add(item);
        }
    }

    /**
     * Создает PreparedStatement для this.connection и выполняет executeQuery() SQL запрос,
     * переданный в качестве параметра метода. Возвращает List<Item> с результатом запроса.
     * Если результатов нет - вернет пустую коллекцию.
     * @param req - SQL запрос.
     * @return - Возвращает List<Item> с результатом запроса, если результатов нет - вернет пустую коллекцию.
     */
    private List<Item> reqSelect(String req) {
        List<Item> result = new ArrayList<>();
        try (final PreparedStatement statement = this.connection.prepareStatement(req)) {
            try (final ResultSet resultSet = statement.executeQuery()) {
                addItem(result, resultSet);
            }
        } catch (SQLException e) {
            LOG.error("trace message {}", e);
        }
        return result;
    }

    /**
     * Создает PreparedStatement для this.connection и выполняет executeQuery() SQL запрос,
     * переданный в качестве первого параметра метода. Возвращает List<Item> с результатом запроса.
     * Если результатов нет - вернет пустую коллекцию.
     * Первым параметром SQL запроса устанавливается name.
     * @param req - SQL запрос.
     * @return - Возвращает List<Item> с результатом запроса, если результатов нет - вернет пустую коллекцию.
     */
    private List<Item> reqSelect(String req, String name) {
        List<Item> result = new ArrayList<>();
        try (final PreparedStatement statement = this.connection.prepareStatement(req)) {
            if (name != null) {
                statement.setString(1, name);
            }
            try (final ResultSet resultSet = statement.executeQuery()) {
                addItem(result, resultSet);
            }
        } catch (SQLException e) {
            LOG.error("trace message {}", e);
        }
        return result;
    }

    /**
     * Создает PreparedStatement для this.connection и выполняет executeQuery() SQL запрос,
     * переданный в качестве первого параметра метода. Возвращает List<Item> с результатом запроса.
     * Если результатов нет - вернет пустую коллекцию.
     * Первым параметром SQL запроса устанавливается name.
     * @param req - SQL запрос.
     * @return - Возвращает List<Item> с результатом запроса, если результатов нет - вернет пустую коллекцию.
     */
    private List<Item> reqSelect(String req, Integer id) {
        List<Item> result = new ArrayList<>();
        try (final PreparedStatement statement = this.connection.prepareStatement(req)) {
            if (req != null && id != null) {
                statement.setInt(1, id);
                try (final ResultSet resultSet = statement.executeQuery()) {
                    addItem(result, resultSet);
                }
            }
        } catch (SQLException e) {
            LOG.error("trace message {}", e);
        }
        return result;
    }


    /**
     * Добавить item в БД.
     * @param item - заявка типа Item.
     * @return - добавляемую заявку.
     */
    @Override
    public Item add(Item item) {
        reqUpdate("INSERT INTO items (name, description, create_date) VALUES (?, ?, ?)", item);
        return item;
    }

    /**
     * Заменить старую заявку новой, по id.
     * @param id - id заявки.
     * @param item - новая заявка.
     */
    @Override
    public void replace(String id, Item item) {
        reqUpdate(
                "UPDATE items SET name = ?, description = ?, create_date = ? WHERE id = ?",
                item,
                Integer.parseInt(id)
        );
    }

    /**
     * Удалить заявку из БД по id.
     * @param id - id заявки.
     */
    @Override
    public void delete(String id) {
        reqUpdate("DELETE FROM items WHERE id = ?", Integer.valueOf(id));
    }

    /**
     * Взять все заявки из БД.
     * @return List<Item> со всеми заявками.
     */
    @Override
    public List<Item> getAll() {
        return reqSelect("SELECT * FROM items");
    }

    /**
     * Взять все заявки из БД с заданным именем.
     * @param key - имя заявки.
     * @return List<Item> со всеми заявками с заданным именем.
     */
    @Override
    public List<Item> findByName(String key) {
        return reqSelect("SELECT * FROM items WHERE name = ?;", key);
    }

    /**
     * Взять заявку из БД во id.
     * @param id - id заявки.
     * @return объект типа Item.
     */
    @Override
    public Item findById(String id) {
        Item item = new Item();
        List<Item> items = reqSelect("SELECT * FROM items WHERE id = ?;", Integer.valueOf(id));
        if (!items.isEmpty()) {
            item = items.get(0);
        }
        return item;
    }
}
