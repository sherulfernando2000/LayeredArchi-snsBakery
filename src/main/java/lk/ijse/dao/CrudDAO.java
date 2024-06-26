package lk.ijse.dao;

import java.sql.SQLException;
import java.util.List;

public interface CrudDAO<T> extends SuperDAO {
    public  boolean save(T entity) throws SQLException, ClassNotFoundException ;

    public  boolean update(T entity) throws SQLException, ClassNotFoundException ;


    public  boolean delete(String id) throws SQLException, ClassNotFoundException ;

    public  T searchId(String id) throws SQLException,ClassNotFoundException ;

    public  T searchTel(String tel) throws SQLException,ClassNotFoundException ;

    public List<T> getAll() throws SQLException,ClassNotFoundException ;

}
