package Dashboard;

public class DataStore5 {

    private String firstName;
    private String lastName;
    private String type;
    private String cost;
    private String status;
    private String date;



    public DataStore5(String firstName, String lastName, String type, String cost, String status, String date){
        this.firstName = new String(firstName);
        this.lastName = new String(lastName);
        this.type = new String(type);
        this.cost = new String(cost);
        this.status = new String(status);
        this.date = new String(date);
    }

    public String getFirstName() {return firstName;}
    public void setFirstName(String firstName) {this.firstName=firstName;}

    public String getLastName() {return lastName;}
    public void setLastName(String lastName) {this.lastName=lastName;}

    public String getType() {return type;}
    public void setType(String type) {this.type=type;}

    public String getCost() {return cost;}
    public void setCost(String cost) {this.cost=cost;}

    public String getStatus() {return status;}
    public void setStatus(String status) {this.status=status;}

    public String getDate() {return date;}
    public void setDate(String date) {this.date=date;}


}
