package de.frauas.java.projectWS1920.models;

import java.util.*;

public class Graph {

    private int id;
    private String name;
    // todo: which kind of list should we use?  => set not list!
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

    public Node getNodeById(int myNodeId) {
        for (Node currentNode : this.nodes) {
            if (currentNode.nodeId == myNodeId) {
                return currentNode;
            } // todo: else statement to return an exception if the node is not found
        }
        return null;
    }

    // set the adjacent nodes
    public void setAdjacentNodes() {
        for (Edge edge : this.edges) {
            edge.getDestinationNode().adjacentNodes.put(edge.getOriginNode(), edge.getWeight());
            edge.getOriginNode().adjacentNodes.put(edge.getDestinationNode(), edge.getWeight());
        }
    }

    // considered as a function in node but need access to all other nodes in the graph - graph-wide function
    // doesn't return the shortestPaths, just sets the attribute in the particular node
    public void calculateShortestPathsFrom(Node originNode) {
        // add all nodes to the unfinalised set to start with and remove one by one
        HashSet<Node> unfinalised = new HashSet<Node>(this.nodes);
        initialiseShortestPaths(originNode);

        while (!unfinalised.isEmpty()) {
            Node nodeInProgress = getNodeWithSmallestShortestPath(originNode, unfinalised);

            // we check all the adjacent nodes to our nodeInProgress to see if we can make an improvement
            for (Node adjacentToNodeInProgress : nodeInProgress.getAdjacentNodes().keySet()) {
                int currentPathLength = originNode.shortestPaths.get(adjacentToNodeInProgress);
                int pathLengthViaNodeInProgress = originNode.shortestPaths.get(nodeInProgress) +
                        nodeInProgress.adjacentNodes.get(adjacentToNodeInProgress);
                if (currentPathLength > pathLengthViaNodeInProgress) {
                    originNode.updateShortestPath(adjacentToNodeInProgress, pathLengthViaNodeInProgress);
                }
            }
            unfinalised.remove(nodeInProgress);
        }
    }


    // can be private because only called from Graph.calculateShortestPaths
    private void initialiseShortestPaths(Node originNode) {
        for (Node currentNode : this.nodes) {
            // todo: check if == or .equals() is better. Also check if  ? : syntax is possible
            if (currentNode == originNode) {
                originNode.addShortestPath(currentNode, 0);
            } else {
                originNode.addShortestPath(currentNode, Integer.MAX_VALUE);
            }
        }
    }

    private Node getNodeWithSmallestShortestPath(Node originNode, HashSet<Node> unfinalisedNodes) {
        // todo: if (unvisitedNodes is empty) { exception thrown }
        // initialise this to something? Are the nodes with 0 id?
        // lowest or shortest? do we want to distinguish? lowest = shortest shortest
        Node NodeWithSmallestShortestPath = originNode; // or don't initialise here and check before return;
        int smallestShortestPath = Integer.MAX_VALUE;
        for (Node currentNode : unfinalisedNodes) {
            if (originNode.shortestPaths.get(currentNode) < smallestShortestPath) {
                smallestShortestPath = originNode.shortestPaths.get(currentNode);
                NodeWithSmallestShortestPath = currentNode;
            }
        }
        return NodeWithSmallestShortestPath;
    }
}

