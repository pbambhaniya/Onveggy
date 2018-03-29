package com.multipz.onveggy.Model;

/**
 * Created by Admin on 31-08-2017.
 */

public class VegpriceModel {
    String name,price;
    boolean is_check = false;

    public VegpriceModel(String name, String price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public boolean is_check() {
        return is_check;
    }

    public void setIs_check(boolean is_check) {
        this.is_check = is_check;
    }
}
