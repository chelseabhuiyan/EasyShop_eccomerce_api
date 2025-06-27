package org.yearup.data.mysql;

import org.springframework.stereotype.Component;
import org.yearup.data.ShoppingCartDao;
import org.yearup.models.Product;
import org.yearup.models.ShoppingCart;
import org.yearup.models.ShoppingCartItem;
import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class MySqlShoppingCartDao extends MySqlDaoBase implements ShoppingCartDao {

    public MySqlShoppingCartDao(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public ShoppingCart getByUserId(int userId) {

        ShoppingCart shoppingCart = new ShoppingCart();

        String sql = """
                SELECT p.*, sc.* FROM shopping_cart sc
                JOIN products p ON sc.product_id = p.product_id
                WHERE sc.user_id = ?
                """;

        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql))
        {
            preparedStatement.setInt(1,userId);

            try(ResultSet results = preparedStatement.executeQuery()){

                while(results.next()){
                    Product product = mapRow(results);
                    ShoppingCartItem shoppingCartItem = new ShoppingCartItem();
                    shoppingCartItem.setProduct(product);
                    shoppingCartItem.setQuantity(results.getInt("quantity"));
                    shoppingCart.add(shoppingCartItem);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return shoppingCart;
    }

    @Override
    public void addToCart(int userId, int productId) {

        String sql = """
                INSERT INTO shopping_cart (user_id, product_id)
                VALUES (?, ?)
                ON DUPLICATE KEY UPDATE quantity = quantity + 1;
                """;

        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ){

            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, productId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ShoppingCart deleteFromCart(int userId) {

        ShoppingCart shoppingCart = new ShoppingCart();

        String sql = """
                DELETE FROM shopping_cart
                WHERE user_id = ?
                """;

        try(Connection connection = getConnection()){

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,userId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return shoppingCart;
    }

    @Override
    public void addQuantity(int userId, int productId, int quantity) {

        String sql = """
                INSERT INTO shopping_cart (user_id, product_id, quantity)
                VALUES (?, ?, ?)
                ON DUPLICATE KEY UPDATE quantity = quantity + ?;
                """;

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql))
        {
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, productId);
            preparedStatement.setInt(3, quantity); //for the first time product added
            preparedStatement.setInt(4, quantity); //for duplicates

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    protected static Product mapRow(ResultSet row) throws SQLException
    {
        int productId = row.getInt("product_id");
        String name = row.getString("name");
        BigDecimal price = row.getBigDecimal("price");
        int categoryId = row.getInt("category_id");
        String description = row.getString("description");
        String color = row.getString("color");
        int stock = row.getInt("stock");
        boolean isFeatured = row.getBoolean("featured");
        String imageUrl = row.getString("image_url");

        return new Product(productId, name, price, categoryId, description, color, stock, isFeatured, imageUrl);
    }
}
