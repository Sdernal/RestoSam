package com.example.sdern_000.restosam;

import java.util.List;

/**
 * Created by Nikita Rykov on 02.12.2016.
 */

public class Restaurant {
    private String name;
    private Integer imageId;

    public Restaurant(String name, Integer imageId) {
        this.name = name;
        this.imageId = imageId;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getImageId() {
        return imageId;
    }

    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }

    @Override
    public String toString() {
        return name;
    }
}
