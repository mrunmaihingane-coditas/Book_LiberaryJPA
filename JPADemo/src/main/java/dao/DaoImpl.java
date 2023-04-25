package dao;

import model.Book;
import model.Library;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DaoImpl implements Dao{
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("rt");


    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    @Override
    public void insertBook() throws IOException, ParseException {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        System.out.println("enter Book name");
        String bookname=br.readLine();

        System.out.println("Enter date of publication (yyyy-MM-dd):");
        String dateString = br.readLine();
        Date datepulish = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);

        System.out.println("enter publisher ");
        String name_publisher=br.readLine();

        System.out.println("enter Price ");
        int Book_price= Integer.parseInt(br.readLine());

        Book book=new Book(bookname,datepulish,name_publisher,Book_price);

        entityManager.persist(book);

        entityManager.getTransaction().commit();
        entityManager.close();



    }
    public void insertLiberary() throws IOException, ParseException {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        System.out.println("enter Libary name");
        String libname=br.readLine();

        Library library = new Library();
        library.setLibary_name(libname);

        entityManager.persist(library);

        entityManager.getTransaction().commit();
        entityManager.close();



    }

    @Override
    public void updateBook(int id) throws IOException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Book student = entityManager.find(Book.class, id);

        System.out.println("Enter Book name you want to update");
        String name = br.readLine();

        student.setBook_name(name);

        entityManager.persist(student);

        entityManager.getTransaction().commit();
        entityManager.close();

    }

    @Override
    public void deleteBook(int id) throws IOException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Book product = entityManager.find(Book.class,id);



        entityManager.remove(product);

        entityManager.getTransaction().commit();
        entityManager.close();


    }

    @Override
    public void ShowBook(int id) throws IOException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Book product = entityManager.find(Book.class,id);


        System.out.println(product);
        entityManager.persist(product);

        entityManager.getTransaction().commit();
        entityManager.close();


    }
    public void insertBookandstudentNumber() throws IOException {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();





        System.out.println("Enter Library Id in which you when add book ");
        int userFetchId = Integer.parseInt(br.readLine());
        Library user = entityManager.find(Library.class, userFetchId);

        List<Book> productList = new ArrayList<>();
        System.out.println("Enter Number Books to add ");
        int count = Integer.parseInt(br.readLine());
        for (int i = 0; i < count; i++) {

            System.out.println("Enter  Book Id ");

            int bid = Integer.parseInt(br.readLine());


            Book book1 = entityManager.find(Book.class, bid);


            book1.setLibrary(user);


            productList.add(book1);
            entityManager.persist(book1);

        }
        System.out.println("Add in list");
        user.setBookList(productList);
        entityManager.persist(user);

        entityManager.getTransaction().commit();
        entityManager.close();

    }
    public List<Book> getBooksByPriceGreaterThan(int price) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> cq = cb.createQuery(Book.class);
        Root<Book> bookRoot = cq.from(Book.class);

        cq.select(bookRoot).where(cb.greaterThan(bookRoot.<Comparable>get("book_price"),price));
        TypedQuery<Book> query = entityManager.createQuery(cq);

        List<Book> resultList = query.getResultList();
        for(Book book :resultList){
            System.out.println(book.getBook_id()+" : "+book.getBook_price()+" : "+book.getBook_name());
        }


        entityManager.getTransaction().commit();
        entityManager.close();

        return resultList;
    }
    public void getBooksByPriceBetween() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> cq = cb.createQuery(Book.class);
        Root<Book> bookRoot = cq.from(Book.class);

        cq.select(bookRoot).where(cb.between(bookRoot.<Comparable>get("book_price"),200,500));
        TypedQuery<Book> query = entityManager.createQuery(cq);

        List<Book> resultList = query.getResultList();
        for(Book book :resultList){
            System.out.println(book.getBook_id()+" : "+book.getBook_price()+" : "+book.getBook_name());
        }


        entityManager.getTransaction().commit();
        entityManager.close();


    }
    public void BookNameStartingWithB() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> cq = cb.createQuery(Book.class);
        Root<Book> root = cq.from(Book.class);

        cq.where(cb.like(root.<String>get("book_name"), "B%"));

        List<Book> animals = entityManager.createQuery(cq).getResultList();

        if (animals.isEmpty()) {
            System.out.println("No book found with names starting with 'L'.");
        } else {
            System.out.println("Book with names starting with 'B':");
            for (Book book : animals) {
                System.out.println(" " + book.getBook_name() + ", date: " + book.getDate_publisher());
            }
        }

        entityManager.getTransaction().commit();
        entityManager.close();
    }
    public void printBooksByPublisher(String publisherName) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> cq = cb.createQuery(Book.class);
        Root<Book> bookRoot = cq.from(Book.class);

        cq.select(bookRoot).where(cb.equal(bookRoot.get("publisher_name"), publisherName));
        TypedQuery<Book> query = entityManager.createQuery(cq);

        List<Book> resultList = query.getResultList();
        for (Book book : resultList) {
            System.out.println("Book ID: " + book.getBook_id());
            System.out.println("Book Name: " + book.getBook_name());
            System.out.println("Publisher Name: " + book.getPublisher_name());
            System.out.println("Book Price: " + book.getBook_price());
            System.out.println("--------------------");
        }

        entityManager.getTransaction().commit();
        entityManager.close();
    }






}
