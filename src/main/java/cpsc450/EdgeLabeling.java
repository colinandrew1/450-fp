/**
 * CPSC 450, HW-3
 *
 * NAME: Colin McClelland
 * DATE: Fall 2024
 */

package cpsc450;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


/**
 * An edge labeling for a given graph, where labels can be of any
 * type and a graph can have many edge labelings.
 */
public class EdgeLabeling<T> {

  private Graph graph;                                  // the graph to be labeled
  private HashMap<Integer,HashMap<Integer,T>> labels;   // the graph edge labels

  /**
   * Create an edge labeling for the given graph.
   * @param graph The graph to label.
   */ 
  public EdgeLabeling(Graph graph) {
    this.graph = graph;
    this.labels = new HashMap<>();
  }

  /**
   * Check to see if a label exists on an edge. Only returns true if
   * underlying graph still has the given edge. 
   * @param x The start (from) vertex of the edge.
   * @param y The end (to) vertex of the edge.
   * @returns True if the edge has a label, false otherwise.
   */
  public boolean hasLabel(int x, int y) {
    if (this.graph.hasEdge(x, y) && (labels.containsKey(x) && labels.get(x).containsKey(y))) {
        return true;
    }
    else if (!(this.graph.hasEdge(x, y)) && (labels.containsKey(x) && labels.get(x).containsKey(y))) {
        this.labels.get(x).remove(y);
        if (this.labels.get(x).isEmpty()) {
            this.labels.remove(x);
        }
        return false;
    }
    else {
        return false;
    }
  }
  
  /**
   * Add or overwrite an edge label. The edge must exist in the
   * underlying graph.
   * @param x The start (from) vertex of the edge.
   * @param y The end (to) vertex of the edge.
   */ 
  public void addLabel(int x, int y, T label) {
    if (graph.hasEdge(x, y)) {
        this.labels.putIfAbsent(x, new HashMap<>());
        this.labels.get(x).put(y, label);
    }
  }

  /**
   * Remove an edge label if it exists. 
   * @param x The start (from) vertex of the edge.
   * @param y The end (to) vertex of the edge.
   */ 
  public void removeLabel(int x, int y) {
    if (hasLabel(x, y)) {
        // need to delete a key if there are no labels associated with it because of how hasLabel() works
        this.labels.get(x).remove(y);
        if (this.labels.get(x).isEmpty()) {
            this.labels.remove(x);
        }
    }
  }

  /**
   * Returns the label for the given edge, if it exists. 
   * @param x The start (from) vertex of the edge.
   * @param y The end (to) vertex of the edge.
   * @returns The label or empty as an optional value.
   */ 
  public Optional<T> getLabel(int x, int y) {
    if (hasLabel(x, y)) {
        return Optional.ofNullable(this.labels.get(x).get(y));
    }
    return Optional.empty();
  }

  @Override
  public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("Labels:\n");

      // Iterate over the outer map
      for (Map.Entry<Integer, HashMap<Integer, T>> outerEntry : labels.entrySet()) {
          Integer outerKey = outerEntry.getKey();
          HashMap<Integer, T> innerMap = outerEntry.getValue();

          sb.append("Outer Key ").append(outerKey).append(" -> {");

          // Iterate over the inner map
          for (Map.Entry<Integer, T> innerEntry : innerMap.entrySet()) {
              Integer innerKey = innerEntry.getKey();
              T value = innerEntry.getValue();
              sb.append(innerKey).append(": ").append(value).append(", ");
          }

          // Remove trailing comma and space, add closing brace
          if (!innerMap.isEmpty()) {
              sb.setLength(sb.length() - 2);
          }
          sb.append("}\n");
      }

      return sb.toString();
  }
}
