package com.shopping.cart.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    @ElementCollection
    private List<ProductInChart> products;
    @ManyToOne
    private Person person;
    private double totalPrice;

    public void addProduct(ProductInChart product) {
        if (products == null) {
            products = new ArrayList<>();
        }
        products.add(product);
        countTotal();
    }

    public void removeProduct(Product product) {
        if (products != null) {
            products.remove(product);
            countTotal();
        }
    }

    private void countTotal() {
        totalPrice = 0;
        for (ProductInChart productInChart : products) {
            totalPrice += productInChart.getProduct().getPrice() * productInChart.getQuantity();
        }
    }

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

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "Chart{" +
                "id=" + id +
                ", products=" + products +
                ", person=" + person +
                '}';
    }
}
