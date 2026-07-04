package by.vsu.database.storages;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import by.vsu.database.models.User;

public class UsersStorage {
    private static String jdbcUrl = null;
    private static String jdbcUser = null;
    private static String jdbcPassword = null;

    public static void init(String jdbcDriver, String jdbcUrl, String jdbcUser, String jdbcPassword)
            throws ClassNotFoundException {
        Class.forName(jdbcDriver);
        UsersStorage.jdbcUrl = jdbcUrl;
        UsersStorage.jdbcUser = jdbcUser;
        UsersStorage.jdbcPassword = jdbcPassword;
    }

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);
    }

    public static User parseUserFromResultSet(ResultSet r) throws SQLException {
        return new User(
                r.getInt("id"),
                r.getString("login"),
                r.getString("password"),
                r.getString("role"));
    }

    public static void setUserInStatement(PreparedStatement s, User user) throws SQLException {
        s.setString(1, user.getLogin());
        s.setString(2, user.getPassword());
        s.setString(3, user.getRole());
    }

    public static Collection<User> readAll(String sort) throws SQLException {
        String sql = "SELECT * FROM users ORDER BY " + sort;

        try (Connection c = getConnection(); Statement s = c.createStatement(); ResultSet r = s.executeQuery(sql)) {
            Collection<User> users = new ArrayList<>();
            while (r.next()) {
                users.add(parseUserFromResultSet(r));
            }
            return users;
        }
    }

    public static Collection<User> readAll() throws SQLException {
        String sql = "SELECT * FROM users";

        try (Connection c = getConnection(); Statement s = c.createStatement(); ResultSet r = s.executeQuery(sql)) {
            Collection<User> users = new ArrayList<>();
            while (r.next()) {
                users.add(parseUserFromResultSet(r));
            }
            return users;
        }
    }

    public static User readById(Integer id) throws SQLException {
        String sql = "SELECT * FROM users WHERE id = ?";

        try (Connection c = getConnection(); PreparedStatement s = c.prepareStatement(sql)) {
            s.setInt(1, id);
            try (ResultSet r = s.executeQuery()) {
                User user = null;
                if (r.next()) {
                    user = parseUserFromResultSet(r);
                }
                return user;
            }
        }
    }

    public static User readByLogin(String login) throws SQLException {
        String sql = "SELECT * FROM users WHERE login = ?";

        try (Connection c = getConnection(); PreparedStatement s = c.prepareStatement(sql)) {
            s.setString(1, login);
            try (ResultSet r = s.executeQuery()) {
                User user = null;
                if (r.next()) {
                    user = parseUserFromResultSet(r);
                }
                return user;
            }
        }
    }

    public static void create(User user) throws SQLException {
        String sql = "INSERT INTO users (login, password, role) VALUES (?, ?, ?)";

        try (Connection c = getConnection(); PreparedStatement s = c.prepareStatement(sql)) {
            setUserInStatement(s, user);
            s.executeUpdate();
        }
    }

    public static void update(User user) throws SQLException {
        String sql = "UPDATE users SET login=?, password=?, role=? WHERE id=?";

        try (Connection c = getConnection(); PreparedStatement s = c.prepareStatement(sql)) {
            setUserInStatement(s, user);
            s.setInt(4, user.getId());
            s.executeUpdate();
        }
    }

    public static void delete(Integer id) throws SQLException {
        String sql = "DELETE FROM users WHERE id=?";

        try (Connection c = getConnection(); PreparedStatement s = c.prepareStatement(sql)) {
            s.setInt(1, id);
            s.executeUpdate();
        }
    }

    public static User checkUser(String login, String password) throws SQLException {
        String sql = "SELECT * FROM users WHERE login=? AND password=?";

        try (Connection c = getConnection(); PreparedStatement s = c.prepareStatement(sql)) {
            s.setString(1, login);
            s.setString(2, password);
            try (ResultSet r = s.executeQuery()) {
                if (r.next()) {
                    return parseUserFromResultSet(r);
                }
                return null;
            }
        }
    }

    public static User authorize(String login, String password) throws SQLException {
        String sql = "SELECT * FROM users WHERE login=? AND password=?";

        try (Connection c = getConnection(); PreparedStatement s = c.prepareStatement(sql)) {
            s.setString(1, login);
            s.setString(2, password);

            try (ResultSet r = s.executeQuery()) {
                User user = null;
                if (r.next()) {
                    user = parseUserFromResultSet(r);
                }
                return user;
            }
        }
    }
}