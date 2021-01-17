package ik.mint.pos.dao.custom.impl;

import ik.mint.pos.dao.CrudUtil;
import ik.mint.pos.dao.custom.LoginDAO;
import ik.mint.pos.entity.Custom;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAOImpl implements LoginDAO {
    @Override
    public boolean add(Custom custom) throws ClassNotFoundException, SQLException {
        return false;
    }

    @Override
    public boolean delete(String s) throws ClassNotFoundException, SQLException {
        return false;
    }

    @Override
    public boolean update(Custom custom) throws ClassNotFoundException, SQLException {
        return false;
    }

    @Override
    public Custom search(String userName) throws ClassNotFoundException, SQLException {
        String SQL = "select Officer_Id,password from Officer_Login where Officer_Id=?";
        ResultSet rst =  CrudUtil.executeQuery(SQL,userName);
        if (rst.next()){
            return new Custom(rst.getString("Officer_Id"),rst.getString("Password"));
        }
        return null;
    }

    @Override
    public ObservableList<Custom> getAll() throws ClassNotFoundException, SQLException {
        return null;
    }
}
