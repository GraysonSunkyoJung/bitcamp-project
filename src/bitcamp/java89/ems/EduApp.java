package bitcamp.java89.ems;
import java.util.Scanner;
public class EduApp {

  static Scanner keyScan = new Scanner(System.in);

  public static void main(String[] args) {
    //EduApp에서 사용하는 keyScan을 BookController와 공유한다.
    BookController bookController = new BookController(keyScan);

    System.out.println("비트캠프 관리 시스템에 오신걸 환영합니다.");

      loop:
      while (true) {
        System.out.print("명령> ");
        String command = keyScan.nextLine().toLowerCase(); //입력받은걸 그대로 출력

        switch (command) {
        case "menu": doMenu();break;
        case "go 1": bookController.service();break;
        case "quit":
          System.out.println("Good bye!");
          break loop;
        default:
          System.out.println("지원하지 않는 명령어 입니다.");
        }
      }
    }
  static void doMenu() {
    System.out.println("[메뉴]");
    System.out.println("1.학생관리");
    System.out.println("메뉴 이동은 'go 메뉴번호'를 입력하세요.");
  }
}



/*
    //여러명의 학생 정보를 입력하기 위한 레퍼런스 배열을 만든다.
    Book[] books = new Book[100];
    int length = 0; //레퍼런스 배열에 몇명의 학생 정보를 저장했는지 개수를 보관한다.
                    //레퍼런스 배열에 몇개의 Book 인스턴스가 들어있는지 그 개수를 보관한다.

    Scanner keyScan = new Scanner(System.in);

    //반복해서 입력 받는다.
    //Book b1 = null; //null 주소없음을 의미
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
    printStudentList(books, length);
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
    }*/
