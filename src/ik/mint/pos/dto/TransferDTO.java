package ik.mint.pos.dto;

import java.util.ArrayList;

public class TransferDTO {
    private String TransferId;
    private Integer AccountNumber;
    private Double Amount;
    private Integer TransferAccountNumber;
    private String Date;
    private ArrayList<AcccountDetailDTO> allAccountDetail;

    public TransferDTO(){

    }
    public TransferDTO(String TransferId,Integer AccountNumber,Double Amount,Integer TransferAccountNumber,String Date,ArrayList<AcccountDetailDTO> allAccountDetail){
        this.setTransferId(TransferId);
        this.setAccountNumber(AccountNumber);
        this.setAmount(Amount);
        this.setTransferAccountNumber(TransferAccountNumber);
        this.setDate(Date);
        this.setAllAccountDetail(allAccountDetail);
    }

    public String getTransferId() {
        return TransferId;
    }

    public void setTransferId(String transferId) {
        TransferId = transferId;
    }

    public Integer getAccountNumber() {
        return AccountNumber;
    }

    public void setAccountNumber(Integer accountNumber) {
        AccountNumber = accountNumber;
    }

    public Double getAmount() {
        return Amount;
    }

    public void setAmount(Double amount) {
        Amount = amount;
    }

    public Integer getTransferAccountNumber() {
        return TransferAccountNumber;
    }

    public void setTransferAccountNumber(Integer transferAccountNumber) {
        TransferAccountNumber = transferAccountNumber;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public ArrayList<AcccountDetailDTO> getAllAccountDetail() {
        return allAccountDetail;
    }

    public void setAllAccountDetail(ArrayList<AcccountDetailDTO> allAccountDetail) {
        this.allAccountDetail = allAccountDetail;
    }
}
