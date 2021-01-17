package ik.mint.pos.entity;

public class AccountDetail {
    private Integer Ref;
    private String Date;
    private Integer AccountNumber;
    private String Deatil;
    private Double Withdraw;
    private Double Deposit;
    private Double Balance;

    public AccountDetail(){

    }

    public AccountDetail(Integer Ref,String Date,Integer AccountNumber,String Deatil,Double Withdraw,Double Deposit,Double Balance){
        this.setRef(Ref);
        this.setDate(Date);
        this.setAccountNumber(AccountNumber);
        this.setDeatil(Deatil);
        this.setWithdraw(Withdraw);
        this.setDeposit(Deposit);
        this.setBalance(Balance);
    }


    public Integer getAccountNumber() {
        return AccountNumber;
    }

    public void setAccountNumber(Integer accountNumber) {
        AccountNumber = accountNumber;
    }

    public String getDeatil() {
        return Deatil;
    }

    public void setDeatil(String deatil) {
        Deatil = deatil;
    }

    public Double getWithdraw() {
        return Withdraw;
    }

    public void setWithdraw(Double withdraw) {
        Withdraw = withdraw;
    }

    public Double getDeposit() {
        return Deposit;
    }

    public void setDeposit(Double deposit) {
        Deposit = deposit;
    }

    public Double getBalance() {
        return Balance;
    }

    public void setBalance(Double balance) {
        Balance = balance;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public Integer getRef() {
        return Ref;
    }

    public void setRef(Integer ref) {
        Ref = ref;
    }
}
