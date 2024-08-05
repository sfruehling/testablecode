package com.example.demo1;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private final List<String> items;

    public ShoppingCart() {
        this.items = new ArrayList<>();
    }

    public void addItem(String item) {
        items.add(item);
    }

    public void removeItem(String item) {
        items.remove(item);
    }

    public int totalItems() {
        return items.size();
    }
}
