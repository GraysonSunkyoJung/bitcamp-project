package bitcamp.java89.ems.server.controller;

import java.io.PrintStream;
import java.util.HashMap;

import bitcamp.java89.ems.server.Command;
import bitcamp.java89.ems.server.dao.LectureDao;
import bitcamp.java89.ems.server.vo.Lecture;

public class LectureUpdateController implements Command {
  
  
  public void service(HashMap<String, String> paramMap, PrintStream out) {
    try {
      LectureDao lectureDao = LectureDao.getInstance();
      if (!lectureDao.existName(paramMap.get("name"))) {
        out.println("교재를 찾지 못했습니다.");
        return;
      }
      
      Lecture lecture = new Lecture();
      lecture.setName(paramMap.get("name"));
      lecture.setIntroduce(paramMap.get("introduce"));
      lecture.setLimit(Integer.parseInt(paramMap.get("limit")));
      lecture.setLevelTest(paramMap.get("leveltest").equals("y") ? true : false);
      lectureDao.update(lecture);
      out.println("변경하였습니다.");
    } catch (Exception e) {
      out.println("작업중 예외가 발생하였습니다.");
      e.printStackTrace();
    }
  }
}
