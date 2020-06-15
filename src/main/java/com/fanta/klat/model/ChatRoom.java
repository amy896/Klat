package com.fanta.klat.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tbl_chat_room")
public class ChatRoom implements Serializable {
   
   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name="cr_num")
   private int crNum;
   
   @Column(name="cr_title")
   private String crTitle;
   
   public int getCrNum() {
      return crNum;
   }
   public void setCrNum(int crNum) {
      this.crNum = crNum;
   }
   public String getCrTitle() {
      return crTitle;
   }
   public void setCrTitle(String crTitle) {
      this.crTitle = crTitle;
   }
   @Override
   public String toString() {
      return "ChatRoom [crNum=" + crNum + ", crTitle=" + crTitle + "]";
   }
}