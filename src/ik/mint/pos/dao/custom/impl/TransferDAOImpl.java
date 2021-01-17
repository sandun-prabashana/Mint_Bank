package ik.mint.pos.dao.custom.impl;

import ik.mint.pos.dao.CrudUtil;
import ik.mint.pos.dao.custom.TransferDAO;
import ik.mint.pos.dto.TransferDTO;
import ik.mint.pos.entity.Transfer;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TransferDAOImpl implements TransferDAO {
    @Override
    public boolean add(Transfer transfer) throws ClassNotFoundException, SQLException {
        String SQL = "INSERT INTO Money_Transfer VALUES (?,?,?,?,?)";
        return CrudUtil.executeUpdate(SQL,transfer.getTransferId(),transfer.getAccountNumber(),transfer.getAmount(),transfer.getTransferAccountNumber(),transfer.getDate());
    }

    @Override
    public boolean delete(String s) throws ClassNotFoundException, SQLException {
        return false;
    }

    @Override
    public boolean update(Transfer transfer) throws ClassNotFoundException, SQLException {
        return false;
    }

    @Override
    public Transfer search(String s) throws ClassNotFoundException, SQLException {
        return null;
    }

    @Override
    public ObservableList<Transfer> getAll() throws ClassNotFoundException, SQLException {
        return null;
    }

    @Override
    public int getRowCount() throws ClassNotFoundException, SQLException {
        String SQL = "SELECT COUNT(Transfer_Id) FROM Money_Transfer";
        ResultSet rst = CrudUtil.executeQuery(SQL);
        if (rst.next()){
            return rst.getInt(1);
        }
        return -1;
    }
}
