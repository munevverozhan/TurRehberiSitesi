/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package controller;

import dao.usersDAO;
import entity.users;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.ValidatorException;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author munevver
 */
@Named(value = "usersBean")
@SessionScoped
public class usersBean implements Serializable {

    /**
     * Creates a new instance of usersBean
     */
    public usersBean() {

    }

    public boolean validatePassword(FacesContext context, UIComponent cmp, Object value) throws ValidatorException {

        String v = (String) value;
        if (v.isEmpty()) {
            throw new ValidatorException(new FacesMessage("Şifre alanı boş olamaz."));
        } else if (v.length() < 7) {
            throw new ValidatorException(new FacesMessage("Şifre alanı 8 karakterden kısa olamaz."));

        }
        return true;
    }

    private users entity;
    private usersDAO dao;
    private List<users> list;

    private int page = 1;
    private int pageSize = 4;
    private int pageCount;

    public void next() {
        if (this.page == pageCount)// sonraki sayfaya geçecek metod.
        {
            this.page = 1;
        } else {
            this.page++;
        }

    }

    public void previous() {// önceki sayfaya geçecek metod.
        if (this.page == 1) {
            this.page = this.getPageCount();
        } else {
            this.page--;
        }
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageCount() { // kaç tane sayfamız olduğunu bulacak olan metod.
        this.pageCount = (int) Math.ceil(this.getDao().count() / (double) pageSize);
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public users getEntity() {
        if (this.entity == null) {
            this.entity = new users();
        }
        return entity;
    }

    public void setEntity(users entity) {
        this.entity = entity;
    }

    public usersDAO getDao() {
        if (this.dao == null) {
            this.dao = new usersDAO();
        }
        return dao;
    }

    public void setDao(usersDAO dao) {
        this.dao = dao;
    }

    public List<users> getList() {
        this.list = this.getDao().getUsersList(page, pageSize);
        return list;
    }

    public void setList(List<users> list) {
        this.list = list;
    }

    public void createUsers() {

        this.getDao().createUsers(entity);
        this.entity = new users();

    }

    public void delete(users c) {
        this.getDao().delete(c);
    }

    public void update() {
        this.getDao().update(this.entity);
        this.entity = new users();
    }

    public void clear() {
        entity = new users();
    }

}
