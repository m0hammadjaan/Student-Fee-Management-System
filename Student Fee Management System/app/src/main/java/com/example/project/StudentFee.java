package com.example.project;

public class StudentFee {

    private Integer id;
    private String name;
    private Integer enrollment;
    private String  amount;
    private  String type;
    private  String date;

    public  Integer getId(){return id;}
    public String getName(){
        return  name;
    }
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

    public void setName(String name) { this.name = name; }

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
