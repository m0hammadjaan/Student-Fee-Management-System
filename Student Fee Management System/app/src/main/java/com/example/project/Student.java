package com.example.project;

public class Student {

    private  int id;
   private String name;
   private  String address;
   private String  contact;
   private  String email;
   private  String password;

    public Student(String nm, String add, String con, String em, String ps) {

        this.name=nm;
        this.address=add;
        this.contact=con;
        this.email=em;
        this.password=ps;
    }

    public Student(String name, String address, String contact, String email) {
        this.name=name;
        this.address=address;
        this.contact=contact;
        this.email=email;
    }

    public Student() {

    }

    public Integer getid(){return id;}
    public String getName(){
       return  name;
   }
    public String getAddress(){
        return  address;
    }
    public String getContact(){
        return  contact;
    }
    public String getEmail(){
        return email;
    }
    public String getPassword(){
        return  password;
    }

    public  void  setId(Integer id){this.id = id;}

    public void setName(String name) { this.name = name; }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
