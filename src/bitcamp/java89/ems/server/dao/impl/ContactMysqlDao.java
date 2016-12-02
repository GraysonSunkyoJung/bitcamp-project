package bitcamp.java89.ems.server.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import bitcamp.java89.ems.server.annotation.Component;
import bitcamp.java89.ems.server.dao.ContactDao;
import bitcamp.java89.ems.server.vo.Contact;

@Component // ApplicationContext가 관리하는 클래스임을 표시하기 위해 태그를 단다.
public class ContactMysqlDao implements ContactDao {
  Connection con;
  

  // Connection 객체를 외부에서 주입 받는다.
  public void setConnection(Connection con) {
    this.con = con;
  }

  public ArrayList<Contact> getList() throws Exception {
    ArrayList<Contact> list =  new ArrayList<>();
    try (
      PreparedStatement stmt = con.prepareStatement(
          "select posi, name, tel, email from ex_contacts");
      ResultSet rs = stmt.executeQuery();){
      
      
      while (rs.next()) { // 서버에서 레코드 한개를 가져와 실행.
        Contact contact = new Contact();
        contact.setName(rs.getString("name"));
        contact.setPosition(rs.getString("posi"));
        contact.setTel(rs.getString("tel"));
        contact.setEmail(rs.getString("email"));
        list.add(contact);
      }
    } 
    return list;
  }
  
  public ArrayList<Contact> getListByName(String name) throws Exception {
    ArrayList<Contact> list =  new ArrayList<>();
    try (
      PreparedStatement stmt = con.prepareStatement(
          "select posi, name, tel, email from ex_contacts where name=?");){
      
      stmt.setString(1, name); //일반 구분은 소괄호 안에 넣을수 없다.
      ResultSet rs = stmt.executeQuery();
      
      while (rs.next()) { // 서버에서 레코드 한개를 가져와 실행.
        Contact contact = new Contact();
        contact.setName(rs.getString("name"));
        contact.setPosition(rs.getString("posi"));
        contact.setTel(rs.getString("tel"));
        contact.setEmail(rs.getString("email"));
        list.add(contact);
      }
      rs.close();
    } 
    return list;
  }
  
  synchronized public void insert(Contact contact) throws Exception {
    try (
      PreparedStatement stmt = con.prepareStatement(
          "insert into ex_contacts(name,email,tel,posi) values(?,?,?,?)"); ) {
      stmt.setString(1, contact.getName());
      stmt.setString(2, contact.getEmail());
      stmt.setString(3, contact.getTel());
      stmt.setString(4, contact.getPosition());
      
      stmt.executeUpdate();
    }
  }
  
  public void update(Contact contact) throws Exception {
    try (
      PreparedStatement stmt = con.prepareStatement(
          "update ex_contacts set name=?, tel=?, posi=? where email=?"); ) {
      
      stmt.setString(1, contact.getName());
      stmt.setString(2, contact.getTel());
      stmt.setString(3, contact.getPosition());
      stmt.setString(4, contact.getEmail());
      
      stmt.executeUpdate();
    }
  }
  
  public void delete(String email) throws Exception {
    try (      
      PreparedStatement stmt = con.prepareStatement(
          "delete from ex_contacts where email=?"); ) {
      
      stmt.setString(1, email);
      
      stmt.executeUpdate();
    }
  }
  
  public boolean existEmail(String email) throws Exception {
    try (
      PreparedStatement stmt = con.prepareStatement(
          "select * from ex_contacts where email=?");){
      
      stmt.setString(1, email); //일반 구분은 소괄호 안에 넣을수 없다.
      ResultSet rs = stmt.executeQuery();
      
      if (rs.next()) { // 서버에서 레코드 한개를 가져와 실행.
        rs.close();
        return true;
      } else {
        rs.close();
        return false;
      }
    }
  } 
}