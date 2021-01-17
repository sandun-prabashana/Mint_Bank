package ik.mint.pos.entity;

public class Customer {
    private String CustomerId;
    private String Status;
    private String FristName;
    private String LastName;
    private String City;
    private String Address;
    private String EmailAddress;
    private String PhoneNo;

    public Customer(){

    }

    public Customer(String CustomerId,String Status,String FristName,String LastName,String City,String Address,String EmailAddress,String PhoneNo) {
        this.setCustomerId(CustomerId);
        this.setStatus(Status);
        this.setFristName(FristName);
        this.setLastName(LastName);
        this.setCity(City);
        this.setAddress(Address);
        this.setEmailAddress(EmailAddress);
        this.setPhoneNo(PhoneNo);
    }

    public String getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(String customerId) {
        CustomerId = customerId;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getFristName() {
        return FristName;
    }

    public void setFristName(String fristName) {
        FristName = fristName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getEmailAddress() {
        return EmailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        EmailAddress = emailAddress;
    }

    public String getPhoneNo() {
        return PhoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        PhoneNo = phoneNo;
    }
}
