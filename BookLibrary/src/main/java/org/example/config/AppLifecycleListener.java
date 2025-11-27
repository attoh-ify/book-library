package org.example.config;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class AppLifecycleListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("[Book-Library] Service starting up...");
        System.out.println("Environment: " + System.getProperty("env", "development"));
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("[Book-Library] Service shutting down...");
    }
}


//Logs startup events
//
//Good place for initializing:
//
//database connections
//
//background jobs
//
//caches
//
//config loading