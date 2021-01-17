package ik.mint.pos.bo.custom.impl;

import ik.mint.pos.bo.custom.CardBO;
import ik.mint.pos.dao.DAOFactry;
import ik.mint.pos.dao.custom.CardDAO;
import ik.mint.pos.dao.custom.QueryDAO;
import ik.mint.pos.dto.CardDTO;
import ik.mint.pos.dto.CustomDTO;
import ik.mint.pos.entity.AccountDetail;
import ik.mint.pos.entity.Card;
import ik.mint.pos.entity.Custom;
import ik.mint.pos.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;


public class CardBOImpl implements CardBO {


    static QueryDAO queryDAO= (QueryDAO) DAOFactry.getInstance().getDAO(DAOFactry.DAOType.QUERY);
    static CardDAO cardDAO= (CardDAO) DAOFactry.getInstance().getDAO(DAOFactry.DAOType.CARD);

    @Override
    public CustomDTO searchAccountType(int accountNo) throws ClassNotFoundException, SQLException {
        Custom search = queryDAO.searchAccountType(accountNo);
        return new CustomDTO(search.getAccountNumber(),search.getAccountType());
    }

    @Override
    public boolean addCard(CardDTO cardDTO) throws ClassNotFoundException, SQLException {
        return cardDAO.add(new Card(cardDTO.getAccountNumber(),cardDTO.getCardNumber(),cardDTO.getCardType(),cardDTO.getDateOfIssue(),cardDTO.getDateOfExprie(),cardDTO.getStatus(),cardDTO.getPassword()));
    }

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
    public ArrayList<CustomDTO> getCardDetail(Integer accountNo) throws ClassNotFoundException, SQLException {
        ArrayList<Custom> all = queryDAO.card(accountNo);
        ArrayList<CustomDTO> cardList = new ArrayList<>();
        for (Custom c : all) {
            cardList.add(new CustomDTO(c.getAccountNumber(),c.getAccountType()));
        }
        return cardList;
    }

    @Override
    public ArrayList<CustomDTO> getCardNo() throws ClassNotFoundException, SQLException {
        ArrayList<Custom> all = queryDAO.allCard();
        ArrayList<CustomDTO> card = new ArrayList<>();
        for (Custom c : all) {
            card.add(new CustomDTO(c.getAccountNumber()));
        }
        return card;
    }

    @Override
    public CustomDTO searchPassword(Integer cardNo) throws ClassNotFoundException, SQLException {
        Custom search = queryDAO.searchPassword(cardNo);
        return new CustomDTO(search.getCardNo(),search.getCardPassword());
    }

    @Override
    public boolean Update(CustomDTO customDTO) throws ClassNotFoundException, SQLException {
        return queryDAO.update(new Custom(customDTO.getCardNo(),customDTO.getCardPassword()));
    }

    @Override
    public ArrayList<CustomDTO> getCustId(Integer cardNo) throws ClassNotFoundException, SQLException {
        ArrayList<Custom> all = queryDAO.custID(cardNo);
        ArrayList<CustomDTO> custList = new ArrayList<>();
        for (Custom c : all) {
            custList.add(new CustomDTO(c.getAccountNumber(),c.getAccountType()));
        }
        return custList;
    }

    @Override
    public boolean UpdateStatues(CustomDTO customDTO) throws ClassNotFoundException, SQLException {
        return queryDAO.updateStatues(new Custom(customDTO.getAccountNumber(),customDTO.getAccountType()));
    }
}
