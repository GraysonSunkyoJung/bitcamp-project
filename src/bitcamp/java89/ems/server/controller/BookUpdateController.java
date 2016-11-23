package bitcamp.java89.ems.server.controller;

import java.io.PrintStream;
import java.util.HashMap;

import bitcamp.java89.ems.server.Command;
import bitcamp.java89.ems.server.dao.BookDao;
import bitcamp.java89.ems.server.vo.Book;

public class BookUpdateController implements Command {
 
 private BookDao bookDao;
  

  public BookUpdateController() {
    bookDao = BookDao.getInstance();
  }
  
  
  public void service(HashMap<String, String> paramMap, PrintStream out) {
    
    if (!bookDao.existName(paramMap.get("name"))) {
      out.println("교재를 찾지 못했습니다.");
      return;
    }
    
    Book book = new Book();
    book.setName(paramMap.get("name"));
    book.setAuthor(paramMap.get("author"));
    book.setPrice(Integer.parseInt(paramMap.get("price")));
    book.setPage(Integer.parseInt(paramMap.get("page")));
    book.setCd(paramMap.get("cd").equals("y") ? true : false);
    bookDao.update(book);
    out.println("변경하였습니다.");
  }
}
