package com.beri.beriid.Model;

import android.graphics.Bitmap;

public class Donation {
    int  user_id, foundation_id, quantity;
    String name, description;
    Bitmap image;

    public Donation(int user_id, int foundation_id, int quantity, String name, String description, Bitmap image) {
        this.user_id = user_id;
        this.foundation_id = foundation_id;
        this.quantity = quantity;
        this.name = name;
        this.description = description;
        this.image = image;
    }

    public Donation() {
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getFoundation_id() {
        return foundation_id;
    }

    public void setFoundation_id(int foundation_id) {
        this.foundation_id = foundation_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }
}
