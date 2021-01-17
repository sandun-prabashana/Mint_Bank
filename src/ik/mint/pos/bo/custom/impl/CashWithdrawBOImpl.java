package ik.mint.pos.bo.custom.impl;

import ik.mint.pos.bo.custom.CashWithdrawBO;
import ik.mint.pos.dao.DAOFactry;
import ik.mint.pos.dao.custom.CashDepositDAO;
import ik.mint.pos.dao.custom.CashWithdrawDAO;
import ik.mint.pos.dao.custom.QueryDAO;
import ik.mint.pos.dto.AcccountDetailDTO;
import ik.mint.pos.dto.CustomDTO;
import ik.mint.pos.entity.AccountDetail;
import ik.mint.pos.entity.Custom;

import java.sql.SQLException;
import java.util.ArrayList;

public class CashWithdrawBOImpl implements CashWithdrawBO {

    static CashWithdrawDAO cashWithdrawDAO= (CashWithdrawDAO) DAOFactry.getInstance().getDAO(DAOFactry.DAOType.CASHWITHDRAW);
    static QueryDAO queryDAO= (QueryDAO) DAOFactry.getInstance().getDAO(DAOFactry.DAOType.QUERY);

    @Override
    public CustomDTO getBalance(Integer accountNumber) throws ClassNotFoundException, SQLException {
        Custom search = queryDAO.search(accountNumber);
        return new CustomDTO(search.getRef(),search.getAccountNumber(),search.getCustomerId(),search.getFname(),search.getLname(),search.getAccountType(),search.getBalance());
    }

    @Override
    public boolean addAccountDetail(AcccountDetailDTO acccountDetailDTO) throws ClassNotFoundException, SQLException {
        return cashWithdrawDAO.add(new AccountDetail(acccountDetailDTO.getRef(),acccountDetailDTO.getDate(),acccountDetailDTO.getAccountNumber(),acccountDetailDTO.getDeatil(),acccountDetailDTO.getWithdraw(),acccountDetailDTO.getDeposit(),acccountDetailDTO.getBalance()));
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
}
