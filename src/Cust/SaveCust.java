package Cust;

public class SaveCust {

    private String custFirstName;
    private String custLastName;
    private String custPhone;
    private String custEmail;
    private String custStreetAddress;
    private String custState;
    private String custCity;
    private String custZip;

    public SaveCust(String custFirstName, String custLastName, String custPhone, String custEmail, String custStreetAddress, String custState, String custCity, String custZip) {
        this.custFirstName = custFirstName;
        this.custLastName = custLastName;
        this.custPhone = custPhone;
        this.custEmail = custEmail;
        this.custStreetAddress = custStreetAddress;
        this.custState = custState;
        this.custCity = custCity;
        this.custZip = custZip;
    }

    public String getCustFirstName() {return custFirstName;}
    public void setCustFirstName(String custFirstName){this.custFirstName = custFirstName;}
    public String getCustLastName() {return custLastName;}
    public void setCustLastName(String custLastName){this.custLastName = custLastName;}
    public String getCustPhone() {return custPhone;}
    public void setCustPhone(String custPhone){this.custPhone = custPhone;}
    public String getCustEmail() {return custEmail;}
    public void setCustEmail(String custEmail){this.custEmail = custEmail;}
    public String getCustStreetAddress(){return custStreetAddress;}
    public void setCustStreetAddress(String custStreetAddress){this.custStreetAddress = custStreetAddress;}
    public String getCustState(){return custState;}
    public void setCustState(String custState){this.custState = custState;}
    public String getCustCity(){return custCity;}
    public void setCustCity(String custCity){this.custCity = custCity;}
    public String getCustZip(){return custZip;}
    public void setCustZip(String custZip){this.custZip = custZip;}
}
