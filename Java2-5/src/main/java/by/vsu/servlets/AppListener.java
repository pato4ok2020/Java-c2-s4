package by.vsu.servlets;

import by.vsu.Book.Storage;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class AppListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            Storage.init("org.postgresql.Driver", "jdbc:postgresql://localhost:5432/library_db",
                    "postgres", "postgres");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
