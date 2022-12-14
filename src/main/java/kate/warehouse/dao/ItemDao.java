package kate.warehouse.dao;

import kate.warehouse.model.Item;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemDao {
    private final Connection connection;

    public ItemDao(Connection connection) {
        this.connection = connection;
    }

    public List<Item> getAllItemsFromDB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");      //load class
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/warehouse","root","1234");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from items");

            List<Item> list = new ArrayList<>();
            while (resultSet.next()) {
                list.add(makeItemFromRS(resultSet));
            }
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Item makeItemFromRS(ResultSet rs) throws SQLException {
        long id = rs.getLong("id");
        String name = rs.getString("name");
        BigDecimal quantity = rs.getBigDecimal("quantity");

        return new Item(id, name, quantity);
    }




}
