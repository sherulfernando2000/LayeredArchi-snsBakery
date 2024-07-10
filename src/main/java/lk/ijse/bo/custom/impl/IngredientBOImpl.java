package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.IngredientBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.IngredientDAO;
import lk.ijse.dto.IngredientDTO;
import lk.ijse.entity.Ingredient;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IngredientBOImpl implements IngredientBO {
    IngredientDAO ingredientDAO = (IngredientDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.INGREDIENT);
    @Override
    public  boolean saveIngredient(IngredientDTO ingredient) throws SQLException, ClassNotFoundException {
       return ingredientDAO.save(new Ingredient(ingredient.getId(),ingredient.getName(),ingredient.getCategory()));
    }
    @Override
    public  boolean updateIngredient(IngredientDTO ingredient) throws SQLException, ClassNotFoundException {
       return ingredientDAO.update(new Ingredient(ingredient.getId(),ingredient.getName(),ingredient.getCategory()));
    }
    @Override
    public  boolean deleteIngredient(String id) throws SQLException, ClassNotFoundException {
        return ingredientDAO.delete(id);
    }

    @Override
    public IngredientDTO searchTel(String tel) throws SQLException, ClassNotFoundException {
        return null;
    }
    @Override
    public  IngredientDTO searchIngredientId(String id) throws SQLException, ClassNotFoundException {
       Ingredient ingredient = ingredientDAO.searchId(id);
       return new IngredientDTO(ingredient.getId(),ingredient.getName(),ingredient.getCategory());
    }
    @Override
    public List<IngredientDTO> getAllIngredient() throws SQLException, ClassNotFoundException {
        List<Ingredient> ingredients = ingredientDAO.getAll();

        List<IngredientDTO> ingredList = new ArrayList<>();

        for (Ingredient ingredient:ingredients) {
            ingredList.add(new IngredientDTO(ingredient.getId(),ingredient.getName(),ingredient.getCategory()));
        }
        return ingredList;
    }
    @Override
    public  List<String> getIngredientName() throws SQLException {
        List<String> ingredients = ingredientDAO.getName();

        List<String> nameList = new ArrayList<>();
        for (String ingredient:ingredients) {
            nameList.add(ingredient);
        }
        return nameList;
    }
    @Override
    public  IngredientDTO searchByIngredientName(String nameValue) throws SQLException {
        Ingredient ingredient = ingredientDAO.searchByName(nameValue);
        return new IngredientDTO(ingredient.getId(),ingredient.getName(),ingredient.getCategory());
    }
    @Override
    public  String getIngredientName(String ingredientId) throws SQLException {
       return ingredientDAO.getName(ingredientId);

    }
}
