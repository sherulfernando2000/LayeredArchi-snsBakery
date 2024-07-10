package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.ProductDTO;
import lk.ijse.entity.OrderProductDetail;

import java.sql.SQLException;
import java.util.List;

public interface ProductBO extends SuperBO {
    public  boolean saveProduct(ProductDTO product) throws SQLException, ClassNotFoundException;

    public  boolean updateProduct(ProductDTO product) throws SQLException, ClassNotFoundException;

    public  boolean deleteProduct(String id) throws SQLException, ClassNotFoundException;



    public ProductDTO searchTel(String tel) throws SQLException, ClassNotFoundException;

    public ProductDTO searchProductId(String id) throws SQLException, ClassNotFoundException;

    public List<ProductDTO> getAllProduct() throws SQLException, ClassNotFoundException;

    public  List<String> getProductDescription() throws SQLException;

    public  ProductDTO searchProductByDescription(String description) throws SQLException;

    public  boolean updateProduct(List<OrderProductDetail> odList) throws SQLException;

    public  boolean updateProductQty(String productId, int qty) throws SQLException;

    public  List<String> getProductName() throws SQLException ;

    public  String getProductName(String productId) throws SQLException;

    public  ProductDTO searchProductByName(String nameValue) throws SQLException;
    public  boolean saveWaste(List<ProductDTO> dtos) throws SQLException;

    public int getLastId() throws SQLException;
}
