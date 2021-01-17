package ik.mint.pos.dao;

import ik.mint.pos.dao.custom.impl.*;

public class DAOFactry {
    private static DAOFactry daoFactry;

    private DAOFactry(){}

    public static DAOFactry getInstance(){
        if(daoFactry==null){
            daoFactry=new DAOFactry();
        }
        return daoFactry;
    }

    public enum DAOType{
        CUSTOMER,LOGIN,ACCOUNT,QUERY,ACCOUNTDETAIL,CASHDEPOSIT,CASHWITHDRAW,CARD,TRANSFER
    }

    public SuperDAO getDAO (DAOType daoType){
        switch (daoType){
            case CUSTOMER:
                return new CustomerDAOImpl();
            case LOGIN:
                return new LoginDAOImpl();
            case ACCOUNT:
                return new AccountDAOImpl();
            case QUERY:
                return new QueryDAOImpl();
            case ACCOUNTDETAIL:
                return new AccountDetailDAOImpl();
            case CASHDEPOSIT:
                return new CashDepositDAOImpl();
            case CASHWITHDRAW:
                return new CashWithdrawDAOImpl();
            case CARD:
                return new CardDAOImpl();
            case TRANSFER:
                return new TransferDAOImpl();
            default:
                return null;
        }
    }
}
