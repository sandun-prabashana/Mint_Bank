package ik.mint.pos.dao.custom.impl;

import ik.mint.pos.dao.CrudUtil;
import ik.mint.pos.dao.custom.AccountDAO;
import ik.mint.pos.entity.Account;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class AccountDAOImpl implements AccountDAO {
    @Override
    public boolean add(Account account) throws ClassNotFoundException, SQLException {
        String SQL = "INSERT INTO Account VALUES (?,?,?,?,?)";
        return CrudUtil.executeUpdate(SQL,account.getAccountNumber(),account.getCustomerId(),account.getAccountType(),account.getDate(),account.getStatus());
    }

    @Override
    public boolean delete(String s) throws ClassNotFoundException, SQLException {
        return false;
    }

    @Override
    public boolean update(Account account) throws ClassNotFoundException, SQLException {
        return false;
    }

    @Override
    public Account search(String s) throws ClassNotFoundException, SQLException {
        return null;
    }

    @Override
    public ObservableList<Account> getAll() throws ClassNotFoundException, SQLException {
        return null;
    }
}
