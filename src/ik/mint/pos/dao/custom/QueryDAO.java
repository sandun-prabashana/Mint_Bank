package ik.mint.pos.dao.custom;

import ik.mint.pos.dao.SuperDAO;
import ik.mint.pos.entity.Custom;

import java.sql.SQLException;
import java.util.ArrayList;

public interface QueryDAO extends SuperDAO {
    ArrayList<Custom> accountList(String customerId)throws ClassNotFoundException, SQLException;
    public Custom search(Integer integer)throws ClassNotFoundException, SQLException;

    Custom searchAccountType(int accountNo)throws ClassNotFoundException, SQLException;

    ArrayList<Custom> account()throws ClassNotFoundException, SQLException;

    ArrayList<Custom> card(Integer accountNo)throws ClassNotFoundException, SQLException;

    ArrayList<Custom> allCard()throws ClassNotFoundException, SQLException;

    Custom searchPassword(Integer cardNo)throws ClassNotFoundException, SQLException;

    boolean update(Custom custom)throws ClassNotFoundException, SQLException;

    ArrayList<Custom> custID(Integer cardNo)throws ClassNotFoundException, SQLException;

    boolean updateStatues(Custom custom)throws ClassNotFoundException, SQLException;
}
