package de.frauas.java.projectWS1920.models;

import java.util.ArrayList;
import java.util.List;

public class Graph {

    private int id;
    private String name;
    // todo: which kind of list should we use? => need to iterate over the list
    private List<Node> nodes = new ArrayList<>();
    private List<Edge> edges = new ArrayList<>();
    // diameter is the largest "shortest distance" between two nodes
    // if all weights are whole numbers the diameter must be too
    private int diameter;
    // if all nodes are connected to each other (directly or indirectly)
    private boolean connected;


    // todo: constructor, getters, setters
    public List<Node> getNodes() {
        return nodes;
    }

    public List<Edge> getEdges() {
        return edges;
    }




    public void addNode(Node newNode) {
        this.nodes.add(newNode);
    }

    public void addEdge(Edge newEdge) {
        this.edges.add(newEdge);
    }

    // set the adjacent nodes
    public void setAdjacentNodes() {
        for (Edge edge : this.edges) {
            edge.getDestinationNode().adjacentNodes.put(edge.getOriginNode(), edge.getWeight());
            edge.getOriginNode().adjacentNodes.put(edge.getDestinationNode(), edge.getWeight());
        }
    }
}
