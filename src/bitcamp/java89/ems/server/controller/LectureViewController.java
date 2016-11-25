package bitcamp.java89.ems.server.controller;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;

import bitcamp.java89.ems.server.AbstractCommand;
import bitcamp.java89.ems.server.dao.LectureDao;
import bitcamp.java89.ems.server.vo.Lecture;

public class LectureViewController extends AbstractCommand {
 
  @Override
  protected void doResponse(HashMap<String, String> paramMap, PrintStream out) throws Exception {
    LectureDao lectureDao = LectureDao.getInstance();
    ArrayList<Lecture> list = lectureDao.getListByName(paramMap.get("name"));
    for (Lecture lecture : list) {
      out.println("--------------------------");
      out.printf("제목: %s\n", lecture.getName());
      out.printf("소개: %s\n", lecture.getIntroduce());
      out.printf("제한인원: %d\n", lecture.getLimit());
      out.printf("레벨테스트: %s\n", (lecture.isLevelTest() ? "y" : "n"));
    }
  }
}
