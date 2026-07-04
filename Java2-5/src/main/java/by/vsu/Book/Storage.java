package by.vsu.Book;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

public class Storage {
    private static String jdbcUrl = null;
    private static String jdbcUser = null;
    private static String jdbcPassword = null;

    public static void init(String jdbcDriver, String jdbcUrl, String jdbcUser, String jdbcPassword)
            throws ClassNotFoundException {
        Class.forName(jdbcDriver);
        Storage.jdbcUrl = jdbcUrl;
        Storage.jdbcUser = jdbcUser;
        Storage.jdbcPassword = jdbcPassword;
    }

    public static Collection<Book> readAll() throws SQLException {
        String sql = "SELECT * FROM books";

        try (Connection c = getConnection(); Statement s = c.createStatement(); ResultSet r = s.executeQuery(sql)) {
            Collection<Book> books = new ArrayList<>();
            while (r.next()) {
                Book book = new Book(
                        r.getInt("id"),
                        r.getString("code"),
                        r.getString("title"),
                        r.getString("author"),
                        r.getInt("year"),
                        r.getInt("amountPages"),
                        r.getInt("amountLinks"));
                books.add(book);
            }
            return books;
        }
    }

    public static Book readById(Integer id) throws SQLException {
        String sql = "SELECT * FROM books WHERE id = ?;";

        try (Connection c = getConnection(); PreparedStatement s = c.prepareStatement(sql);) {
            s.setInt(1, id);
            try (ResultSet r = s.executeQuery()) {
                Book book = null;
                if (r.next()) {
                    book = new Book(
                            r.getInt("id"),
                            r.getString("code"),
                            r.getString("title"),
                            r.getString("author"),
                            r.getInt("year"),
                            r.getInt("amountPages"),
                            r.getInt("amountLinks"));
                }
                return book;
            }
        }
    }

    public static void create(Book book) throws SQLException {
        if (existsByCode(book.getCode())) {
            throw new IllegalArgumentException("Шифр должен быть уникальным!");
        }

        String sql = "INSERT INTO books (code, title, author, year, amountPages, amountLinks, citationIndex) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?);";

        try (Connection c = getConnection(); PreparedStatement s = c.prepareStatement(sql)) {
            s.setString(1, book.getCode());
            s.setString(2, book.getTitle());
            s.setString(3, book.getAuthor());
            s.setInt(4, book.getYear());
            s.setInt(5, book.getAmountPages());
            s.setInt(6, book.getAmountLinks());
            s.setFloat(7, book.getCitationIndex());
            s.executeUpdate();
        }
    }

    public static void update(Book book) throws SQLException {
        String sql = "UPDATE books SET code=?, title=?, author=?, " +
                "year=?, amountPages=?, amountLinks=?, citationIndex=? WHERE id=?";

        try (Connection c = getConnection(); PreparedStatement s = c.prepareStatement(sql)) {
            s.setString(1, book.getCode());
            s.setString(2, book.getTitle());
            s.setString(3, book.getAuthor());
            s.setInt(4, book.getYear());
            s.setInt(5, book.getAmountPages());
            s.setInt(6, book.getAmountLinks());
            s.setFloat(7, book.getCitationIndex());
            s.setInt(8, book.getId());
            s.executeUpdate();
        }
    }

    public static void delete(Integer id) throws SQLException {
        String sql = "DELETE FROM books WHERE id=?";

        try (Connection c = getConnection(); PreparedStatement s = c.prepareStatement(sql)) {
            s.setInt(1, id);
            s.executeUpdate();
        }
    }

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);
    }

    public static boolean existsByCode(String code) throws SQLException {
        String sql = "SELECT * FROM books WHERE code = ?;";

        try (Connection c = getConnection(); PreparedStatement s = c.prepareStatement(sql);) {
            s.setString(1, code);
            try (ResultSet r = s.executeQuery()) {
                return r.next();
            }
        }
    }

    public static Collection<String> getBestAuthors(Collection<Book> books) throws SQLException {
        String sql = """
                SELECT author FROM books
                GROUP BY author
                HAVING SUM(citationIndex) = (
                    SELECT MAX(sumCitationIndex) FROM (
                        SELECT SUM(citationIndex) AS sumCitationIndex FROM books
                        GROUP BY author
                    ) AS sums
                );
                """;

        try (Connection c = getConnection(); Statement s = c.createStatement(); ResultSet r = s.executeQuery(sql);) {
            Collection<String> bestAuthors = new ArrayList<>();
            while (r.next()) {
                bestAuthors.add(r.getString("author"));
            }
            return bestAuthors;
        }
    }
}