package com.placeholder.engine.quadTree;

import com.placeholder.engine.AABB;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: adamchlupacek
 * Date: 22/10/14
 * Time: 22:19
 */

/**
 * QuadTree structure, used for representing a 2D space in effective blocks depending on concentration of elements
 * inside the block
 *
 * -----------       -----------
 * | nw | ne |       |         |
 * -----------   vs  |  ENTRY  |    => two possible states of the QuadTree
 * | sw | se |       |         |
 * -----------       -----------
 *
 * Planed to be used to hold chunks
 */
public class QuadTree {

  private AABB aabb;

  private QuadTree ne;
  private QuadTree nw;
  private QuadTree se;
  private QuadTree sw;

  private QuadTreeEntry contains;

  /**
   * Constructor for an empty QuadTree only with a size of the QuadTree
   *
   * @param aabb the size of the current quadTree
   */
  public QuadTree(AABB aabb){
    this.aabb = aabb;
  }

  /**
   * Constructor for a QuadTree that contains an entry
   * @param contains the entry of the quadTree
   * @param aabb the size of the current quadTree
   */
  public QuadTree(QuadTreeEntry contains, AABB aabb){
    this.contains = contains;
    this.aabb = aabb;
  }

  /**
   * Constructor for a QuadTree that does not contain an entry but has sub Trees
   *
   * @param ne north east quadTree
   * @param nw north west quadTree
   * @param se south east quadTree
   * @param sw south west quadTree
   * @param aabb the size of the current quadTree
   */
  public QuadTree(QuadTree ne, QuadTree nw, QuadTree se, QuadTree sw, AABB aabb) {
    this.ne = ne;
    this.nw = nw;
    this.se = se;
    this.sw = sw;
    this.aabb = aabb;
  }


  /**
   * A way to insert a new entry into the quadTree
   * In case that there already is an entry in the quadTree, it will be split into a 4 sub QuadTrees
   *
   * In case that the entry overlaps with another entry that is already in there will be an EXCEPTION!!
   * NOT STACK SAFE! DO NOT TRY TO NEST THOUSANDS OF ENTRIES PLEASE -_-
   *
   * @param entry entry to be inserted
   */
  public void insert(QuadTreeEntry entry) throws Exception {
    if (!aabb.contains(entry.getAABB())) return;
    if (contains == null && ne == null){ contains = entry; return;}
    if (contains != null && contains.getAABB().contains(entry.getAABB())) throw new Exception();


    float yHalf = (aabb.getMaxY() - aabb.getMinY()) / 2;
    float xHalf = (aabb.getMaxX() - aabb.getMinX()) / 2;

    ne = new QuadTree(new AABB(aabb.getMinX() + xHalf, aabb.getMinY() + yHalf, xHalf, yHalf));
    nw = new QuadTree(new AABB(aabb.getMinX(), aabb.getMinY() + yHalf, xHalf, yHalf));
    se = new QuadTree(new AABB(aabb.getMinX() + xHalf, aabb.getMinY(), xHalf, yHalf));
    sw = new QuadTree(new AABB(aabb.getMinX(), aabb.getMinY(), xHalf, yHalf));

    ne.insert(entry);
    nw.insert(entry);
    se.insert(entry);
    sw.insert(entry);
  }

  /**
   * Queries the QuadTree to return an Entry that is in given range
   * @param aabb the range of query
   * @return list of entries that are inside the queried area
   */
  public List<QuadTreeEntry> getEntry(AABB aabb){

    List<QuadTreeEntry> res = new ArrayList<>();

    if (contains.getAABB().contains(aabb)) res.add(contains);

    if (ne == null) return res;

    if (ne.getAABB().contains(aabb))res.addAll(ne.getEntry(aabb));
    if (nw.getAABB().contains(aabb))res.addAll(nw.getEntry(aabb));
    if (se.getAABB().contains(aabb))res.addAll(se.getEntry(aabb));
    if (sw.getAABB().contains(aabb))res.addAll(sw.getEntry(aabb));

    return res;
  }

  /**
   * Getter for quadTree's AABB
   * @return AABB {@link com.placeholder.engine.AABB}
   */
  public AABB getAABB() {
    return aabb;
  }
}
