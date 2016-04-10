/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.childtracker.urlrewriting;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author Dinesh Karpe <contact@dineshkarpe.me>
 */
@Named
@RequestScoped
public class PageMeta implements Serializable {

    private String title = "Welcome Dinesh Karpe Website"; //default
    private String locale = "en"; //default

    private String selectedMenu;

    @PostConstruct
    private void init() {
        selectedMenu = "home";
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        if (locale != null && !locale.isEmpty()) {
            this.locale = locale;
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title == null || title.isEmpty()) {
            return;
        }
        this.title = title;
    }

    public String getSelectedMenu() {
        return selectedMenu;
    }

    public void setSelectedMenu(String selectedMenu) {
        this.selectedMenu = selectedMenu;
    }

    public boolean isMenuSelected(String menuKey) {
        if (menuKey == null || menuKey.isEmpty()) {
            return false;
        }
        return selectedMenu.startsWith(menuKey);
    }
}
