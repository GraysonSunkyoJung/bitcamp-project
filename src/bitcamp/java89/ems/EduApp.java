package bitcamp.java89.ems;
import java.util.Scanner;
public class EduApp {
  public static void main(String[] args) {
    System.out.println("비트캠프 관리 시스템에 오신걸 환영합니다.");

    Scanner keyScan = new Scanner(System.in);

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

    System.out.printf("제목: %s\n", b1.name);
    System.out.printf("작가: %s\n", b1.author);
    System.out.printf("가격: %s\n", b1.price);
    System.out.printf("쪽수: %s\n", b1.page);
    System.out.printf("부록(y/n): %s\n", b1.cd);

  }
}
