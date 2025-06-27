package org.yearup.data;

import org.yearup.models.Product;
import org.yearup.models.ShoppingCart;
import org.yearup.models.ShoppingCartItem;

public interface ShoppingCartDao
{
    ShoppingCart getByUserId(int userId);
    // add additional method signatures here
    void addToCart(int userId, int productId);
    ShoppingCart deleteFromCart(int userId);
    void addQuantity(int userId, int productId, int quantity);
}
