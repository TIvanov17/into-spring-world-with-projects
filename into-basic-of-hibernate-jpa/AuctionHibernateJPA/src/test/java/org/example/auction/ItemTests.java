package org.example.auction;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.auction.entities.Category;
import org.example.auction.entities.Item;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;


public class ItemTests {

    private static final String PERSISTENCE_UNIT_NAME = "auction";

    @Test
    public void insertItem(){

        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Category booksCategory = new Category();
        booksCategory.setName("Books");
        booksCategory.setDescription("Here you can find all great book we offer.");
        entityManager.persist(booksCategory);

        Item item = new Item();
        item.setName("The Great Book");
        item.setCreatedOn(LocalDate.of(2020, 10, 10));
        item.setInitPrice(new BigDecimal(10));
        item.setCategory(booksCategory);
        item.setAuctionStart(LocalDate.now());
        item.setAuctionEnd(item.getAuctionStart().plusWeeks(1));
        entityManager.persist(item);

        Long itemId = item.getId();

        entityManager.getTransaction().commit();

        entityManager.getTransaction().begin();
        Assert.assertEquals(entityManager.find(Item.class, itemId).getName(), "The Great Book");

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Test
    public void updateItem(){

        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Item item = new Item();
        item.setName("Book to Refresh Title");
        item.setInitPrice(new BigDecimal(10));

        entityManager.persist(item);

        Long itemId = item.getId();
        entityManager.getTransaction().commit();

        entityManager.getTransaction().begin();

        String newItemName = "Changed Book Title";
        item.setName(newItemName);
        entityManager.persist(item);

        Assert.assertEquals(newItemName, entityManager.find(Item.class, itemId).getName());

        entityManager.getTransaction().commit();
        entityManager.close();

/*        entityManager.createQuery("UPDATE Item i SET i.name = :newName WHERE i.id = :id")
                .setParameter("newName", newItemName)
                .setParameter("id", itemId)
                .executeUpdate();
        entityManager.getTransaction().commit();


        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Assert.assertEquals(newItemName, entityManager.find(Item.class, itemId).getName());

        entityManager.getTransaction().commit();
        entityManager.close();*/
    }

    @Test
    public void deleteItem(){
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Item item = new Item();
        item.setName("Book to Refresh Title");
        item.setInitPrice(new BigDecimal(10));

        entityManager.persist(item);
        entityManager.getTransaction().commit();

        entityManager.getTransaction().begin();
        entityManager.remove(item);

        Assert.assertFalse(entityManager.contains(item));

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
