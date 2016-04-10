/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.childtracker.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Dinesh Karpe <contact@dineshkarpe.me>
 */
@Entity
@Table(catalog = "childtracking", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"emailid"}),
    @UniqueConstraint(columnNames = {"contactno"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Parent.findAll", query = "SELECT p FROM Parent p"),
    @NamedQuery(name = "Parent.findById", query = "SELECT p FROM Parent p WHERE p.id = :id"),
    @NamedQuery(name = "Parent.findByParentname", query = "SELECT p FROM Parent p WHERE p.parentname = :parentname"),
    @NamedQuery(name = "Parent.findByAddress", query = "SELECT p FROM Parent p WHERE p.address = :address"),
    @NamedQuery(name = "Parent.findByEmailid", query = "SELECT p FROM Parent p WHERE p.emailid = :emailid"),
    @NamedQuery(name = "Parent.findByImie", query = "SELECT p FROM Parent p WHERE p.imie = :imie"),
    @NamedQuery(name = "Parent.findByContactno", query = "SELECT p FROM Parent p WHERE p.contactno = :contactno"),
    @NamedQuery(name = "Parent.findByPassword", query = "SELECT p FROM Parent p WHERE p.password = :password")})
public class Parent implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @Size(max = 45)
    @Column(length = 45)
    private String parentname;
    @Size(max = 45)
    @Column(length = 45)
    private String address;
    @Size(max = 45)
    @Column(length = 45)
    private String emailid;
    @Size(max = 45)
    @Column(length = 45)
    private String imie;
    @Size(max = 45)
    @Column(length = 45)
    private String contactno;
    @Size(max = 45)
    @Column(length = 45)
    private String password;

    public Parent() {
    }

    public Parent(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getParentname() {
        return parentname;
    }

    public void setParentname(String parentname) {
        this.parentname = parentname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmailid() {
        return emailid;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getContactno() {
        return contactno;
    }

    public void setContactno(String contactno) {
        this.contactno = contactno;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Parent)) {
            return false;
        }
        Parent other = (Parent) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.childtracker.entity.Parent[ id=" + id + " ]";
    }
    
}
