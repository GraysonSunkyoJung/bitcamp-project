package bitcamp.java89.ems.server.controller;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;

import bitcamp.java89.ems.server.Command;
import bitcamp.java89.ems.server.dao.BookDao;
import bitcamp.java89.ems.server.vo.Book;

public class BookListController implements Command {
 
 private BookDao bookDao;
  

  public BookListController() {
    bookDao = BookDao.getInstance();
  }
  
 
  public void service(HashMap<String, String> paramMap, PrintStream out) {
    ArrayList<Book> list = bookDao.getList();
    for (Book book : list) {
      out.printf("%s,%s,%d,%d, %s\n",
        book.getName(),
        book.getAuthor(),
        book.getPrice(),
        book.getPage(),
        (book.isCd() ? "y" : "n"));
    }
  }
}
