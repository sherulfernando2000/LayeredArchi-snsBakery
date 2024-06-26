package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.entity.Ingredient;

import java.sql.SQLException;
import java.util.List;

public interface IngredientDAO extends CrudDAO<Ingredient> {
    public List<String> getName() throws SQLException;
    public  Ingredient searchByName(String nameValue) throws SQLException;
    public  String getName(String ingredientId) throws SQLException;
}
