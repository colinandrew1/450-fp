/**
 * CPSC 450, Final Project
 *
 * NAME: Colin McClelland
 * DATE: Fall 2024
 *
 */ 

package cpsc450;

import java.util.HashSet;
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
}

