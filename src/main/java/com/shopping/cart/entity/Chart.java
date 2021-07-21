package com.shopping.cart.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Chart {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    @ElementCollection
    private List<ProductInChart> products;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<ProductInChart> getProducts() {
        return products;
    }

    public void setProducts(List<ProductInChart> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Chart{" +
                "id=" + id +
                ", products=" + products +
                '}';
    }
}
