/* 값을 저장할 때 사용할 메모리를 정의한다. */
package bitcamp.java89.ems;

public class Box {
  Object value;
  Box next;


  public Box() {}

  public Box(Object value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return "Box(" + this.value +")";
  }
}
