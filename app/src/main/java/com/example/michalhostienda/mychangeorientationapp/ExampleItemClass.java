package com.example.michalhostienda.mychangeorientationapp;

import java.io.Serializable;

/**
 * Created by Michal Krysiak.
 */
public class ExampleItemClass implements Serializable {

    private int id;
    private String name;
    private double price;
    private String description;
    private boolean selected;

    public ExampleItemClass() {

    }

    public ExampleItemClass(int id, String name, double price, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.selected = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
