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
  void myFirstPositiveKernelTestNaive() {  // not a DAG, not bipartite
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

    Set<Integer> kernel = DigraphKernel.findKernelNaive(g);
    assertTrue(kernel.equals(possibilityA) || kernel.equals(possibilityB));
  }

  @Test
  void mySecondPositiveKernelTestNaive() { // not a DAG, no TS exists, not bipartite
    Graph g = new AdjMatrix(4);
    g.addEdge(0, 1); 
    g.addEdge(1,2); 
    g.addEdge(1,3); 
    g.addEdge(3,2); 
    g.addEdge(3,0);

    Set<Integer> possibilityA = new HashSet<>();
    possibilityA.add(0);
    possibilityA.add(2);

    Set<Integer> kernel = DigraphKernel.findKernelNaive(g);
    assertTrue(kernel.equals(possibilityA));
  }

  @Test
  void myThirdPositiveKernelTestNaive() {  // not a DAG, not bipartite
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

    Set<Integer> kernel = DigraphKernel.findKernelNaive(g);
    assertTrue(kernel.equals(possibilityA));
  }

  @Test
  void myFourthPositiveKernelTestNaive() { // DAG, not bipartite
    Graph g = new AdjMatrix(4);
    g.addEdge(0, 1); 
    g.addEdge(1,2); 
    g.addEdge(2,3); 
    g.addEdge(1,3); 
    
    Set<Integer> possibilityA = new HashSet<>();
    possibilityA.add(0);
    possibilityA.add(3);

    Set<Integer> kernel = DigraphKernel.findKernelNaive(g);
    assertTrue(kernel.equals(possibilityA));
  }

  @Test
  void myFifthPositiveKernelTestNaive() {  // DAG, bipartite
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

    Set<Integer> kernel = DigraphKernel.findKernelNaive(g);
    assertTrue(kernel.equals(possibilityA));
  }

  @Test
  void mySixthPositiveKernelTestNaive() {  // not a DAG, Bipartite
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

    Set<Integer> kernel = DigraphKernel.findKernelNaive(g);
    assertTrue(kernel.equals(possibilityA) || kernel.equals(possibilityB));
  }

  @Test
  void mySeventhPositiveKernelTestNaive() {  // modified star graph - not a DAG, not bipartite
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

    Set<Integer> kernel = DigraphKernel.findKernelNaive(g);
    assertTrue(kernel.equals(possibilityA));
  }

  @Test
  void myEighthPositiveKernelTestNaive() { // DAG, bipartite
    Graph g = new AdjMatrix(4);
    g.addEdge(0, 1); 
    g.addEdge(0,2); 
    g.addEdge(2,3); 

    Set<Integer> possibilityA = new HashSet<>();
    possibilityA.add(1);
    possibilityA.add(3);

    Set<Integer> kernel = DigraphKernel.findKernelNaive(g);

    assertTrue(kernel.equals(possibilityA));
  }

  @Test
  void myNinthPositiveKernelTestNaive() {  // DAG, not bipartite
    Graph g = new AdjMatrix(4);
    g.addEdge(0,2); 
    g.addEdge(1,2); 
    g.addEdge(1,3); 
    g.addEdge(3,2); 

    Set<Integer> possibilityA = new HashSet<>();
    possibilityA.add(2);

    Set<Integer> kernel = DigraphKernel.findKernelNaive(g);
    assertTrue(kernel.equals(possibilityA));
  }

  @Test
  void myTenthPositiveKernelTestNaive() {  // DAG, not bipartite
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

    Set<Integer> kernel = DigraphKernel.findKernelNaive(g);
    assertTrue(kernel.equals(possibilityA));
  }
  
  @Test
  void myEleventhPositiveKernelTestNaive() { // DAG, not bipartite
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

    Set<Integer> kernel = DigraphKernel.findKernelNaive(g);
    assertTrue(kernel.equals(possibilityA));
  }

  @Test
  void myTwelfthPositiveKernelTestNaive() {  // DAG, not bipartite
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

    Set<Integer> kernel = DigraphKernel.findKernelNaive(g);
    assertTrue(kernel.equals(possibilityA));
  }

  @Test
  void myThirteenthPositiveKernelTestNaiveInefficent() {  // DAG, not bipartite
    Graph g = new AdjMatrix(3);
    g.addEdge(0, 1); 
    g.addEdge(1,2); 
    g.addEdge(0,2); 

    Set<Integer> possibilityA = new HashSet<>();
    possibilityA.add(2);

    Set<Integer> kernel = DigraphKernel.findKernelNaive(g);
    assertTrue(kernel.equals(possibilityA));
  }

  @Test
  void myFourteenththPositiveKernelTestNaiveInefficent() { // DAG, bipartite
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

    Set<Integer> kernel = DigraphKernel.findKernelNaive(g);
    assertTrue(kernel.equals(possibilityA));
  }

  @Test
  void myFifteenthPositiveKernelTestNaiveInefficent() {  // DAG, bipartite
    Graph g = new AdjMatrix(5);
    g.addEdge(1,0); 
    g.addEdge(2,0); 
    g.addEdge(3,0); 
    g.addEdge(4,0); 
    
    Set<Integer> possibilityA = new HashSet<>();
    possibilityA.add(0);

    Set<Integer> kernel = DigraphKernel.findKernelNaive(g);
    assertTrue(kernel.equals(possibilityA));
  }

  @Test
  void mySixteenthPositiveKernelTestNaive() {  // modified graph from positive test 3 -- now a DAG, not bipartite, different kernel exists
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

    Set<Integer> kernel = DigraphKernel.findKernelNaive(g);
    assertTrue(kernel.equals(possibilityA));
  }

  @Test
  void mySeventeenthPositiveKernelTestNaive() {  // not a DAG, Bipartite
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
    possibilityB.add(0);
    possibilityB.add(3);

    Set<Integer> possibilityC = new HashSet<>();
    possibilityC.add(1);
    possibilityC.add(3);

    Set<Integer> kernel = DigraphKernel.findKernelNaive(g);
    assertTrue(kernel.equals(possibilityA) || kernel.equals(possibilityB) || kernel.equals(possibilityC));
  }

  @Test
  void myEighteenthPositiveKernelTestNaive() { // not a DAG, not bipartite
    Graph g = new AdjMatrix(4);
    g.addEdge(0, 1); 
    g.addEdge(1,2); 
    g.addEdge(2,3); 
    g.addEdge(1,3); 
    g.addEdge(3,1); 

    Set<Integer> possibilityA = new HashSet<>();
    possibilityA.add(0);
    possibilityA.add(3);

    Set<Integer> kernel = DigraphKernel.findKernelNaive(g);
    assertTrue(kernel.equals(possibilityA));
  }

  @Test
  void myNinteenthPositiveKernelTestNaive() { // DAG, not bipartite
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

    Set<Integer> kernel = DigraphKernel.findKernelNaive(g);
    assertTrue(kernel.equals(possibilityA));
  }

  @Test
  void myTwentiethPositiveKernelTestNaive() {
    Graph g = new AdjMatrix(4);
    g.addEdge(0, 2); 
    g.addEdge(2,3); 
    g.addEdge(1,3); 
    g.addEdge(1,0); 

    Set<Integer> possibilityA = new HashSet<>();
    possibilityA.add(0);
    possibilityA.add(3);

    Set<Integer> kernel = DigraphKernel.findKernelNaive(g);
    assertTrue(kernel.equals(possibilityA));

  }
  
  @Test
  void myTwentyfirstPositiveKernelTestNaive () {
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

    Set<Integer> kernel = DigraphKernel.findKernelNaive(g);
    assertTrue(kernel.equals(possibilityA));
  }

  @Test
  void myTwentysecondPositiveKernelTestNaive () {
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
    possibilityA.add(2);
    possibilityA.add(4);
    possibilityA.add(5);
    possibilityA.add(7);
    possibilityA.add(10);
    possibilityA.add(12);

    Set<Integer> kernel = DigraphKernel.findKernelNaive(g);
    assertTrue(kernel.equals(possibilityA));
  }

  @Test
  void myTwentythirdPositiveKernelTestNaive () { // DAG, not bipartite
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

    Set<Integer> kernel = DigraphKernel.findKernelNaive(g);
    assertTrue(kernel.equals(possibilityA));
  }

  @Test
  void myTwentyfourthPositiveKernelTestNaive() { // DAG, bipartite
    Graph g = new AdjMatrix(1);

    Set<Integer> possibilityA = new HashSet<>();
    possibilityA.add(0);

    Set<Integer> kernel = DigraphKernel.findKernelNaive(g);
    assertTrue(kernel.equals(possibilityA));
  }

  @Test
  void myTwentyfifthPositiveKernelTestNaive() { //  DAG, bipartite
    Graph g = new AdjMatrix(2);
    g.addEdge(0, 1);

    Set<Integer> possibilityA = new HashSet<>();
    possibilityA.add(1);

    Set<Integer> kernel = DigraphKernel.findKernelNaive(g);
    assertTrue(kernel.equals(possibilityA));
  }
  
  @Test
  void myTwentysixthPositiveKernelTestNaive() { // not a DAG, not bipartite
    Graph g = new AdjMatrix(4);
    g.addEdge(0, 1); 
    g.addEdge(1,0); 
    g.addEdge(0,2); 
    g.addEdge(2,3); 

    Set<Integer> possibilityA = new HashSet<>();
    possibilityA.add(1);
    possibilityA.add(3);

    Set<Integer> possibilityB = new HashSet<>();
    possibilityB.add(0);
    possibilityB.add(3);

    Set<Integer> kernel = DigraphKernel.findKernelNaive(g);
    assertTrue(kernel.equals(possibilityA) || kernel.equals(possibilityB));
  }

  @Test
  void myTwentyseventhPositiveKernelTestNaive() {  // not a DAG, not bipartite
    Graph g = new AdjMatrix(4);
    g.addEdge(0, 2); 
    g.addEdge(2,1); 
    g.addEdge(1,3); 
    g.addEdge(2,3); 
    g.addEdge(3,2); 

    Set<Integer> possibilityA = new HashSet<>();
    possibilityA.add(0);
    possibilityA.add(3);

    Set<Integer> kernel = DigraphKernel.findKernelNaive(g);
    assertTrue(kernel.equals(possibilityA));
  }

  @Test
  void myTwentyeighthPositiveKernelTestNaive() {  // not a DAG, not bipartite
    Graph g = new AdjMatrix(10);
    g.addEdge(0,1);
    g.addEdge(1,4); 
    g.addEdge(1,3); 
    g.addEdge(1,8); 
    g.addEdge(8,4); 
    g.addEdge(2,0); 
    g.addEdge(3,2); 
    g.addEdge(3,5); 
    g.addEdge(3,9); 
    g.addEdge(5,7); 
    g.addEdge(5,6); 
    g.addEdge(6,7); 
    g.addEdge(4,6); 
    g.addEdge(9,1); 

    Set<Integer> possibilityA = new HashSet<>();
    possibilityA.add(0);
    possibilityA.add(4);
    possibilityA.add(7);
    possibilityA.add(9);

    Set<Integer> kernel = DigraphKernel.findKernelNaive(g);
    assertTrue(kernel.equals(possibilityA));
  }

  

  // -------------------------------------------------------------
  // Negative Kernel Tests
  // -------------------------------------------------------------



  @Test
  void myFirstNegativeKernelTestNaive() { // not a DAG, not bipartite
    Graph g = new AdjMatrix(3);
    g.addEdge(0, 1); 
    g.addEdge(1,2); 
    g.addEdge(2,0); 

    Set<Integer> kernel = DigraphKernel.findKernelNaive(g);
    assertTrue(kernel.isEmpty());
  }

  @Test
  void mySecondNegativeKernelTestNaivecnt() {  // star graph - not a DAG, not bipartite
    Graph g = new AdjMatrix(5);
    g.addEdge(0, 1); 
    g.addEdge(1,2); 
    g.addEdge(2,3); 
    g.addEdge(3,4); 
    g.addEdge(4,0); 

    Set<Integer> kernel = DigraphKernel.findKernelNaive(g);
    assertTrue(kernel.isEmpty());
  }
















  @Test
   void myFirstPositiveKernelTestOptimized() {  // not a DAG, not bipartite
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
 
     Set<Integer> kernel = DigraphKernel.findKernelOptimized(g);
     assertTrue(kernel.equals(possibilityA) || kernel.equals(possibilityB));
   }
 
   @Test
   void mySecondPositiveKernelTestOptimized() { // not a DAG, no TS exists, not bipartite
     Graph g = new AdjMatrix(4);
     g.addEdge(0, 1); 
     g.addEdge(1,2); 
     g.addEdge(1,3); 
     g.addEdge(3,2); 
     g.addEdge(3,0);
 
     Set<Integer> possibilityA = new HashSet<>();
     possibilityA.add(0);
     possibilityA.add(2);
 
     Set<Integer> kernel = DigraphKernel.findKernelOptimized(g);
     assertTrue(kernel.equals(possibilityA));
   }
 
   @Test
   void myThirdPositiveKernelTestOptimized() {  // not a DAG, not bipartite
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
 
     Set<Integer> kernel = DigraphKernel.findKernelOptimized(g);
     assertTrue(kernel.equals(possibilityA));
   }
 
   @Test
   void myFourthPositiveKernelTestOptimized() { // DAG, not bipartite
     Graph g = new AdjMatrix(4);
     g.addEdge(0, 1); 
     g.addEdge(1,2); 
     g.addEdge(2,3); 
     g.addEdge(1,3); 
     
     Set<Integer> possibilityA = new HashSet<>();
     possibilityA.add(0);
     possibilityA.add(3);
 
     Set<Integer> kernel = DigraphKernel.findKernelOptimized(g);
     assertTrue(kernel.equals(possibilityA));
   }
 
   @Test
   void myFifthPositiveKernelTestOptimized() {  // DAG, bipartite
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
 
     Set<Integer> kernel = DigraphKernel.findKernelOptimized(g);
     assertTrue(kernel.equals(possibilityA));
   }
 
   @Test
   void mySixthPositiveKernelTestOptimized() {  // not a DAG, Bipartite
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
 
     Set<Integer> kernel = DigraphKernel.findKernelOptimized(g);
     assertTrue(kernel.equals(possibilityA) || kernel.equals(possibilityB));
   }
 
   @Test
   void mySeventhPositiveKernelTestOptimized() {  // modified star graph - not a DAG, not bipartite
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
 
     Set<Integer> kernel = DigraphKernel.findKernelOptimized(g);
     assertTrue(kernel.equals(possibilityA));
   }
 
   @Test
   void myEighthPositiveKernelTestOptimized() { // DAG, bipartite
     Graph g = new AdjMatrix(4);
     g.addEdge(0, 1); 
     g.addEdge(0,2); 
     g.addEdge(2,3); 
 
     Set<Integer> possibilityA = new HashSet<>();
     possibilityA.add(1);
     possibilityA.add(3);
 
     Set<Integer> kernel = DigraphKernel.findKernelOptimized(g);
 
     assertTrue(kernel.equals(possibilityA));
   }
 
   @Test
   void myNinthPositiveKernelTestOptimized() {  // DAG, not bipartite
     Graph g = new AdjMatrix(4);
     g.addEdge(0,2); 
     g.addEdge(1,2); 
     g.addEdge(1,3); 
     g.addEdge(3,2); 
 
     Set<Integer> possibilityA = new HashSet<>();
     possibilityA.add(2);
 
     Set<Integer> kernel = DigraphKernel.findKernelOptimized(g);
     assertTrue(kernel.equals(possibilityA));
   }
 
   @Test
   void myTenthPositiveKernelTestOptimized() {  // DAG, not bipartite
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
 
     Set<Integer> kernel = DigraphKernel.findKernelOptimized(g);

     assertTrue(kernel.equals(possibilityA));
   }
   
   @Test
   void myEleventhPositiveKernelTestOptimized() { // DAG, not bipartite
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
 
     Set<Integer> kernel = DigraphKernel.findKernelOptimized(g);
     assertTrue(kernel.equals(possibilityA));
   }
 
   @Test
   void myTwelfthPositiveKernelTestOptimized() {  // DAG, not bipartite
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
 
     Set<Integer> kernel = DigraphKernel.findKernelOptimized(g);
     assertTrue(kernel.equals(possibilityA));
   }
 
   @Test
   void myThirteenthPositiveKernelTestOptimizedInefficent() {  // DAG, not bipartite
     Graph g = new AdjMatrix(3);
     g.addEdge(0, 1); 
     g.addEdge(1,2); 
     g.addEdge(0,2); 
 
     Set<Integer> possibilityA = new HashSet<>();
     possibilityA.add(2);
 
     Set<Integer> kernel = DigraphKernel.findKernelOptimized(g);
     assertTrue(kernel.equals(possibilityA));
   }
 
   @Test
   void myFourteenththPositiveKernelTestOptimizedInefficent() { // DAG, bipartite
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
 
     Set<Integer> kernel = DigraphKernel.findKernelOptimized(g);
     assertTrue(kernel.equals(possibilityA));
   }
 
   @Test
   void myFifteenthPositiveKernelTestOptimizedInefficent() {  // DAG, bipartite
     Graph g = new AdjMatrix(5);
     g.addEdge(1,0); 
     g.addEdge(2,0); 
     g.addEdge(3,0); 
     g.addEdge(4,0); 
     
     Set<Integer> possibilityA = new HashSet<>();
     possibilityA.add(0);
 
     Set<Integer> kernel = DigraphKernel.findKernelOptimized(g);
     assertTrue(kernel.equals(possibilityA));
   }
 
   @Test
   void mySixteenthPositiveKernelTestOptimized() {  // modified graph from positive test 3 -- now a DAG, not bipartite, different kernel exists
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
 
     Set<Integer> kernel = DigraphKernel.findKernelOptimized(g);
     assertTrue(kernel.equals(possibilityA));
   }
 
   @Test
   void mySeventeenthPositiveKernelTestOptimized() {  // not a DAG, Bipartite
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
     possibilityB.add(0);
     possibilityB.add(3);
 
     Set<Integer> possibilityC = new HashSet<>();
     possibilityC.add(1);
     possibilityC.add(3);
 
     Set<Integer> kernel = DigraphKernel.findKernelOptimized(g);
     assertTrue(kernel.equals(possibilityA) || kernel.equals(possibilityB) || kernel.equals(possibilityC));
   }
 
   @Test
   void myEighteenthPositiveKernelTestOptimized() { // not a DAG, not bipartite
     Graph g = new AdjMatrix(4);
     g.addEdge(0, 1); 
     g.addEdge(1,2); 
     g.addEdge(2,3); 
     g.addEdge(1,3); 
     g.addEdge(3,1); 
 
     Set<Integer> possibilityA = new HashSet<>();
     possibilityA.add(0);
     possibilityA.add(3);
 
     Set<Integer> kernel = DigraphKernel.findKernelOptimized(g);
     assertTrue(kernel.equals(possibilityA));
   }
 
   @Test
   void myNinteenthPositiveKernelTestOptimized() { // DAG, not bipartite
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
 
     Set<Integer> kernel = DigraphKernel.findKernelOptimized(g);
     assertTrue(kernel.equals(possibilityA));
   }
 
   @Test
   void myTwentiethPositiveKernelTestOptimized() {  // DAG
     Graph g = new AdjMatrix(4);
     g.addEdge(0, 2); 
     g.addEdge(2,3); 
     g.addEdge(1,3); 
     g.addEdge(1,0); 
 
     Set<Integer> possibilityA = new HashSet<>();
     possibilityA.add(0);
     possibilityA.add(3);
 
     Set<Integer> kernel = DigraphKernel.findKernelOptimized(g);
     assertTrue(kernel.equals(possibilityA));
 
   }
   
   @Test
   void myTwentyfirstPositiveKernelTestOptimized () { // DAG
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
 
     Set<Integer> kernel = DigraphKernel.findKernelOptimized(g);
     assertTrue(kernel.equals(possibilityA));
   }
 
   @Test
   void myTwentysecondPositiveKernelTestOptimized () {   // not a DAG, not bipartite
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
     possibilityA.add(2);
     possibilityA.add(4);
     possibilityA.add(5);
     possibilityA.add(7);
     possibilityA.add(10);
     possibilityA.add(12);
 
     Set<Integer> kernel = DigraphKernel.findKernelOptimized(g);
     assertTrue(kernel.equals(possibilityA));
   }
 
   @Test
   void myTwentythirdPositiveKernelTestOptimized () { // DAG, not bipartite
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
 
     Set<Integer> kernel = DigraphKernel.findKernelOptimized(g);
     assertTrue(kernel.equals(possibilityA));
   }
 
   @Test
   void myTwentyfourthPositiveKernelTestOptimized() { // DAG, bipartite
     Graph g = new AdjMatrix(1);
 
     Set<Integer> possibilityA = new HashSet<>();
     possibilityA.add(0);
 
     Set<Integer> kernel = DigraphKernel.findKernelOptimized(g);
     assertTrue(kernel.equals(possibilityA));
   }
 
   @Test
   void myTwentyfifthPositiveKernelTestOptimized() { //  DAG, bipartite
     Graph g = new AdjMatrix(2);
     g.addEdge(0, 1);
 
     Set<Integer> possibilityA = new HashSet<>();
     possibilityA.add(1);
 
     Set<Integer> kernel = DigraphKernel.findKernelOptimized(g);
     assertTrue(kernel.equals(possibilityA));
   }
   
   @Test
   void myTwentysixthPositiveKernelTestOptimized() { // not a DAG, not bipartite
     Graph g = new AdjMatrix(4);
     g.addEdge(0, 1); 
     g.addEdge(1,0); 
     g.addEdge(0,2); 
     g.addEdge(2,3); 
 
     Set<Integer> possibilityA = new HashSet<>();
     possibilityA.add(1);
     possibilityA.add(3);
 
     Set<Integer> possibilityB = new HashSet<>();
     possibilityB.add(0);
     possibilityB.add(3);
 
     Set<Integer> kernel = DigraphKernel.findKernelOptimized(g);
     assertTrue(kernel.equals(possibilityA) || kernel.equals(possibilityB));
   }
 
   @Test
   void myTwentyseventhPositiveKernelTestOptimized() {  // not a DAG, not bipartite
     Graph g = new AdjMatrix(4);
     g.addEdge(0, 2); 
     g.addEdge(2,1); 
     g.addEdge(1,3); 
     g.addEdge(2,3); 
     g.addEdge(3,2); 
 
     Set<Integer> possibilityA = new HashSet<>();
     possibilityA.add(0);
     possibilityA.add(3);
 
     Set<Integer> kernel = DigraphKernel.findKernelOptimized(g);
     assertTrue(kernel.equals(possibilityA));
   }
 
   @Test
   void myTwentyeighthPositiveKernelTestOptimized() {  // not a DAG, not bipartite
     Graph g = new AdjMatrix(10);
     g.addEdge(0,1);
     g.addEdge(1,4); 
     g.addEdge(1,3); 
     g.addEdge(1,8); 
     g.addEdge(8,4); 
     g.addEdge(2,0); 
     g.addEdge(3,2); 
     g.addEdge(3,5); 
     g.addEdge(3,9); 
     g.addEdge(5,7); 
     g.addEdge(5,6); 
     g.addEdge(6,7); 
     g.addEdge(4,6); 
     g.addEdge(9,1); 
 
     Set<Integer> possibilityA = new HashSet<>();
     possibilityA.add(0);
     possibilityA.add(4);
     possibilityA.add(7);
     possibilityA.add(9);
 
     Set<Integer> kernel = DigraphKernel.findKernelOptimized(g);
     assertTrue(kernel.equals(possibilityA));
   }
 
   
 
   // -------------------------------------------------------------
   // Negative Kernel Tests
   // -------------------------------------------------------------
 
 
 
   @Test
   void myFirstNegativeKernelTestOptimized() { // not a DAG, not bipartite
     Graph g = new AdjMatrix(3);
     g.addEdge(0, 1); 
     g.addEdge(1,2); 
     g.addEdge(2,0); 
 
     Set<Integer> kernel = DigraphKernel.findKernelOptimized(g);
     assertTrue(kernel.isEmpty());
   }
 
   @Test
   void mySecondNegativeKernelTestOptimized() {  // star graph - not a DAG, not bipartite
     Graph g = new AdjMatrix(5);
     g.addEdge(0, 1); 
     g.addEdge(1,2); 
     g.addEdge(2,3); 
     g.addEdge(3,4); 
     g.addEdge(4,0); 
 
     Set<Integer> kernel = DigraphKernel.findKernelOptimized(g);
     assertTrue(kernel.isEmpty());
   }
  
  
}
