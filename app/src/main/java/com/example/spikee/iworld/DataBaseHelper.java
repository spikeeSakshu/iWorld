package com.example.spikee.iworld;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {


    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "Product.db";

    // Product table name
    private static final String TABLE_PRODUCT = "product";

    // Product Table Columns names
    private static final String COLUMN_PRODUCT_ID = "product_id";
    private static final String COLUMN_PRODUCT_NAME = "product_name";
    private static final String COLUMN_PRODUCT_MODEL = "product_model";
    private static final String COLUMN_PRODUCT_PRICE= "product_price";
    private static final String COLUMN_PRODUCT_CATEGORY = "product_category";
    private static final String COLUMN_PRODUCT_DESC= "product_desc";


    //create table query
    private String CREATE_PRODUCT_TABLE = "CREATE TABLE " + TABLE_PRODUCT + "("
            + COLUMN_PRODUCT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_PRODUCT_NAME + " TEXT,"
            + COLUMN_PRODUCT_MODEL + " TEXT," + COLUMN_PRODUCT_PRICE + " TEXT," + COLUMN_PRODUCT_CATEGORY + " TEXT,"
            + COLUMN_PRODUCT_DESC + " TEXT" + ")";

//drop table query

    private String DROP_PRODUCT_TABLE = "DROP TABLE IF EXISTS " + TABLE_PRODUCT;

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_PRODUCT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(DROP_PRODUCT_TABLE);

        onCreate(db);
    }

    public void addProd(Product product)
    {
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues values=new ContentValues();
        values.put(COLUMN_PRODUCT_NAME,product.getName());
        values.put(COLUMN_PRODUCT_MODEL,product.getModel());
        values.put(COLUMN_PRODUCT_PRICE,product.getPrice());
        values.put(COLUMN_PRODUCT_CATEGORY,product.getCat());
        values.put(COLUMN_PRODUCT_DESC,product.getDesc());
//        values.put(COLUMN_PRODUCT_,product.getName());

        db.insert(TABLE_PRODUCT,null,values);
        db.close();

    }
    //Getting all users

    public List<Product> getAllProduct() {


        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {
                COLUMN_PRODUCT_ID,
                COLUMN_PRODUCT_NAME,
                COLUMN_PRODUCT_MODEL,
                COLUMN_PRODUCT_PRICE,
                COLUMN_PRODUCT_CATEGORY,
                COLUMN_PRODUCT_DESC
        };
        //sorting Order

        String sortOrder =
                COLUMN_PRODUCT_NAME + " ASC";
        List<Product> userList = new ArrayList<Product>();


        // query the user table


        Cursor cursor = db.query(TABLE_PRODUCT,
                columns,
                null,
                null,
                null,
                null,
                sortOrder);

        if (cursor.moveToFirst()) {
            do {
                Product user = new Product();
                user.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCT_ID))));
                user.setName(cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCT_NAME)));
                user.setModel(cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCT_MODEL)));
                user.setPrice(cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCT_PRICE)));
                user.setCat(cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCT_CATEGORY)));
                user.setDesc(cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCT_DESC)));

                userList.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return userList;
    }


    //getting product by category


    public List<Product> getProductByCategory(String category)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {
                COLUMN_PRODUCT_ID,
                COLUMN_PRODUCT_NAME,
                COLUMN_PRODUCT_MODEL,
                COLUMN_PRODUCT_PRICE,
                COLUMN_PRODUCT_CATEGORY,
                COLUMN_PRODUCT_DESC
        };
        //sorting Order

        String sortOrder =
                COLUMN_PRODUCT_NAME + " ASC";
        List<Product> userList = new ArrayList<Product>();


        // query the user table


        Cursor cursor = db.query(TABLE_PRODUCT,
                columns,
                "product_category = ?",
                new String[]{category},
                null,
                null,
                sortOrder);

        if (cursor.moveToFirst()) {
            do {
                Product user = new Product();
                user.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCT_ID))));
                user.setName(cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCT_NAME)));
                user.setModel(cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCT_MODEL)));
                user.setPrice(cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCT_PRICE)));
                user.setCat(cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCT_CATEGORY)));
                user.setDesc(cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCT_DESC)));

                userList.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return userList;
    }


    public void deleteProduct(Product product) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_PRODUCT, COLUMN_PRODUCT_ID + "=?",
                new String[]{String.valueOf(product.getId())});

        db.close();
    }
    public boolean checkProduct(String model) {

        String[] columns = {
                COLUMN_PRODUCT_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();

        //selecting upon SID
        String selection = COLUMN_PRODUCT_MODEL + " = ?";

        //argumnet

        String[] selectionArgs = {model};

        Cursor cursor = db.query(TABLE_PRODUCT, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0) {
            return true;
        }
        return false;
    }

}
