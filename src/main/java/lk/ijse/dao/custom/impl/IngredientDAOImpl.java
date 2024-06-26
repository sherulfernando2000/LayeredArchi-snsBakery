package lk.ijse.dao.custom.impl;

import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.IngredientDAO;
import lk.ijse.db.DbConnection;
import lk.ijse.entity.Ingredient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IngredientDAOImpl implements IngredientDAO {
    @Override
    public  boolean save(Ingredient ingredient) throws SQLException {
        String sql = "INSERT INTO ingredient VALUES(?, ?, ?)";
        return SQLUtil.execute(sql,ingredient.getId(),ingredient.getName(),ingredient.getCategory());
    }
    @Override
    public  boolean update(Ingredient ingredient) throws SQLException {
        String sql = "UPDATE ingredient SET iName = ?,  category = ? WHERE  ingredientId = ?";
        return SQLUtil.execute(sql,ingredient.getName(),ingredient.getCategory(),ingredient.getId());
    }
    @Override
    public  boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM ingredient WHERE  ingredientId = ?";
        return SQLUtil.execute(sql, id);
    }

    @Override
    public Ingredient searchTel(String tel) throws SQLException, ClassNotFoundException {
        return null;
    }
    @Override
    public  Ingredient searchId(String id) throws SQLException {
        String sql = "SELECT * FROM ingredient WHERE  ingredientId  = ?";
        ResultSet resultSet = SQLUtil.execute(sql,id);
        if (resultSet.next()) {
            String ingre_id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String category = resultSet.getString(3);


            Ingredient ingredient = new Ingredient(ingre_id,name,category);

            return ingredient;
        }

        return null;
    }
    @Override
    public  List<Ingredient> getAll() throws SQLException {
        String sql = "SELECT * FROM ingredient";
        ResultSet resultSet = SQLUtil.execute(sql);

        List<Ingredient> ingredList = new ArrayList<>();

        while (resultSet.next()) {
            String id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String category = resultSet.getString(3);


            Ingredient ingredient = new Ingredient(id,name,category);
            ingredList.add(ingredient);
        }
        return ingredList;
    }
    @Override
    public  List<String> getName() throws SQLException {
        String sql = "SELECT iName FROM ingredient";
        ResultSet resultSet = SQLUtil.execute(sql);

        List<String> nameList = new ArrayList<>();
        while (resultSet.next()) {
            nameList.add(resultSet.getString(1));
        }
        return nameList;
    }
    @Override
    public  Ingredient searchByName(String nameValue) throws SQLException {
        String sql = "SELECT * FROM ingredient WHERE iName = ?";
        ResultSet resultSet = SQLUtil.execute(sql,nameValue);

        if (resultSet.next()) {
            return new Ingredient(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3));
        }

        return null;
    }
    @Override
    public  String getName(String ingredientId) throws SQLException {
        String sql = "SELECT iName FROM ingredient WHERE ingredientId = ?";
        ResultSet resultSet = SQLUtil.execute(sql,ingredientId);

        if(resultSet.next()){
            String iName = resultSet.getString(1);

            return iName;
        }
        return null;

    }
}
