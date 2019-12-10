package de.frauas.java.projectWS1920.models;

public class Edge {

    int edgeId;
    Node originNode;
    Node destinationNode;
    int weight;

    public Edge(int edgeId, Node originNode, Node destinationNode, int weight) {

        this.edgeId = edgeId;
        this.originNode = originNode;
        this.destinationNode = destinationNode;
        this.weight = weight;

    }

    public Node getOriginNode() {
        return originNode;
    }

    public Node getDestinationNode() {
        return destinationNode;
    }

    public int getWeight() {
        return weight;
    }

}
