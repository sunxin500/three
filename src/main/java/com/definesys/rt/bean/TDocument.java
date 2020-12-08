package com.definesys.rt.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "T_DOCUMENT")
//@Document(indexName = "t_doc",type = "_doc")
public class TDocument implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "document_seq")
  @SequenceGenerator(name = "document_seq",sequenceName = "document_seq",allocationSize = 1)
  @Column(name = "DOCUMENTID")
  private long documentid;

  @Column(name = "DOCUMENTNAME")
  private String documentname;

  @Column(name = "DDOCNAME")
  private String ddocname;

  @Column(name = "DID")
  private String did;

  @Column(name = "UPLOADDATE")
  @ApiModelProperty("创建时间")
//  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  @Field(type = FieldType.Date, format = DateFormat.custom,pattern ="yyyy-MM-dd HH:mm:ss")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
  @CreatedDate
  private Date uploaddate;

  @Column(name = "USERID")
  private long userid;

  @Column(name = "USERNAME")
  private String username;

  @Column(name = "VERSION_NUMBER")
  private String versionNumber;

  @Column(name = "CREATION_DATE")
//  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  @Field(type = FieldType.Date, format = DateFormat.custom,pattern ="yyyy-MM-dd HH:mm:ss")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
  @CreatedDate
  private Date creationDate;

  @Column(name = "CREATE_BY")
  private String createBy;

  @Column(name = "LAST_UPDATE_DATE")
//  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  @Field(type = FieldType.Date, format = DateFormat.custom,pattern ="yyyy-MM-dd HH:mm:ss")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
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


  public long getDocumentid() {
    return documentid;
  }

  public void setDocumentid(long documentid) {
    this.documentid = documentid;
  }

  public String getDdocname() {
    return ddocname;
  }

  public void setDdocname(String ddocname) {
    this.ddocname = ddocname;
  }

  public String getDid() {
    return did;
  }

  public void setDid(String did) {
    this.did = did;
  }

  public String getDocumentname() {
    return documentname;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setDocumentname(String documentname) {
    this.documentname = documentname;
  }

  public Date getUploaddate() {
    return uploaddate;
  }

  public void setUploaddate(Date uploaddate) {
    this.uploaddate = uploaddate;
  }

  public long getUserid() {
    return userid;
  }

  public void setUserid(long userid) {
    this.userid = userid;
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
    return "TDocument{" +
            "documentid=" + documentid +
            ", documentname='" + documentname + '\'' +
            ", ddocname='" + ddocname + '\'' +
            ", did='" + did + '\'' +
            ", uploaddate=" + uploaddate +
            ", userid=" + userid +
            ", username='" + username + '\'' +
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
