package bitcamp.java89.ems;
import java.util.Scanner;
public class EduApp {

  static Scanner keyScan = new Scanner(System.in);

  public static void main(String[] args) {
    //EduApp에서 사용하는 keyScan을 BookController와 공유한다.
    BookController.keyScan = keyScan;

    System.out.println("비트캠프 관리 시스템에 오신걸 환영합니다.");

      loop:
      while (true) {
        System.out.print("명령> ");
        String command = keyScan.nextLine().toLowerCase(); //입력받은걸 그대로 출력
        switch (command) {
        case "add": BookController.doAdd(); break;
        case "list": BookController.doList(); break;
        case "view": BookController.doView(); break;
        case "quit":
            System.out.println("Good bye!");
              break loop;
            default:
              System.out.println("지원하지 않는 명령어 입니다.");
        }
      }
    }
  }
