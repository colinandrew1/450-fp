/**
 * CPSC 450, HW-3
 * 
 * NAME: Colin McClelland
 * DATE: Fall, 2024 
 */

package cpsc450;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


/**
 * Basic adjacency List implementation of the Graph interface.
 */
public class AdjList implements Graph {

  private int vertexCount;                     // total number of vertices
  private int edgeCount;                       // running count of number of edges
  private Map<Integer,Set<Integer>> outEdges;  // storage for the out edges
  private Map<Integer,Set<Integer>> inEdges;   // storage for the in edges

  /**
   * Create an adjacency list (graph) given a specific (fixed) number
   * of vertices.
   * @param vertices The number of vertices of the graph.
   */
  public AdjList(int vertices) throws GraphException {
    if (vertices <= 0) {
        throw new GraphException("number of vertices must be at least 1");
    }
    outEdges = new HashMap<>();
    inEdges = new HashMap<>();
    for (int i = 0; i < vertices; i++) {
        outEdges.put(i, new HashSet<>());
        inEdges.put(i, new HashSet<>());
    }
    vertexCount = vertices;
    edgeCount = 0;
  }

  @Override
  public void addEdge(int x, int y) {
    if (hasVertex(x) && hasVertex(y)) {
        outEdges.get(x).add(y);
        inEdges.get(y).add(x);
        edgeCount++;
    }
  }

  @Override
  public void removeEdge(int x, int y) {
    if (hasVertex(x) && hasVertex(y)) {
        if (hasEdge(x, y)) {
            outEdges.get(x).remove(y);
            inEdges.get(y).remove(x);
            edgeCount--;
        }
    }
  }

  @Override
  public Set<Integer> out(int x) {
    if (hasVertex(x)) {
        Set<Integer> outVertices = new HashSet<>();
        outVertices.addAll(outEdges.get(x));
        return outVertices;
    }
    else {
        throw new GraphException("vertex does not exist");
    }
  }

  @Override
  public Set<Integer> in(int x) {
    if (hasVertex(x)) {
      Set<Integer> inVertices = new HashSet<>();
      inVertices.addAll(inEdges.get(x));
        return inVertices;
    }
    else {
        throw new GraphException("Vertex does not exist.");
    }
  }

  @Override
  public Set<Integer> adj(int x) {
    if (hasVertex(x)) {
        Set<Integer> adjVertices = new HashSet<>();
        adjVertices.addAll(out(x));
        adjVertices.addAll(in(x));
        return adjVertices;
    }
    else {
        throw new GraphException("Vertex does not exist.");
    }
    
  }

  @Override
  public boolean hasEdge(int x, int y) {
    if (hasVertex(x) && hasVertex(y)) {
        if (outEdges.get(x).contains(y)) {
            return true;
        }
    }
    return false;
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
