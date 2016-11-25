package bitcamp.java89.ems.server;

import java.net.ServerSocket;
import java.util.HashMap;

import bitcamp.java89.ems.server.controller.ContactAddController;
import bitcamp.java89.ems.server.controller.ContactDeleteController;
import bitcamp.java89.ems.server.controller.ContactListController;
import bitcamp.java89.ems.server.controller.ContactUpdateController;
import bitcamp.java89.ems.server.controller.ContactViewController;
import bitcamp.java89.ems.server.controller.LectureAddController;
import bitcamp.java89.ems.server.controller.LectureDeleteController;
import bitcamp.java89.ems.server.controller.LectureListController;
import bitcamp.java89.ems.server.controller.LectureUpdateController;
import bitcamp.java89.ems.server.controller.LectureViewController;

public class EduAppServer {
  // Command 구현체 보관
  // HashMap<명령문자열, 요청처리객체> commandMap
  HashMap<String, Command> commandMap = new HashMap<>(); 
  
  public EduAppServer() {
    // 클라이언트 요청을 처리할 Command 구현체 준비
    commandMap.put("contact/list", new ContactListController());
    commandMap.put("contact/view", new ContactViewController());
    commandMap.put("contact/add", new ContactAddController());
    commandMap.put("contact/delete", new ContactDeleteController());
    commandMap.put("contact/update", new ContactUpdateController());
    
    commandMap.put("lecture/list", new LectureListController());
    commandMap.put("lecture/view", new LectureViewController());
    commandMap.put("lecture/add", new LectureAddController());
    commandMap.put("lecture/delete", new LectureDeleteController());
    commandMap.put("lecture/update", new LectureUpdateController());

  }
  
  private void service() throws Exception {
    ServerSocket ss = new ServerSocket(8888);
    System.out.println("서버 실행 중...");
    
    while (true) {
      new RequestThread(ss.accept(), commandMap).start();
    }
    //ss.close();
  }
  
  public static void main(String[] args) throws Exception {
    EduAppServer eduServer = new EduAppServer();
    eduServer.service();
   }
}
