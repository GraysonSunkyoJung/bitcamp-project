package bitcamp.java89.ems;

import java.util.Scanner;

public class EduApp_b04 {
  public static void main(String[] args) {
    System.out.println("비트캠프 관리 시스템에 오신걸 환영합니다.");

    Book[] books = new Book[100];
    int length = 0;
    Scanner keyScan = new Scanner(System.in);

    loop:
    while (true) {
      System.out.print("명령> ");
      String command = keyScan.nextLine();

      switch (command) {
      case "add":
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

          System.out.print("계속 입력하시겠습니까(y/n)? ");
          if (!keyScan.nextLine().equals("y"))
            break;
        }
        break;
      case "list":
      case "view":
        System.out.println(command);
        break;
      case "quit":
        System.out.println("Good bye!");
        break loop;
      default:
        System.out.println("지원하지 않는 명령어 입니다.");
      }
    }
  }

  static void printStudentList(Book[] books, int length) {
    //Book b1 = null;
    for (int i = 0; i <length; i++){
      Book b1 = books[i];
      System.out.printf("%s, %s, %d, %d, %b\n",
        b1.name,
        b1.author,
        b1.price,
        b1.page,
        ((b1.cd)? "y" : "n"));
    }
  }
}