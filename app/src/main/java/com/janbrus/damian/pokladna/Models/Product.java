package com.janbrus.damian.pokladna.Models;

import java.math.BigDecimal;

/**
 * Created by Honza on 20.07.2017.
 */
public class Product {
    private String name;
    private int id;
    private Double price;
    private int count;

    public Product() {
        id = -1;
        name = "-unknown-";
        price = 0.0;
        count = 0;
    }
    public Product(int id, String name, Double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }
    public Double getPrice() {
        return price;
    }
    public void increaseCount() {
        count++;
    }
    public int getCount() {
        return count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (id != product.id) return false;
        return name != null ? name.equals(product.name) : product.name == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + id;
        return result;
    }
}
