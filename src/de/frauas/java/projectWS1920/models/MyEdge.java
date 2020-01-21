package de.frauas.java.projectWS1920.models;

public class MyEdge {
    private int edgeId;
    private MyNode originNode;
    private MyNode destinationNode;
    private int weight;

    public MyEdge(int edgeId, MyNode originNode, MyNode destinationNode, int weight) {
        this.edgeId = edgeId;
        this.originNode = originNode;
        this.destinationNode = destinationNode;
        this.weight = weight;
    }

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

    public void print() {
        System.out.println("Edge: " + edgeId);
        System.out.println("Weight: " + weight);
        System.out.println("Edge between Node" + originNode.getNodeId() + " and Node " + destinationNode.getNodeId());
    }
}
