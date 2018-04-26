package warehouseManagementSystem;

import java.util.Objects;

public class Product implements Comparable<Product>{

    private String name;
    private double unitPrice;
    private String quality;


    public Product(String name, double unitPrice, String quality) {
        this.name = name;
        this.unitPrice = unitPrice;
        this.quality = quality;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
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

    @Override
    public String toString() {
        return "product // name: "+name+" -- quality: "+quality+" -- unitprice: "+unitPrice;
    }
}