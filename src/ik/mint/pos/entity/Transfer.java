package ik.mint.pos.entity;

public class Transfer {
    private String TransferId;
    private Integer AccountNumber;
    private Double Amount;
    private Integer TransferAccountNumber;
    private String Date;

    public Transfer(){

    }
    public Transfer(String TransferId,Integer AccountNumber,Double Amount,Integer TransferAccountNumber,String Date){
        this.setTransferId(TransferId);
        this.setAccountNumber(AccountNumber);
        this.setAmount(Amount);
        this.setTransferAccountNumber(TransferAccountNumber);
        this.setDate(Date);
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
}
