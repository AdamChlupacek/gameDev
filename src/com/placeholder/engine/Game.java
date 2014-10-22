package com.placeholder.engine;

/**
 * Created with IntelliJ IDEA.
 * User: adamchlupacek
 * Date: 22/10/14
 * Time: 15:25
 */

/**
 * An interface for a general game
 */
public interface Game {

  /**
   * A method that is called at the start of the engine
   */
  public void init();

  /**
   * A method that is called in the of the program, used for releasing resources
   */
  public void cleanUp();

}
