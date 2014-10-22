package com.placeholder.engine;

import com.placeholder.game.CustomGame;

/**
 * Created with IntelliJ IDEA.
 * User: adamchlupacek
 * Date: 22/10/14
 * Time: 15:23
 */
public class Main {
  public static void main(String[] args){

    Game game = new CustomGame();

    Engine engine = new Engine(game);
    engine.start();

  }
}
