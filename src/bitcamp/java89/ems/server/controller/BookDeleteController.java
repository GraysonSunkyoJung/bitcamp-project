package bitcamp.java89.ems.server.controller;

import java.io.PrintStream;
import java.util.HashMap;

import bitcamp.java89.ems.server.Command;
import bitcamp.java89.ems.server.dao.BookDao;

public class BookDeleteController implements Command {
 
 private BookDao bookDao;
  

  public BookDeleteController() {
    bookDao = BookDao.getInstance();
  }
  
 
  public void service(HashMap<String, String> paramMap, PrintStream out) {
    
    
    if (!bookDao.existName(paramMap.get("name"))) {
      out.println("해당 데이터가 없습니다.");
      return;
    }
    
    bookDao.delete(paramMap.get("name"));
    out.println("해당 데이터를 삭제 완료하였습니다.");
  }
}
