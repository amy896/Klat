package com.fanta.klat.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="tbl_chat_message")
public class ChatMessage implements Serializable {

   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name="cm_num")
   private int cmNum;
   
   @Column(name="cm_content")
   private String cmContent;
   
   @Column(name="cm_type")
   private String cmType;
   
   @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
   @Column(name="cm_write_date")
   private Date cmWriteDate;

   @Column(name="cr_num")
   private int crNum;
   
   @Column(name="m_num")
   private int mNum;
   
   public int getCmNum() {
      return cmNum;
   }
   public void setCmNum(int cmNum) {
      this.cmNum = cmNum;
   }
   public String getCmContent() {
      return cmContent;
   }
   public void setCmContent(String cmContent) {
      this.cmContent = cmContent;
   }
   public String getCmType() {
      return cmType;
   }
   public void setCmType(String cmType) {
      this.cmType = cmType;
   }
   public Date getCmWriteDate() {
      return cmWriteDate;
   }
   public void setCmWriteDate(Date cmWriteDate) {
      this.cmWriteDate = cmWriteDate;
   }
   public int getCrNum() {
      return crNum;
   }
   public void setCrNum(int crNum) {
      this.crNum = crNum;
   }
   public int getmNum() {
      return mNum;
   }
   public void setmNum(int mNum) {
      this.mNum = mNum;
   }
   
   @Override
   public String toString() {
      return "ChatMessage [cmNum=" + cmNum + ", cmContent=" + cmContent + ", cmType=" + cmType + ", cmWriteDate="
            + cmWriteDate + ", crNum=" + crNum + ", mNum=" + mNum + "]";
   }
}