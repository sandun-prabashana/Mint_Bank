package ik.mint.pos.dto;

public class CardDTO {
    private Integer AccountNumber;
    private Integer CardNumber;
    private String CardType;
    private String DateOfIssue;
    private String DateOfExprie;
    private String Status;
    private Integer Password;

    public CardDTO(){

    }
    public CardDTO(Integer AccountNumber,Integer CardNumber,String CardType,String DateOfIssue,String DateOfExprie,String Status,Integer Password){
      this.setAccountNumber(AccountNumber);
        this.setCardNumber(CardNumber);
        this.setCardType(CardType);
        this.setDateOfIssue(DateOfIssue);
        this.setDateOfExprie(DateOfExprie);
        this.setStatus(Status);
        this.setPassword(Password);
    }

    public Integer getAccountNumber() {
        return AccountNumber;
    }

    public void setAccountNumber(Integer accountNumber) {
        AccountNumber = accountNumber;
    }

    public Integer getCardNumber() {
        return CardNumber;
    }

    public void setCardNumber(Integer cardNumber) {
        CardNumber = cardNumber;
    }

    public String getCardType() {
        return CardType;
    }

    public void setCardType(String cardType) {
        CardType = cardType;
    }

    public String getDateOfIssue() {
        return DateOfIssue;
    }

    public void setDateOfIssue(String dateOfIssue) {
        DateOfIssue = dateOfIssue;
    }

    public String getDateOfExprie() {
        return DateOfExprie;
    }

    public void setDateOfExprie(String dateOfExprie) {
        DateOfExprie = dateOfExprie;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public Integer getPassword() {
        return Password;
    }

    public void setPassword(Integer password) {
        Password = password;
    }
}
