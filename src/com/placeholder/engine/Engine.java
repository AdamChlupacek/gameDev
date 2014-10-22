package com.placeholder.engine;

/**
 * Created with IntelliJ IDEA.
 * User: adamchlupacek
 * Date: 22/10/14
 * Time: 15:28
 */
public class Engine {

  private Game game;


  public Engine(Game game){
    this.game = game;
  }

  public void start(){
    loop();
  }

  private void loop(){
    Window.create();
    game.init();
    while(!Window.isClosed()){
      update();
      render();
    }

    game.cleanUp();
    Window.destroy();
  }

  private void render() {

  }

  private void update() {

  }


}
