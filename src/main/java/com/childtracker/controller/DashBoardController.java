/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.childtracker.controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

/**
 *
 * @author Dinesh Karpe <contact@dineshkarpe.me>
 */

@Named
@RequestScoped
public class DashBoardController {
    
    
    @Inject
    private UserSession userSession;
    
    private MapModel simpleModel;
    
    @PostConstruct
    public void init() {
        if (userSession.isLogin()) {
            
        simpleModel = new DefaultMapModel();
          
        //Shared coordinates
        LatLng coord1 = new LatLng(19.285459, 73.05510090000007);
          
        //Basic marker
        simpleModel.addOverlay(new Marker(coord1, "Konyaalti"));
        
        } else {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("/");
            } catch (IOException ex) {
                Logger.getLogger(DashBoardController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public UserSession getUserSession() {
        return userSession;
    }

    public void setUserSession(UserSession userSession) {
        this.userSession = userSession;
    }
    
     public MapModel getSimpleModel() {
        return simpleModel;
    }
    
    
}
