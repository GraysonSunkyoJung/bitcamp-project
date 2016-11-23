package bitcamp.java89.ems.server.controller;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;

import bitcamp.java89.ems.server.Command;
import bitcamp.java89.ems.server.dao.BookDao;
import bitcamp.java89.ems.server.vo.Book;

public class BookViewController implements Command {
 
 private BookDao bookDao;
  

  public BookViewController() {
    bookDao = BookDao.getInstance();
  }
  
 
  public void service(HashMap<String, String> paramMap, PrintStream out) {
    ArrayList<Book> list = bookDao.getListByName(paramMap.get("name"));
    for (Book book : list) {
      out.println("--------------------------");
      out.printf("제목: %s\n", book.getName());
      out.printf("저자: %s\n", book.getAuthor());
      out.printf("가격: %d\n", book.getPrice());
      out.printf("쪽수: %d\n", book.getPage());
      out.printf("부록: %s\n", (book.isCd() ? "y" : "n"));
    }
  }
}
