package bitcamp.java89.ems.server.controller;

import java.io.PrintStream;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import bitcamp.java89.ems.server.annotation.RequestMapping;
import bitcamp.java89.ems.server.annotation.RequestParam;
import bitcamp.java89.ems.server.dao.LectureDao;
import bitcamp.java89.ems.server.vo.Lecture;

@Component// ApplicationContext가 관리하는 대상 클래스임을 태깅한다.
public class LectureController {
  // 의존 객체 DAO를 저장할 변수 선언
  @Autowired LectureDao lectureDao;


  @RequestMapping(value = "lecture/add")
  public void add(
      @RequestParam("name") String name,
      @RequestParam("introduce") String introduce,
      @RequestParam("limit") int limit,
      @RequestParam("leveltest") boolean levelTest,
      PrintStream out) throws Exception {

    // 주입받은 lectureDao를 사용할 것이기 때문에
    // 더이상 이 메서드에서LectureDao객체를 준비하지 않는다.
    // => 단 이 메서드가 호출되기 전에 반드시 LectureDao가 주입되어 있어야 한다.
    if (lectureDao.existName(name)) {
      out.println("같은 강의명이 존재합니다. 등록을 취소합니다.");
      return;
    }

    Lecture lecture = new Lecture();
    lecture.setName(name);
    lecture.setIntroduce(introduce);
    lecture.setLimit(limit);
    lecture.setLevelTest(levelTest);

    lectureDao.insert(lecture);
    out.println("등록하였습니다.");

  }

  @RequestMapping(value = "lecture/delete")
  public void delete(@RequestParam("name") String name, PrintStream out) throws Exception {

    // 주입받은 lectureDao를 사용할 것이기 때문에
    // 더이상 이 메서드에서LectureDao객체를 준비하지 않는다.
    // => 단 이 메서드가 호출되기 전에 반드시 LectureDao가 주입되어 있어야 한다.
    if (!lectureDao.existName(name)) {
      out.println("해당 데이터가 없습니다.");
      return;
    }

    lectureDao.delete(name);
    out.println("해당 데이터를 삭제 완료하였습니다.");
  }

  @RequestMapping(value = "lecture/list")
  public void list(PrintStream out) throws Exception {

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

  @RequestMapping(value = "lecture/update")
  public void update(
      @RequestParam("name") String name,
      @RequestParam("introduce") String introduce,
      @RequestParam("limit") int limit,
      @RequestParam("leveltest") boolean levelTest,
      PrintStream out) throws Exception {

    // 주입받은 lectureDao를 사용할 것이기 때문에
    // 더이상 이 메서드에서LectureDao객체를 준비하지 않는다.
    // => 단 이 메서드가 호출되기 전에 반드시 LectureDao가 주입되어 있어야 한다.
    if (!lectureDao.existName(name)) {
      out.println("해당 강의 찾지 못했습니다.");
      return;
    }

    Lecture lecture = new Lecture();
    lecture.setName(name);
    lecture.setIntroduce(introduce);
    lecture.setLimit(limit);
    lecture.setLevelTest(levelTest);
    lectureDao.update(lecture);
    out.println("변경하였습니다.");
  }

  @RequestMapping(value = "lecture/view")
  public void view(@RequestParam("name") String name, PrintStream out) throws Exception {
    // 주입받은 lectureDao를 사용할 것이기 때문에
    // 더이상 이 메서드에서LectureDao객체를 준비하지 않는다.
    // => 단 이 메서드가 호출되기 전에 반드시 LectureDao가 주입되어 있어야 한다.
    ArrayList<Lecture> list = lectureDao.getListByName(name);
    for (Lecture lecture : list) {
      out.println("--------------------------");
      out.printf("제목: %s\n", lecture.getName());
      out.printf("소개: %s\n", lecture.getIntroduce());
      out.printf("제한인원: %d\n", lecture.getLimit());
      out.printf("레벨테스트: %s\n", (lecture.isLevelTest() ? "y" : "n"));
    }
  }
}
