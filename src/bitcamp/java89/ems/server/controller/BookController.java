
package bitcamp.java89.ems.server.controller;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import bitcamp.java89.ems.server.dao.BookDao;
import bitcamp.java89.ems.server.vo.Book;

public class BookController {
  private Scanner in;
  private PrintStream out;
  
  private BookDao bookDao;


  public BookController(Scanner in, PrintStream out) {
    this.in = in;
    this.out = out;

    bookDao = BookDao.getInstance();
  }

  public void save() throws Exception {
    if (bookDao.isChanged()) {
      bookDao.save();
    }
  }
    
  public boolean service() {
    while (true) {
      out.println("교재관리>");
      out.println();
      
      String[] commands = in.nextLine().split("\\?");

      try {
        switch (commands[0]) {
        case "add": this.doAdd(commands[1]); break;
        case "list": this.doList(); break;
        case "view": this.doView(commands[1]); break;
        case "delete": this.doDelete(commands[1]); break;
        case "update": this.doUpdate(commands[1]); break;
        case "main": return true;
        case "quit": return false;
        default:
          System.out.println("지원하지 않는 명령어 입니다.");
        }
      } catch (IndexOutOfBoundsException e) {
        out.println("인덱스가 유효하지 않습니다.");
      } catch (Exception e) {
        out.println("인덱스 값이 잘못 되었거나, 실행중 오류가 발생했습니다.");
      }
    }
  }

   // add?name=myBook&author=you&price=1010&page=1111&cd=y  add?name=myBook2&author=she&price=1010&page=1111&cd=y 
  private void doAdd(String params) {
    String[] values = params.split("&");
    HashMap<String,String> paramMap = new HashMap<>();
    
    for (String value : values) {
      String[] kv = value.split("=");
      paramMap.put(kv[0], kv[1]);
    }
    
    if (bookDao.existName(paramMap.get("name"))) {
      out.println("같은 제목의 교재가 존재합니다. 등록을 취소합니다.");
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
  

  private void doView(String params) {
    String[] kv = params.split("=");

    ArrayList<Book> list = bookDao.getListByName(kv[1]);
    for (Book book : list) {
      out.println("--------------------------");
      out.printf("제목: %s\n", book.getName());
      out.printf("저자: %s\n", book.getAuthor());
      out.printf("가격: %d\n", book.getPrice());
      out.printf("쪽수: %d\n", book.getPage());
      out.printf("부록: %s\n", (book.isCd() ? "y" : "n"));
    }
  }

  private void doList() {
    ArrayList<Book> list = bookDao.getList();
    for (Book book : list) {
      out.printf("%s, %s, %d, %d, %s\n",
        book.getName(),
        book.getAuthor(),
        book.getPrice(),
        book.getPage(),
        (book.isCd() ? "y" : "n"));
    }
  }

  private void doDelete(String params) {
    String[] kv = params.split("=");
    
    if (!bookDao.existName(kv[1])) {
      out.println("해당 데이터가 없습니다.");
      return;
    }
    
    bookDao.delete(kv[1]);
    out.println("해당 데이터를 삭제 완료하였습니다.");
  }

  // update?name=myBook&author=she&price=2222&page=1111&cd=y
  private void doUpdate(String params) {
    String[] values = params.split("&");
    HashMap<String,String> paramMap = new HashMap<>();
    
    for (String value : values) {
      String[] kv = value.split("=");
      paramMap.put(kv[0], kv[1]);
    }
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
