package com.placeholder.engine;

/**
 * User: Rugnarog the Mighty!
 * Date: 9/11/13
 * Time: 2:48 PM
 * */
public class Vector2f {
    private float x;
    private float y;

    public Vector2f(float x, float y) {
        this.x = x;
        this.y = y;
    }


    public float length(){
        return (float)Math.sqrt(x*x + y*y);
    }

    public float dot(Vector2f r){
        return x*r.getX() + y*r.getY();
    }

    public float cross(Vector2f r){
        return x * r.getY() - y * r.getX();
    }

    public Vector2f normalize(){
        float length = length();

        return new Vector2f(x / length, y / length);
    }

    public Vector2f rotate(float angle){

        double rad = Math.toRadians(angle);
        double cos = Math.cos(rad);
        double sin = Math.sin(rad);

        return new Vector2f((float)(x*cos - y*sin),(float)(x*sin +y*cos));
    }

    public Vector2f lerp(Vector2f destination, float lerpFactor){
        return destination.sub(this).mul(lerpFactor).add(this);
    }

    public Vector2f add(Vector2f r){
        return new Vector2f(x + r.getX(),y + r.getY());
    }
    public Vector2f add(float r){
        return new Vector2f(x + r,y + r);
    }

    public Vector2f sub(Vector2f r){
        return new Vector2f(x - r.getX(),y - r.getY());
    }
    public Vector2f sub(float r){
        return new Vector2f(x - r,y - r);
    }

    public Vector2f mul(Vector2f r){
        return new Vector2f(x * r.getX(),y * r.getY());
    }
    public Vector2f mul(float r){
        return new Vector2f(x * r,y * r);
    }

    public Vector2f div(Vector2f r){
        return new Vector2f(x / r.getX(),y / r.getY());
    }
    public Vector2f div(float r){
        return new Vector2f(x / r,y / r);
    }

    public String toString(){
        return "(" + x + " " + y + ")" ;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vector2f vector2f = (Vector2f) o;

        if (Float.compare(vector2f.x, x) != 0) return false;
        if (Float.compare(vector2f.y, y) != 0) return false;

        return true;
    }

}
