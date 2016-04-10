/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.childtracker.controller;


import com.childtracker.facade.ParentFacade;
import com.childtracker.model.ParentModel;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Dinesh Karpe <contact@dineshkarpe.me>
 */
@Named
@RequestScoped
public class UserLogin {

    private ParentModel parentModel;

    @Inject
    private UserSession userSession;

    @EJB
    private ParentFacade parentFacade;


    @PostConstruct
    private void init() {
        if (userSession.isLogin()) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("/dashboard");
            } catch (IOException ex) {
                Logger.getLogger(UserLogin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public ParentModel getSelected() {

        if (parentModel == null) {
            parentModel = new ParentModel();
        }
        return parentModel;
    }

    public void login() {
        ParentModel validateUser = parentFacade.validateUser(parentModel.getContactNo(), parentModel.getPassword());
        if (validateUser == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid contact no or paswword", "PrimeFaces Rocks."));
        } else {
            try {
                userSession.setContactNo(validateUser.getContactNo());
                userSession.setLogin(true);
                FacesContext.getCurrentInstance().getExternalContext().redirect("/dashboard");
            } catch (IOException ex) {
                Logger.getLogger(UserLogin.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
    
    public void logout() {
        
        if(userSession.isLogin()){
            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        }
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/");
        } catch (IOException ex) {
            Logger.getLogger(UserLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
//        try {
//            if (userSession.isLogin()) {
//                FacesContext context = FacesContext.getCurrentInstance();
//                HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
//                try {
//                    request.logout();
//                    
////                FacesContext.getCurrentInstance().getExternalContext().redirect("/");
//                    
//                FacesContext.getCurrentInstance().getExternalContext().redirect("/");
//                } catch (ServletException e) {
//
//                    context.addMessage(null, new FacesMessage("Logout failed."));
//                }
//            }
//
//        } catch (IOException ex) {
//            Logger.getLogger(UserLogin.class.getName()).log(Level.SEVERE, null, ex);
//        }

    }

}
