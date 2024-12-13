/**
 * CPSC 450, Final Project
 *
 * NAME: Colin McClelland
 * DATE: Fall 2024
 *
 */ 

package cpsc450;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;


public class DigraphKernel {

  /**
   * Finds the kernel of a directed graph. A kernel mets two conditions: Independent & Dominating
   * Independent: No edge exists between any two pairs of vertices in the graph
   * Dominating: Every Vertex not included in the kernel has an edge to a vertex in the kernel
   * @param g The given directed, connected graph.
   * @returns a set of vertices that meet the conditions of independence and domination
   */
  public static Set<Integer> findKernelInefficent(Graph g) {
    Set<Integer> kernel = new HashSet<>();
    return kernel;
  }



  /**
   * Computes a topological sort of the given directed graph, if one
   * exists,  using a modified version of iterative DFS.
   * @param g The graph to sort
   * @returns A list giving a topological sort of the graph vertices,
   * if one exists, otherwise returns an empty list. 
   */
  public static List<Integer> topologicalSort(Graph g) {
    List<Integer> vertexOrdering = new ArrayList<>();
    Deque<Integer> stack = new LinkedList<>();
    int numVertices = g.vertices();

    final int WHITE = 0;
    final int GRAY = 1;
    final int BLACK = 2;

    int[] vertices = new int[numVertices];
    for (int i = 0; i < numVertices; i++) {
      vertices[i] = WHITE;
    }

    for (int graphNode = 0; graphNode < numVertices; graphNode++) {
      if (vertices[graphNode] == WHITE && g.in(graphNode).isEmpty()) {  // find a start node (one with no in edges)
        stack.addFirst(graphNode);
        vertices[graphNode] = GRAY;

        while (!stack.isEmpty()) {
          Integer currNode = stack.getFirst(); // peek the first element on the stack
          Set <Integer> adjVertices = g.out(currNode);
          boolean foundUnvisitedNeighbor = false;
          for (Integer vertex : adjVertices) {
            if (!foundUnvisitedNeighbor && vertices[vertex] == WHITE) {  // we want to add the first unvisited neighbor to the stack and then take that path
              vertices[vertex] = GRAY;
              stack.addFirst(vertex);
              foundUnvisitedNeighbor = true;
              break;
            }
            else if (vertices[vertex] == GRAY) {
              return new ArrayList<>();  // we have detected a cycle which means the graph doesn't have a topological sort
            }
          }
          if (!foundUnvisitedNeighbor) {  // remove currNode from the stack if all of its adjacent vertices have been visited
            stack.removeFirst();
            vertices[currNode] = BLACK; // every node will be marked black eventually
            vertexOrdering.addFirst(currNode);
          }
        }
      }
    }
    return vertexOrdering;
  }


  /**
   * Checks if the given undirected graph is bipartite by using a
   * modified, iterative version of DFS that tries to perform a
   * 2-coloring.
   * @param g The graph to check
   * @returns True if the graph does not have any cycles, and false
   * otherwise. 
  */
  public static boolean bipartite(Graph g) {
    boolean bipartite = true;
    int numVertices = g.vertices();
    int[] vertices = new int[numVertices];
    Deque<Integer> stack = new LinkedList<>();

    final int UNVISITED = 0;
    final int BLUE = 1;
    final int GREEN = 2;

    for (int i = 0; i < numVertices; i++) {
      vertices[i] = UNVISITED;
    }

    for (int graphNode = 0; graphNode < numVertices; graphNode++) {
      if (vertices[graphNode] == UNVISITED) {
        stack.addFirst(graphNode);
        vertices[graphNode] = BLUE;

        while (!stack.isEmpty()) {
          Integer currNode = stack.getFirst(); // peek the first element on the stack
          Set <Integer> adjVertices = g.adj(currNode);
          boolean foundUnvisitedNeighbor = false;

          for (Integer vertex : adjVertices) {
            if (!foundUnvisitedNeighbor && vertices[vertex] == UNVISITED) {  // we want to add the first unvisited neighbor to the stack and then take that path
              vertices[vertex] = (vertices[currNode] == BLUE) ? GREEN : (BLUE); // assign the first adjacent node the opposite color of current node
              stack.addFirst(vertex);
              foundUnvisitedNeighbor = true;
              break;
            }
            else if (vertices[vertex] == vertices[currNode]) {
              return !bipartite;
            }
          }
          if (!foundUnvisitedNeighbor) {  // remove currNode from the stack if all of its adjacent vertices have been visited
            stack.removeFirst();          // if this is the case, then we know currNode is on the top of the stack, so we are safe to remove
          }
        }
      }
    }
    return bipartite;
  }



}



