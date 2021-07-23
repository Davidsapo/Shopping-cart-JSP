package com.shopping.cart.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Embeddable
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    @ElementCollection
    private List<ProductInCart> products;
    private long personId;
    private double totalPrice;

    public void addProduct(ProductInCart product) {
        if (products == null) {
            products = new ArrayList<>();
        }
        products.add(product);
        countTotal();
    }

    public void removeProduct(ProductInCart product) {
        if (products != null) {
            products.remove(product);
            countTotal();
        }
    }

    private void countTotal() {
        totalPrice = 0;
        for (ProductInCart productInChart : products) {
            totalPrice += productInChart.getProduct().getPrice() * productInChart.getQuantity();
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<ProductInCart> getProducts() {
        return products;
    }

    public void setProducts(List<ProductInCart> products) {
        this.products = products;
    }

    public long getPersonId() {
        return personId;
    }

    public void setPersonId(long personId) {
        this.personId = personId;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", products=" + products +
                ", personId=" + personId +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
