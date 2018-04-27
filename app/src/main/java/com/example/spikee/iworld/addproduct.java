package com.example.spikee.iworld;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.Toast;

import com.example.spikee.iworld.DataBaseHelper;


public class addproduct extends AppCompatActivity implements View.OnClickListener{
    private final AppCompatActivity activity=addproduct.this;

    private TextInputLayout nameTl;
    private TextInputLayout modelTl;
    private TextInputLayout priceTl;
    private TextInputLayout categoryTl;
    private TextInputLayout descTl;

    private TextInputEditText nameEt;
    private TextInputEditText modelEt;
    private TextInputEditText priceEt;
    private TextInputEditText categoryEt;
    private TextInputEditText descEt;

    private AppCompatButton appCompatButtonRegister;
    private AppCompatButton appCompatButtonView;

    private Validate validate;
    private DataBaseHelper dataBaseHelper;
    private Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addproduct);

        initViews();
        initListeners();
        initObjects();
    }
    private void initViews() {
        nameTl = (TextInputLayout) findViewById(R.id.nameTl);
        modelTl = (TextInputLayout) findViewById(R.id.modelTl);
        priceTl = (TextInputLayout) findViewById(R.id.priceTl);
        categoryTl = (TextInputLayout) findViewById(R.id.categoryTl);
        descTl = (TextInputLayout) findViewById(R.id.descTl);

        nameEt = (TextInputEditText) findViewById(R.id.nameEt);
        modelEt = (TextInputEditText) findViewById(R.id.modelEt);
        priceEt = (TextInputEditText) findViewById(R.id.priceEt);
        categoryEt = (TextInputEditText) findViewById(R.id.categoryEt);
        descEt = (TextInputEditText) findViewById(R.id.descEt);

        appCompatButtonRegister = (AppCompatButton) findViewById(R.id.add);
        appCompatButtonView = (AppCompatButton) findViewById(R.id.view);
    }
    private void initObjects() {
        dataBaseHelper = new DataBaseHelper(activity);
        validate = new Validate(activity);
       product =new Product();
    }
    private void initListeners() {
        appCompatButtonRegister.setOnClickListener(this);
        appCompatButtonView.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.add:
                postDataToSQLite();
                break;

            case R.id.view:
                Intent in = new Intent(this, product_list.class);
                in.putExtra("hello","everything");
                startActivity(in);
                break;
        }
    }
    private void postDataToSQLite()
    {
        if (!validate.isInputEditTextFilled(nameEt, nameTl, "Enter Full Name")) {
            return;
        }
        if (!validate.isInputEditTextFilled(modelEt, modelTl, "Enter correct Model No.")) {
            return;
        }
        if (!validate.isInputEditTextFilled(priceEt, priceTl, "Enter Correct SiD")) {
            return;
        }
        if (!validate.isInputEditTextFilled(categoryEt, categoryTl, "Enter Correct SiD")) {
            return;
        }
        if (!validate.isInputEditTextFilled(descEt, descTl, "Enter Correct SiD")) {
            return;
        }
               if (!dataBaseHelper.checkProduct(modelEt.getText().toString().trim())) {

            product.setName(nameEt.getText().toString().trim());
            product.setModel(modelEt.getText().toString().trim());
            product.setPrice(priceEt.getText().toString().trim());
            product.setCat(categoryEt.getText().toString().trim());
            product.setDesc(descEt.getText().toString().trim());

            dataBaseHelper.addProd(product);

            // record saved successfully
            Toast.makeText(this,"Product Succesfully Added",Toast.LENGTH_SHORT).show();
            emptyInputEditText();


        } else {
            // record already exists
            Toast.makeText(this,"Product Already Exists",Toast.LENGTH_SHORT).show();
        }


    }
    //Empty EditText
    private void emptyInputEditText() {
        nameEt.setText(null);
        modelEt.setText(null);
        priceEt.setText(null);
        categoryEt.setText(null);
        descEt.setText(null);
       // contactEt.setText(null);
    }

}
