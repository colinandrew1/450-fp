/**
 * CPSC 450, Final Project
 * 
 * NAME: Colin McClelland
 * DATE: Fall 2024
 *
 * Unit tests for final project - finding kernel in a digraph
 */

package cpsc450;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;


class ProjectTest {

  // -------------------------------------------------------------
  // Positive Kernel Tests
  // -------------------------------------------------------------

  @Test
  void myFirstPositiveKernelTest() {  // Bipartite case - cycle
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
  void mySecondPositiveKernelTest() { // not a DAG, no TS exists, not bipartite
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
  void myThirdPositiveKernelTest() {  // not a DAG, not bipartite
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
  void myFourthPositiveKernelTest() { // DAG, not bipartite
    Graph g = new AdjMatrix(4);
    g.addEdge(0, 1); 
    g.addEdge(1,2); 
    g.addEdge(2,3); 
    g.addEdge(1,3); 
    

    Set<Integer> possibilityA = new HashSet<>();
    possibilityA.add(0);
    possibilityA.add(3);

    Set<Integer> kernel = DigraphKernel.findKernelInefficent(g);
    assertTrue(kernel.equals(possibilityA));
  }

  @Test
  void myFifthPositiveKernelTest() {  // DAG, bipartite
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
  void mySixthPositiveKernelTest() {  // not a DAG, Bipartite
    Graph g = new AdjMatrix(3);
    g.addEdge(0, 1); 
    g.addEdge(1,0); 
    g.addEdge(1,2); 
    g.addEdge(2,1); 

    Set<Integer> possibilityA = new HashSet<>();
    possibilityA.add(0);
    possibilityA.add(2);

    Set<Integer> possibilityB = new HashSet<>();
    possibilityB.add(1);

    Set<Integer> kernel = DigraphKernel.findKernelInefficent(g);
    assertTrue(kernel.equals(possibilityA) || kernel.equals(possibilityB));
  }

  @Test
  void mySeventhPositiveKernelTest() {  // modified star graph - not a DAG, not bipartite
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
  void myEighthPositiveKernelTest() { // DAG, bipartite
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
  void myNinthPositiveKernelTest() {  // DAG, not bipartite
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
  void myTenthPositiveKernelTest() {  // DAG, not bipartite
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
  void myEleventhPositiveKernelTest() { // DAG, not bipartite
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
  void myTwelfthPositiveKernelTest() {  // DAG, not bipartite
    Graph g = new AdjMatrix(5);
    g.addEdge(0,1); 
    g.addEdge(0,2); 
    g.addEdge(0,3);
    g.addEdge(1,2); 
    g.addEdge(1,4); 
    g.addEdge(2,4);
    g.addEdge(3,1); 

    Set<Integer> possibilityA = new HashSet<>();
    possibilityA.add(3);
    possibilityA.add(4);

    Set<Integer> kernel = DigraphKernel.findKernelInefficent(g);
    assertTrue(kernel.equals(possibilityA));
  }

  @Test
  void myThirteenthPositiveKernelTestInefficent() {  // DAG, not bipartite
    Graph g = new AdjMatrix(3);
    g.addEdge(0, 1); 
    g.addEdge(1,2); 
    g.addEdge(0,2); 

    Set<Integer> possibilityA = new HashSet<>();
    possibilityA.add(2);

    Set<Integer> kernel = DigraphKernel.findKernelInefficent(g);
    assertTrue(kernel.equals(possibilityA));
  }

  @Test
  void myFourteenththPositiveKernelTestInefficent() { // DAG, bipartite
    Graph g = new AdjMatrix(5);
    g.addEdge(0, 1); 
    g.addEdge(0, 2); 
    g.addEdge(0, 3); 
    g.addEdge(0, 4); 

    Set<Integer> possibilityA = new HashSet<>(); 
    possibilityA.add(1);
    possibilityA.add(2);
    possibilityA.add(3);
    possibilityA.add(4);

    Set<Integer> kernel = DigraphKernel.findKernelInefficent(g);
    assertTrue(kernel.equals(possibilityA));
  }

  @Test
  void myFifteenthPositiveKernelTestInefficent() {  // DAG, bipartite
    Graph g = new AdjMatrix(5);
    g.addEdge(1,0); 
    g.addEdge(2,0); 
    g.addEdge(3,0); 
    g.addEdge(4,0); 
    
    Set<Integer> possibilityA = new HashSet<>();
    possibilityA.add(0);

    Set<Integer> kernel = DigraphKernel.findKernelInefficent(g);
    assertTrue(kernel.equals(possibilityA));
  }

  @Test
  void mySixteenthPositiveKernelTest() {  // modified graph from positive test 3 -- now a DAG, not bipartite, different kernel exists
    Graph g = new AdjMatrix(8);
    g.addEdge(0,1);
    g.addEdge(1,4); 
    g.addEdge(1,3); 
    g.addEdge(0,2);
    g.addEdge(3,2); 
    g.addEdge(3,5); 
    g.addEdge(5,7); 
    g.addEdge(5,6); 
    g.addEdge(6,7); 
    g.addEdge(4,6); 
    
    Set<Integer> possibilityA = new HashSet<>();
    possibilityA.add(2);
    possibilityA.add(4);
    possibilityA.add(7);

    Set<Integer> kernel = DigraphKernel.findKernelInefficent(g);
    assertTrue(kernel.equals(possibilityA));
  }

  @Test
  void mySeventeenthPositiveKernelTest() {  // not a DAG, Bipartite
    Graph g = new AdjMatrix(4);
    g.addEdge(0,1);
    g.addEdge(1,0); 
    g.addEdge(1,2); 
    g.addEdge(2,1);
    g.addEdge(2,3);
    g.addEdge(3,2);  
 
    
    Set<Integer> possibilityA = new HashSet<>();
    possibilityA.add(0);
    possibilityA.add(2);

    Set<Integer> possibilityB = new HashSet<>();
    possibilityA.add(0);
    possibilityA.add(3);

    Set<Integer> possibilityC = new HashSet<>();
    possibilityA.add(2);
    possibilityA.add(4);

    Set<Integer> kernel = DigraphKernel.findKernelInefficent(g);
    assertTrue(kernel.equals(possibilityA) || kernel.equals(possibilityB) || kernel.equals(possibilityC));
  }

  @Test
  void myEighteenthPositiveKernelTest() { // not a DAG, not bipartite
    Graph g = new AdjMatrix(4);
    g.addEdge(0, 1); 
    g.addEdge(1,2); 
    g.addEdge(2,3); 
    g.addEdge(1,3); 
    g.addEdge(3,1); 

    Set<Integer> possibilityA = new HashSet<>();
    possibilityA.add(0);
    possibilityA.add(3);

    Set<Integer> kernel = DigraphKernel.findKernelInefficent(g);
    assertTrue(kernel.equals(possibilityA));
  }

  @Test
  void myNinteenthPositiveKernelTest() { // DAG, not bipartite
    Graph g = new AdjMatrix(4);
    g.addEdge(0, 1); 
    g.addEdge(0,3); 
    g.addEdge(1,2); 
    g.addEdge(1,4); 
    g.addEdge(3,1); 
    g.addEdge(3,4); 
    g.addEdge(4,2); 

    Set<Integer> possibilityA = new HashSet<>();
    possibilityA.add(2);
    possibilityA.add(3);

    Set<Integer> kernel = DigraphKernel.findKernelInefficent(g);
    assertTrue(kernel.equals(possibilityA));
  }

  @Test
  void myTwentiethPositiveKernelTest() {
    Graph g = new AdjMatrix(4);
    g.addEdge(0, 2); 
    g.addEdge(2,3); 
    g.addEdge(1,3); 
    g.addEdge(1,0); 

    Set<Integer> possibilityA = new HashSet<>();
    possibilityA.add(0);
    possibilityA.add(3);

    Set<Integer> kernel = DigraphKernel.findKernelInefficent(g);
    assertTrue(kernel.equals(possibilityA));

  }
  
  @Test
  void myTwentyfirstPositiveKernelTest () {
    Graph g = new AdjMatrix(10);
    g.addEdge(0,1);
    g.addEdge(1,2);
    g.addEdge(1,4);
    g.addEdge(2,3);
    g.addEdge(3,6);
    g.addEdge(3,7);
    g.addEdge(3,4);
    g.addEdge(4,5);
    g.addEdge(5,9);
    g.addEdge(6,8);
    g.addEdge(8,9);

    Set<Integer> possibilityA = new HashSet<>();
    possibilityA.add(0);
    possibilityA.add(2);
    possibilityA.add(4);
    possibilityA.add(6);
    possibilityA.add(7);
    possibilityA.add(9);

    Set<Integer> kernel = DigraphKernel.findKernelInefficent(g);
    assertTrue(kernel.equals(possibilityA));
  }

  @Test
  void myTwentysecondPositiveKernelTest () {
    Graph g = new AdjMatrix(13);
    g.addEdge(0, 1);
    g.addEdge(0, 2);
    g.addEdge(0, 3);
    g.addEdge(0, 4);
    g.addEdge(1, 2);
    g.addEdge(1, 4);
    g.addEdge(1, 5);
    g.addEdge(2, 3);
    g.addEdge(2, 6);
    g.addEdge(3, 4);
    g.addEdge(3, 7);
    g.addEdge(4, 8);
    g.addEdge(5, 6);
    g.addEdge(5, 8);
    g.addEdge(5, 9);
    g.addEdge(6, 7);
    g.addEdge(6, 10);
    g.addEdge(7, 8);
    g.addEdge(7, 11);
    g.addEdge(8, 12);
    g.addEdge(9, 10);
    g.addEdge(9, 12);
    g.addEdge(10, 11);
    g.addEdge(11, 12);

    Set<Integer> possibilityA = new HashSet<>();
    possibilityA.add(1);
    possibilityA.add(2);
    possibilityA.add(3);
    possibilityA.add(4);
    possibilityA.add(9);
    possibilityA.add(10);
    possibilityA.add(11);
    possibilityA.add(12);

    Set<Integer> kernel = DigraphKernel.findKernelInefficent(g);
    assertTrue(kernel.equals(possibilityA));

  }

  @Test
  void myTwentythirdPositiveKernelTest () { // DAG, not bipartite
    Graph g = new AdjMatrix(10);
    g.addEdge(0,1);
    g.addEdge(1,2);
    g.addEdge(0,2);
    g.addEdge(1,3);
    g.addEdge(2,3);
    g.addEdge(3,4);
    g.addEdge(4,5);
    g.addEdge(4,6);
    g.addEdge(5,6);
    g.addEdge(5,7);
    g.addEdge(6,7);
    g.addEdge(7,8);
    g.addEdge(8,9);

    Set<Integer> possibilityA = new HashSet<>();
    possibilityA.add(2);
    possibilityA.add(4);
    possibilityA.add(7);
    possibilityA.add(9);

    Set<Integer> kernel = DigraphKernel.findKernelInefficent(g);
    assertTrue(kernel.equals(possibilityA));
  }

  @Test
  void myTwentyfourthPositiveKernelTest() { // DAG, bipartite
    Graph g = new AdjMatrix(1);

    Set<Integer> possibilityA = new HashSet<>();
    possibilityA.add(0);

    Set<Integer> kernel = DigraphKernel.findKernelInefficent(g);
    assertTrue(kernel.equals(possibilityA));
  }

  @Test
  void myTwentyfifthPositiveKernelTest() { //  DAG, bipartite
    Graph g = new AdjMatrix(2);
    g.addEdge(0, 1);

    Set<Integer> possibilityA = new HashSet<>();
    possibilityA.add(1);

    Set<Integer> kernel = DigraphKernel.findKernelInefficent(g);
    assertTrue(kernel.equals(possibilityA));
  }
  
  // -------------------------------------------------------------
  // Negative Kernel Tests
  // -------------------------------------------------------------


  @Test
  void mySecondNegativeKernelTestInefficent() { // not a DAG, not bipartite
    Graph g = new AdjMatrix(3);
    g.addEdge(0, 1); 
    g.addEdge(1,2); 
    g.addEdge(2,0); 

    Set<Integer> kernel = DigraphKernel.findKernelInefficent(g);
    assertTrue(kernel.isEmpty());
  }

  @Test
  void myFourthNegativeKernelTestInefficent() { // not a DAG, not bipartite
    Graph g = new AdjMatrix(4);
    g.addEdge(0, 1); 
    g.addEdge(1,0); 
    g.addEdge(0,2); 
    g.addEdge(2,3); 

    Set<Integer> kernel = DigraphKernel.findKernelInefficent(g);
    assertTrue(kernel.isEmpty());
  }

  @Test
  void myFifthNegativeKernelTestInefficent() {  // not a DAG, not bipartite
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
  void mySixthNegativeKernelTestInefficent() {  // star graph - not a DAG, not bipartite
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
