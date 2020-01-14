package de.frauas.java.projectWS1920.models;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MyNode {

    private int nodeId;
    // key is an adjacent node, value is the weight of connecting edge
    private HashMap<MyNode, Integer> adjacentNodes = new HashMap<>();
    // key is a connected node, value is the length of the path
    private HashMap<MyNode, Integer> shortestPaths = new HashMap<>();
    // key is a connected node, value is the previous node
    private HashMap<MyNode, MyNode> previousNodes = new HashMap<>();

    public MyNode(int nodeId) {
        this.nodeId = nodeId;
    }

    public int getNodeId() { return this.nodeId; }

    public void addAdjacent(MyNode node, int weight) {
        this.adjacentNodes.put(node, weight);
    }

    public Map<MyNode, Integer> getAdjacentNodes() {
        return adjacentNodes;
    }

    public Map<MyNode, Integer> getShortestPaths() {
        return shortestPaths;
    }

    public int getShortestPathLengthTo(MyNode destinationNode) {
        return this.shortestPaths.get(destinationNode);
    }

    // linked for adding nodes at the beginning
    public LinkedList<MyNode> getShortestPathDirectionsTo(MyNode destinationNode) {
        LinkedList<MyNode> directions = new LinkedList<>();
        directions.add(destinationNode);
        if (destinationNode.equals(this)) {
            return directions;
        }
        MyNode previousNode = this.previousNodes.get(destinationNode);
        do {
            directions.addFirst(previousNode);
            destinationNode = previousNode;
            previousNode = this.previousNodes.get(destinationNode);
        } while (!destinationNode.equals(this)); // didn't work with == add to documentation
        return directions;
    }

    public void updateShortestPath(MyNode destinationNode, int pathLength) {
        this.shortestPaths.put(destinationNode, pathLength);
    }

    public void addCurrentPreviousNodePair(MyNode currentNode, MyNode previousNode) {
        this.previousNodes.put(currentNode, previousNode);
    }

    public void print(){
        System.out.println("Node: "+ nodeId);
        System.out.println("_Neighbors_");
        for (Map.Entry<MyNode, Integer> nodeEntry: adjacentNodes.entrySet()
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


