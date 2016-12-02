package bitcamp.java89.ems.server.dao;

import java.util.ArrayList;

import bitcamp.java89.ems.server.vo.Lecture;

public interface LectureDao {
  ArrayList<Lecture> getList() throws Exception;
  ArrayList<Lecture> getListByName(String name) throws Exception;
  void insert(Lecture lecture) throws Exception;
  void update(Lecture lecture) throws Exception;
  void delete(String name) throws Exception;
  boolean existName(String name) throws Exception;
}
