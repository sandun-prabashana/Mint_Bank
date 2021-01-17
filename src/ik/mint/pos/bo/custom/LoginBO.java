package ik.mint.pos.bo.custom;

import ik.mint.pos.bo.SuperBO;
import ik.mint.pos.dto.CustomDTO;

import java.sql.SQLException;

public interface LoginBO extends SuperBO {
    CustomDTO searchPassword(String userName)throws ClassNotFoundException, SQLException;
}
