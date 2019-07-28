package ru.job4j.tracker;

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
     * Читает конфиг из app.properties и создает на основе конфига соединение с БД.
     * Далее вызывается метод private void initDB(), если в БД нет структуры, он ее создаст.
     * @return true если соединение создано.
     */
    public boolean init() {
        try (final InputStream in = TrackerSQL.class.getClassLoader().getResourceAsStream("app.properties")) {
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
            throw new IllegalStateException(e);
        }
        return this.connection != null;
    }

    /**
     * Создает Структуру в БД, если в БД нет ни одной таблицы.
     */
    private void initDB(final StringBuilder request) {
        try {
            DatabaseMetaData metaData = this.connection.getMetaData();
            String[] types = {"TABLE"};
            try (final ResultSet tables = metaData.getTables(null, null, "items", types)) {
                if (!tables.next()) {
                    reqUpdate(request.toString(), null, null);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
     * переданный в качестве первого параметра метода.
     * Если входящий параметр item не null, добавляет в запрос имя, описание и дату создания из item.
     * Если так же не null входящий параметр id, то добавляет в запрос 4 параметром заданный id.
     * Если входящий параметр item null, а входящий параметр id не null, то добавляется в запрос 1 параметром id.
     * @param req - SQL запрос.
     * @param item - объект типа Item.
     * @param id - id в БД.
     */
    private void reqUpdate(String req, Item item, Integer id) {
        try (final PreparedStatement statement = this.connection.prepareStatement(req)) {
            if (item != null) {
                statement.setString(1, item.getName());
                statement.setString(2, item.getDescription());
                statement.setLong(3, item.getCreate());
            }
            if (id != null && item != null) {
                statement.setInt(4, id);
            } else if (id != null) {
                statement.setInt(1, id);
            }
            statement.executeUpdate();
            this.connection.commit();
        } catch (SQLException e) {
            try {
                this.connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    /**
     * Создает PreparedStatement для this.connection и выполняет executeQuery() SQL запрос,
     * переданный в качестве первого параметра метода. Возвращает List<Item> с результатом запроса.
     * Если результатов нет - вернет пустую коллекцию.
     * Если второй параметр метода name не null - первым параметром SQL запроса устанавливается name.
     * Если name is null, а id не null - первым параметром SQL запроса устанавливается id.
     * @param req - SQL запрос.
     * @param name - имя заявки.
     * @param id - id в БД.
     * @return - Возвращает List<Item> с результатом запроса, если результатов нет - вернет пустую коллекцию.
     */
    private List<Item> reqSelect(String req, String name, Integer id) {
        List<Item> result = new ArrayList<>();
        try (final PreparedStatement statement = this.connection.prepareStatement(req)) {
            if (name != null) {
                statement.setString(1, name);
            } else if (id != null) {
                statement.setInt(1, id);
            }
            try (final ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Item item = new Item(resultSet.getString("name"),
                            resultSet.getString("description"),
                            resultSet.getLong("create_date")
                    );
                    item.setId(String.valueOf(resultSet.getInt("id")));
                    result.add(item);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
        reqUpdate("INSERT INTO items (name, description, create_date) VALUES (?, ?, ?)", item, null);
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
        reqUpdate("DELETE FROM items WHERE id = ?", null, Integer.valueOf(id));
    }

    /**
     * Взять все заявки из БД.
     * @return List<Item> со всеми заявками.
     */
    @Override
    public List<Item> getAll() {
        return reqSelect("SELECT * FROM items", null, null);
    }

    /**
     * Взять все заявки из БД с заданным именем.
     * @param key - имя заявки.
     * @return List<Item> со всеми заявками с заданным именем.
     */
    @Override
    public List<Item> findByName(String key) {
        return reqSelect("SELECT * FROM items WHERE name = ?;", key, null);
    }

    /**
     * Взять заявку из БД во id.
     * @param id - id заявки.
     * @return объект типа Item.
     */
    @Override
    public Item findById(String id) {
        Item item = new Item();
        List<Item> items = reqSelect("SELECT * FROM items WHERE id = ?;", null, Integer.valueOf(id));
        if (!items.isEmpty()) {
            item = items.get(0);
        }
        return item;
    }
}
