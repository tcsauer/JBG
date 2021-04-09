package Jobs;

import javafx.scene.image.Image;

import javax.swing.*;

public class Jobs {

    private int JobID;
    private byte[] jobSketch;
    private String jobType;
    private String jobCost;
    private String jobStatus;
    private String dateStart;
    private String dateComplete;
    private String paymentType;
    private String cust_lname;
    private String phone;

    public Jobs(byte[] jobSketch, String jobType, String jobCost, String jobStatus, String dateStart, String dateComplete, String paymentType){
        this.jobSketch = jobSketch;
        this.jobType = new String(jobType);
        this.jobCost = new String(jobCost);
        this.jobStatus = new String(jobStatus);
        this.dateStart = new String(dateStart);
        this.dateComplete = new String(dateComplete);
        this.paymentType = new String(paymentType);
    }

    public Jobs(int JobID, byte[] jobSketch, String jobType, String jobCost, String jobStatus, String dateStart, String dateComplete, String paymentType){
        this.JobID = JobID;
        this.jobSketch = jobSketch;
        this.jobType = new String(jobType);
        this.jobCost = new String(jobCost);
        this.jobStatus = new String(jobStatus);
        this.dateStart = new String(dateStart);
        this.dateComplete = new String(dateComplete);
        this.paymentType = new String(paymentType);
    }
    public Jobs(int JobID, String cust_lname, String jobType, String jobCost, String jobStatus, String dateStart, String dateComplete, String paymentType){
        this.JobID = JobID;
        this.cust_lname = new String(cust_lname);
        this.jobType = new String(jobType);
        this.jobCost = new String(jobCost);
        this.jobStatus = new String(jobStatus);
        this.dateStart = new String(dateStart);
        this.dateComplete = new String(dateComplete);
        this.paymentType = new String(paymentType);
    }

    public Jobs(int JobID, String jobType, String jobCost, String jobStatus, String dateStart, String dateComplete, String paymentType){
        this.JobID = JobID;
        this.jobType = new String(jobType);
        this.jobCost = new String(jobCost);
        this.jobStatus = new String(jobStatus);
        this.dateStart = new String(dateStart);
        this.dateComplete = new String(dateComplete);
        this.paymentType = new String(paymentType);
    }

    public int getJobID(){return JobID;}
    public void setJobID(int JobID){this.JobID = JobID;}
    public byte[] getJobSketch() {return jobSketch;}
    //public void setJobSketch(String jobSketch) {this.jobSketch=jobSketch;}
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

    public String getCust_lname() {
        return cust_lname;
    }
}
