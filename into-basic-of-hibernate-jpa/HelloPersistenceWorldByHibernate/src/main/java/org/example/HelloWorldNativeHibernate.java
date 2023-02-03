package org.example;

import org.example.entity.Message;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataBuilder;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

public class HelloWorldNativeHibernate {

    public static void main(String[] args) {

        StandardServiceRegistryBuilder serviceRegistryBuilder =
                new StandardServiceRegistryBuilder();

        // Configure the services registry by applying settings.
        serviceRegistryBuilder
                .applySetting("hibernate.dialect", "org.hibernate.dialect.MySQLDialect")
                .applySetting("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver")
                .applySetting("hibernate.connection.url",
                        "jdbc:mysql://localhost:3306/message_datastore?createDatabaseIfNotExist=true")
                .applySetting("hibernate.connection.username", "root")
                .applySetting("hibernate.connection.password", "")
                .applySetting("hibernate.transform_hbm_xml.enabled", "true")
                .applySetting("hibernate.format_sql", "true")
                .applySetting("hibernate.use_sql_comments", "true")
                .applySetting("hibernate.hbm2ddl.auto", "update");

        ServiceRegistry serviceRegistry = serviceRegistryBuilder.build();

        // Building all the metadata needed by Hibernate
        MetadataSources metadataSources = new MetadataSources(serviceRegistry);
        metadataSources.addAnnotatedClass(
                org.example.entity.Message.class
        );

        MetadataBuilder metadataBuilder = metadataSources.getMetadataBuilder();
        Metadata metadata = metadataBuilder.build();

        SessionFactory sessionFactory = metadata.buildSessionFactory();

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Message message = new Message();
        message.setText("Hello Persistence World From Native Hibernate");
        session.persist(message);

        session.getTransaction().commit();
        session.close();

    }
}
