/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.childtracker.facade;

import com.childtracker.entity.Parent;
import com.childtracker.model.ParentModel;
import javax.ejb.Stateless;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Dinesh Karpe <contact@dineshkarpe.me>
 */
@Stateless
public class ParentFacade extends AbstractFacade<Parent> {

    @PersistenceContext(unitName = "com.onroad_ChildTracker_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ParentFacade() {
        super(Parent.class);
    }

    public String sigunUpNewParent(final ParentModel parentModel) {
        try {
            Parent parent = new Parent();
            parent.setId(1);
            parent.setContactno(parentModel.getContactNo());
            parent.setPassword(parentModel.getPassword());
            create(parent);
            return "TRUE";
        } catch (EntityExistsException e) {
            System.out.println(e.getMessage());
            return e.getMessage();
        }
    }


   public ParentModel validateUser(final String contactNo, final String password) {
        try {
            Query query = em.createNamedQuery("Parent.findByContactno");
            query.setParameter("contactno", contactNo);
            Parent parent = (Parent) query.getSingleResult();
              if (password.equals(parent.getPassword())) {
                return new ParentModel(parent.getContactno());
              } else {
                return null;
            }
            
//            if (!PasswordEncryption.encreptPassword(password).equals(user.getPassword())) {
//                return null;
//            } else {
//                return new UserDao(user.getMobileno(),user.getType()==3);
//            }
        } catch (NoResultException exception) {
            return null;
        }
    }
}
