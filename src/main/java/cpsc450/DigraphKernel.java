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
  public static Set<Integer> findKernelNaive(Graph g) {
    int numVertices = g.vertices();
    
    // perform a full-DFS traversal from each vertex in the graph
    for (int graphVertex = 0; graphVertex < numVertices; graphVertex++) {
      Set<Integer> k = new HashSet<>();
      boolean [] excluded = new boolean[numVertices];
      Deque<Integer> stack = new LinkedList<>();
      int[] vertices = new int[numVertices];

      final int WHITE = 0;
      final int GRAY = 1;
      final int BLACK = 2;

      for (int i = 0; i < numVertices; i++) {vertices[i] = WHITE;} 
      for (int i = 0; i < numVertices; i++) {excluded[i]=false;}

      for (int graphNode = 0; graphNode < numVertices; graphNode++) {
        if (g.out(graphNode).isEmpty()) { // sink vertices must be in the kernel
          k.add(graphNode);
          for (Integer adjVertex : g.adj(graphNode)) {
            excluded[adjVertex] = true;
          }
        }
      }

      // start of full dfs traversal
      for (int i = graphVertex; i < graphVertex + numVertices; i++) {
        int graphNode = i % numVertices; 
  
        if (vertices[graphNode] == WHITE) {
          stack.addFirst(graphNode);
  
          while (!stack.isEmpty()) {
            Integer currNode = stack.getFirst(); // peek the first element on the stack

            if (!excluded[currNode]) {
              k.add(currNode);  // if a vertex is not excluded, we add it to the kernel
              for (Integer adjVertex : g.adj(currNode)) {
                excluded[adjVertex] = true;
              }
            }

            if (vertices[currNode] == WHITE) {
              vertices[currNode] = GRAY;
              Set <Integer> adjVertices = g.in(currNode);
              for (Integer vertex : adjVertices) {
                if (vertices[vertex] == WHITE) {
                  stack.addFirst(vertex);
                }
              }
            }
            else if (vertices[currNode] == GRAY) {
              vertices[currNode] = BLACK;
              stack.removeFirst();
            }
            else {
              stack.removeFirst();
            }
          }
        }
      }
      // at this point we have a "candidate kernel" that meets the independent criterion. Now we must check domination criterion
      boolean dominating = true;
      for (int a = 0; a < numVertices && dominating; a++) {
        if (excluded[a]) {
          boolean hasKernelEdge = false;
          for (Integer outVertex : g.out(a)) {
            if (k.contains(outVertex)) {
              hasKernelEdge = true;
              break;
            }
          }
          if (!hasKernelEdge) {
            dominating = false;
          }
        }
      }
      if (dominating) {return k;}
    }
    return new HashSet<>();
  }


   /**
   * Finds the kernel of a directed graph. A kernel mets two conditions: Independent & Dominating
   * Independent: No edge exists between any two pairs of vertices in the graph
   * Dominating: Every Vertex not included in the kernel has an edge to a vertex in the kernel
   * @param g The given directed, connected graph.
   * @returns a set of vertices that meet the conditions of independence and domination
   */
  public static Set<Integer> findKernelOptimized(Graph g) {
    int numVertices = g.vertices();
    Set<Integer> k = new HashSet<>();

    List<Integer> ts = topologicalSort(g);
    if (!ts.isEmpty()) { // a topological sort exists, so its reverse order can be used to find a kernel
      List<Integer> traversalOrder = ts.reversed();
      boolean [] excluded = new boolean[numVertices];
      for (int i = 0; i < numVertices; i++) {excluded[i]=false;}

      for (int i = 0; i < numVertices; i++){
        Integer graphNode = traversalOrder.get(i);
        if (!excluded[graphNode]) {
          k.add(graphNode);
          for (Integer vertex : g.adj(graphNode)) {
            excluded[vertex] = true;
          }
        }
      }
      return k;
    }

    if (bipartite(g)) { // if the graph is bipartite, we can perform dfs traversal from any vertex and return a kernel
      Deque<Integer> stack = new LinkedList<>();
      int[] vertices = new int[numVertices];

      final int WHITE = 0;
      final int GRAY = 1;
      final int BLACK = 2;

      for (int i = 0; i < numVertices; i++) {vertices[i] = WHITE;}

    for (int i = 0; i < numVertices; i++) {vertices[i] = WHITE;}
      boolean [] excluded = new boolean[numVertices];
      for (int i = 0; i < numVertices; i++) {excluded[i]=false;}

      for (int graphNode = 0; graphNode < numVertices; graphNode++) {
        if (vertices[graphNode] == WHITE) {
          stack.addFirst(graphNode);
  
          while (!stack.isEmpty()) {
            Integer currNode = stack.getFirst(); // peek the first element on the stack

            if (!excluded[currNode]) {
              k.add(currNode);  // if a vertex is not excluded, we add it to the kernel
              for (Integer adjVertex : g.adj(currNode)) {
                excluded[adjVertex] = true;
              }
            }

            if (vertices[currNode] == WHITE) {
              vertices[currNode] = GRAY;
              Set <Integer> adjVertices = g.in(currNode);
              for (Integer vertex : adjVertices) {
                if (vertices[vertex] == WHITE) {
                  stack.addFirst(vertex);
                }
              }
            }
            else if (vertices[currNode] == GRAY) {
              vertices[currNode] = BLACK;
              stack.removeFirst();
            }
            else {
              stack.removeFirst();
            }
          }
        }
      }
      return k;
    }

    return findKernelNaive(g);
  }


  public static Set<Integer> findKernelBruteForce(Graph g) {
    List<Set<Integer>> allCombinations = new ArrayList<>();
    generateCombinationsRecursive(new HashSet<>(), 0, g.vertices(), allCombinations);

    for (Set<Integer> combo : allCombinations) {
      // Check for independence
      boolean isIndependent = true;
      for (int i = 0; i < combo.size() - 1; i++) {
        for (int j = i + 1; j < combo.size(); j++) {
          int vertex1 = (int) combo.toArray()[i];
          int vertex2 = (int) combo.toArray()[j];

          if (g.hasEdge(vertex1, vertex2) || g.hasEdge(vertex2, vertex1)) {
            isIndependent = false;
            break; // No need to check further if the combination is not independent
          }
        }
        if (!isIndependent) {
          break;
        }
      }

        // If the combination is independent, check if it's dominating
        if (isIndependent) {
          boolean isDominating = true;
          for (int vertex = 0; vertex < g.vertices(); vertex++) {
            // Skip the vertices already in the kernel (combo)
            if (!combo.contains(vertex)) {
              boolean hasEdgeToKernel = false;

              for (int kernelVertex : combo) {
                if (g.hasEdge(vertex, kernelVertex)) {
                  hasEdgeToKernel = true;
                  break;
                }
              }

              if (!hasEdgeToKernel) {
                isDominating = false;
                break;
              }
            }
          }

          // If the combination is both independent and dominating, return it, otherwise return an empty set
          if (isDominating) {
            return combo;  // Return the kernel if both conditions are satisfied
          }
        }
    }

    return new HashSet<>();
}


  public static void generateCombinationsRecursive(Set<Integer> currentCombination, int idx, int numVertices, List<Set<Integer>> allCombinations) {
    if (idx == numVertices) {
        if (!currentCombination.isEmpty()) {
            allCombinations.add(new HashSet<>(currentCombination));
        }
        return;
    }

    currentCombination.add(idx);
    generateCombinationsRecursive(currentCombination, idx + 1, numVertices, allCombinations);

    currentCombination.remove(idx);
    generateCombinationsRecursive(currentCombination, idx + 1, numVertices, allCombinations);
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



