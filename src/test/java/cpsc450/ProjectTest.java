/**
 * CPSC 450, Final Project
 * 
 * NAME: Colin McClelland
 * DATE: Fall 2024
 *
 * Unit tests for final project - finding kernel in a digraph
 */

package cpsc450;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;


class ProjectTest {

  // -------------------------------------------------------------
  // Positive Kernel Tests
  // -------------------------------------------------------------

  @Test
  void myFirstPositiveKernelTest() {
    Graph g = new AdjMatrix(4);
    g.addEdge(0, 2); 
    g.addEdge(2,3); 
    g.addEdge(3,1); 
    g.addEdge(1,0); 

    Set<Integer> possibilityA = new HashSet<>();
    possibilityA.add(0);
    possibilityA.add(3);

    Set<Integer> possibilityB = new HashSet<>();
    possibilityB.add(1);
    possibilityB.add(2);

    Set<Integer> kernel = DigraphKernel.findKernelInefficent(g);
    assertTrue(kernel.equals(possibilityA) || kernel.equals(possibilityB));
  }

  @Test
  void mySecondPositiveKernelTest() {
    Graph g = new AdjMatrix(4);
    g.addEdge(0, 1); 
    g.addEdge(1,2); 
    g.addEdge(1,3); 
    g.addEdge(3,2); 
    g.addEdge(3,0);

    Set<Integer> possibilityA = new HashSet<>();
    possibilityA.add(0);
    possibilityA.add(2);

    Set<Integer> kernel = DigraphKernel.findKernelInefficent(g);
    assertTrue(kernel.equals(possibilityA));
  }

  @Test
  void myThirdPositiveKernelTest() {
    Graph g = new AdjMatrix(8);
    g.addEdge(0,1);
    g.addEdge(1,4); 
    g.addEdge(1,3); 
    g.addEdge(2,0); 
    g.addEdge(3,2); 
    g.addEdge(3,5); 
    g.addEdge(5,7); 
    g.addEdge(5,6); 
    g.addEdge(6,7); 
    g.addEdge(4,6); 
    
    Set<Integer> possibilityA = new HashSet<>();
    possibilityA.add(0);
    possibilityA.add(3);
    possibilityA.add(4);
    possibilityA.add(7);

    Set<Integer> kernel = DigraphKernel.findKernelInefficent(g);
    assertTrue(kernel.equals(possibilityA));
  }

  @Test
  void myFourthPositiveKernelTest() {
    Graph g = new AdjMatrix(4);
    g.addEdge(0, 1); 
    g.addEdge(1,2); 
    g.addEdge(2,3); 
    g.addEdge(1,2); 

    Set<Integer> possibilityA = new HashSet<>();
    possibilityA.add(0);
    possibilityA.add(2);

    Set<Integer> kernel = DigraphKernel.findKernelInefficent(g);
    assertTrue(kernel.equals(possibilityA));
  }

  @Test
  void myFifthPositiveKernelTest() {
    Graph g = new AdjMatrix(9);
    g.addEdge(0, 1); 
    g.addEdge(0, 2); 
    g.addEdge(0, 3); 
    g.addEdge(0, 4); 
    g.addEdge(1,5); 
    g.addEdge(1,6); 
    g.addEdge(4,6); 
    g.addEdge(4,7); 
    g.addEdge(3,7); 
    g.addEdge(3,8); 
    g.addEdge(2,5); 
    g.addEdge(2,8); 

    Set<Integer> possibilityA = new HashSet<>();
    possibilityA.add(0);
    possibilityA.add(5);
    possibilityA.add(6);
    possibilityA.add(7);
    possibilityA.add(8);

    Set<Integer> kernel = DigraphKernel.findKernelInefficent(g);
    assertTrue(kernel.equals(possibilityA));
  }

  @Test
  void mySixthPositiveKernelTest() {
    Graph g = new AdjMatrix(3);
    g.addEdge(0, 1); 
    g.addEdge(1,0); 
    g.addEdge(1,2); 
    g.addEdge(2,1); 

    Set<Integer> possibilityA = new HashSet<>();
    possibilityA.add(0);
    possibilityA.add(2);

    Set<Integer> possibilityB = new HashSet<>();
    possibilityA.add(1);

    Set<Integer> kernel = DigraphKernel.findKernelInefficent(g);
    assertTrue(kernel.equals(possibilityA) || kernel.equals(possibilityB));
  }

  @Test
  void mySeventhPositiveKernelTest() {  // modified star graph
    Graph g = new AdjMatrix(5);
    g.addEdge(0, 1); 
    g.addEdge(1,2); 
    g.addEdge(2,3); 
    g.addEdge(3,4); 
    g.addEdge(4,0); 
    g.addEdge(1,3); 

    Set<Integer> possibilityA = new HashSet<>();
    possibilityA.add(0);
    possibilityA.add(3);

    Set<Integer> kernel = DigraphKernel.findKernelInefficent(g);
    assertTrue(kernel.equals(possibilityA));
  }

  @Test
  void myEighthPositiveKernelTest() {
    Graph g = new AdjMatrix(4);
    g.addEdge(0, 1); 
    g.addEdge(0,2); 
    g.addEdge(2,3); 

    Set<Integer> possibilityA = new HashSet<>();
    possibilityA.add(1);
    possibilityA.add(3);

    Set<Integer> kernel = DigraphKernel.findKernelInefficent(g);
    assertTrue(kernel.equals(possibilityA));
  }

  @Test
  void myNinthPositiveKernelTest() {
    Graph g = new AdjMatrix(4);
    g.addEdge(0,2); 
    g.addEdge(1,2); 
    g.addEdge(1,3); 
    g.addEdge(3,2); 

    Set<Integer> possibilityA = new HashSet<>();
    possibilityA.add(2);

    Set<Integer> kernel = DigraphKernel.findKernelInefficent(g);
    assertTrue(kernel.equals(possibilityA));
  }

  @Test
  void myTenthPositiveKernelTest() {
    Graph g = new AdjMatrix(5);
    g.addEdge(0,2); 
    g.addEdge(0,3);
    g.addEdge(1,2); 
    g.addEdge(1,4); 
    g.addEdge(3,1); 
    g.addEdge(4,2);

    Set<Integer> possibilityA = new HashSet<>();
    possibilityA.add(2);
    possibilityA.add(3);

    Set<Integer> kernel = DigraphKernel.findKernelInefficent(g);
    assertTrue(kernel.equals(possibilityA));
  }
  
  @Test
  void myEleventhPositiveKernelTest() {
    Graph g = new AdjMatrix(5);
    g.addEdge(0,2); 
    g.addEdge(0,1); 
    g.addEdge(1,2); 
    g.addEdge(1,4); 
    g.addEdge(2,4);
    g.addEdge(3,0);
    g.addEdge(3,1); 

    Set<Integer> possibilityA = new HashSet<>();
    possibilityA.add(0);
    possibilityA.add(4);

    Set<Integer> kernel = DigraphKernel.findKernelInefficent(g);
    assertTrue(kernel.equals(possibilityA));
  }

  @Test
  void myTwelfthPositiveKernelTest() {
    Graph g = new AdjMatrix(5);
    g.addEdge(0,1); 
    g.addEdge(0,2); 
    g.addEdge(0,3);
    g.addEdge(1,2); 
    g.addEdge(1,4); 
    g.addEdge(2,4);
    g.addEdge(3,1); 

    Set<Integer> possibilityA = new HashSet<>();
    possibilityA.add(0);
    possibilityA.add(4);

    Set<Integer> kernel = DigraphKernel.findKernelInefficent(g);
    assertTrue(kernel.equals(possibilityA));
  }





  // -------------------------------------------------------------
  // Negative Kernel Tests
  // -------------------------------------------------------------

  @Test
  void myFirstNegativeKernelTestInefficent() {
    Graph g = new AdjMatrix(4);
    g.addEdge(0, 2); 
    g.addEdge(2,3); 
    g.addEdge(1,3); 
    g.addEdge(1,0); 

    Set<Integer> kernel = DigraphKernel.findKernelInefficent(g);
    assertTrue(kernel.isEmpty());
  }

  @Test
  void mySecondNegativeKernelTestInefficent() {
    Graph g = new AdjMatrix(4);
    g.addEdge(0, 1); 
    g.addEdge(1,2); 
    g.addEdge(2,0); 

    Set<Integer> kernel = DigraphKernel.findKernelInefficent(g);
    assertTrue(kernel.isEmpty());
  }

  @Test
  void myThirdNegativeKernelTestInefficent() {
    Graph g = new AdjMatrix(4);
    g.addEdge(0, 1); 
    g.addEdge(1,2); 
    g.addEdge(0,2); 

    Set<Integer> kernel = DigraphKernel.findKernelInefficent(g);
    assertTrue(kernel.isEmpty());
  }

  @Test
  void myFourthNegativeKernelTestInefficent() {
    Graph g = new AdjMatrix(4);
    g.addEdge(0, 1); 
    g.addEdge(1,0); 
    g.addEdge(0,2); 
    g.addEdge(2,3); 

    Set<Integer> kernel = DigraphKernel.findKernelInefficent(g);
    assertTrue(kernel.isEmpty());
  }

  @Test
  void myFifthNegativeKernelTestInefficent() {
    Graph g = new AdjMatrix(4);
    g.addEdge(0, 2); 
    g.addEdge(2,1); 
    g.addEdge(1,3); 
    g.addEdge(2,3); 
    g.addEdge(3,2); 

    Set<Integer> kernel = DigraphKernel.findKernelInefficent(g);
    assertTrue(kernel.isEmpty());
  }

  @Test
  void mySixthNegativeKernelTestInefficent() {  // star graph
    Graph g = new AdjMatrix(5);
    g.addEdge(0, 1); 
    g.addEdge(1,2); 
    g.addEdge(2,3); 
    g.addEdge(3,4); 
    g.addEdge(4,0); 

    Set<Integer> kernel = DigraphKernel.findKernelInefficent(g);
    assertTrue(kernel.isEmpty());
  }

  

  
}
