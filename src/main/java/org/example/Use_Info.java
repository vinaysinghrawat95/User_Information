package org.example;

import jakarta.persistence.*;

@Entity
@Table(name = "User_Info")
class User_Info {

    public User_Info() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column(name = "Name", length = 50,nullable = false)
    private String name;

    @Column(name = "Email", length = 100, nullable = true)
    private String emailId;

    @Column(name = "PhNo", length = 15, nullable = true)
    private String phoneNo;

    public void setId(int id) {this.id = id;}
    public int getId(){return id;}

    public void setName(String name){this.name = name;}
    public String getName(){return name;}

    public void setEmailId(String emailId){this.emailId = emailId;}
    public String getEmailId(){return emailId;}

    public void setPhoneNo(String phoneNo){this.phoneNo = phoneNo;}
    public String getPhoneNo(){return phoneNo;}

}

