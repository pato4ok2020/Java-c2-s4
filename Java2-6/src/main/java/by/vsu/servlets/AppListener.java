package by.vsu.servlets;

import by.vsu.database.storages.StoragesInitializer;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class AppListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            StoragesInitializer.init("org.postgresql.Driver",
                    "jdbc:postgresql://localhost:5432/university_java2_6_db",
                    "postgres", "postgres");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
