
package bitcamp.java89.ems.server.controller;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import bitcamp.java89.ems.server.vo.Book;

public class BookController {
  private Scanner in;
  private PrintStream out;
  
  private String filename = "book-v1.6.data";
  static ArrayList<Book> list;
  private boolean changed;


  public BookController(Scanner in, PrintStream out) {
    list = new ArrayList<Book>();
    this.in = in;
    this.out = out;

    this.load(); // 기존의 데이터 파일을 읽어서 ArrayList에 교재정보를 로딩한다.
  }

  public boolean isChanged() {
    return changed;
  }
 
  @SuppressWarnings("unchecked")
  private void load() {
    FileInputStream in0 = null;
    ObjectInputStream in = null;

    try {
      in0 = new FileInputStream(this.filename);
      in = new ObjectInputStream(in0);
      
      list = (ArrayList<Book>)in.readObject();

     } catch (EOFException e) {
       // 파일을 모두 읽었다.
     } catch (Exception e) {
       System.out.println("데이터 로딩 중 오류 발생!");
     } finally {
      try{
       in.close();
       in0.close();
      } catch (Exception e) {
         // close하다가 예외 발생하면 무시한다.
      }
    }
  }

  public void save() throws Exception {
    FileOutputStream out0 = new FileOutputStream(this.filename);
    ObjectOutputStream out = new ObjectOutputStream(out0);
    
    out.writeObject(list);
    
    changed = false;
    
    out.close();
    out0.close();
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

   // add?name=myBook&author=she&price=1010&page=1111&cd=y  add?name=myBook2&author=she&price=1010&page=1111&cd=y 
  private void doAdd(String params) {
    String[] values = params.split("&");
    HashMap<String,String> paramMap = new HashMap<>();
    
    for (String value : values) {
      String[] kv = value.split("=");
      paramMap.put(kv[0], kv[1]);
    }
    Book book = new Book();
    book.setName(paramMap.get("name"));
    book.setAuthor(paramMap.get("author"));
    book.setPrice(Integer.parseInt(paramMap.get("price")));
    book.setPage(Integer.parseInt(paramMap.get("page")));
    book.setCd(paramMap.get("cd").equals("y") ? true : false);
    
    if (existName(book.getName())) {
      out.println("같은 제목이 존재합니다. 등록을 취소합니다.");
      return;
    }
    
    list.add(book);
    changed = true;
    out.println("등록하였습니다.");
  }
  
  private boolean existName(String name) {
    for (Book book : list) {
      if (book.getName().toLowerCase().equals(name.toLowerCase())) {
        return true;
      }
    }
    return false;
  }

  private void doView(String params) {
    String[] kv = params.split("=");

    for (Book book : list) {
      if (book.getName().equals(kv[1])) {
        out.println("--------------------------");
        out.printf("제목: %s\n", book.getName());
        out.printf("저자: %s\n", book.getAuthor());
        out.printf("가격: %d\n", book.getPrice());
        out.printf("쪽수: %d\n", book.getPage());
        out.printf("부록: %b\n", book.isCd());
      }
    }
  }

  private void doList() {
    for (Book book : list) {
      out.printf("%s, %s, %d, %d, %s\n",
        book.getName(),
        book.getAuthor(),
        book.getPrice(),
        book.getPage(),
        ((book.isCd())? "y" : "n"));
    }
  }

  private void doDelete(String params) {
    String[] kv = params.split("=");
    
    for (int i = 0; i < list.size(); i++) {
      Book book = list.get(i);
      if (book.getName().equals(kv[1])) {
        list.remove(i);
        changed = true;
        out.println("해당 데이터 삭제완료하였습니다.");
        break;
      }
    }
  }

  // update?name=myBook&author=she&price=2222&page=1111&cd=y
  private void doUpdate(String params) {
    String[] values = params.split("&");
    HashMap<String,String> paramMap = new HashMap<>();
    
    for (String value : values) {
      String[] kv = value.split("=");
      paramMap.put(kv[0], kv[1]);
    }
    for (Book book : list) {
      if (book.getName().equals(paramMap.get("name"))) {
        book.setAuthor(paramMap.get("author"));
        book.setPrice(Integer.parseInt(paramMap.get("price")));
        book.setPage(Integer.parseInt(paramMap.get("page")));
        book.setCd(paramMap.get("cd").equals("y") ? true : false);
        changed = true;
        out.println("변경하였습니다.");
        return;
      }
    }
    out.println("책을 찾지 못했습니다.");
  }
}
