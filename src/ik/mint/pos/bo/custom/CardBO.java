package ik.mint.pos.bo.custom;

import ik.mint.pos.bo.SuperBO;
import ik.mint.pos.dto.CardDTO;
import ik.mint.pos.dto.CustomDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CardBO extends SuperBO {


    CustomDTO searchAccountType(int accountNo)throws ClassNotFoundException, SQLException;

    boolean addCard(CardDTO cardDTO)throws ClassNotFoundException, SQLException;

    ArrayList<CustomDTO> getAllAccount() throws ClassNotFoundException, SQLException;

    ArrayList<CustomDTO> getCardDetail(Integer accountNo)throws ClassNotFoundException, SQLException;

    ArrayList<CustomDTO> getCardNo()throws ClassNotFoundException, SQLException;

    CustomDTO searchPassword(Integer cardNo)throws ClassNotFoundException, SQLException;

    boolean Update(CustomDTO customDTO)throws ClassNotFoundException, SQLException;

    ArrayList<CustomDTO> getCustId(Integer cardNo)throws ClassNotFoundException, SQLException;

    boolean UpdateStatues(CustomDTO customDTO)throws ClassNotFoundException, SQLException;
}
