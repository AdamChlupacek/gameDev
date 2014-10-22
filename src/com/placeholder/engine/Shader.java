package com.placeholder.engine;

import java.util.HashMap;
import java.util.Map;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL32.*;

/**
 * Created with IntelliJ IDEA.
 * User: adamchlupacek
 * Date: 22/10/14
 * Time: 16:13
 */
public class Shader {

  private Map<String, Integer> uniforms;

  private int program;

  public Shader() {
    program = glCreateProgram();
    uniforms = new HashMap<>();

    if (program == 0){
      System.err.println("Shader creation failed, could not find valid memory location in constructor");
      System.exit(1);
    }
  }

  public void bind(){
    glUseProgram(program);
  }

  public void updateUniforms(Transform transform){

  }

  public void addUniform(String uniform){
    int uniformLocation = glGetUniformLocation(program,uniform);

    if (uniformLocation == 0xFFFFFFFF){
      System.err.println("Could not find uniform: " + uniform);
      new Exception().printStackTrace();
      System.exit(1);
    }

    uniforms.put(uniform,uniformLocation);
  }

  //  public void addVertexShaderFromFile(String text){
//    addProgram(loadShader(text),GL_VERTEX_SHADER);
//  }

  public void addVertexShader(String text){
    addProgram(text,GL_VERTEX_SHADER);
  }

//  public void addFragmentShaderFromFile(String text){
//    addProgram(loadShader(text),GL_FRAGMENT_SHADER);
//  }

  public void addFragmentShader(String text){
    addProgram(text,GL_FRAGMENT_SHADER);
  }

//  public void addGeometryShaderFromFile(String text){
//    addProgram(loadShader(text),GL_GEOMETRY_SHADER);
//  }

  public void addGeometryShader(String text){
    addProgram(text,GL_GEOMETRY_SHADER);
  }

  public void compileShader(){
    glLinkProgram(program);

    if (glGetProgrami(program, GL_LINK_STATUS) == 0){
      System.err.println(glGetProgramInfoLog(program,1024));
      System.exit(1);
    }

    glValidateProgram(program);

    if (glGetProgrami(program, GL_VALIDATE_STATUS) == 0){
      System.err.println(glGetProgramInfoLog(program,1024));
      System.exit(1);
    }

  }

  private void addProgram(String text, int type){

    int shader = glCreateShader(type);

    if (shader == 0){
      System.err.println("Shader creation failed, could not find valid memory location when adding shader");
      System.exit(1);
    }

    glShaderSource(shader,text);
    glCompileShader(shader);

    if (glGetShaderi(shader, GL_COMPILE_STATUS) == 0){
      System.err.println(glGetShaderInfoLog(shader,1024));
      System.exit(1);
    }

    glAttachShader(program,shader);
  }

  public void setUniformi(String uniformName, int value){
    glUniform1i(uniforms.get(uniformName), value);
  }
  public void setUniformf(String uniformName, float value){
    glUniform1f(uniforms.get(uniformName), value);
  }
  public void setUniform(String uniformName, Vector3f value){
    glUniform3f(uniforms.get(uniformName), value.getX(), value.getY(), value.getZ());
  }
//  public void setUniform(String uniformName, Matrix4f value){
//    glUniformMatrix4(uniforms.get(uniformName), true, Util.createFlippedBuffer(value));
//  }



}
