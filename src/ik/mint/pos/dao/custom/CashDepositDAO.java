package ik.mint.pos.dao.custom;

import ik.mint.pos.dao.CrudDAO;
import ik.mint.pos.entity.AccountDetail;
import ik.mint.pos.entity.Custom;
import org.omg.CORBA.CustomMarshal;

public interface CashDepositDAO extends CrudDAO<AccountDetail,Integer> {
}
