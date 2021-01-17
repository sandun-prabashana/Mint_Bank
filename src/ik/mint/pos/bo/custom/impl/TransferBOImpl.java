package ik.mint.pos.bo.custom.impl;

import ik.mint.pos.bo.custom.TransferBO;
import ik.mint.pos.dao.DAOFactry;
import ik.mint.pos.dao.custom.AccountDetailDAO;
import ik.mint.pos.dao.custom.CashDepositDAO;
import ik.mint.pos.dao.custom.QueryDAO;
import ik.mint.pos.dao.custom.TransferDAO;
import ik.mint.pos.db.DBConnection;
import ik.mint.pos.dto.AcccountDetailDTO;
import ik.mint.pos.dto.CustomDTO;
import ik.mint.pos.dto.TransferDTO;
import ik.mint.pos.entity.Account;
import ik.mint.pos.entity.AccountDetail;
import ik.mint.pos.entity.Custom;
import ik.mint.pos.entity.Transfer;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class TransferBOImpl implements TransferBO {

    static QueryDAO queryDAO= (QueryDAO) DAOFactry.getInstance().getDAO(DAOFactry.DAOType.QUERY);
    static CashDepositDAO cashDepositDAO= (CashDepositDAO) DAOFactry.getInstance().getDAO(DAOFactry.DAOType.CASHDEPOSIT);
    static TransferDAO transferDAO= (TransferDAO) DAOFactry.getInstance().getDAO(DAOFactry.DAOType.TRANSFER);
    static AccountDetailDAO accountDetailDAO= (AccountDetailDAO) DAOFactry.getInstance().getDAO(DAOFactry.DAOType.ACCOUNTDETAIL);
    @Override
    public ArrayList<CustomDTO> getAllAccount() throws ClassNotFoundException, SQLException {
        ArrayList<Custom> all = queryDAO.account();
        ArrayList<CustomDTO> accountList = new ArrayList<>();
        for (Custom c : all) {
            accountList.add(new CustomDTO(c.getAccountNumber()));
        }
        return accountList;
    }

    @Override
    public CustomDTO getBalance(Integer accountNumber) throws ClassNotFoundException, SQLException {
        Custom search = queryDAO.search(accountNumber);
        return new CustomDTO(search.getRef(),search.getAccountNumber(),search.getCustomerId(),search.getFname(),search.getLname(),search.getAccountType(),search.getBalance());
    }

    @Override
    public boolean addTransfer(TransferDTO transferDTO) throws ClassNotFoundException, SQLException {
        Connection connection= DBConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        boolean add= transferDAO.add(new Transfer(transferDTO.getTransferId(),transferDTO.getAccountNumber(),transferDTO.getAmount(),transferDTO.getTransferAccountNumber(),transferDTO.getDate()));
        try{
            if(add){
                for(AcccountDetailDTO aDTO:transferDTO.getAllAccountDetail()){
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
    public boolean addAccountDetail(AcccountDetailDTO acccountDetailDTO) throws ClassNotFoundException, SQLException {
        return cashDepositDAO.add(new AccountDetail(acccountDetailDTO.getRef(),acccountDetailDTO.getDate(),acccountDetailDTO.getAccountNumber(),acccountDetailDTO.getDeatil(),acccountDetailDTO.getWithdraw(),acccountDetailDTO.getDeposit(),acccountDetailDTO.getBalance()));
    }

    @Override
    public int getRegCount() throws ClassNotFoundException, SQLException {
        return transferDAO.getRowCount();
    }
}
