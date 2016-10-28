package bitcamp.java89.ems;

import java.util.Scanner;

public class BookController {
  Book[] books = new Book[100];
  int length = 0;
  Scanner keyScan;

  public BookController(Scanner keyScan) {
    this.keyScan = keyScan;
  }

  void doAdd() {
    while (length < this.books.length){
      Book b1 = new Book();
      System.out.print("제목? ");
      b1.name = this.keyScan.nextLine();

      System.out.print("저자? ");
      b1.author = this.keyScan.nextLine();

      System.out.print("가격? ");
      b1.price = Integer.parseInt(this.keyScan.nextLine());

      System.out.print("쪽수? ");
      b1.page = Integer.parseInt(this.keyScan.nextLine());

      System.out.print("부록(y/n)? ");
      b1.cd = this.keyScan.nextLine().equals("y") ? true : false;

      this.books[length++] = b1;

      System.out.print("계속 입력하시겠습니까(y/n)?");
      if (!keyScan.nextLine().equals("y"))
        break;

    }
  }
  void doView() {
    System.out.print("조회할 책의 제목은?");
    String name = this.keyScan.nextLine().toLowerCase();
    for (int i = 0; i < this.length; i++) {
      if(this.books[i].name.toLowerCase().equals(name)){
        System.out.printf("제목: %s\n", this.books[i].name);
        System.out.printf("저자: %s\n", this.books[i].author);
        System.out.printf("가격: %d\n", this.books[i].price);
        System.out.printf("쪽수: %d\n", this.books[i].page);
        System.out.printf("부록: %b\n", this.books[i].cd);
        break;
      }
    }
  }
  void doList() {
    for (int i = 0; i <this.length; i++) {
      Book b1 = this.books[i];
      System.out.printf("%s, %s, %d, %d, %b\n",
        b1.name,
        b1.author,
        b1.price,
        b1.page,
        ((b1.cd)? "y" : "n"));
    }
  }
}
