package de.frauas.java.projectWS1920.models;

import java.util.HashMap;
import java.util.Map;

public class Node {

    int nodeId;
    // in order to implement dijkstra, we need easy access to the adjacent nodes and how far away they are -
    // use some kind of map with key = neighbouring node and value = shortest distance to the neighbouring node -
    // todo: which kind of map should we use?
    Map<Node, Integer> adjacentNodes = new HashMap<>();

    // todo: also what kind of map? -> should be able to justify in presentation
    Map<Node, Integer> shortestPaths = new HashMap<>();

    // constructor with only nodeId. Adjacent nodes can be added later
    // (have to wait until all nodes exist before adjacent nodes can be added)
    public Node(int nodeId) {
        this.nodeId = nodeId;
    }

    public int getNodeId() { return this.nodeId; }

    // this method is called by Graph.setAdjacentNodes
    public void addAdjacent(Node node, int weight) {
        this.adjacentNodes.put(node, weight);
    }

    public Map<Node, Integer> getAdjacentNodes() {
        return adjacentNodes;
    }

    public int getShortestPathTo(Node destinationNode) {
        return this.shortestPaths.get(destinationNode);
    }

    //for dijkstra, called by calculateShortestPaths in graph to initialise shortestPaths
    public void addShortestPath(Node destinationNode, int pathLength) {
        this.shortestPaths.put(destinationNode, pathLength);
    }

    //
    public void updateShortestPath(Node destinationNode, int pathLength) {
        this.shortestPaths.put(destinationNode, pathLength);
    }

    public void print(){
        System.out.println("Node: "+ nodeId);
        System.out.println("_Neighbors_");
        for (Map.Entry<Node, Integer> nodeEntry: adjacentNodes.entrySet()
             ) {
            System.out.println("Node: "+ nodeEntry.getKey().getNodeId() + " Weight: "+ nodeEntry.getValue());
        }
    }
}


