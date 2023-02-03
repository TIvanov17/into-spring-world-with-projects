package org.example.auction.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categories")
public class Category {

    private Long id;
    private String name;
    private String description;
    private List<Item> itemsCollection;

    public Category(){
        this.itemsCollection = new ArrayList<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(unique = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @OneToMany
    public List<Item> getItemsCollection() {
        return itemsCollection;
    }

    public void setItemsCollection(List<Item> itemsCollection) {
        this.itemsCollection = itemsCollection;
    }
}
