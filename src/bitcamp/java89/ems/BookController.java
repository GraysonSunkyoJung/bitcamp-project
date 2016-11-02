package bitcamp.java89.ems;

import java.util.Scanner;

public class BookController {


  private Box head;
  private Box tail;
  private int length;
  private Scanner keyScan;

  public BookController(Scanner keyScan) {
    head = new Box();
    tail = head;
    length = 0;
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
    while (true) {
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

      //tail이 가리키는 빈 상자에 book 인스턴스의 주소를 담는다.
      //그리고 새 상자를 만든다음 현재 상자에 연결한다.
      //tail은 다시 맨 마지막 빈 상자를 가리킨다.
      tail.value = b1;
      tail.next = new Box();
      tail = tail.next;
      length++;

      System.out.print("계속 입력하시겠습니까(y/n)?");
      if (!keyScan.nextLine().equals("y"))
        break;

    }
  }

  private void doView() {
    System.out.print("책의 인덱스?");
    int index = Integer.parseInt(this.keyScan.nextLine());

    if(index < 0 || index >= length) {
      System.out.println("인덱스가 유효하지 않습니다.");
      return;
    }
      Box currentBox = head;
      for (int i = 0; i < index; i++) {
        currentBox = currentBox.next;
      }
        Book b1 = (Book)currentBox.value;

        System.out.printf("제목: %s\n", b1.name);
        System.out.printf("저자: %s\n", b1.author);
        System.out.printf("가격: %d\n", b1.price);
        System.out.printf("쪽수: %d\n", b1.page);
        System.out.printf("부록: %s\n", ((b1.cd)? "y" :"n"));
  }

  private void doList() {
    Box currentBox = head;
    while (currentBox != null && currentBox != tail) {
      Book b1 = (Book)currentBox.value;
      System.out.printf("%s, %s, %d, %d, %s\n",
        b1.name,
        b1.author,
        b1.price,
        b1.page,
        ((b1.cd)? "y" : "n"));
      currentBox = currentBox.next;
    }
  }
  private void doDelete() {
    System.out.print("삭제할 책의 인덱스?");
    int index = Integer.parseInt(keyScan.nextLine());

    if(index < 0 || index >= length) {
      System.out.println("인덱스가 유효하지 않습니다.");
      return;
    }
    Book deletedBook = null;
    if (index == 0) {
      deletedBook = (Book)head.value;
      head = head.next;
      length--;
    } else {
      Box currentBox = head;
      for (int i = 0; i < (index - 1); i++) {
        currentBox = currentBox.next;
    }
    deletedBook = (Book)currentBox.next.value;
    currentBox.next = currentBox.next.next;
  }
      //length--;
      System.out.printf("%s 책을 삭제하였습니다.\n", deletedBook.name);

}

  private void doUpdate() {
    System.out.print("책의 인덱스?");
    int index = Integer.parseInt(this.keyScan.nextLine());
    //유효한 인덱스 인지 검사
    if(index < 0 || index >= length) {
      System.out.println("인덱스가 유효하지 않습니다.");
      return;
    }
    //변경 하려는 책 정보가 저장된 상자를 찾는다.
    Box currentBox = head;
    for (int i = 0; i < index; i++) {
      currentBox = currentBox.next;
    }
      //찾은 상자에서 변경할 학생의 정보를 꺼낸다.
    Book oldBook = (Book)currentBox.value;

    //새 학생 정보를 입력받는다.
    Book b1 = new Book();
  //  System.out.printf("제목(%s)? ", oldBook.name);
  //  b1.name = this.keyScan.nextLine();

    System.out.printf("저자(%s)?", oldBook.author);
    b1.author = this.keyScan.nextLine();

    System.out.printf("가격(%d)? ", oldBook.price);
    b1.price = Integer.parseInt(this.keyScan.nextLine());

    System.out.printf("쪽수(%d)? ", oldBook.page);
    b1.page = Integer.parseInt(this.keyScan.nextLine());

    System.out.print("부록(y/n)? ");
    b1.cd = this.keyScan.nextLine().equals("y") ? true : false;

    System.out.print("저장하시겠습니까(y/n)?");
    if (keyScan.nextLine().toLowerCase().equals("y")) {
      b1.name = oldBook.name;
      currentBox.value = b1;
      System.out.println("저장하였습니다.");
    } else {
      System.out.println("변경을 취소하였습니다.");
    }
  }
}
