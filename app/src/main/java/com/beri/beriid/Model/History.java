package com.beri.beriid.Model;

import android.graphics.Bitmap;

public class History {
    int user_id, donation_id, foundation_id, quantity;
//    String foundation_name, foundation_description, foundation_address, donation_name, donation_description, donation_image;
    String foundation_name, foundation_description, foundation_address, donation_name, donation_description;
    Bitmap donation_image;

    public History(int user_id, int donation_id, int foundation_id, int quantity, String foundation_name, String foundation_description, String foundation_address, String donation_name, String donation_description, Bitmap donation_image) {
        this.user_id = user_id;
        this.donation_id = donation_id;
        this.foundation_id = foundation_id;
        this.quantity = quantity;
        this.foundation_name = foundation_name;
        this.foundation_description = foundation_description;
        this.foundation_address = foundation_address;
        this.donation_name = donation_name;
        this.donation_description = donation_description;
        this.donation_image = donation_image;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getDonation_id() {
        return donation_id;
    }

    public void setDonation_id(int donation_id) {
        this.donation_id = donation_id;
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

    public String getFoundation_name() {
        return foundation_name;
    }

    public void setFoundation_name(String foundation_name) {
        this.foundation_name = foundation_name;
    }

    public String getFoundation_description() {
        return foundation_description;
    }

    public void setFoundation_description(String foundation_description) {
        this.foundation_description = foundation_description;
    }

    public String getFoundation_address() {
        return foundation_address;
    }

    public void setFoundation_address(String foundation_address) {
        this.foundation_address = foundation_address;
    }

    public String getDonation_name() {
        return donation_name;
    }

    public void setDonation_name(String donation_name) {
        this.donation_name = donation_name;
    }

    public String getDonation_description() {
        return donation_description;
    }

    public void setDonation_description(String donation_description) {
        this.donation_description = donation_description;
    }

    public Bitmap getDonation_image() {
        return donation_image;
    }

    public void setDonation_image(Bitmap donation_image) {
        this.donation_image = donation_image;
    }
}