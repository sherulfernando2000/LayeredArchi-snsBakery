package lk.ijse.dao.custom.impl;

import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.ProductDAO;
import lk.ijse.entity.Product;
import lk.ijse.entity.OrderProductDetail;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImpl implements ProductDAO {
    @Override
    public  boolean save(Product product) throws SQLException {
        String sql = "INSERT INTO product VALUES(?, ?, ?, ?,?)";
        return SQLUtil.execute(sql,product.getId(),product.getName(),product.getCategory(),product.getQty(),product.getPrice());



    }
    @Override
    public  boolean update(Product product) throws SQLException {
        String sql = "UPDATE product SET pName = ?, category = ?, qtyInSale = ? ,price = ? WHERE productId = ?";
        return SQLUtil.execute(sql,product.getName(),product.getCategory(),product.getQty(),product.getPrice(),product.getId());


    }
    @Override
    public  boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM product WHERE ProductId = ?";
        return SQLUtil.execute(sql,id);

    }


    @Override
    public Product searchTel(String tel) throws SQLException, ClassNotFoundException {
        return null;
    }
    @Override
    public Product searchId(String id) throws SQLException {
        String sql = "SELECT * FROM product WHERE productId = ?";

        ResultSet resultSet = SQLUtil.execute(sql,id);

        if (resultSet.next()) {
            String product_id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String category = resultSet.getString(3);
            int qty = resultSet.getInt(4);
            int price = resultSet.getInt(5);

            Product product = new Product(product_id,name,category,qty,price);

            return product;
        }

        return null;

    }
    @Override
    public  List<Product> getAll() throws SQLException {
        String sql = "SELECT * FROM product";

        ResultSet resultSet = SQLUtil.execute(sql);

        List<Product> proList = new ArrayList<>();

        while (resultSet.next()) {
            String id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String category = resultSet.getString(3);
            int qty = Integer.parseInt(resultSet.getString(4));
            int price = Integer.parseInt(resultSet.getString(5));

            Product product = new Product(id, name,category,qty,price);
            proList.add(product);
        }
        return proList;


    }
    @Override
    public  List<String> getDescription() throws SQLException {
        String sql = "SELECT pName FROM product";
        ResultSet resultSet = SQLUtil.execute(sql);

        List<String> codeList = new ArrayList<>();
        while (resultSet.next()) {
            codeList.add(resultSet.getString(1));
        }
        return codeList;
    }
    @Override
    public  Product searchByDescription(String description) throws SQLException {
        String sql = "SELECT * FROM product WHERE pName = ?";
        ResultSet resultSet = SQLUtil.execute(sql,description);

        if (resultSet.next()) {
            return new Product(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getInt(4),resultSet.getInt(5));
        }

        return null;
    }
    @Override
    public  boolean update(List<OrderProductDetail> odList) throws SQLException {
        for (OrderProductDetail od : odList) {
            boolean isUpdateQty = updateQty(od.getProductId(), od.getQty());
            if(!isUpdateQty) {
                return false;
            }
        }
        return true;
    }
    @Override
    public  boolean updateQty(String productId, int qty) throws SQLException {
        String sql = "UPDATE product SET qtyInSale = qtyInSale - ? WHERE productId = ?";

        return SQLUtil.execute(sql,qty,productId);
    }
    @Override
    public  List<String> getName() throws SQLException {
        String sql = "SELECT pName FROM product";
        ResultSet resultSet = SQLUtil.execute(sql);
        List<String> nameList = new ArrayList<>();
        while (resultSet.next()) {
            nameList.add(resultSet.getString(1));
        }
        return nameList;
    }
    @Override
    public  String getName(String productId) throws SQLException {
        String sql = "SELECT pName FROM product WHERE productId = ?";
        ResultSet resultSet = SQLUtil.execute(sql,productId);

        if(resultSet.next()){
            String pName = resultSet.getString(1);

            return pName;
        }
        return null;

    }
    @Override
    public  Product searchByName(String nameValue) throws SQLException {
        String sql = "SELECT * FROM product WHERE pName = ?";
        ResultSet resultSet = SQLUtil.execute(sql,nameValue);

        if (resultSet.next()) {
            return new Product(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getInt(4),resultSet.getInt(5));
        }

        return null;
    }
}
