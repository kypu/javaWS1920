package de.frauas.java.projectWS1920.Models;

public class MyEdge {
    private int edgeId;
    private MyNode originNode;
    private MyNode destinationNode;
    private int weight;

    // CONSTRUCTOR
    public MyEdge(int edgeId, MyNode originNode, MyNode destinationNode, int weight) {
        this.edgeId = edgeId;
        this.originNode = originNode;
        this.destinationNode = destinationNode;
        this.weight = weight;
    }

    // GETTERS AND SETTERS
    public int getEdgeId() {
        return edgeId;
    }
    public MyNode getOriginNode() {
        return originNode;
    }
    public MyNode getDestinationNode() {
        return destinationNode;
    }
    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "Edge{Id=" + edgeId + "}";
    }
}
