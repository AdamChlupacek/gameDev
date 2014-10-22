package com.placeholder.engine;


/**
 * User: Adam Chlupacek
 * Date: 18/03/14
 * Time: 20:01
 * Package: com.base.engine
 */
public class Camera {

    public static final Vector3f yAxis = new Vector3f(0,1,0);

    private Vector3f pos;
    private Vector3f forward;
    private Vector3f up;

    private Matrix4f projection;

    public Camera(float fov, float aspect, float zNear, float zFar) {
        this.pos = new Vector3f(0,0,0);
        this.forward = new Vector3f(0,0,1).normalize();
        this.up = new Vector3f(0,1,0).normalize();

        projection = new Matrix4f().initPesrspective(fov, aspect, zNear, zFar);
    }

    public Matrix4f getViewProjection(){
        Matrix4f cameraRotation = new Matrix4f().initRotation(forward, up);
        Matrix4f cameraTranslation = new Matrix4f().initTranslation(-pos.getX(), -pos.getY(), -pos.getZ());

        return projection.mul(cameraRotation.mul(cameraTranslation));
    }

    public void move(Vector3f dir, float amt){
        pos = pos.add(dir.mul(amt));
    }

    public void rotateY(float angle){

        Vector3f hAxis = yAxis.cross(forward).normalize();

        forward = forward.rotate(angle,yAxis).normalize();

        up = forward.cross(hAxis).normalize();
    }

    public void rotateX(float angle){

        Vector3f hAxis = yAxis.cross(forward).normalize();

        forward = forward.rotate(angle,hAxis).normalize();

        up = forward.cross(hAxis).normalize();
    }

    public Vector3f getLeft(){
        return forward.cross(up).normalize();
    }

    public Vector3f getRight(){
        return up.cross(forward).normalize();
    }


    public Vector3f getPos() {
        return pos;
    }

    public void setPos(Vector3f pos) {
        this.pos = pos;
    }

    public Vector3f getForward() {
        return forward;
    }

    public void setForward(Vector3f forward) {
        this.forward = forward;
    }

    public Vector3f getUp() {
        return up;
    }

    public void setUp(Vector3f up) {
        this.up = up;
    }
}
