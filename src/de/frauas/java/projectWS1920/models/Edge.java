package de.frauas.java.projectWS1920.models;

import java.util.Map;

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

    public int getEdgeId() {
        return edgeId;
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

    public void print(){
        System.out.println("Edge: "+ edgeId);
        System.out.println("Weight: "+weight);
        System.out.println("Edge between Node"+originNode.getNodeId()+" and Node"+destinationNode.getNodeId());

    }
}
