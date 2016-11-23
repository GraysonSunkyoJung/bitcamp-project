
package bitcamp.java89.ems.server.dao;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import bitcamp.java89.ems.server.vo.Book;

public class BookDao {
  static BookDao obj;
  private String filename = "book-v1.8.data";
  static ArrayList<Book> list;

  public static BookDao getInstance() {
    if (obj == null) {
      obj = new BookDao();
    }
    return obj;
  }
  
  public BookDao() {
    this.load();
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
       list = new ArrayList<>();
       
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
    
    
    out.close();
    out0.close();
  }
    
  public ArrayList<Book> getList() {
    return this.list;
  }

   
  public ArrayList<Book> getListByName(String name) {
    ArrayList<Book> results = new ArrayList<>();
    for (Book book : list) {
      if (book.getName().equals(name)) {
        results.add(book);
      }
    }
    return results;
  }
    
  synchronized public void insert(Book book) {
    list.add(book);
    try {this.save();} catch (Exception e) {}
  }
  
  synchronized public void update(Book book) {
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).getName().equals(book.getName())) {
        list.set(i, book);
        try {this.save();} catch (Exception e) {}
        return;
      }
    }
  }
  
  synchronized public void delete(String name) {
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).getName().equals(name)) {
        list.remove(i);
        try {this.save();} catch (Exception e) {}
        return;
      }
    }
  }
  
  public boolean existName(String name) {
    for (Book book : list) {
      if (book.getName().toLowerCase().equals(name.toLowerCase())) {
        return true;
      }
    }
    return false;
  }
}
