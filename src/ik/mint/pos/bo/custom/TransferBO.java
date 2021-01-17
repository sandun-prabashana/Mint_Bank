package ik.mint.pos.bo.custom;

import ik.mint.pos.bo.SuperBO;
import ik.mint.pos.dto.AcccountDetailDTO;
import ik.mint.pos.dto.CustomDTO;
import ik.mint.pos.dto.TransferDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface TransferBO extends SuperBO {
    ArrayList<CustomDTO> getAllAccount()throws ClassNotFoundException, SQLException;

    CustomDTO getBalance(Integer accountNumber)throws ClassNotFoundException, SQLException;

    boolean addTransfer(TransferDTO transferDTO)throws ClassNotFoundException, SQLException;

    boolean addAccountDetail(AcccountDetailDTO acccountDetailDTO)throws ClassNotFoundException, SQLException;

    int getRegCount()throws ClassNotFoundException, SQLException;
}
