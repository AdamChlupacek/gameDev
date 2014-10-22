package com.placeholder.engine;

/**
 * User: Rugnarog the Mighty!
 * Date: 9/11/13
 * Time: 3:48 PM
 * Created for my own pleasure please do not use it in any way without permission, I guess I would not do anything to you even if you would, but you know, be nice! :D
 */
public class Vector3f {

    private float x;
    private float y;
    private float z;


    public Vector3f(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public float length(){
        return (float)Math.sqrt(x*x + y*y + z*z);
    }

    public float dot(Vector3f r){
        return x*r.getX() + y*r.getY() + z*r.getZ();
    }

    public Vector3f cross(Vector3f r){
        float x_ = y * r.getZ() - z * r.getY();
        float y_ = z * r.getX() - x * r.getZ();
        float z_ = x * r.getY() - y * r.getX();

        return new Vector3f(x_,y_,z_);


    }

    public Vector3f normalize(){
        float length = length();

        return new Vector3f(x / length, y / length, z / length);
    }

    public Vector3f rotate(float angle, Vector3f axis) {

        float sinHalfAngle = (float)Math.sin(Math.toRadians(angle/2));
        float cosHalfAngle = (float)Math.cos(Math.toRadians(angle / 2));

        float rX = axis.getX() * sinHalfAngle;
        float rY = axis.getY() * sinHalfAngle;
        float rZ = axis.getZ() * sinHalfAngle;
        float rW = cosHalfAngle;

        Quaternion rotation = new Quaternion(rX,rY,rZ,rW);
        Quaternion conjugate = rotation.conjugate();

        Quaternion w = rotation.mul(this).mul(conjugate);

        return new Vector3f(w.getX(),w.getY(),w.getZ());
    }

    public Vector3f lerp(Vector3f destination, float lerpFactor){
        return destination.sub(this).mul(lerpFactor).add(this);
    }

    public Vector3f add(Vector3f r){
        return new Vector3f(x + r.getX(),y + r.getY(),z + r.getZ());
    }
    public Vector3f add(float r){
        return new Vector3f(x + r,y + r, z + r);
    }
    public Vector3f sub(Vector3f r){
        return new Vector3f(x - r.getX(),y - r.getY(),z - r.getZ());
    }
    public Vector3f sub(float r){
        return new Vector3f(x - r,y - r, z - r);
    }
    public Vector3f mul(Vector3f r){
        return new Vector3f(x * r.getX(),y * r.getY(),z * r.getZ());
    }
    public Vector3f mul(float r){
        return new Vector3f(x * r,y * r, z * r);
    }
    public Vector3f div(Vector3f r){
        return new Vector3f(x / r.getX(),y / r.getY(),z / r.getZ());
    }
    public Vector3f div(float r){
        return new Vector3f(x / r,y / r, z / r);
    }

    public Vector3f abs(){
        return new Vector3f(Math.abs(x),Math.abs(y),Math.abs(z));
    }

    public Vector2f getXY(){
        return new Vector2f(x,y);
    }
    public Vector2f getYZ(){
        return new Vector2f(y,z);
    }
    public Vector2f getZX(){
        return new Vector2f(z,x);
    }

    public Vector2f getYX(){
        return new Vector2f(y,x);
    }
    public Vector2f getZY(){
        return new Vector2f(z,y);
    }
    public Vector2f getXZ(){
        return new Vector2f(x,z);
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vector3f vector3f = (Vector3f) o;

        if (Float.compare(vector3f.x, x) != 0) return false;
        if (Float.compare(vector3f.y, y) != 0) return false;
        if (Float.compare(vector3f.z, z) != 0) return false;

        return true;
    }


}
