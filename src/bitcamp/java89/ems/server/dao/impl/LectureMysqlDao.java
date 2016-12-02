package bitcamp.java89.ems.server.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import bitcamp.java89.ems.server.annotation.Component;
import bitcamp.java89.ems.server.dao.LectureDao;
import bitcamp.java89.ems.server.util.DataSource;
import bitcamp.java89.ems.server.vo.Lecture;

@Component // ApplicationContext가 관리하는 클래스임을 표시하기 위해 태그를 단다.
public class LectureMysqlDao implements LectureDao {
  DataSource ds;



  public void setDs(DataSource dataSource) {
    this.ds = dataSource;
  }


  public ArrayList<Lecture> getList() throws Exception {
    Connection con = ds.getConnection();
    ArrayList<Lecture> list =  new ArrayList<>();
    try (
        PreparedStatement stmt = con.prepareStatement(
            "select name, intro, limi, lvtst from ex_lectures");
        ResultSet rs = stmt.executeQuery();){


      while (rs.next()) { // 서버에서 레코드 한개를 가져와 실행.
        Lecture lecture = new Lecture();
        lecture.setName(rs.getString("name"));
        lecture.setIntroduce(rs.getString("intro"));
        lecture.setLimit(rs.getInt("limi"));
        lecture.setLevelTest(rs.getBoolean("lvtst"));
        list.add(lecture);
      }
    }finally {
      ds.returnConnection(con);
    } 
    return list;
  }


  public ArrayList<Lecture> getListByName(String name) throws Exception {
    Connection con = ds.getConnection();
    ArrayList<Lecture> list =  new ArrayList<>();
    try (
        PreparedStatement stmt = con.prepareStatement(
            "select name, intro, limi, lvtst from ex_lectures where name=?");){

      stmt.setString(1, name); //일반 구분은 소괄호 안에 넣을수 없다.
      ResultSet rs = stmt.executeQuery();

      while (rs.next()) { // 서버에서 레코드 한개를 가져와 실행.
        Lecture lecture = new Lecture();
        lecture.setName(rs.getString("name"));
        lecture.setIntroduce(rs.getString("intro"));
        lecture.setLimit(rs.getInt("limi"));
        lecture.setLevelTest(rs.getBoolean("lvtst"));
        list.add(lecture);
      }
      rs.close();
    }finally {
      ds.returnConnection(con);
    } 
    return list;
  }

  public void insert(Lecture lecture) throws Exception {
    Connection con = ds.getConnection();
    try (
        PreparedStatement stmt = con.prepareStatement(
            "insert into ex_lectures(name,intro,limi,lvtst) values(?,?,?,?)"); ) {
      stmt.setString(1, lecture.getName());
      stmt.setString(2, lecture.getIntroduce());
      stmt.setInt(3, lecture.getLimit());
      stmt.setBoolean(4, lecture.isLevelTest());

      stmt.executeUpdate();
    }finally {
      ds.returnConnection(con);
    }
  }

  public void update(Lecture lecture) throws Exception {
    Connection con = ds.getConnection();
    try (
        PreparedStatement stmt = con.prepareStatement(
            "update ex_lectures set intro=?, limi=?, lvtst=? where name=?"); ) {

      stmt.setString(1, lecture.getIntroduce());
      stmt.setInt(2, lecture.getLimit());
      stmt.setBoolean(3, lecture.isLevelTest());
      stmt.setString(4, lecture.getName());

      stmt.executeUpdate();
    }finally {
      ds.returnConnection(con);
    }
  }

  public void delete(String name) throws Exception {
    Connection con = ds.getConnection();
    try (
        PreparedStatement stmt = con.prepareStatement(
            "delete from ex_lectures where name=?"); ) {

      stmt.setString(1, name);

      stmt.executeUpdate();
    }finally {
      ds.returnConnection(con);
    }
  }

  public boolean existName(String name) throws Exception {
    Connection con = ds.getConnection();
    try (
        PreparedStatement stmt = con.prepareStatement(
            "select * from ex_lectures where name=?");){

      stmt.setString(1, name); //일반 구분은 소괄호 안에 넣을수 없다.
      ResultSet rs = stmt.executeQuery();

      if (rs.next()) { // 서버에서 레코드 한개를 가져와 실행.
        rs.close();
        return true;
      } else {
        rs.close();
        return false;
      }
    }finally {
      ds.returnConnection(con);
    }
  }
}

