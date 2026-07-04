package by.vsu.database.storages;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import by.vsu.database.models.Group;
import by.vsu.database.models.Student;

public class StudentsStorage {
    private static String jdbcUrl = null;
    private static String jdbcUser = null;
    private static String jdbcPassword = null;

    public static void init(String jdbcDriver, String jdbcUrl, String jdbcUser, String jdbcPassword)
            throws ClassNotFoundException {

        Class.forName(jdbcDriver);
        StudentsStorage.jdbcUrl = jdbcUrl;
        StudentsStorage.jdbcUser = jdbcUser;
        StudentsStorage.jdbcPassword = jdbcPassword;
    }

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);
    }

    public static Student parseStudentFromResultSet(ResultSet r) throws SQLException {
        return new Student(
                r.getInt("id"),
                r.getInt("group_id"),
                r.getString("last_name"),
                r.getString("first_name"),
                r.getString("middle_name"),
                r.getString("sex"),
                r.getDate("birthday").toLocalDate(),
                r.getString("funding_type"),
                r.getInt("failed_exams"));
    }

    public static void setStudentInStatement(PreparedStatement s, Student student) throws SQLException {
        s.setInt(1, student.getGroupId());
        s.setString(2, student.getLastName());
        s.setString(3, student.getFirstName());
        s.setString(4, student.getMiddleName());
        s.setString(5, student.getSex());
        s.setDate(6, Date.valueOf(student.getBirthday()));
        s.setString(7, student.getFundingType());
        s.setInt(8, student.getFailedExams());
    }

    public static Collection<Student> readAll() throws SQLException {
        String sql = "SELECT * FROM students";

        try (Connection c = getConnection(); Statement s = c.createStatement(); ResultSet r = s.executeQuery(sql)) {
            Collection<Student> students = new ArrayList<>();

            while (r.next()) {
                students.add(parseStudentFromResultSet(r));
            }

            return students;
        }
    }

    public static Collection<Student> readAll(String sort) throws SQLException {
        if (!sort.equals("group_id")
                && !sort.equals("last_name")
                && !sort.equals("first_name")
                && !sort.equals("middle_name")
                && !sort.equals("sex")
                && !sort.equals("birthday")
                && !sort.equals("funding_type")
                && !sort.equals("failed_exams")) {

            sort = "id";
        }

        String sql = "SELECT * FROM students ORDER BY " + sort;

        try (Connection c = getConnection(); Statement s = c.createStatement(); ResultSet r = s.executeQuery(sql)) {
            Collection<Student> students = new ArrayList<>();

            while (r.next()) {
                students.add(parseStudentFromResultSet(r));
            }

            return students;
        }
    }

    public static Student readById(Integer id) throws SQLException {
        String sql = "SELECT * FROM students WHERE id=?";

        try (Connection c = getConnection(); PreparedStatement s = c.prepareStatement(sql)) {
            s.setInt(1, id);

            try (ResultSet r = s.executeQuery()) {
                if (r.next()) {
                    return parseStudentFromResultSet(r);
                }

                return null;
            }
        }
    }

    public static void create(Student student) throws SQLException {
        checkGroupLimit(student.getGroupId());

        String sql = "INSERT INTO students " +
                "(group_id, last_name, first_name, " +
                "middle_name, sex, birthday, " +
                "funding_type, failed_exams) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection c = getConnection();
                PreparedStatement s = c.prepareStatement(sql)) {
            setStudentInStatement(s, student);
            s.executeUpdate();
        }
    }

    public static void update(Student student) throws SQLException {
        // Получаем текущие данные студента из базы до обновления
        Student oldStudent = readById(student.getId());

        // Если группа изменилась, нужно проверить лимит в НОВОЙ группе
        if (oldStudent != null && !oldStudent.getGroupId().equals(student.getGroupId())) {
            checkGroupLimit(student.getGroupId());
        }

        String sql = "UPDATE students SET " +
                "group_id=?, " +
                "last_name=?, " +
                "first_name=?, " +
                "middle_name=?, " +
                "sex=?, " +
                "birthday=?, " +
                "funding_type=?, " +
                "failed_exams=? " +
                "WHERE id=?";

        try (Connection c = getConnection(); PreparedStatement s = c.prepareStatement(sql)) {
            setStudentInStatement(s, student);
            s.setInt(9, student.getId());
            s.executeUpdate();
        }
    }

    public static void delete(Integer id) throws SQLException {
        String sql = "DELETE FROM students WHERE id=?";

        try (Connection c = getConnection(); PreparedStatement s = c.prepareStatement(sql)) {
            s.setInt(1, id);
            s.executeUpdate();
        }
    }

    public static Collection<Student> readByGroupId(Integer groupId) throws SQLException {
        String sql = "SELECT * FROM students WHERE group_id=?";

        try (Connection c = getConnection(); PreparedStatement s = c.prepareStatement(sql)) {
            s.setInt(1, groupId);
            try (ResultSet r = s.executeQuery()) {
                Collection<Student> students = new ArrayList<>();

                while (r.next()) {
                    students.add(parseStudentFromResultSet(r));
                }

                return students;
            }
        }
    }

    public static Collection<Student> readByGroupId(Integer groupId, String sort) throws SQLException {
        if (!sort.equals("last_name") && !sort.equals("first_name")
                && !sort.equals("middle_name") && !sort.equals("sex")
                && !sort.equals("birthday") && !sort.equals("funding_type")
                && !sort.equals("failed_exams")) {
            sort = "id";
        }

        String sql = "SELECT * FROM students WHERE group_id=? ORDER BY " + sort;

        try (Connection c = getConnection(); PreparedStatement s = c.prepareStatement(sql)) {
            s.setInt(1, groupId);
            try (ResultSet r = s.executeQuery()) {
                Collection<Student> students = new ArrayList<>();
                while (r.next()) {
                    students.add(parseStudentFromResultSet(r));
                }
                return students;
            }
        }
    }

    private static void checkGroupLimit(Integer groupId) throws SQLException {
        Group group = GroupsStorage.readById(groupId);
        if (group == null) {
            throw new IllegalArgumentException("Группа не существует");
        }

        int limit = group.getStudyForm().equals("очная") ? 20 : 30;

        String sql = "SELECT COUNT(*) FROM students WHERE group_id = ?";
        try (Connection c = getConnection(); PreparedStatement s = c.prepareStatement(sql)) {
            s.setInt(1, groupId);
            try (ResultSet r = s.executeQuery()) {
                if (r.next()) {
                    int currentCount = r.getInt(1);
                    if (currentCount >= limit) {
                        throw new IllegalArgumentException("Превышен лимит студентов для группы ("
                                + group.getStudyForm() + "): максимум " + limit);
                    }
                }
            }
        }
    }
}