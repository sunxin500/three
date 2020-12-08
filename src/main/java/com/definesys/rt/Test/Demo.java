package com.definesys.rt.Test;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "DEMO")
public class Demo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "phone_seq")
    @SequenceGenerator(name = "phone_seq",sequenceName = "phone_seq",allocationSize = 1)
    @Column(name = "ID")
    private long id;

    @Column(name = "JOBNUMBER")
    private String jobNumber;

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "NAME")
    private String name;

    @Column(name = "POST")
    private String post;

    public Demo(){}


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getJobNumber() {
        return jobNumber;
    }

    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    @Override
    public String toString() {
        return "Demo{" +
                "id=" + id +
                ", jobNumber='" + jobNumber + '\'' +
                ", phone='" + phone + '\'' +
                ", name='" + name + '\'' +
                ", post='" + post + '\'' +
                '}';
    }
}
