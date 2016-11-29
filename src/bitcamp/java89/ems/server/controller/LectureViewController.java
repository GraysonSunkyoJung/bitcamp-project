package bitcamp.java89.ems.server.controller;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;

import bitcamp.java89.ems.server.annotation.Component;
import bitcamp.java89.ems.server.annotation.RequestMapping;
import bitcamp.java89.ems.server.dao.LectureDao;
import bitcamp.java89.ems.server.vo.Lecture;

@Component(value = "lecture/view") //ApplicationContxt가 관리하는 클래스 임을 표시
public class LectureViewController {
  //의존 객체 DAO를 저장할 변수 선언  
  LectureDao lectureDao;
  
  // 의존객체 주입할 때 호출할 셋터 추가
  public void setLectureDao(LectureDao lectureDao) {
    this.lectureDao = lectureDao;
  }
  
  @RequestMapping
  public void doResponse(HashMap<String, String> paramMap, PrintStream out) throws Exception {
    // 주입받은 lectureDao를 사용할 것이기 때문에
    // 더이상 이 메서드에서LectureDao객체를 준비하지 않는다.
    // => 단 이 메서드가 호출되기 전에 반드시 LectureDao가 주입되어 있어야 한다.
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
