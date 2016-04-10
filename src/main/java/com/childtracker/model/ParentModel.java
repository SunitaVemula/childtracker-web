/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.childtracker.model;

/**
 *
 * @author Dinesh Karpe <contact@dineshkarpe.me>
 */
public class ParentModel {
    
   private String contactNo;

    public ParentModel(String contactno) {
        this.contactNo = contactno;
    }

    public ParentModel() {
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
   private String password;
   
   
}
