package ik.mint.pos.dto;

import ik.mint.pos.entity.AccountDetail;

import java.util.ArrayList;

public class AccountDTO {
    private Integer AccountNumber;
    private String CustomerId;
    private String AccountType;
    private String Date;
    private String Status;
    private ArrayList<AcccountDetailDTO> allAccountDetail;

    public AccountDTO(){

    }
    public AccountDTO(Integer AccountNumber,String CustomerId,String AccountType,String Date,String Status,ArrayList<AcccountDetailDTO> allAccountDetail){
        this.setAccountNumber(AccountNumber);
        this.setCustomerId(CustomerId);
        this.setAccountType(AccountType);
        this.setDate(Date);
        this.setStatus(Status);
        this.setAllAccountDetail(allAccountDetail);
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


    public ArrayList<AcccountDetailDTO> getAllAccountDetail() {
        return allAccountDetail;
    }

    public void setAllAccountDetail(ArrayList<AcccountDetailDTO> allAccountDetail) {
        this.allAccountDetail = allAccountDetail;
    }
}
