/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import tools.BirthdayConverter;

/**
 *
 * @author Melnikov
 */
@Entity
public class Author implements Serializable{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstname;
    private String lastname;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date birthday;
    @OneToMany
    private List<Book> books;
    @Transient
    private String strBirthday;
    

    public Author() {
        books = new ArrayList<>();
        
    }

    public Author(String firstname, String lastname, Date birthday, List<Book> books) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthday = birthday;
        this.books = books;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    
    @Override
    public String toString() {
                
    BirthdayConverter bc = new BirthdayConverter();
    bc.setBirthday(birthday);
        return "Author{" 
                + "firstname=" + firstname 
                + ", lastname=" + lastname 
                + ", birthday=" + bc.getBirthDay()
                +"." +bc.getBirthMonth()
                +"." +bc.getBirthYear()
                + '}';
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    
    public String getStrBirthday(){
        BirthdayConverter bc = new BirthdayConverter();
        bc.setBirthday(birthday);
        return bc.getBirthDay()+"." +bc.getBirthMonth()+"." +bc.getBirthYear();
    }
    
    public void setStrBirthday(String strBirthday){
        this.strBirthday=strBirthday;
    }
    
   

    
}
