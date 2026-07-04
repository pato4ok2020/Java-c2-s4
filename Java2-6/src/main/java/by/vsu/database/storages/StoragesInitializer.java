package by.vsu.database.storages;

public class StoragesInitializer {
    public static void init(String jdbcDriver, String jdbcUrl, String jdbcUser, String jdbcPassword)
            throws ClassNotFoundException {
        GroupsStorage.init(jdbcDriver, jdbcUrl, jdbcUser, jdbcPassword);
        StudentsStorage.init(jdbcDriver, jdbcUrl, jdbcUser, jdbcPassword);
        UsersStorage.init(jdbcDriver, jdbcUrl, jdbcUser, jdbcPassword);
    }
}