package bitcamp.java89.ems.server.controller;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;

import bitcamp.java89.ems.server.AbstractCommand;
import bitcamp.java89.ems.server.annotation.Component;
import bitcamp.java89.ems.server.dao.LectureDao;
import bitcamp.java89.ems.server.vo.Lecture;

@Component(value = "lecture/list") //ApplicationContxt가 관리하는 클래스 임을 표시
public class LectureListController extends AbstractCommand {
  //의존 객체 DAO를 저장할 변수 선언
  LectureDao lectureDao;
  
  // 의존객체 주입할 때 호출할 셋터 추가
  public void setLectureDao(LectureDao lectureDao) {
    this.lectureDao = lectureDao;
  }
  
  @Override
  protected void doResponse(HashMap<String, String> paramMap, PrintStream out) throws Exception {
 
    // 주입받은 lectureDao를 사용할 것이기 때문에
    // 더이상 이 메서드에서LectureDao객체를 준비하지 않는다.
    // => 단 이 메서드가 호출되기 전에 반드시 LectureDao가 주입되어 있어야 한다.
    ArrayList<Lecture> list = lectureDao.getList();
    for (Lecture lecture : list) {
      out.printf("%s,%s,%d,%s\n",
        lecture.getName(),
        lecture.getIntroduce(),
        lecture.getLimit(),
        (lecture.isLevelTest() ? "y" : "n"));
    } 
  }
}
