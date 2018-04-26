package main.java.model.warehouse;

import java.util.Objects;

public class Product implements Comparable<Product>{

    private String name;
    private Double unitPrice;
    private String quality;

    public Product(String name, Double unitPrice, String quality) {
        this.name = name;
        this.unitPrice = unitPrice;
        this.quality = quality;
    }

    public Product(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    @Override
    public int compareTo(Product o) {
        Objects.requireNonNull(o, "Can't Be Null");

        return name.compareTo(o.name);
    }


}