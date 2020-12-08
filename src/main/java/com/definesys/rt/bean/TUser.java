package com.definesys.rt.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "T_USER")
public class TUser implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "user_seq")
  @SequenceGenerator(name = "user_seq",sequenceName = "user_seq",allocationSize = 1)
  @Column(name = "USERID")
  private long userid;

  @Column(name = "USERNAME")
  private String username;

  @Column(name = "PASSWORD")
  private String password;

  @Column(name = "name")
  private String name;

  @Column(name = "ROLE")
  private String role;

  @Column(name = "VERSION_NUMBER")
  private String versionNumber;

  @Column(name = "CREATION_DATE")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  @CreatedDate
  private Date creationDate;

  @Column(name = "CREATE_BY")
  private String createBy;

  @Column(name = "LAST_UPDATE_DATE")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  @CreatedDate
  private Date lastUpdateDate;

  @Column(name = "LAST_UPDATE_BY")
  private String lastUpdateBy;

  private String textattribute1;
  private String textattribute2;
  private String textattribute3;
  private String textattribute4;
  private String textattribute5;
  private String textattribute6;
  private String textattribute7;
  private String textattribute8;
  private String textattribute9;
  private String textattribute10;

  public long getUserid() {
    return userid;
  }

  public void setUserid(long userid) {
    this.userid = userid;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public String getVersionNumber() {
    return versionNumber;
  }

  public void setVersionNumber(String versionNumber) {
    this.versionNumber = versionNumber;
  }

  public Date getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(Date creationDate) {
    this.creationDate = creationDate;
  }

  public String getCreateBy() {
    return createBy;
  }

  public void setCreateBy(String createBy) {
    this.createBy = createBy;
  }

  public Date getLastUpdateDate() {
    return lastUpdateDate;
  }

  public void setLastUpdateDate(Date lastUpdateDate) {
    this.lastUpdateDate = lastUpdateDate;
  }

  public String getLastUpdateBy() {
    return lastUpdateBy;
  }

  public void setLastUpdateBy(String lastUpdateBy) {
    this.lastUpdateBy = lastUpdateBy;
  }

  public String getTextattribute1() {
    return textattribute1;
  }

  public void setTextattribute1(String textattribute1) {
    this.textattribute1 = textattribute1;
  }

  public String getTextattribute2() {
    return textattribute2;
  }

  public void setTextattribute2(String textattribute2) {
    this.textattribute2 = textattribute2;
  }

  public String getTextattribute3() {
    return textattribute3;
  }

  public void setTextattribute3(String textattribute3) {
    this.textattribute3 = textattribute3;
  }

  public String getTextattribute4() {
    return textattribute4;
  }

  public void setTextattribute4(String textattribute4) {
    this.textattribute4 = textattribute4;
  }

  public String getTextattribute5() {
    return textattribute5;
  }

  public void setTextattribute5(String textattribute5) {
    this.textattribute5 = textattribute5;
  }

  public String getTextattribute6() {
    return textattribute6;
  }

  public void setTextattribute6(String textattribute6) {
    this.textattribute6 = textattribute6;
  }

  public String getTextattribute7() {
    return textattribute7;
  }

  public void setTextattribute7(String textattribute7) {
    this.textattribute7 = textattribute7;
  }

  public String getTextattribute8() {
    return textattribute8;
  }

  public void setTextattribute8(String textattribute8) {
    this.textattribute8 = textattribute8;
  }

  public String getTextattribute9() {
    return textattribute9;
  }

  public void setTextattribute9(String textattribute9) {
    this.textattribute9 = textattribute9;
  }

  public String getTextattribute10() {
    return textattribute10;
  }

  public void setTextattribute10(String textattribute10) {
    this.textattribute10 = textattribute10;
  }

  @Override
  public String toString() {
    return "TUser{" +
            "userid=" + userid +
            ", username='" + username + '\'' +
            ", password='" + password + '\'' +
            ", name='" + name + '\'' +
            ", role='" + role + '\'' +
            ", versionNumber='" + versionNumber + '\'' +
            ", creationDate=" + creationDate +
            ", createBy='" + createBy + '\'' +
            ", lastUpdateDate=" + lastUpdateDate +
            ", lastUpdateBy='" + lastUpdateBy + '\'' +
            ", textattribute1='" + textattribute1 + '\'' +
            ", textattribute2='" + textattribute2 + '\'' +
            ", textattribute3='" + textattribute3 + '\'' +
            ", textattribute4='" + textattribute4 + '\'' +
            ", textattribute5='" + textattribute5 + '\'' +
            ", textattribute6='" + textattribute6 + '\'' +
            ", textattribute7='" + textattribute7 + '\'' +
            ", textattribute8='" + textattribute8 + '\'' +
            ", textattribute9='" + textattribute9 + '\'' +
            ", textattribute10='" + textattribute10 + '\'' +
            '}';
  }
}
