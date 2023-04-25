package web;
import dao.DaoImpl;
import model.Book;
import model.Library;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.annotation.WebServlet;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;

public class main {
	public static void main(String[] args) throws IOException, SQLException, ParseException {
		System.out.println("BOOK MANAGEMENT SYSTEM");

		Scanner scanner = new Scanner(System.in);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		Book book = new Book();
		Library library = new Library();
		DaoImpl daoimpl=new DaoImpl();

		boolean flag = true;
		while (flag) {
			System.out.println("1.Insert Book&Libary ");
			System.out.println("5.Insert Book ");
			System.out.println("3.Update Book");
			System.out.println("4.Delete Book ");
			System.out.println("5.Show Book");
			System.out.println("6.Insert library  ");
			System.out.println("7.All books who price greter than enter value");
			System.out.println("8.All books who between 200 and 500");
			System.out.println("9.All books starting with B");
			System.out.println("10.All book according to publisher_name");


			int choice = scanner.nextInt();
			switch (choice) {
				case 5:
					daoimpl.insertBook();
					break;
				case 2:
					System.out.println("Enter id to update");
					int id = Integer.parseInt(br.readLine());
					daoimpl.updateBook(id);
					break;
				case 3:
					System.out.println("Enter id to delete");
					int id1 = Integer.parseInt(br.readLine());
					daoimpl.deleteBook(id1);
					break;
				case 4:
					System.out.println("Enter id to show book");
					int id2 = Integer.parseInt(br.readLine());
					daoimpl.ShowBook(id2);
					break;
				case 1:
					daoimpl.insertBookandstudentNumber();
					break;
				case 6:
					daoimpl.insertLiberary();
					break;
				case 7:
					System.out.println("Enter Price which want : ");
					daoimpl.getBooksByPriceGreaterThan(Integer.parseInt(br.readLine()));
					break;
				case 8:
					daoimpl.getBooksByPriceBetween();
					break;
				case 9:
					daoimpl.BookNameStartingWithB();
					break;
				case 10:
					System.out.println("Enter Pushlisher which want : ");
					daoimpl.printBooksByPublisher(br.readLine());
					break;

				case 0:
					flag = false;
					System.out.println("Exiting...");
					break;
				default:
					System.out.println("Invalid choice. Try again.");
			}
		}
	}
}




