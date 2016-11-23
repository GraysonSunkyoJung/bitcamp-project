package bitcamp.java89.ems.server.controller;

import java.io.PrintStream;
import java.util.HashMap;

import bitcamp.java89.ems.server.Command;
import bitcamp.java89.ems.server.dao.BookDao;
import bitcamp.java89.ems.server.vo.Book;

public class BookAddController implements Command {
 
 private BookDao bookDao;
  

  public BookAddController() {
    bookDao = BookDao.getInstance();
  }
  
 
  public void service(HashMap<String, String> paramMap, PrintStream out) {
    
    if (bookDao.existName(paramMap.get("name"))) {
      out.println("같은 제목이 존재합니다. 등록을 취소합니다.");
      return;
    }
    
    Book book = new Book();
    book.setName(paramMap.get("name"));
    book.setAuthor(paramMap.get("author"));
    book.setPrice(Integer.parseInt(paramMap.get("price")));
    book.setPage(Integer.parseInt(paramMap.get("page")));
    book.setCd(paramMap.get("cd").equals("y") ? true : false);
    
    bookDao.insert(book);
    out.println("등록하였습니다.");
  }
}
