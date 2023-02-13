package org.example.auction;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.auction.entities.User;
import org.example.auction.entities.enums.GenderEnum;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class UserTests {

    private static final String PERSISTENCE_UNIT_NAME = "auction";

    @Test
    public void listUsers(){

        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        User user = new User();
        user.setFirstName("Ivan");
        user.setLastName("Stoyanov");
        user.setGender(GenderEnum.MALE);
        user.setEmail("ms@abv.bg");
        user.setUsername("some");
        user.setPassword("312321");

        User user1 = new User();
        user1.setFirstName("Misho");
        user1.setLastName("Ivanov");
        user1.setGender(GenderEnum.MALE);
        user1.setEmail("mi@abv.bg");
        user1.setUsername("some1");
        user1.setPassword("3123123112321");

        User user2 = new User();
        user2.setFirstName("Ziba");
        user2.setLastName("Pencheva");
        user2.setGender(GenderEnum.FEMALE);
        user2.setEmail("mp@abv.bg");
        user2.setUsername("some2");
        user2.setPassword("sad321");

        entityManager.persist(user);
        entityManager.persist(user1);
        entityManager.persist(user2);

        List<User> usersCollection = entityManager
                .createQuery("FROM User u ORDER BY u.firstName DESC", User.class)
                .getResultList();

        Assert.assertEquals(3, usersCollection.size());

        usersCollection.forEach(System.out::println);

        entityManager.getTransaction().commit();
        entityManager.close();

    }
}
