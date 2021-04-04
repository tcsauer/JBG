package Jobs;

public class Jobs {

    private String jobSketch;
    private String jobType;
    private String jobCost;
    private String jobStatus;
    private String dateStart;
    private String dateComplete;
    private String paymentType;

    public Jobs(String jobSketch, String jobType, String jobCost, String jobStatus, String dateStart, String dateComplete, String paymentType){
        this.jobSketch = new String(jobSketch);
        this.jobType = new String(jobType);
        this.jobCost = new String(jobCost);
        this.jobStatus = new String(jobStatus);
        this.dateStart = new String(dateStart);
        this.dateComplete = new String(dateComplete);
        this.paymentType = new String(paymentType);
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
    public String getDateComplete() {return dateComplete;}
    public void setDateComplete(String dateComplete) {this.dateComplete=dateComplete;}
    public String getPaymentType() {return paymentType;}
    public void setPaymentType(String paymentType) {this.paymentType=paymentType;}
}
