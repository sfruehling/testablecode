package com.example.demo1;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ShoppingCartTest {

    private final AShoppingCart shoppingCart = new AShoppingCart();


    @Test
    void hasZeroTotalItemsByDefault() {
        assertThat(shoppingCart.totalItems()).isEqualTo(0);
    }

    @Test
    void hasItemAfterAddingIt() {
        shoppingCart.addItem("anitem");
        assertThat(shoppingCart.totalItems()).isEqualTo(1);
    }

    @Test
    void hasTwoItemsAfterAddingThem() {
        shoppingCart.addItem("anitem1");
        shoppingCart.addItem("anitem2");
        assertThat(shoppingCart.totalItems()).isEqualTo(2);
    }

    @Test
    void removeItemOnEmptyListKeepsToBeEmpty() {
        shoppingCart.removeItem("removeItem");
        assertThat(shoppingCart.totalItems()).isEqualTo(0);
    }

    @Test
    void removeItemReducesNumberOfItems() {
        shoppingCart.addItem("anItem");
        assertThat(shoppingCart.totalItems()).isEqualTo(1);
        shoppingCart.removeItem("anItem");
        assertThat(shoppingCart.totalItems()).isEqualTo(0);
    }
}