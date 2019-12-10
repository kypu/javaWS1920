package de.frauas.java.projectWS1920.models;

import java.util.HashMap;
import java.util.Map;

public class Node {

    int nodeId;
    // in order to implement dijkstra, we need easy access to the adjacent nodes and how far away they are -
    // use some kind of map with key = neighbouring node and value = shortest distance to the neighbouring node -
    // todo: which kind of map should we use?
    Map<Node, Integer> adjacentNodes = new HashMap<>();

    // constructor with only nodeId. Adjacent nodes can be added later
    // (have to wait until all nodes exist before adjacent nodes can be added)
    public Node(int nodeId) {
        this.nodeId = nodeId;
    }

    // this method is called by Graph.setAdjacentNodes
    public void addAdjacent(Node node, int weight) {
        this.adjacentNodes.put(node, weight);
    }

    public Map<Node, Integer> getAdjacentNodes() {
        return adjacentNodes;
    }
}
