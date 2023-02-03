package org.example.auction.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "bids")
public class Bid {

    private Long id;
    private Item item;
    private User userBidder;
    private BigDecimal bidAmount;
    private LocalDate bidCreatedOn;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    @ManyToOne
    public User getUserBidder() {
        return userBidder;
    }

    public void setUserBidder(User userBidder) {
        this.userBidder = userBidder;
    }

    @Column(nullable = false)
    public BigDecimal getBidAmount() {
        return bidAmount;
    }

    public void setBidAmount(BigDecimal bidAmount) {
        this.bidAmount = bidAmount;
    }

    @Column(nullable = false)
    public LocalDate getBidCreatedOn() {
        return bidCreatedOn;
    }

    public void setBidCreatedOn(LocalDate bidCreatedOn) {
        this.bidCreatedOn = bidCreatedOn;
    }

    @Transient
    public boolean isCurrentBidSetOnAnItem(){
        return this.item != null;
    }
}
