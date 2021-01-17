package ik.mint.pos.dao.custom.impl;

import ik.mint.pos.dao.CrudUtil;
import ik.mint.pos.dao.custom.QueryDAO;
import ik.mint.pos.entity.Custom;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class QueryDAOImpl implements QueryDAO {
    @Override
    public ArrayList<Custom> accountList(String customerId) throws SQLException, ClassNotFoundException {
        String sql = " select Customer_Id_Number,Account_Type from account where Customer_Id_Number=?";
        ResultSet rst = CrudUtil.executeQuery(sql, customerId);
        ArrayList<Custom> allDetails = new ArrayList();
        while (rst.next()) {
            String Customer_Id = rst.getString(1);
            String Account_type = rst.getString(2);

            Custom c = new Custom(Customer_Id, Account_type);
            allDetails.add(c);
        }
        return allDetails;
    }

    @Override
    public Custom search(Integer integer) throws ClassNotFoundException, SQLException {
        String SQL = "select Ref_No,a.Account_Number,c.Customer_Id_Number,Frist_Name,Last_Name,Account_Type,Balance from customer c,account a,account_detail ad where c.Customer_Id_Number=a.Customer_Id_Number && a.Account_Number=ad.Account_Number && ad.Account_Number=? order by Ref_No desc limit 1";
        ResultSet rst =  CrudUtil.executeQuery(SQL,integer);
        if (rst.next()){
            return new Custom(rst.getInt("Ref_No"),rst.getInt("Account_Number"),rst.getString("Customer_Id_Number"),rst.getString("Frist_Name"),rst.getString("Last_Name"),rst.getString("Account_Type"),rst.getDouble("Balance"));
        }
        return null;
    }

    @Override
    public Custom searchAccountType(int accountNo) throws ClassNotFoundException, SQLException {
        String SQL = "select Account_Number,Account_Type from Account where Account_Number=?";
        ResultSet rst =  CrudUtil.executeQuery(SQL,accountNo);
        if (rst.next()){
            return new Custom(rst.getInt("Account_Number"),rst.getString("Account_Type"));
        }
        return null;
    }

    @Override
    public ArrayList<Custom> account() throws ClassNotFoundException, SQLException {
        String sql = "select Account_Number from Account";
        ResultSet rst = CrudUtil.executeQuery(sql);
        ArrayList<Custom> allAccount = new ArrayList<>();
        while (rst.next()) {
            allAccount.add(new Custom(rst.getInt(1)));
        }
        return allAccount;
    }

    @Override
    public ArrayList<Custom> card(Integer accountNo) throws ClassNotFoundException, SQLException {
        String sql = "select Account_Number,Card_Type from Card where Account_Number=?";
        ResultSet rst = CrudUtil.executeQuery(sql,accountNo);
        ArrayList<Custom> cardList = new ArrayList<>();
        while (rst.next()) {
            cardList.add(new Custom(rst.getInt(1),rst.getString(2)));
        }
        return cardList;
    }

    @Override
    public ArrayList<Custom> allCard() throws ClassNotFoundException, SQLException {

        String sql = "select Card_Number from Card";
        ResultSet rst = CrudUtil.executeQuery(sql);
        ArrayList<Custom> card = new ArrayList<>();
        while (rst.next()) {
            card.add(new Custom(rst.getInt(1)));
        }
        return card;
    }

    @Override
    public Custom searchPassword(Integer cardNo) throws ClassNotFoundException, SQLException {
        String SQL = "select Card_Number,Password  from Card where Card_Number=?";
        ResultSet rst =  CrudUtil.executeQuery(SQL,cardNo);
        if (rst.next()){
            return new Custom(rst.getInt("Card_Number"),rst.getInt("Password"));
        }
        return null;
    }

    @Override
    public boolean update(Custom custom) throws ClassNotFoundException, SQLException {
        String sql = "update Card set Password=? where Card_Number=?";
        return CrudUtil.executeUpdate(sql,custom.getCardPassword(),custom.getCardNo());
    }

    @Override
    public ArrayList<Custom> custID(Integer cardNo) throws ClassNotFoundException, SQLException {
        String sql = "select  Card_Number,a.Customer_Id_Number from Account a,Account_Detail ad,Card c where a.Account_Number=ad.Account_Number && ad.Account_Number=c.Account_Number && Card_Number=?";
        ResultSet rst = CrudUtil.executeQuery(sql,cardNo);
        ArrayList<Custom> card = new ArrayList<>();
        while (rst.next()) {
            card.add(new Custom(rst.getInt(1),rst.getString(2)));
        }
        return card;
    }

    @Override
    public boolean updateStatues(Custom custom) throws ClassNotFoundException, SQLException {
        String sql = "update Card set Status =? where Card_Number=?";
        return CrudUtil.executeUpdate(sql,custom.getAccountType(),custom.getAccountNumber());
    }


}
