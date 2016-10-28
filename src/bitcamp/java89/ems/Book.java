package bitcamp.java89.ems;

public class Book {
  //인스턴스 변수
  String name;     //제목
  String author;   //저자
  int price;       //가격
  int page;        //쪽수
  boolean cd;      //부록CD여부


  public Book() {}

  public Book(String name, String author,int price, int page, boolean cd) {
    this.name = name;
    this.author = author;
    this.price = price;
    this.page = page;
    this.cd = cd;
  }
}
