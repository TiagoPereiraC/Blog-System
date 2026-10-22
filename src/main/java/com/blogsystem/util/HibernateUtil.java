package com.blogsystem.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import com.blogsystem.model.Autor;
import com.blogsystem.model.Post;
import com.blogsystem.model.Comentario;
import java.util.Properties;

public class HibernateUtil {
    
    private static final SessionFactory sessionFactory = buildSessionFactory();
    
    private static SessionFactory buildSessionFactory() {
        try {
            Properties properties = new Properties();
            
            String dbUrl = System.getenv().getOrDefault("DB_URL", "jdbc:mysql://localhost:3306/blog_db?serverTimezone=UTC");
            String dbUser = System.getenv().getOrDefault("DB_USER", "root");
            String dbPass = System.getenv().getOrDefault("DB_PASS", "");

            // Configuración de la Base de Datos
            properties.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
            properties.setProperty("hibernate.connection.url", dbUrl);
            properties.setProperty("hibernate.connection.username", dbUser);
            properties.setProperty("hibernate.connection.password", dbPass);
            
            // Configuración de Hibernate
            properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
            properties.setProperty("hibernate.show_sql", "true");
            properties.setProperty("hibernate.format_sql", "true");
            properties.setProperty("hibernate.hbm2ddl.auto", "update");
            
            return new Configuration()
                    .addProperties(properties)
                    .addAnnotatedClass(Autor.class)
                    .addAnnotatedClass(Post.class)
                    .addAnnotatedClass(Comentario.class)
                    .buildSessionFactory();
                    
        } catch (Throwable ex) {
            System.err.println("Error al crear SessionFactory: " + ex);
            ex.printStackTrace();
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    
    public static void shutdown() {
        getSessionFactory().close();
    }
}
