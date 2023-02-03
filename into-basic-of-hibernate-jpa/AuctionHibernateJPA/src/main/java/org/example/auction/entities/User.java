package org.example.auction.entities;

import jakarta.persistence.*;
import org.example.auction.entities.enums.GenderEnum;

import java.util.List;
import java.util.Set;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "users")
public class User implements Serializable {

    private Long id;
    private String username;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private int age;
    private GenderEnum gender;
    private String phoneNumber;
    private Address homeAddress;
    private DigitalWallet digitalWallet;

    private Set<Item> saleItemsCollection;
    private Set<Bid> bidsMadeCollection;
    private List<Item> boughtItemsCollection;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(unique = true, nullable = false)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(unique = true, nullable = false)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(length = 30, nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {

        this.password = password;
    }

    @Column(length = 20, nullable = false)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Column(length = 20,nullable = false)
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    public GenderEnum getGender() {
        return gender;
    }

    public void setGender(GenderEnum gender) {
        this.gender = gender;
    }

    @Column(unique = true)
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Embedded
    public Address getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(Address homeAddress) {
        this.homeAddress = homeAddress;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @OneToOne
    public DigitalWallet getDigitalWallet() {
        return digitalWallet;
    }

    public void setDigitalWallet(DigitalWallet digitalWallet) {
        this.digitalWallet = digitalWallet;
    }

    @OneToMany
    public Set<Item> getSaleItemsCollection() {
        return saleItemsCollection;
    }

    public void setSaleItemsCollection(Set<Item> saleItemsCollection) {
        this.saleItemsCollection = saleItemsCollection;
    }

    @OneToMany
    public Set<Bid> getBidsMadeCollection() {
        return bidsMadeCollection;
    }

    public void setBidsMadeCollection(Set<Bid> bidsMadeCollection) {
        this.bidsMadeCollection = bidsMadeCollection;
    }

    @OneToMany
    public List<Item> getBoughtItemsCollection() {
        return boughtItemsCollection;
    }

    public void setBoughtItemsCollection(List<Item> boughtItemsCollection) {
        this.boughtItemsCollection = boughtItemsCollection;
    }

    public BigDecimal calculateShippingCosts(Address fromLocation){
        return null;
    }

}
