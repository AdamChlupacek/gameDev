package com.placeholder.engine;

/**
 * Created with IntelliJ IDEA.
 * User: adamchlupacek
 * Date: 22/10/14
 * Time: 16:32
 */
public class Vertex {

  public static final int SIZE = 8;

  private Vector3f position;
  private Vector2f texture;
  private Vector3f normal;

  public Vertex(Vector3f position, Vector2f texture){
    this(position,texture, new Vector3f(0,0,0));
  }

  public Vertex(Vector3f position, Vector2f texture, Vector3f normal) {
    this.position = position;
    this.texture = texture;
    this.normal = normal;
  }

  public Vector3f getPosition() {
    return position;
  }

  public void setPosition(Vector3f position) {
    this.position = position;
  }

  public Vector2f getTexture() {
    return texture;
  }

  public void setTexture(Vector2f texture) {
    this.texture = texture;
  }

  public Vector3f getNormal() {
    return normal;
  }

  public void setNormal(Vector3f normal) {
    this.normal = normal;
  }
}
