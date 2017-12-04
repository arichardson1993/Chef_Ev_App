package com.tcdata.andrewrichardson.chef_ev_app;

import android.content.ClipData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andrewrichardson on 10/23/17.
 */

public class OrderInformation {

    private String ProductID;
    private String ProductName;
    private String Quantity;
    private String Price;

    public OrderInformation(){

    }

    public OrderInformation(String productID, String productName, String quantity, String price) {
        ProductID = productID;
        ProductName = productName;
        Quantity = quantity;
        Price = price;
    }

    public String getProductID() {
        return ProductID;
    }

    public void setProductID(String productID) {
        ProductID = productID;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }
}