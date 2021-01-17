package ik.mint.pos.dao.custom;

import ik.mint.pos.dao.CrudDAO;
import ik.mint.pos.entity.Customer;

import java.sql.SQLException;

public interface CustomerDAO extends CrudDAO<Customer,String> {
    int getRowCount()throws ClassNotFoundException, SQLException;
}
