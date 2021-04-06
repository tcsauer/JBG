package Dashboard;

public class DataStore2 {

    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String street;
    private String city;
    private String state;
    private String zip;



    public DataStore2(String firstName, String lastName, String phone, String email, String street, String city, String state, String zip){
        this.firstName = new String(firstName);
        this.lastName = new String(lastName);
        this.phone = new String(phone);
        this.email = new String(email);
        this.street = new String(street);
        this.city = new String(city);
        this.state = new String(state);
        this.zip = new String(zip);
    }

    public String getFirstName() {return firstName;}
    public void setFirstName(String firstName) {this.firstName=firstName;}

    public String getLastName() {return lastName;}
    public void setLastName(String lastName) {this.lastName=lastName;}

    public String getPhone() {return phone;}
    public void setPhone(String phone) {this.phone=phone;}

    public String getEmail() {return email;}
    public void setEmail(String email) {this.email=email;}

    public String getStreet() {return street;}
    public void setStreet(String street) {this.street=street;}

    public String getCity() {return city;}
    public void setCity(String city) {this.city=city;}

    public String getState() {return state;}
    public void setState(String state) {this.state=state;}

    public String getZip() {return zip;}
    public void setZip(String zip) {this.zip=zip;}
    }

