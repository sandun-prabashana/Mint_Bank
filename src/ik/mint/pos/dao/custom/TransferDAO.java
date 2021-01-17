package ik.mint.pos.dao.custom;

import ik.mint.pos.dao.CrudDAO;
import ik.mint.pos.entity.Transfer;

import java.sql.SQLException;

public interface TransferDAO extends CrudDAO<Transfer,String> {
    int getRowCount()throws ClassNotFoundException, SQLException;
}
