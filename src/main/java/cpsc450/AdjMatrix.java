/**
 * CPSC 450, Fall 2024
 * 
 * NAME: Colin McClelland
 * DATE: Fall 2024
 */

package cpsc450;

import java.util.HashSet;
import java.util.Set;

/**
 * Adjacency Matrix implementation of the Graph interface. 
 */
public class AdjMatrix implements Graph {

  private int vertexCount;      // total number of vertices
  private int edgeCount;        // running count of number of edges
  private boolean matrix[];     // storage for the matrix as "flattened" 2D array

  /**
   * Create an adjacency matrix (graph) given a specific (fixed)
   * number of vertices.
   * @param vertices The number of vertices in the graph. 
   */ 
  public AdjMatrix(int vertices) throws GraphException {
    if (vertices <= 0) {
      throw new GraphException("number of vertices must be at least 1");
    }
    matrix = new boolean[vertices * vertices];
    for (int i = 0; i < matrix.length; i++) {
      matrix[i] = false;
    }
    vertexCount = vertices;
    edgeCount = 0;
  }

  @Override
  public void addEdge(int x, int y) {
    if (hasVertex(x) && hasVertex(y)) {
      int index = (x * vertexCount) + y;
      matrix[index] = true;
      edgeCount++;
    }
  }

  @Override
  public void removeEdge(int x, int y) {
    if (hasVertex(x) && hasVertex(y)) {
      int index = (x * vertexCount) + y;
      if (matrix[index] == true) {
        matrix[index] = false;
        edgeCount--;
      }
    }
  }

  @Override
  public Set<Integer> out(int x) {
    Set<Integer> outVertices = new HashSet<>();
    if (hasVertex(x)) {
      int start_idx = x * vertexCount;
      int end_idx = start_idx + vertexCount - 1;
      for (int i = start_idx; i <= end_idx; i++) {
        if (matrix[i] == true) {
          outVertices.add(i % vertexCount);
        }
      }
    }
    return outVertices;
  }

  @Override
  public Set<Integer> in(int x) {
    Set<Integer> inVertices = new HashSet<>();
    if (hasVertex(x)) {
      for (int i = 0; i < vertexCount; i++) {
        int index = x + (i * vertexCount);
        if (matrix[index] == true) {
          inVertices.add(i);
        }
      }
    }
    return inVertices;
  }

  @Override
  public Set<Integer> adj(int x) {
    Set<Integer> adjVertices = new HashSet<>();
    if (hasVertex(x)) {
      adjVertices.addAll(out(x));
      adjVertices.addAll(in(x));
    }
    return adjVertices;
  }

  @Override
  public boolean hasEdge(int x, int y) {
    if (!(hasVertex(x) && hasVertex(y))) {
      return false;
    }
    else {
      int index = (x * vertexCount) + y;
      return matrix[index];
    }
  }

  @Override
  public boolean hasVertex(int x) {
    return (!(((x < 0) || (x > vertexCount-1))));
  }

  @Override
  public int vertices() {
    return vertexCount;
  }
  
  @Override
  public int edges() {
    return edgeCount;
  }
  
}
