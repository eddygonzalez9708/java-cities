package com.lambdaschool.javacities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class City {
    private @Id @GeneratedValue Long id;
    private String name;
    private int price;
    private int affordability;

    public City() {
        // Default Constructor
    }

    public City(String name, int price, int affordability) {
        this.name = name;
        this.price = price;
        this.affordability = affordability;
    }
}
