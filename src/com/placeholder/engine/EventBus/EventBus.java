package com.placeholder.engine.EventBus;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: adamchlupacek
 * Date: 22/10/14
 * Time: 23:20
 */

/**
 * An EventBus for communication of objects inside of the engine
 * As of now its not great piece of code but it will do until we get into some performance issues
 *
 * Works on basis that you have object, you pass that to the EventBus, the event bus is looking for any method in form:
 *
 * {@code
 *  @RegisterHadnler
 *  public void method(Event argument){
 *    //Some code here
 *  }
 * }
 *
 * While the return type and name of the method can be what ever you decide. But it HAS to be public and have only one
 * argument that extends the interface Event
 *
 * !!!!ATTENTION!!!!ACHTUNG!!!! (Why not german riiiight :3)
 *
 * !!!!ALL registering should be done in innit phase of the runtime!!!!
 *
 * !!!!ATTENTION!!!!ACHTUNG!!!!
 */
public class EventBus {

  private Map<Class<?>, List<Method>> regHandlers;
  private String name;

  /**
   * A constructor for a event bus
   * @param name name of the eventBus
   */
  public EventBus(String name){
    regHandlers = new HashMap<>();
    this.name = name;
  }

  /**
   * Method to register the Object's methods that are used as EventHandlers
   *
   * Checks every method for the annotation {@link com.placeholder.engine.EventBus.RegisterHandler}
   * if found adds the method to pool of methods that will/can be invoked when correct event is received
   *
   * @param object object to be registered
   * @param <T> anonymous type
   */
  public <T> void register(T object){
    Method[] methods = object.getClass().getMethods();

    for (Method m: methods){
      if (m.isAnnotationPresent(RegisterHandler.class) && m.getParameters().length == 1){
        if (regHandlers.containsKey(m.getParameters()[0].getClass()))
          regHandlers.get(m.getParameters()[0].getClass()).add(m);
        else {
          List<Method> newReg = new ArrayList<>();
          newReg.add(m);
          regHandlers.put(m.getParameters()[0].getClass(),newReg);
        }
      }
    }
  }

  /**
   * Dispatches event to the methods that were registered beforehand
   * @param event event that is to be dispatched
   * @param <E> type of the event
   */
  public <E extends Event> void dispatch(E event){
    if (regHandlers.containsKey(event.getClass())){
      try {
        List<Method> toDispatch = regHandlers.get(event.getClass());
        for (Method m:toDispatch){
          m.invoke(this,event);
        }
      } catch (IllegalAccessException | InvocationTargetException e) {
        e.printStackTrace();
      }
    }
  }

  public String getName() {
    return name;
  }
}
