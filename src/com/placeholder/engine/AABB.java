package com.placeholder.engine;

/**
 * Created with IntelliJ IDEA.
 * User: adamchlupacek
 * Date: 22/10/14
 * Time: 22:21
 */
public class AABB {

  private float maxX;
  private float minX;
  private float maxY;
  private float minY;

  public AABB(float minX, float minY, float widthX, float widthY) {
    this.minY = minY;
    this.minX = minX;
    this.maxY = this.minY + widthY;
    this.maxX = this.minX + widthX;
  }

  public boolean contains(AABB aabb){
    return minX < aabb.getMaxX() && maxX > aabb.getMinX() && minY < aabb.getMaxY() && maxY > aabb.getMinY();
  }

  public float getMaxX() {
    return maxX;
  }

  public float getMinX() {
    return minX;
  }

  public float getMaxY() {
    return maxY;
  }

  public float getMinY() {
    return minY;
  }
}
