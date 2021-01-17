package ik.mint.pos.dao.custom.impl;

import ik.mint.pos.dao.CrudUtil;
import ik.mint.pos.dao.custom.CardDAO;
import ik.mint.pos.entity.Card;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class CardDAOImpl implements CardDAO {
    @Override
    public boolean add(Card card) throws ClassNotFoundException, SQLException {
        String SQL = "INSERT INTO Card VALUES (?,?,?,?,?,?,?)";
        return CrudUtil.executeUpdate(SQL,card.getAccountNumber(),card.getCardNumber(),card.getCardType(),card.getDateOfIssue(),card.getDateOfExprie(),card.getStatus(),card.getPassword());
    }

    @Override
    public boolean delete(Integer integer) throws ClassNotFoundException, SQLException {
        return false;
    }

    @Override
    public boolean update(Card card) throws ClassNotFoundException, SQLException {
        return false;
    }

    @Override
    public Card search(Integer integer) throws ClassNotFoundException, SQLException {
        return null;
    }

    @Override
    public ObservableList<Card> getAll() throws ClassNotFoundException, SQLException {
        return null;
    }
}
