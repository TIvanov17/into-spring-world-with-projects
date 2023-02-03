package org.example;

import org.example.entity.Message;

import jakarta.persistence.*;

public class HelloWorldJPA {

    public static void main(String[] args){

        // This represents persistence unit "HelloPersistenceWorldPU"
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("HelloPersistenceWorldPU");

        // With EntityManager you enable all operations over the database
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        // Start a new transaction;
        entityManager.getTransaction().begin();

        Message message = new Message();
        message.setText("Hello Persistence World!");

        // This make the instance persistent;
        entityManager.persist(message);

        // Commit the transaction. Hibernate executes the necessary SQL INSERT statement.
        /// INSERT into MESSAGE (ID, TEXT) values (1, 'Hello Persistence World!')
        entityManager.getTransaction().commit();


        // After all the created EntityManager must be closed.
        entityManager.close();
    }
}
