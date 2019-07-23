package ru.job4j.tracker;

import ru.job4j.trackerrefactor.ITracker;
import ru.job4j.trackerrefactor.Item;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class TrackerSQL implements ITracker, AutoCloseable {

    private Connection connection;

    public boolean init() {
        try (InputStream in = TrackerSQL.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            this.connection = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
            connection.setAutoCommit(false);
            initDB();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        return this.connection != null;
    }

    private void initDB() {
        try {
            DatabaseMetaData metaData = this.connection.getMetaData();
            String[] types = {"TABLE"};
            ResultSet tables = metaData.getTables(null, null, "items", types);
            if (!tables.next()) {
                StringBuilder request = new StringBuilder();
                request.append("CREATE TABLE items (")
                        .append("id SERIAL PRIMARY KEY,")
                        .append("name VARCHAR(50) NOT NULL,")
                        .append("description VARCHAR(50) NOT NULL,")
                        .append("create_date BIGINT NOT NULL );");
                reqUpdate(request.toString());
            }
            if (!tables.isClosed()) {
                tables.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void reqUpdate(String req) {
        try (Statement state = this.connection.createStatement()) {
            state.executeUpdate(req);
            this.connection.commit();
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

    private PreparedStatement createStatement(String req, Item item) throws SQLException {
        PreparedStatement statement =  this.connection.prepareStatement(req);
        if (item != null) {
            statement.setString(1, item.getName());
            statement.setString(2, item.getDescription());
            statement.setLong(3, item.getCreate());
        }
        return statement;
    }

    @Override
    public Item add(Item item) {
        try (PreparedStatement statement = createStatement(
                "INSERT INTO items (name, description, create_date) VALUES (?, ?, ?)", item)) {
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
        return item;
    }

    @Override
    public void replace(String id, Item item) {
        try (PreparedStatement statement = createStatement(
                "UPDATE items SET name = ?, description = ?, create_date = ? WHERE id = ?", item)) {
            statement.setInt(4, Integer.parseInt(id));
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

    @Override
    public void delete(String id) {
        try (PreparedStatement statement = createStatement(
                "DELETE FROM items WHERE id = ?", null)) {
            statement.setInt(1, Integer.parseInt(id));
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

    @Override
    public List<Item> getAll() {
        List<Item> items = new ArrayList<>();
        try (PreparedStatement statement = createStatement("SELECT * FROM items;", null)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Item item = new Item(resultSet.getString("name"),
                            resultSet.getString("description"),
                            resultSet.getLong("create_date")
                    );
                    item.setId(String.valueOf(resultSet.getInt("id")));
                    items.add(item);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    @Override
    public List<Item> findByName(String key) {
        List<Item> items = new ArrayList<>();
        try (PreparedStatement statement = createStatement("SELECT * FROM items WHERE name = ?;", null)) {
            statement.setString(1, key);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Item item = new Item(resultSet.getString("name"),
                            resultSet.getString("description"),
                            resultSet.getLong("create_date")
                    );
                    item.setId(String.valueOf(resultSet.getInt("id")));
                    items.add(item);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    @Override
    public Item findById(String id) {
        return null;
    }
}
