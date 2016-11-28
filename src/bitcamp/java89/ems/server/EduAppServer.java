package bitcamp.java89.ems.server;

import java.io.File;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.HashMap;

import bitcamp.java89.ems.server.dao.ContactDao;
import bitcamp.java89.ems.server.dao.LectureDao;

public class EduAppServer {
  // Command 구현체 보관
  // HashMap<명령문자열, 요청처리객체> commandMap
  HashMap<String, Command> commandMap = new HashMap<>(); 
  
  public EduAppServer() {
    // Controller 가 사용할 DAO 객체 준비
    ContactDao contactDao = new ContactDao();
    contactDao.setFilename("contact-v1.9.data");
    try {
    contactDao.load();
    } catch(Exception e) {
      System.out.println("데이터 로딩중 오류 발생!");
    }
    
    LectureDao lectureDao = new LectureDao();
    lectureDao.setFilename("lecture-v1.9.data");
    try {
      lectureDao.load();
    } catch(Exception e) {
      System.out.println("데이터 로딩중 오류 발생!");
    }
    
    // bin 폴더를 뒤져서 AbstractCommand의 서브클래스를 찾아낸다.
    ArrayList<Class> classList = new ArrayList<>();
    ReflectionUtil.getCommandClasses(new File("./bin"), classList);
    
    for (Class c : classList) {
      try {
        AbstractCommand command = (AbstractCommand)c.newInstance();
        
        // commmandMap에 저장하기 전에 각 Controller에 대해 DAO를 주입한다.
        try {
          Method m = command.getClass().getMethod("setContactDao", ContactDao.class);
          m.invoke(command, contactDao);
          //System.out.printf("%s:%s\n", command.getClass().getName(), m.getName());
        } catch (Exception e) {}
        
        try {
          Method m = command.getClass().getMethod("setLectureDao", LectureDao.class);
          m.invoke(command, lectureDao);
          //System.out.printf("%s:%s\n", command.getClass().getName(), m.getName());
        } catch (Exception e) {}
        

        
        commandMap.put(command.getCommandString(), command);
      } catch (Exception e) {}
    }
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
