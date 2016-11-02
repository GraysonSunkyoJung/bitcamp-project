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
