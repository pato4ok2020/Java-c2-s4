package by.vsu.servlets;

import by.vsu.Book.Storage;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class AppListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        String path = "D:\\PATO4OK2020\\APPS\\apache-tomcat-11.0.20\\webapps\\storage.bin";
        Storage.init(path);
    }
}