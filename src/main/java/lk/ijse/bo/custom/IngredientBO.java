package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.IngredientDTO;

import java.sql.SQLException;
import java.util.List;

public interface IngredientBO extends SuperBO {

    public  boolean saveIngredient(IngredientDTO ingredient) throws SQLException, ClassNotFoundException;

    public  boolean updateIngredient(IngredientDTO ingredient) throws SQLException, ClassNotFoundException;

    public  boolean deleteIngredient(String id) throws SQLException, ClassNotFoundException;


    public IngredientDTO searchTel(String tel) throws SQLException, ClassNotFoundException;

    public  IngredientDTO searchIngredientId(String id) throws SQLException, ClassNotFoundException;

    public List<IngredientDTO> getAllIngredient() throws SQLException, ClassNotFoundException;

    public  List<String> getIngredientName() throws SQLException;

    public  IngredientDTO searchByIngredientName(String nameValue) throws SQLException;

    public  String getIngredientName(String ingredientId) throws SQLException;
}
