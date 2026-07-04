package by.vsu.database.storages;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import by.vsu.database.models.Group;

public class GroupsStorage {
    private static String jdbcUrl = null;
    private static String jdbcUser = null;
    private static String jdbcPassword = null;

    public static void init(String jdbcDriver, String jdbcUrl, String jdbcUser, String jdbcPassword)
            throws ClassNotFoundException {
        Class.forName(jdbcDriver);
        GroupsStorage.jdbcUrl = jdbcUrl;
        GroupsStorage.jdbcUser = jdbcUser;
        GroupsStorage.jdbcPassword = jdbcPassword;
    }

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);
    }

    public static Group parseGroupFromResultSet(ResultSet r) throws SQLException {
        return new Group(
                r.getInt("id"),
                r.getString("name"),
                r.getInt("amount_students"),
                r.getString("specialization"),
                r.getInt("course"),
                r.getString("study_form"));
    }

    public static Collection<Group> readAll(String sort) throws SQLException {
        if (sort == null || (!sort.equals("name")
                && !sort.equals("specialization")
                && !sort.equals("course")
                && !sort.equals("study_form"))) {
            sort = "id";
        }

        String sql = "SELECT g.id, g.name, g.specialization, g.course, g.study_form, " +
                "COUNT(s.id) AS amount_students " +
                "FROM groups g " +
                "LEFT JOIN students s ON g.id = s.group_id " +
                "GROUP BY g.id, g.name, g.specialization, g.course, g.study_form " +
                "ORDER BY " + sort;

        try (Connection c = getConnection(); Statement s = c.createStatement(); ResultSet r = s.executeQuery(sql)) {
            Collection<Group> groups = new ArrayList<>();
            while (r.next()) {
                groups.add(parseGroupFromResultSet(r));
            }
            return groups;
        }
    }

    public static Group readById(Integer id) throws SQLException {
        String sql = "SELECT g.id, g.name, g.specialization, g.course, g.study_form, " +
                "COUNT(s.id) AS amount_students " +
                "FROM groups g " +
                "LEFT JOIN students s ON g.id = s.group_id " +
                "WHERE g.id = ? " +
                "GROUP BY g.id, g.name, g.specialization, g.course, g.study_form";

        try (Connection c = getConnection(); PreparedStatement s = c.prepareStatement(sql)) {
            s.setInt(1, id);
            try (ResultSet r = s.executeQuery()) {
                if (r.next()) {
                    return parseGroupFromResultSet(r);
                }
                return null;
            }
        }
    }

    public static void create(Group group) throws SQLException {
        String sql = "INSERT INTO groups (name, specialization, course, study_form) VALUES (?, ?, ?, ?)";

        try (Connection c = getConnection(); PreparedStatement s = c.prepareStatement(sql)) {
            s.setString(1, group.getName());
            s.setString(2, group.getSpecialization());
            s.setInt(3, group.getCourse());
            s.setString(4, group.getStudyForm());
            s.executeUpdate();
        }
    }

    public static void update(Group group) throws SQLException {
        String sql = "UPDATE groups SET name=?, specialization=?, course=?, study_form=? WHERE id=?";

        try (Connection c = getConnection(); PreparedStatement s = c.prepareStatement(sql)) {
            s.setString(1, group.getName());
            s.setString(2, group.getSpecialization());
            s.setInt(3, group.getCourse());
            s.setString(4, group.getStudyForm());
            s.setInt(5, group.getId());
            s.executeUpdate();
        }
    }

    public static void delete(Integer id) throws SQLException {
        String sql = "DELETE FROM groups WHERE id=?";
        try (Connection c = getConnection(); PreparedStatement s = c.prepareStatement(sql)) {
            s.setInt(1, id);
            s.executeUpdate();
        }
    }
}