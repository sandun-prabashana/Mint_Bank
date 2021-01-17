package ik.mint.pos.bo;

import ik.mint.pos.bo.custom.impl.*;

public class BOFactry {

    private static BOFactry boFactry;

    private BOFactry(){

    }

    public static BOFactry getInstance(){
        if(boFactry==null){
            boFactry=new BOFactry();
        }
        return boFactry;
    }

    public enum BOTypes{
        CUSTOMER,LOGIN,CASHDEPOSIT,CASHWITHDRAW,CARD,TRANSFER
    }

    public SuperBO getBO(BOTypes boTypes){
        switch (boTypes){
            case CUSTOMER:
                return new CustomerBOImpl();
            case LOGIN:
                return new LoginBOImpl();
            case CASHDEPOSIT:
                return new CashDepositBOImpl();
            case CASHWITHDRAW:
                return new CashWithdrawBOImpl();
            case CARD:
                return new CardBOImpl();
            case TRANSFER:
                return new TransferBOImpl();
            default:
                return null;
        }
    }
}
