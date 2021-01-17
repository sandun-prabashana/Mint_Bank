package ik.mint.pos.bo.custom.impl;

import ik.mint.pos.bo.custom.CustomerBO;
import ik.mint.pos.dao.DAOFactry;
import ik.mint.pos.dao.custom.AccountDAO;
import ik.mint.pos.dao.custom.AccountDetailDAO;
import ik.mint.pos.dao.custom.CustomerDAO;
import ik.mint.pos.dao.custom.QueryDAO;
import ik.mint.pos.db.DBConnection;
import ik.mint.pos.dto.AcccountDetailDTO;
import ik.mint.pos.dto.AccountDTO;
import ik.mint.pos.dto.CustomDTO;
import ik.mint.pos.dto.CustomerDTO;
import ik.mint.pos.entity.Account;
import ik.mint.pos.entity.AccountDetail;
import ik.mint.pos.entity.Custom;
import ik.mint.pos.entity.Customer;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBOImpl implements CustomerBO {
    static CustomerDAO customerDAO= (CustomerDAO) DAOFactry.getInstance().getDAO(DAOFactry.DAOType.CUSTOMER);
    static AccountDAO accountDAO= (AccountDAO) DAOFactry.getInstance().getDAO(DAOFactry.DAOType.ACCOUNT);
    static QueryDAO queryDAO= (QueryDAO) DAOFactry.getInstance().getDAO(DAOFactry.DAOType.QUERY);
    static AccountDetailDAO accountDetailDAO= (AccountDetailDAO) DAOFactry.getInstance().getDAO(DAOFactry.DAOType.ACCOUNTDETAIL);
    @Override
    public int getRegCount() throws ClassNotFoundException, SQLException {
        return customerDAO.getRowCount();
    }

    @Override
    public boolean addCustomer(CustomerDTO customerDTO) throws ClassNotFoundException, SQLException {
        return customerDAO.add(new Customer(customerDTO.getCustomerId(),customerDTO.getStatus(),customerDTO.getFristName(),customerDTO.getLastName(),customerDTO.getCity(),customerDTO.getAddress(),customerDTO.getEmailAddress(),customerDTO.getPhoneNo()));
    }

    @Override
    public boolean addAcccount(AccountDTO accountDTO) throws ClassNotFoundException, SQLException {
        Connection connection= DBConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        boolean add= accountDAO.add(new Account(accountDTO.getAccountNumber(),accountDTO.getCustomerId(),accountDTO.getAccountType(),accountDTO.getDate(),accountDTO.getStatus()));
        try{
            if(add){
                for(AcccountDetailDTO aDTO:accountDTO.getAllAccountDetail()){
                    AccountDetail aDetail=new AccountDetail(aDTO.getRef(),aDTO.getDate(),aDTO.getAccountNumber(),aDTO.getDeatil(),aDTO.getWithdraw(),aDTO.getDeposit(),aDTO.getBalance());
                    boolean add1=accountDetailDAO.add(aDetail);

                    if(!add1){
                        connection.rollback();
                        return false;
                    }
                }
                connection.commit();
                return true;
            }else{
                connection.rollback();
                return false;
            }
        }finally{
            connection.setAutoCommit(true);
        }
    }

    @Override
    public ArrayList<CustomDTO> getAllDetails(String customerId) throws ClassNotFoundException, SQLException {
        ArrayList<Custom> all = queryDAO.accountList(customerId);
        ArrayList<CustomDTO> accountList = new ArrayList<>();
        for (Custom c : all) {
            accountList.add(new CustomDTO(c.getOfficerId(),c.getPassword()));
        }
        return accountList;
    }
}
