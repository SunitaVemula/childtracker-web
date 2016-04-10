/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.childtracker.controller;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Dinesh Karpe <contact@dineshkarpe.me>
 */
@Named
@SessionScoped
public class UserSession implements Serializable {


    private String contactNo;
    private boolean login=false;
   

    public String getContactNo() {
        return contactNo;
    }


    public boolean isLogin() {
        return login;
    }

    public void setLogin(boolean login) {
        this.login = login;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

}
