package bitcamp.java89.ems;

import java.util.Scanner;

public class BookController {
  //아래 인스턴스 변수들은 외부에서 사용할 일이 없기 때문에
  //private으로 접근을 제한한다.
  private Book[] books = new Book[100];
  private int length = 0;
  private Scanner keyScan;

  public BookController(Scanner keyScan) {
    this.keyScan = keyScan;
  }

  public void service() {
    loop:
    while (true) {
      System.out.print("교재관리> ");
      String command = keyScan.nextLine().toLowerCase(); //입력받은걸 그대로 출력

      switch (command) {
      case "add": this.doAdd(); break;
      case "list": this.doList(); break;
      case "view": this.doView(); break;
      case "delete": this.doDelete(); break;
      case "update": this.doUpdate(); break;
      case "main":
        break loop;
      default:
        System.out.println("지원하지 않는 명령어 입니다.");
      }
    }
  }
  //아래 doXXX() 메서드들은 오직 service()에서만 호출하기 때문에
  //private으로 접근을 제한한다.
  private void doAdd() {
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
  private void doView() {
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
  private void doList() {
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
  private void doDelete() {
    System.out.print("삭제할 책의 이름은?");
    String name = this.keyScan.nextLine().toLowerCase();

    for (int i = 0; i < this.length; i++) {
      if(this.books[i].name.toLowerCase().equals(name)) {
        for (int x = i + 1; x < this.length; x++, i++) {
          this.books[i] = this.books[x];
        }
        this.books[--length] = null;

        System.out.printf("%s 책을 삭제하였습니다.\n", name);
      return;
      }
    }
    System.out.printf("%s 책이 없습니다.\n", name);

}
  private void doUpdate() {
    System.out.print("변경할 책의 제목은?");
    String name = this.keyScan.nextLine().toLowerCase();
    for (int i = 0; i < this.length; i++) {
      if(this.books[i].name.toLowerCase().equals(name)) {
        Book b1 = new Book();

        b1.name = this.books[i].name;

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

        System.out.print("저장하시겠습니까(y/n)?");
        if (keyScan.nextLine().toLowerCase().equals("y")) {
          this.books[i] = b1;
          System.out.println("저장하였습니다.");
        } else {
          System.out.println("변경을 취소하였습니다.");
        }
        return;
      }
    }
    System.out.printf("%s 이라는 학생이 없습니다.\n", name);
  }
}
