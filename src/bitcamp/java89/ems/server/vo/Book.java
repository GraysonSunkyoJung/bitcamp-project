package bitcamp.java89.ems.server.vo;

import java.io.Serializable;

public class Book implements Serializable {
  private static final long serialVersionUID = 1L;
  
  protected String name;     //제목
  protected String author;   //저자
  protected int price;       //가격
  protected int page;        //쪽수
  protected boolean cd;      //부록CD여부


  public Book() {}

  public Book(String name, String author,int price, int page, boolean cd) {
    this.name = name;
    this.author = author;
    this.price = price;
    this.page = page;
    this.cd = cd;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public int getPage() {
    return page;
  }

  public void setPage(int page) {
    this.page = page;
  }

  public boolean isCd() {
    return cd;
  }

  public void setCd(boolean cd) {
    this.cd = cd;
  }

  @Override
  public String toString() {
    return "Book [name=" + name + ", author=" + author + ", price=" + price + ", page=" + page + ", cd=" + cd + "]";
  }

}
