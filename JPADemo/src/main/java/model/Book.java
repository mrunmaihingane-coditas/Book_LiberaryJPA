package model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int book_id;

    private String book_name;

    private Date date_publisher;

    private String publisher_name;

    private int book_price;



    public Book() {
    }



    public Book(String book_name, Date date_publisher, String publisher_name, int book_price, Library library) {
        this.book_name = book_name;
        this.date_publisher = date_publisher;
        this.publisher_name = publisher_name;
        this.book_price = book_price;
        this.library = library;
    }

    public Book(String book_name, Date date_publisher, String publisher_name, int book_price) {
        this.book_name = book_name;
        this.date_publisher = date_publisher;
        this.publisher_name = publisher_name;
        this.book_price = book_price;
    }

    public int getBook_price() {
        return book_price;
    }

    public void setBook_price(int book_price) {
        this.book_price = book_price;
    }

    @ManyToOne
    private Library library;

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public Date getDate_publisher() {
        return date_publisher;
    }

    public void setDate_publisher(Date date_publisher) {
        this.date_publisher = date_publisher;
    }

    public String getPublisher_name() {
        return publisher_name;
    }

    public void setPublisher_name(String publisher_name) {
        this.publisher_name = publisher_name;
    }

    public Library getLibrary() {
        return library;
    }

    public void setLibrary(Library library) {
        this.library = library;
    }

    @Override
    public String toString() {
        return "Book{" +
                "book_id=" + book_id +
                ", book_name='" + book_name + '\'' +
                ", date_publisher=" + date_publisher +
                ", publisher_name='" + publisher_name + '\'' +
                ", library=" + library +
                '}';
    }
}
