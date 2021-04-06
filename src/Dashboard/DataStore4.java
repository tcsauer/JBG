package Dashboard;

public class DataStore4 {

    private String year;
    private String month;
    private String count;


    public DataStore4(String year, String month, String count){
        this.year = new String(year);
        this.month = new String(month);
        this.count = new String(count);

    }

    public String getYear() {return year;}
    public void setYear(String year) {this.year=year;}

    public String getMonth() {return month;}
    public void setMonth(String month) {this.month=month;}

    public String getCount() {return count;}
    public void setCount(String count) {this.count=count;}



}
