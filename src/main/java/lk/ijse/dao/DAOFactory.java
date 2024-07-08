package lk.ijse.dao;

import lk.ijse.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory(){}

    public static DAOFactory getDaoFactory(){
        return (daoFactory == null)? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOTypes{
        Customer,EMPLOYEE,INGREDIENT,PRODUCT,ORDER,ORDER_PRODUCT_DETAILS,PAYMENT, SUPPLIER,SUPPLIER_ORDER,QUERY,PRODUCT_EMPLOYEE,USER
    }
    public SuperDAO getDAO(DAOTypes daoTypes){
        switch (daoTypes){
            case Customer:
                return new CustomerDAOImpl();
            case EMPLOYEE:
                return new EmployeeDAOImpl();
            case INGREDIENT:
                return new IngredientDAOImpl();
            case PRODUCT:
                return new ProductDAOImpl();
            case ORDER:
                return new OrderDAOImpl();
            case ORDER_PRODUCT_DETAILS:
                return new OrderProductDetailsDAOImpl();
            case PAYMENT:
                return new PaymentDAOImpl();
            case SUPPLIER:
                return new SupplierDAOImpl();
            case SUPPLIER_ORDER:
                return new SupplierOrderDAOImpl();
            case QUERY:
                return new QueryDAOImpl();
            case PRODUCT_EMPLOYEE:
                return new ProductEmployeeDAOImpl();
            case USER:
                return new UserDAOImpl();
            default:
                return null;
        }
    }
}
