package bitcamp.java89.ems.server.context;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;

import bitcamp.java89.ems.server.annotation.RequestMapping;

public class RequestHandlerMapping {//메소드만 추려놓은 단어장! 
  HashMap<String,RequestHandler> handlerMap = new HashMap<>();
  
  public RequestHandlerMapping(Collection<Object> objList) { //받은 객채를 반복
    for (Object obj : objList) {
      Method[] methods = obj.getClass().getMethods(); // 메소드 목록을 뽑아냄 
      for (Method m : methods) {
        RequestMapping anno = m.getAnnotation(RequestMapping.class); // 태깅된 메소드만 뽑아냄 
        if (anno == null) {
          continue;
        }

        handlerMap.put(anno.value(), new RequestHandler(obj, m)); //뽑아낸걸 해시맵에담음, 객체주소와 함께.

      }
    }
  }
  // 클라이언트가 입력한 명령어를 주면 그 명령어를 처리할 메서드 정보를 리턴한다.
  // 물론, 메서드와 함께 인스턴스도 리턴한다.
  public RequestHandler getRequestHandler(String command) {
    return handlerMap.get(command);
  }
  
  public static class RequestHandler {
    public Object obj;
    public Method method;
    
    public RequestHandler(Object obj, Method method) {
      this.obj = obj;
      this.method = method;
    }
  }
}
