package bitcamp.java89.ems.server.controller;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;

import bitcamp.java89.ems.server.AbstractCommand;
import bitcamp.java89.ems.server.dao.LectureDao;
import bitcamp.java89.ems.server.vo.Lecture;

public class LectureListController extends AbstractCommand {
  
  @Override
  protected void doResponse(HashMap<String, String> paramMap, PrintStream out) throws Exception {
    LectureDao lectureDao = LectureDao.getInstance();
    ArrayList<Lecture> list = lectureDao.getList();
    for (Lecture lecture : list) {
      out.printf("%s,%s,%d,%s\n",
        lecture.getName(),
        lecture.getIntroduce(),
        lecture.getLimit(),
        (lecture.isLevelTest() ? "y" : "n"));
    } 
  }

  @Override
  public String getCommandString() {
    // TODO Auto-generated method stub
    return "lecture/list";
  }
}
