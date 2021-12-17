package com.example.pharmacy;

public class SellList {
    private String Name, Quantity, Discount;

    public SellList(String name, String quantity, String discount) {
        Name = name;
        Quantity = quantity;
        Discount = discount;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }

    public String getDiscount() {
        return Discount;
    }

    public void setDiscount(String discount) {
        Discount = discount;
    }
}
