package ik.mint.pos.entity;

public class Custom {
    private String OfficerId;
    private String Password;
    private Integer Ref;
    private Integer AccountNumber;
    private String CustomerId;
    private String Fname;
    private String Lname;
    private String AccountType;
    private Double Balance;
    private Integer CardNo;
    private Integer CardPassword;

    public Custom(){

    }
    public Custom(Integer CardNo,Integer CardPassword){
        this.setCardNo(CardNo);
        this.setCardPassword(CardPassword);
    }
    public Custom(Integer AccountNumber){
        this.AccountNumber=AccountNumber;
    }
    public Custom(Integer AccountNumber,String AccountType){
        this.AccountNumber=AccountNumber;
        this.AccountType=AccountType;
    }
    public Custom(String OfficerId, String Password){
        this.setOfficerId(OfficerId);
        this.setPassword(Password);
    }
    public Custom(Integer Ref,Integer AccountNumber, String CustomerId,String Fname,String Lname,String AccountType,Double Balance){
        this.setRef(Ref);
        this.setAccountNumber(AccountNumber);
        this.setCustomerId(CustomerId);
        this.setFname(Fname);
        this.setLname(Lname);
        this.setAccountType(AccountType);
        this.setBalance(Balance);
    }


    public String getOfficerId() {
        return OfficerId;
    }

    public void setOfficerId(String officerId) {
        OfficerId = officerId;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public Integer getAccountNumber() {
        return AccountNumber;
    }

    public void setAccountNumber(Integer accountNumber) {
        AccountNumber = accountNumber;
    }

    public String getFname() {
        return Fname;
    }

    public void setFname(String fname) {
        Fname = fname;
    }

    public String getLname() {
        return Lname;
    }

    public void setLname(String lname) {
        Lname = lname;
    }

    public Double getBalance() {
        return Balance;
    }

    public void setBalance(Double balance) {
        Balance = balance;
    }

    public String getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(String customerId) {
        CustomerId = customerId;
    }

    public Integer getRef() {
        return Ref;
    }

    public void setRef(Integer ref) {
        Ref = ref;
    }

    public String getAccountType() {
        return AccountType;
    }

    public void setAccountType(String accountType) {
        AccountType = accountType;
    }

    public Integer getCardNo() {
        return CardNo;
    }

    public void setCardNo(Integer cardNo) {
        CardNo = cardNo;
    }

    public Integer getCardPassword() {
        return CardPassword;
    }

    public void setCardPassword(Integer cardPassword) {
        CardPassword = cardPassword;
    }
}
