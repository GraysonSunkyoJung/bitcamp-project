package bitcamp.java89.ems.server.controller;

import java.io.PrintStream;
import java.util.HashMap;

import bitcamp.java89.ems.server.AbstractCommand;
import bitcamp.java89.ems.server.dao.LectureDao;
import bitcamp.java89.ems.server.vo.Lecture;

public class LectureAddController extends AbstractCommand {
 
  @Override
  protected void doResponse(HashMap<String, String> paramMap, PrintStream out) throws Exception {
    LectureDao lectureDao = LectureDao.getInstance();
    if (lectureDao.existName(paramMap.get("name"))) {
      out.println("같은 강의명이 존재합니다. 등록을 취소합니다.");
      return;
    }
    
    Lecture lecture = new Lecture();
    lecture.setName(paramMap.get("name"));
    lecture.setIntroduce(paramMap.get("introduce"));
    lecture.setLimit(Integer.parseInt(paramMap.get("limit")));
    lecture.setLevelTest(paramMap.get("leveltest").equals("y") ? true : false);
    
    lectureDao.insert(lecture);
    out.println("등록하였습니다.");
    
  }
}
