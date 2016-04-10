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
import javax.inject.Named;

/**
 *
 * @author Dinesh Karpe <contact@dineshkarpe.me>
 */
@Named
@RequestScoped
public class ParentController {

    @EJB
    private ParentFacade parentFacade;

    private ParentModel parentModel;

    @PostConstruct
    private void init() {
        parentModel = new ParentModel();
    }

    public void signUp() {
        switch (parentFacade.sigunUpNewParent(getParentModel())) {

            case "TRUE": {
                try {
                    FacesContext.getCurrentInstance().getExternalContext().redirect("/");
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucessfully Register", "PrimeFaces Rocks."));
                    parentModel = null;
                   
                } catch (IOException ex) {
                    Logger.getLogger(ParentController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            break;
            case "":
                break;

        }
    }

    public ParentModel getParentModel() {
        return parentModel;
    }

    public void setParentModel(ParentModel parentModel) {
        this.parentModel = parentModel;
    }

}
