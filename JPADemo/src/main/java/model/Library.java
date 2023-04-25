package model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
@Entity
public class Library {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Libery_id;

    private String Libary_name;


    @OneToMany(mappedBy = "library",cascade = CascadeType.ALL)
    private List<Book> bookList;

    public int getLibery_id() {
        return Libery_id;
    }

    public void setLibery_id(int libery_id) {
        Libery_id = libery_id;
    }

    public String getLibary_name() {
        return Libary_name;
    }

    public void setLibary_name(String libary_name) {
        Libary_name = libary_name;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }
}
