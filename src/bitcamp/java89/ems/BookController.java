/* 작업내용
- LinkedList를 ArrayList로 교체한다.
*/
package bitcamp.java89.ems;

import java.util.Scanner;
import java.util.ArrayList;

public class BookController {
  private ArrayList<Book> list;
  private Scanner keyScan;

  public BookController(Scanner keyScan) {
    list = new ArrayList<Book>();
    this.keyScan = keyScan;
  }

  public void service() {
    loop:
    while (true) {
      System.out.print("교재관리> ");
      String command = keyScan.nextLine().toLowerCase(); //입력받은걸 그대로 출력

      try {
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
      } catch (IndexOutOfBoundsException e) {
      System.out.println("인덱스가 유효하지 않습니다.");
      } catch (Exception e) {
        System.out.println("인덱스 값이 잘못 되었거나, 실행중 오류가 발생했습니다.");
      }//try
    }
  }

  private void doAdd() {

    while (true) {
      Book b1 = new Book();
      System.out.print("제목? ");
      b1.name = this.keyScan.nextLine();

      System.out.print("저자? ");
      b1.author = this.keyScan.nextLine();
      while(true) {
        try{
          System.out.print("가격? ");
          b1.price = Integer.parseInt(this.keyScan.nextLine());
          break;
        } catch(Exception e) {
          System.out.println("정수 값을 입력하세요");
        }
      }
      while (true) {
        try{
          System.out.print("쪽수? ");
          b1.page = Integer.parseInt(this.keyScan.nextLine());
          break;
        } catch(Exception e) {
          System.out.println("정수 값을 입력하세요.");
        }
      }
      System.out.print("부록(y/n)? ");
      b1.cd = this.keyScan.nextLine().equals("y") ? true : false;

      list.add(b1);
      System.out.print("계속 입력하시겠습니까(y/n)?");
      if (!keyScan.nextLine().equals("y"))
        break;
    }
  }

  private void doView() {
    System.out.print("책의 인덱스?");
    int index = Integer.parseInt(this.keyScan.nextLine());

    if(index < 0 || index >= list.size()) {
      System.out.println("인덱스가 유효하지 않습니다.");
      return;
    }
      Book b1 = list.get(index);
      System.out.printf("제목: %s\n", b1.name);
      System.out.printf("저자: %s\n", b1.author);
      System.out.printf("가격: %d\n", b1.price);
      System.out.printf("쪽수: %d\n", b1.page);
      System.out.printf("부록: %s\n", ((b1.cd)? "y" :"n"));
  }

  private void doList() {
    for (Book b1 : list) {
      System.out.printf("%s, %s, %d, %d, %s\n",
        b1.name,
        b1.author,
        b1.price,
        b1.page,
        ((b1.cd)? "y" : "n"));
    }
  }

  private void doDelete() {
    System.out.print("삭제할 책의 인덱스?");
    int index = Integer.parseInt(keyScan.nextLine());
    Book deletedBook = list.remove(index);
    System.out.printf("%s 책을 삭제하였습니다.\n", deletedBook.name);
  }

  private void doUpdate() {

    System.out.print("책의 인덱스?");
    int index = Integer.parseInt(this.keyScan.nextLine());

    Book oldBook = list.get(index);

    //새 학생 정보를 입력받는다.
    Book b1 = new Book();

    System.out.printf("저자(%s)?", oldBook.author);
    b1.author = this.keyScan.nextLine();

    while(true) {
      try{
        System.out.print("가격? ");
        b1.price = Integer.parseInt(this.keyScan.nextLine());
        break;
      } catch(Exception e) {
        System.out.println("정수 값을 입력하세요");
      }
    }
    while (true) {
      try{
        System.out.print("쪽수? ");
        b1.page = Integer.parseInt(this.keyScan.nextLine());
        break;
      } catch(Exception e) {
        System.out.println("정수 값을 입력하세요.");
      }
    }

    System.out.print("부록(y/n)? ");
    b1.cd = this.keyScan.nextLine().equals("y") ? true : false;

    System.out.print("저장하시겠습니까(y/n)?");
    if (keyScan.nextLine().toLowerCase().equals("y")) {
      b1.name = oldBook.name;
      list.set(index, b1);
      System.out.println("저장하였습니다.");
    } else {
      System.out.println("변경을 취소하였습니다.");
    }
  }
}
