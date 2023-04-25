package dao;

import java.io.IOException;
import java.text.ParseException;

public interface Dao {

    void insertBook() throws IOException, ParseException;


    void updateBook(int id) throws IOException ;



    void deleteBook(int id) throws IOException;

    void ShowBook(int id) throws IOException;


}
