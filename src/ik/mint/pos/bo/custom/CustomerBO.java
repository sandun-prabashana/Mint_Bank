package ik.mint.pos.bo.custom;

import ik.mint.pos.bo.SuperBO;
import ik.mint.pos.dto.AccountDTO;
import ik.mint.pos.dto.CustomDTO;
import ik.mint.pos.dto.CustomerDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerBO extends SuperBO {
    int getRegCount()throws ClassNotFoundException, SQLException;

    boolean addCustomer(CustomerDTO customerDTO)throws ClassNotFoundException, SQLException;

    boolean addAcccount(AccountDTO accountDTO)throws ClassNotFoundException, SQLException;

    ArrayList<CustomDTO> getAllDetails(String customerId)throws ClassNotFoundException, SQLException;
}
