package com.example.project;

public class Fee {

    private  Integer id;
    private Integer  enrollment;
    private String  amount;
    private  String type;
    private  String date;


    public Fee() {

    }

    public Fee(int en, String am, String ty, String dt) {

        this.enrollment=en;
        this.amount=am;
        this.type=ty;
        this.date=dt;
    }

    public  Integer getId(){return id;}
    public Integer getEnrollment(){ return enrollment; }
    public String getAmount(){
        return amount;
    }
    public String getType(){
        return  type;
    }
    public String getDate(){
        return  date;
    }

    public  void setId(int id){this.id=id;}
    public void setEnrollment(Integer enrollment) {
        this.enrollment = enrollment;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
