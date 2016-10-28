package bitcamp.java89.ems;

import java.util.Scanner;

public class EduApp_b06 {
  static Book[] books = new Book[100];
  static int length = 0;
  static Scanner keyScan = new Scanner(System.in);

  public static void main(String[] args) {
    System.out.println("비트캠프 관리 시스템에 오신걸 환영합니다.");



    loop:
    while (true) {
      System.out.print("명령> ");
      String command = keyScan.nextLine().toLowerCase();

      switch (command) {
      case "add": doAdd(); break;
      case "list": doList(); break;
      case "view": doView(); break;
      case "quit":
        System.out.println("Good bye!");
        break loop;
      default:
        System.out.println("지원하지 않는 명령어 입니다.");
      }
    }
  }
  static void doList() {
    for (int i = 0; i <length; i++) {
      Book b1 = books[i];
      System.out.printf("%s, %s, %d, %d, %b\n",
        b1.name,
        b1.author,
        b1.price,
        b1.page,
        ((b1.cd)? "y" : "n"));
    }
  }

  static void doAdd() {
    while (length < books.length){
      Book b1 = new Book();
      System.out.print("제목? ");
      b1.name = keyScan.nextLine();

      System.out.print("저자? ");
      b1.author = keyScan.nextLine();

      System.out.print("가격? ");
      b1.price = Integer.parseInt(keyScan.nextLine());

      System.out.print("쪽수? ");
      b1.page = Integer.parseInt(keyScan.nextLine());

      System.out.print("부록(y/n)? ");
      b1.cd = keyScan.nextLine().equals("y") ? true : false;
      books[length++] = b1;

      System.out.print("계속 입력하시겠습니까(y/n)?");
      if (!keyScan.nextLine().equals("y"))
        break;

    }
  }
  static void doView() {
    System.out.print("조회할 책의 제목은?");
    String name = keyScan.nextLine().toLowerCase();
    for (int i = 0; i < length; i++) {
      if(books[i].name.toLowerCase().equals(name)){
        System.out.printf("제목: %s\n", books[i].name);
        System.out.printf("저자: %s\n", books[i].author);
        System.out.printf("가격: %d\n", books[i].price);
        System.out.printf("쪽수: %d\n", books[i].page);
        System.out.printf("부록: %b\n", books[i].cd);
        break;
      }
    }
  }
}
