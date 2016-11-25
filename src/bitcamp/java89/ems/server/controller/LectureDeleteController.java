package bitcamp.java89.ems.server.controller;

import java.io.PrintStream;
import java.util.HashMap;

import bitcamp.java89.ems.server.Command;
import bitcamp.java89.ems.server.dao.LectureDao;

public class LectureDeleteController implements Command {
  
  public void service(HashMap<String, String> paramMap, PrintStream out) {
    try {
      LectureDao lectureDao = LectureDao.getInstance();
      
      if (!lectureDao.existName(paramMap.get("name"))) {
        out.println("해당 데이터가 없습니다.");
        return;
      }
      
      lectureDao.delete(paramMap.get("name"));
      out.println("해당 데이터를 삭제 완료하였습니다.");
    } catch (Exception e) {
      out.println("작업중 예외가 발생하였습니다.");
      e.printStackTrace();  
    }
  }
}
