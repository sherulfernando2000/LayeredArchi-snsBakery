package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.ProductBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.ProductDAO;
import lk.ijse.dto.ProductDTO;
import lk.ijse.entity.Product;
import lk.ijse.entity.OrderProductDetail;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductBOImpl implements ProductBO {
    ProductDAO productDAO = (ProductDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PRODUCT);
    @Override
    public  boolean saveProduct(ProductDTO product) throws SQLException, ClassNotFoundException {
        return productDAO.save(new Product(product.getId(), product.getName(),product.getCategory(),product.getQty(),product.getPrice()));

    }
    @Override
    public  boolean updateProduct(ProductDTO product) throws SQLException, ClassNotFoundException {
        return productDAO.update(new Product(product.getId(), product.getName(),product.getCategory(),product.getQty(),product.getPrice()));

    }
    @Override
    public  boolean deleteProduct(String id) throws SQLException, ClassNotFoundException {
        return productDAO.delete(id);

    }


    @Override
    public ProductDTO searchTel(String tel) throws SQLException, ClassNotFoundException {
        return null;
    }
    @Override
    public ProductDTO searchProductId(String id) throws SQLException, ClassNotFoundException {
       Product product = productDAO.searchId(id);
       return new ProductDTO(product.getId(), product.getName(),product.getCategory(),product.getQty(),product.getPrice());


    }
    @Override
    public  List<ProductDTO> getAllProduct() throws SQLException, ClassNotFoundException {
        List<Product> products = productDAO.getAll();
        List<ProductDTO> proList = new ArrayList<>();

        for (Product product:products) {
            proList.add(new ProductDTO(product.getId(), product.getName(),product.getCategory(),product.getQty(),product.getPrice()));
        }
        return proList;
    }
    @Override
    public  List<String> getProductDescription() throws SQLException {
        return productDAO.getDescription();

    }
    @Override
    public  ProductDTO searchProductByDescription(String description) throws SQLException {
       Product product = productDAO.searchByDescription(description);
       return new ProductDTO(product.getId(), product.getName(),product.getCategory(),product.getQty(),product.getPrice());
    }
    @Override
    public  boolean updateProduct(List<OrderProductDetail> odList) throws SQLException {
        for (OrderProductDetail od : odList) {
            boolean isUpdateQty = updateProductQty(od.getProductId(), od.getQty());
            if(!isUpdateQty) {
                return false;
            }
        }
        return true;
    }
    @Override
    public  boolean updateProductQty(String productId, int qty) throws SQLException {
        return productDAO.updateQty(productId,qty);
    }
    @Override
    public  List<String> getProductName() throws SQLException {
        return productDAO.getName();

    }
    @Override
    public  String getProductName(String productId) throws SQLException {
       return productDAO.getName(productId);

    }
    @Override
    public  ProductDTO searchProductByName(String nameValue) throws SQLException {
        Product product = productDAO.searchByName(nameValue);
        return new ProductDTO(product.getId(), product.getName(),product.getCategory(),product.getQty(),product.getPrice());

    }
}
