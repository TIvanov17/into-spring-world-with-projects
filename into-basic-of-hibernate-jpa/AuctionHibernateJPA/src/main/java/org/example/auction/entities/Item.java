package org.example.auction.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import org.example.auction.entities.enums.ItemStatusEnum;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "items")
public class Item {

    private Long id;
    private String name;
    private Category category;
    private String description;
    private LocalDate createdOn;
    private BigDecimal initPrice;
    private LocalDate auctionStart;
    private LocalDate auctionEnd;
    private ItemStatusEnum status;

    private Set<Bid> itemBidsMadeCollection;

    public Item(){
        this.itemBidsMadeCollection = new HashSet<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId(){
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "item_name", length = 30, nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToOne
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDate createdOn) {
        this.createdOn = createdOn;
    }

    @Column(name = "start_price", nullable = false)
    public BigDecimal getInitPrice() {
        return initPrice;
    }

    public void setInitPrice(BigDecimal initPrice) {
        this.initPrice = initPrice;
    }

    public LocalDate getAuctionStart() {
        return auctionStart;
    }

    public void setAuctionStart(LocalDate auctionStart) {
        this.auctionStart = auctionStart;
    }

    @Future
    public LocalDate getAuctionEnd() {
        return auctionEnd;
    }

    public void setAuctionEnd(LocalDate auctionEnd) {
        this.auctionEnd = auctionEnd;
    }

    @Transient
    public Set<Bid> getItemBidsMadeCollection() {
        return itemBidsMadeCollection;
    }

    public void setItemBidsMadeCollection(Set<Bid> itemBidsMadeCollection) {
        this.itemBidsMadeCollection = itemBidsMadeCollection;
    }

    @Enumerated(EnumType.STRING)
    public ItemStatusEnum getStatus() {
        return status;
    }

    public void setStatus(ItemStatusEnum status) {
        this.status = status;
    }

    public void removeBidForTheItem(Bid currentBid){
        if(currentBid == null){
            throw new NullPointerException("Can't remm bid, that is null! Enter a valid bid." );
        }
        if(!currentBid.getItem().equals(this)){
            throw new IllegalStateException("Can't remove that bid, because it's not made for this item.");
        }
        if(!this.itemBidsMadeCollection.contains(currentBid)){
            throw new IllegalStateException("Can't remove that bid, " +
                    "because it's already removed or can't be found.");
        }

        this.itemBidsMadeCollection.remove(currentBid);
        currentBid.setItem(null);
    }

    public void addBidForTheItem(Bid currentBid){
        if(currentBid == null){
            throw new NullPointerException("Can't add bid, that is null! Enter a valid bid." );
        }

        this.itemBidsMadeCollection.add(currentBid);
        currentBid.setItem(this);
    }
}
