package ik.mint.pos.dao.custom.impl;

import ik.mint.pos.dao.CrudUtil;
import ik.mint.pos.dao.custom.CustomerDAO;
import ik.mint.pos.entity.Customer;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDAOImpl implements CustomerDAO {

    @Override
    public boolean add(Customer customer) throws ClassNotFoundException, SQLException {
        String SQL = "INSERT INTO Customer VALUES (?,?,?,?,?,?,?,?)";
        return CrudUtil.executeUpdate(SQL,customer.getCustomerId(),customer.getStatus(),customer.getFristName(),customer.getLastName(),customer.getCity(),customer.getAddress(),customer.getEmailAddress(),customer.getPhoneNo());
    }

    @Override
    public boolean delete(String s) throws ClassNotFoundException, SQLException {
        return false;
    }

    @Override
    public boolean update(Customer customer) throws ClassNotFoundException, SQLException {
        return false;
    }

    @Override
    public Customer search(String s) throws ClassNotFoundException, SQLException {
        return null;
    }

    @Override
    public ObservableList getAll() throws ClassNotFoundException, SQLException {
        return null;
    }

    @Override
    public int getRowCount() throws ClassNotFoundException, SQLException {
        String SQL = "SELECT COUNT(Customer_Id) FROM Customer";
        ResultSet rst = CrudUtil.executeQuery(SQL);
        if (rst.next()){
            return rst.getInt(1);
        }
        return -1;
    }
}
