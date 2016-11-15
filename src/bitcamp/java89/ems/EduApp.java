package bitcamp.java89.ems;

import java.util.Scanner;

public class EduApp {
  static Scanner keyScan = new Scanner(System.in);
  static BookController bookController;

  public static void main(String[] args) throws Exception {
    bookController = new BookController(keyScan);

    System.out.println("비트캠프 관리 시스템에 오신걸 환영합니다.");

      loop:
      while (true) {
        System.out.print("명령> ");
        String command = keyScan.nextLine().toLowerCase(); //입력받은걸 그대로 출력

        switch (command) {
        case "menu": doMenu();break;
        case "go 1": bookController.service();break;
        case "save" : doSave();break;
        case "quit":
          if (doQuit())
            break loop;
          break;
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

  static boolean doQuit() {
    boolean changed = bookController.isChanged();
    if (changed) {
      System.out.print("학생 정보가 변경되었습니다. 그래도 종료하시겠습니까?(y/n)");
      if (!keyScan.nextLine().toLowerCase().equals("y"))
        return false;
      System.out.println("학생 정보가 변경된 것을 취소하고 종료합니다.");
    }
    System.out.println("Good bye!");
    return true;
  }

  static void doSave() {
    try{
      bookController.save();
      System.out.println("저장하였습니다.");
    } catch (Exception e) {
      System.out.println("파일 저장 중에 오류가 발생했습니다.");
    }
  }
}
