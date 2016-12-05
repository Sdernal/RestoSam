package com.example.sdern_000.restosam.Restaurant;

import com.example.sdern_000.restosam.Menu.Menu;

import java.util.List;
import java.util.Map;

/**
 * Created by Nikita Rykov on 02.12.2016.
 */

public class Restaurant {
    private String name;
    private Integer imageId;
    private Menu menu;

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

    public void setMenu(Menu menu) { this.menu = menu;}

    public Menu getMenu() {
        return menu;
    }

    @Override
    public String toString() {
        return name;
    }
}
