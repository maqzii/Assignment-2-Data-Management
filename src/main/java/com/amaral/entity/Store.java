package com.amaral.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Store implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String name;
    private String location;

    @OneToMany // Specifies OneToMany relationship
    @JoinColumn // Marks column as a join column for entity association
    private List<Inventory> inventoryList;
} // END OF class
