package com.placeholder.engine;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;

/**
 * Created with IntelliJ IDEA.
 * User: adamchlupacek
 * Date: 22/10/14
 * Time: 15:42
 */
public class Window {

  public static void create(){
    try {
      Display.create();
    } catch (LWJGLException e) {
      e.printStackTrace();
    }
  }

  public static boolean isClosed(){
    return Display.isCloseRequested();
  }


  public static void destroy(){
    Display.destroy();
  }



}
