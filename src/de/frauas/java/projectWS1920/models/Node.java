package de.frauas.java.projectWS1920.models;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Node {

    private int nodeId;
    // key is an adjacent node, value is the weight of connecting edge
    private HashMap<Node, Integer> adjacentNodes = new HashMap<>();
    // key is a connected node, value is the length of the path
    private HashMap<Node, Integer> shortestPaths = new HashMap<>();
    // key is a connected node, value is the previous node
    private HashMap<Node, Node> previousNodes = new HashMap<>();

    public Node(int nodeId) {
        this.nodeId = nodeId;
    }

    public int getNodeId() { return this.nodeId; }

    public void addAdjacent(Node node, int weight) {
        this.adjacentNodes.put(node, weight);
    }

    public Map<Node, Integer> getAdjacentNodes() {
        return adjacentNodes;
    }

    public Map<Node, Integer> getShortestPaths() {
        return shortestPaths;
    }

    public int getShortestPathLengthTo(Node destinationNode) {
        return this.shortestPaths.get(destinationNode);
    }

    // linked for adding nodes at the beginning
    public LinkedList<Node> getShortestPathDirectionsTo(Node destinationNode) {
        LinkedList<Node> directions = new LinkedList<>();
        directions.add(destinationNode);
        if (destinationNode.equals(this)) {
            return directions;
        }
        Node previousNode = this.previousNodes.get(destinationNode);
        do {
            directions.addFirst(previousNode);
            destinationNode = previousNode;
            previousNode = this.previousNodes.get(destinationNode);
        } while (!destinationNode.equals(this)); // didn't work with == add to documentation
        return directions;
    }

    public void updateShortestPath(Node destinationNode, int pathLength) {
        this.shortestPaths.put(destinationNode, pathLength);
    }

    public void addCurrentPreviousNodePair(Node currentNode, Node previousNode) {
        this.previousNodes.put(currentNode, previousNode);
    }

    public void print(){
        System.out.println("Node: "+ nodeId);
        System.out.println("_Neighbors_");
        for (Map.Entry<Node, Integer> nodeEntry: adjacentNodes.entrySet()
             ) {
            System.out.println("Node: "+ nodeEntry.getKey().getNodeId() + " Weight: "+ nodeEntry.getValue());
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "nodeId=" + nodeId +
                '}';
    }
}


