package com.tcdata.andrewrichardson.chef_ev_app.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;
import com.tcdata.andrewrichardson.chef_ev_app.OrderInformation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andrewrichardson on 10/29/17.
 */

public class Database extends SQLiteAssetHelper{


    private static final String DB_NAME = "OrderDetail.db";
    private static final int DB_VER = 1;


    public Database(Context context) {

        super(context, DB_NAME, null, DB_VER);

    }

    public List<OrderInformation> getCarts() {


        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect = {"ProductID", "ProductName", "Quantity", "Price"};
        String sqlTable = "OrderDetail";

        qb.setTables(sqlTable);
        Cursor c = qb.query(db, sqlSelect, null, null, null, null, null);
        final List<OrderInformation> result = new ArrayList<>();
        if (c.moveToFirst()) {

            do {
                result.add(new OrderInformation(c.getString(c.getColumnIndex("ProductID")),
                        c.getString(c.getColumnIndex("ProductName")),
                        c.getString(c.getColumnIndex("Quantity")),
                        c.getString(c.getColumnIndex("Price"))));
            } while (c.moveToNext());
        }

        return result;
    }

    public void addToCart(OrderInformation orderInformation){

        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("INSERT INTO OrderDetail(ProductID, ProductName, Quantity, Price) VALUES('%s','%s','%s','%s');",
                orderInformation.getProductID(),
                orderInformation.getProductName(),
                orderInformation.getQuantity(),
                orderInformation.getPrice()) ;


        db.execSQL(query);
    }

    public void clearCart(){

        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("DELETE FROM OrderDetail");


        db.execSQL(query);
    }
}


