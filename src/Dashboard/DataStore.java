package Dashboard;

public class DataStore {

    private String jobSketch;
    private String jobType;
    private String jobCost;
    private String jobStatus;
    private String dateStart;

    public DataStore(String jobSketch, String jobType, String jobCost, String jobStatus, String dateStart){
        this.jobSketch = new String(jobSketch);
        this.jobType = new String(jobType);
        this.jobCost = new String(jobCost);
        this.jobStatus = new String(jobStatus);
        this.dateStart = new String(dateStart);
    }

    public String getJobSketch() {return jobSketch;}
    public void setJobSketch(String jobSketch) {this.jobSketch=jobSketch;}
    public String getJobType() {return jobType;}
    public void setJobType(String jobType) {this.jobType=jobType;}
    public String getJobCost() {return jobCost;}
    public void setJobCost(String jobCost) {this.jobCost=jobCost;}
    public String getJobStatus() {return jobStatus;}
    public void setJobStatus(String jobStatus) {this.jobStatus=jobStatus;}
    public String getDateStart() {return dateStart;}
    public void setDateStart(String dateStart) {this.dateStart=dateStart;}
}
