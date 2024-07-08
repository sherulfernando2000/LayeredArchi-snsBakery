package lk.ijse.bo;

import lk.ijse.bo.custom.impl.*;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory(){}

    public static BOFactory getBoFactory(){
        return (boFactory == null)?  boFactory =  new BOFactory():boFactory;

    }

    public enum BOTypes{
        Customer, EMPLOYEE,INGREDIENT,PRODUCT,ORDER,SUPPLIER,SUPPLIER_ORDER,DASHBOARD,DAILYREPORT,WEEKLYREPORT,PRODUCT_EMPLOYEE,OFFERS,SIGNUP,LOGIN
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
            case SUPPLIER:
                return new SupplierBOImpl();
            case SUPPLIER_ORDER:
                return new SupplierOrderBOImpl();
            case DASHBOARD:
                return new DashBoardBOImpl();
            case DAILYREPORT:
                return new DailyReportBOImpl();
            case WEEKLYREPORT:
                return new WeeklyReportBOImpl();
            case PRODUCT_EMPLOYEE:
                return new ProductEmployeeBOImpl();
            case OFFERS:
                return new OffersBOImpl();
            case SIGNUP:
                return new SignupBOImpl();
            case LOGIN:
                return new LoginBOImpl();

            default:
                return null;
        }
    }
}
