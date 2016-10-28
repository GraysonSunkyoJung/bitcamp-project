package bitcamp.java89.ems;

import java.util.Scanner;

public class EduApp_b01 {
  public static void main(String[] args) {
    System.out.println("비트캠프 관리 시스템에 오신걸 환영합니다.");

  Book[] books = new Book[100];
  int length = 0;
  Scanner keyScan = new Scanner(System.in);

    while (true){
      System.out.print("명령> ");
      String command = keyScan.nextLine();
      System.out.println(command);
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
