package ik.mint.pos.entity;

public class Account {
    private Integer AccountNumber;
    private String CustomerId;
    private String AccountType;
    private String Date;
    private String Status;

    public Account(){

    }
    public Account(Integer AccountNumber,String CustomerId,String AccountType,String Date,String Status){
        this.setAccountNumber(AccountNumber);
        this.setCustomerId(CustomerId);
        this.setAccountType(AccountType);
        this.setDate(Date);
        this.setStatus(Status);
    }

    public Integer getAccountNumber() {
        return AccountNumber;
    }

    public void setAccountNumber(Integer accountNumber) {
        AccountNumber = accountNumber;
    }

    public String getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(String customerId) {
        CustomerId = customerId;
    }

    public String getAccountType() {
        return AccountType;
    }

    public void setAccountType(String accountType) {
        AccountType = accountType;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
