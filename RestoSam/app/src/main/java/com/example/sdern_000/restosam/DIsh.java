package com.example.sdern_000.restosam;

/**
 * Created by Sdern_000 on 02.12.2016.
 */

public class Dish {
    String name;
    Integer price;
    Integer pictureId;
    public Dish(String name_, Integer price_, Integer pictureId_) {
        name = name_;
        price = price_;
        pictureId = pictureId_;
    }

    public String GetName() {
        return name;
    }

    public Integer GetPrice() {
        return price;
    }

    public Integer GetPictureId() {
        return pictureId;
    }
}
