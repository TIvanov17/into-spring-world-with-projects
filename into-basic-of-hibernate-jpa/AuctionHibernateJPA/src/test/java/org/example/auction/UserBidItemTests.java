package org.example.auction;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.auction.entities.*;
import org.example.auction.entities.enums.GenderEnum;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

public class UserBidItemTests {

    private static final String PERSISTENCE_UNIT_NAME = "auction";

    @Test
    public void makeABid() {

        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        User user = new User();
        user.setFirstName("Yonko");
        user.setLastName("Hristov");
        user.setUsername("yonko-hristov");

        user.setGender(GenderEnum.MALE);
        user.setAge(31);

        DigitalWallet digitalWallet = new DigitalWallet();
        digitalWallet.setBankName("MiBank");
        digitalWallet.setMoneyAmount(BigDecimal.valueOf(100));
        digitalWallet.setCreatedOn(LocalDate.now());
        digitalWallet.setUserOwner(user);

        user.setDigitalWallet(digitalWallet);

        Category booksCategory = new Category();
        booksCategory.setName("Books");
        booksCategory.setDescription("Here you can find all great book we offer.");

        Item item = new Item();
        item.setName("The Great Book");
        item.setCreatedOn(LocalDate.of(2020, 10, 10));
        item.setInitPrice(new BigDecimal(10));
        item.setCategory(booksCategory);
        item.setAuctionStart(LocalDate.now());
        item.setAuctionEnd(item.getAuctionStart().plusWeeks(1));

        Bid bid = new Bid();
        bid.setItem(item);
        bid.setUserBidder(user);
        bid.setBidCreatedOn(LocalDate.now());
        bid.setBidAmount(BigDecimal.valueOf(50));

        item.addBidForTheItem(bid);

        entityManager.persist(user);
        entityManager.persist(digitalWallet);
        entityManager.persist(booksCategory);
        entityManager.persist(item);
        entityManager.persist(bid);

        Long userId = user.getId();
        Long digitalWalletId = digitalWallet.getId();
        Long bidId = bid.getId();
        Long categoryId = booksCategory.getId();
        Long itemId = item.getId();
        entityManager.getTransaction().commit();

        entityManager.getTransaction().begin();
        Assert.assertEquals(entityManager.find(User.class, userId).getDigitalWallet().getId(), digitalWalletId);

        entityManager.getTransaction().commit();

        entityManager.close();
    }

}
