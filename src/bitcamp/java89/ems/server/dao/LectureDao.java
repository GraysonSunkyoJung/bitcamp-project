package bitcamp.java89.ems.server.dao;

import java.util.ArrayList;

import bitcamp.java89.ems.server.annotation.Component;
import bitcamp.java89.ems.server.vo.Lecture;

@Component // ApplicationContext가 관리하는 클래스임을 표시하기 위해 태그를 단다.
public class LectureDao extends AbstractDao<Lecture> {
  public LectureDao() {
    this.setFilename("lecture-v1.9.data");
    try {this.load();} catch (Exception e) {}
  }
    
  public ArrayList<Lecture> getList() {
    return this.list;
  }

   
  public ArrayList<Lecture> getListByName(String name) {
    ArrayList<Lecture> results = new ArrayList<>();
    for (Lecture lecture : list) {
      if (lecture.getName().equals(name)) {
        results.add(lecture);
      }
    }
    return results;
  }
    
  synchronized public void insert(Lecture lecture) {
    list.add(lecture);
    try {this.save();} catch (Exception e) {}
  }
  
  synchronized public void update(Lecture lecture) {
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).getName().equals(lecture.getName())) {
        list.set(i, lecture);
        try {this.save();} catch (Exception e) {}
        return;
      }
    }
  }
  
  synchronized public void delete(String name) {
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).getName().equals(name)) {
        list.remove(i);
        try {this.save();} catch (Exception e) {}
        return;
      }
    }
  }
  
  public boolean existName(String name) {
    for (Lecture lecture : list) {
      if (lecture.getName().toLowerCase().equals(name.toLowerCase())) {
        return true;
      }
    }
    return false;
  }
}

