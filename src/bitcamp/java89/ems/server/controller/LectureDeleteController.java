package bitcamp.java89.ems.server.controller;

import java.io.PrintStream;
import java.util.HashMap;

import bitcamp.java89.ems.server.AbstractCommand;
import bitcamp.java89.ems.server.dao.LectureDao;

public class LectureDeleteController extends AbstractCommand {
  
  @Override
  protected void doResponse(HashMap<String, String> paramMap, PrintStream out) throws Exception {
    LectureDao lectureDao = LectureDao.getInstance();
    
    if (!lectureDao.existName(paramMap.get("name"))) {
      out.println("해당 데이터가 없습니다.");
      return;
    }
    
    lectureDao.delete(paramMap.get("name"));
    out.println("해당 데이터를 삭제 완료하였습니다.");
  }

  @Override
  public String getCommandString() {
    // TODO Auto-generated method stub
    return "lecture/delete";
  }
}
