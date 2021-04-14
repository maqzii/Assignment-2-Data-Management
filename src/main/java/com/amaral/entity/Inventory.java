package com.amaral.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data // Reduces boilerplate code for model/data objects, also generates getter & setter methods
@AllArgsConstructor // Creates a Constructor with a parameter for each field in the class
@NoArgsConstructor // Creates a Default Constructor
@Entity // Defines that this class can be mapped to a table
@NamedQuery(name = "Inventory.findAll", query = "SELECT i FROM Inventory i")
@NamedQuery(name = "Inventory.getByName", query = "SELECT i FROM Inventory i WHERE i.name = :name")
@NamedQuery(name = "Inventory.clearAll", query = "DELETE FROM Inventory")

// Serializable Implementation converts objects to bytes
public class Inventory implements Comparable<Inventory>, Serializable {

    @Id // Indicates id as the Primary Key of the current entity
    @GeneratedValue // Auto Increments the id column
    private Long id;

    @Column(unique = true) // Same name cannot exist more than one
    private String name;

    private String sport;
    private int quantity;
    private double price;
    private Date dateUpdated;


    @PrePersist // EntityListener() Set generated fields
    void createdAt() {
        this.dateUpdated = new Date();
    }

    @PreUpdate // EntityListener() Set generated fields
    void updatedAt() {
        this.dateUpdated = new Date();
    }

    @Override
    public int compareTo(Inventory i) {
        return dateUpdated.compareTo(i.dateUpdated);
    }
} // END OF class