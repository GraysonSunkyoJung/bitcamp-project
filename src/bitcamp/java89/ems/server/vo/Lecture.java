package bitcamp.java89.ems.server.vo;

import java.io.Serializable;

public class Lecture implements Serializable {
  private static final long serialVersionUID = 1L;
  
  protected String name;     // 강좌이름
  protected String introduce;   // 강좌소개
  protected int limit;       // 제한인원
  protected boolean levelTest;      // 레벨테스트 여부


  public Lecture () {}

  public Lecture(String name, String introduce, int limit, boolean levelTest) {
    this.name = name;
    this.introduce = introduce;
    this.limit = limit;
    this.levelTest = levelTest;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getIntroduce() {
    return introduce;
  }

  public void setIntroduce(String introduce) {
    this.introduce = introduce;
  }

  public int getLimit() {
    return limit;
  }

  public void setLimit(int limit) {
    this.limit = limit;
  }

  public boolean isLevelTest() {
    return levelTest;
  }

  public void setLevelTest(boolean levelTest) {
    this.levelTest = levelTest;
  }

  @Override
  public String toString() {
    return "Lecture [name=" + name + ", introduce=" + introduce + ", limit=" + limit + ", levelTest=" + levelTest
        + "]";
  }
}
