package bitcamp.java89.ems.server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import bitcamp.java89.ems.server.controller.BookController;
import bitcamp.java89.ems.server.controller.ContactController;

public class EduAppServer {
  static Scanner in;
  static PrintStream out;
  static Scanner keyScan = new Scanner(System.in);
  static BookController bookController;
  static ContactController contactController;

  public static void main(String[] args) throws Exception {
    ServerSocket ss = new ServerSocket(8888);
    System.out.println("서버 실행 중...");
    
    while (true) {
      processRequest(ss.accept());
    }
    //ss.close();
  }

  private static void processRequest(Socket socket) {
    try {
      in = new Scanner(
        new BufferedInputStream(socket.getInputStream()));
      out = new PrintStream(
        new BufferedOutputStream(socket.getOutputStream()), true);
      
      bookController = new BookController(in, out);
      contactController = new ContactController(in, out);
  
      out.println("비트캠프 관리 시스템에 오신걸 환영합니다.");
  
      
      
      loop:
      while (true) {
        // 클라이언트에게 데이터를 전송한다.
        out.println("명령>");
        out.println(); // 빈 줄은 보내는 데이터의 끝을 의미한다.
        
        // 클라이언트로부터 명령을 읽는다.
        String command = in.nextLine().toLowerCase(); //입력받은걸 그대로 출력
  
        boolean running = true;
        switch (command) {
        case "menu": doMenu();break;
        case "go 1": running = bookController.service();break;
        case "go 2": running = contactController.service();break;
        case "save" : doSave(); break;
        case "quit":
          if (doQuit())
            break loop;
          break;
        default:
          out.println("지원하지 않는 명령어 입니다.");
        }
        
        if (!running) {
          doQuit();
          break;
        }
      }
    } catch(Exception e) {
    } finally {
      try {in.close();} catch (Exception e){}
      try {out.close();} catch (Exception e){}
      try {socket.close();} catch (Exception e){}
    }
  }
  
  static void doMenu() {
    out.println("[메뉴]");
    out.println("1.교재관리");
    out.println("2.연락처관리");
    out.println("메뉴 이동은 'go 메뉴번호'를 입력하세요.");
    out.println("[명령]");
    out.println("save   데이터 저장");
    out.println("quit   프로그램 종료");
  }

  static boolean doQuit() {
    boolean changed = bookController.isChanged();
    if (changed) {
      doSave();
    }
    System.out.println("클라이언트 연결 종료!");
    return true;
  }

  static void doSave() {
    try{
      contactController.save();
    } catch (Exception e) {
      System.out.println("연락처 저장 중에 오류가 발생했습니다.");
    }
    
    try{
      bookController.save();
    } catch (Exception e) {
      System.out.println("교재 정보 저장 중에 오류가 발생했습니다.");
    }
  }
}
