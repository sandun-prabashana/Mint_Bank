package ik.mint.pos.bo.custom;

import ik.mint.pos.bo.SuperBO;
import ik.mint.pos.dto.AcccountDetailDTO;
import ik.mint.pos.dto.CustomDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CashDepositBO extends SuperBO {
    

    CustomDTO getBalance(Integer accountNumber)throws ClassNotFoundException, SQLException;

    boolean addAccountDetail(AcccountDetailDTO acccountDetailDTO)throws ClassNotFoundException, SQLException;

    ArrayList<CustomDTO> getAllAccount()throws ClassNotFoundException, SQLException;
}
