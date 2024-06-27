package lk.ijse.bo;

import lk.ijse.bo.custom.impl.*;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory(){}

    public static BOFactory getBoFactory(){
        return (boFactory == null)?  boFactory =  new BOFactory():boFactory;

    }

    public enum BOTypes{
        Customer, EMPLOYEE,INGREDIENT,PRODUCT,ORDER
    }

    public SuperBO getBO(BOTypes boTypes){
        switch (boTypes){
            case Customer:
                return new CustomerBOImpl();
            case EMPLOYEE:
                return new EmployeeBOImpl();
            case INGREDIENT:
                return new IngredientBOImpl();
            case PRODUCT:
                return new ProductBOImpl();
            case ORDER:
                return new OrderBOImpl();
            default:
                return null;
        }
    }
}
