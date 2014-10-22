package com.placeholder.engine;

import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL15.*;


/**
 * Created with IntelliJ IDEA.
 * User: adamchlupacek
 * Date: 22/10/14
 * Time: 16:39
 */
public class Mesh {

  private int vbo;
  private int vao;
  private int ibo;
  private int size;

  public Mesh(Vertex[] vertices, int[] indices, boolean calcNormals){
    initMesh();

    addVertices(vertices, indices, calcNormals);
  }

  private void addVertices(Vertex[] vertices, int[] indices, boolean calcNormals){

    size = indices.length;
    glBindVertexArray(vao);

    if (calcNormals){
      calcNormals(vertices, indices);
    }

    glBindBuffer(GL_ARRAY_BUFFER, vbo);
    glBufferData(GL_ARRAY_BUFFER, Util.createFlippedBuffer(vertices), GL_STATIC_DRAW);

    glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo);
    glBufferData(GL_ELEMENT_ARRAY_BUFFER,Util.createFlippedBuffer(indices), GL_STATIC_DRAW);
  }

  public void draw(){

    glEnableVertexAttribArray(0);
    glEnableVertexAttribArray(1);
    glEnableVertexAttribArray(2);

    glBindBuffer(GL_ARRAY_BUFFER, vbo);
    glVertexAttribPointer(0,3,GL_FLOAT,false,3 * 4,0);
    glVertexAttribPointer(1,2,GL_FLOAT,false,2 * 4,12);
    glVertexAttribPointer(2,3,GL_FLOAT,false,3 * 4,20);

    glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo);
    glDrawElements(GL_TRIANGLES, size, GL_UNSIGNED_INT, 0);

    glDisableVertexAttribArray(0);
    glDisableVertexAttribArray(1);
    glDisableVertexAttribArray(2);
  }

  private void calcNormals(Vertex[] vertices, int[] indices){
    for ( int i = 0; i<indices.length; i += 3){
      int i0 = indices[i];
      int i1 = indices[i + 1];
      int i2 = indices[i + 2];

      Vector3f v1 = vertices[i1].getPosition().sub(vertices[i0].getPosition());
      Vector3f v2 = vertices[i2].getPosition().sub(vertices[i0].getPosition());

      Vector3f normal = v1.cross(v2).normalize();

      vertices[i0].setNormal(vertices[i0].getNormal().add(normal));
      vertices[i1].setNormal(vertices[i1].getNormal().add(normal));
      vertices[i2].setNormal(vertices[i2].getNormal().add(normal));

    }

    for (int i = 0; i<vertices.length; i++){
      vertices[i].setNormal(vertices[i].getNormal().normalize());
    }
  }

  public void initMesh(){
    vbo = glGenBuffers();
    ibo = glGenBuffers();
    vao = glGenVertexArrays();
    size = 0;
  }

}
