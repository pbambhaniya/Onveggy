package com.multipz.onveggy.Model;

/**
 * Created by Admin on 25-08-2017.
 */

public class VegModel {
    private String image;
    private String name;
    private String quantity;
    private String amount;
    private String delivery_time;
    private int cartItem = 0;

    public VegModel(String image, String name, String quantity, String amount, String delivery_time) {
        this.image=image;
        this.name=name;
        this.quantity=quantity;
        this.amount=amount;
        this.delivery_time=delivery_time;
    }

    public VegModel() {
    }


    public int getCartItem() {
        return cartItem;
    }

    public void setCartItem(int cartItem) {
        this.cartItem = cartItem;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDelivery_time() {
        return delivery_time;
    }

    public void setDelivery_time(String delivery_time) {
        this.delivery_time = delivery_time;
    }
}
