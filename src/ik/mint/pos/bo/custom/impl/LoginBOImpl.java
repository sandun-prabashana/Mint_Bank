package ik.mint.pos.bo.custom.impl;

import ik.mint.pos.bo.custom.LoginBO;
import ik.mint.pos.dao.DAOFactry;
import ik.mint.pos.dao.custom.LoginDAO;
import ik.mint.pos.dto.CustomDTO;
import ik.mint.pos.entity.Custom;

import java.sql.SQLException;

public class LoginBOImpl implements LoginBO {
    static LoginDAO loginDAO= (LoginDAO) DAOFactry.getInstance().getDAO(DAOFactry.DAOType.LOGIN);
    @Override
    public CustomDTO searchPassword(String userName)throws ClassNotFoundException, SQLException {
        Custom search = loginDAO.search(userName);
        return new CustomDTO(search.getOfficerId(),search.getPassword());
    }
}
