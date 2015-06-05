/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.badway.db.test;

import net.badway.db.annotation.ManyToOne;
import net.badway.db.annotation.SqlField;
import net.badway.db.annotation.DbEntity;
import net.badway.db.annotation.Id;
import java.util.Date;

/**
 *
 * @author jonathan
 */
@DbEntity
public class User {
    @SqlField(sqlType = "INT")
    @Id(autoIncrement = true)
    private int id;
    
    @SqlField(sqlType = "VARCHAR(75)")
    private String login;
    
    @SqlField(sqlType = "VARCHAR(75)")
    private String password;
    
     @SqlField(sqlType = "DATETIME")
    private Date date;
     @ManyToOne
     private Country country;

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    
    
    
    
    
    
    
}
