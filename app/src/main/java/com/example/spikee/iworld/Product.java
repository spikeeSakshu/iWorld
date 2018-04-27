package com.example.spikee.iworld;

public class Product {

    private int id;
    private String name;
    private String model;
    private String price;
    private String cat;
    private String desc;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getModel() {
        return model;
    }
    public String getPrice() {
        return price;
    }
    public String getCat() {
        return cat;
    }

    public String getDesc() {
        return desc;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}

