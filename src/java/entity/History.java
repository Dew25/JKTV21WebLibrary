/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.CascadeType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author Melnikov
 */
@Entity
public class History implements Serializable{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne()
    private Reader reader;
    @OneToOne(cascade = {CascadeType.MERGE})
    private Book book;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date takeOnBook;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date returnBook;

    public History() {
    }

    public History(Reader reader, Book book, Date takeOnBook, Date returnBook) {
        this.reader = reader;
        this.book = book;
        this.takeOnBook = takeOnBook;
        this.returnBook = returnBook;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public Date getReturnBook() {
        return returnBook;
    }

    public void setReturnBook(Date returnBook) {
        this.returnBook = returnBook;
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Date getTakeOnBook() {
        return takeOnBook;
    }

    public void setTakeOnBook(Date takeOnBook) {
        this.takeOnBook = takeOnBook;
    }

    @Override
    public String toString() {
        return "History{" 
                + "reader=" + reader 
                + ", book=" + book 
                + ", takeOnBook=" + takeOnBook 
                + ", returnBook=" + returnBook 
                + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.id);
        hash = 37 * hash + Objects.hashCode(this.reader);
        hash = 37 * hash + Objects.hashCode(this.book);
        hash = 37 * hash + Objects.hashCode(this.takeOnBook);
        hash = 37 * hash + Objects.hashCode(this.returnBook);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final History other = (History) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.reader, other.reader)) {
            return false;
        }
        if (!Objects.equals(this.book, other.book)) {
            return false;
        }
        if (!Objects.equals(this.takeOnBook, other.takeOnBook)) {
            return false;
        }
        if (!Objects.equals(this.returnBook, other.returnBook)) {
            return false;
        }
        return true;
    }

  
    
}
