package de.frauas.java.projectWS1920.models;

import java.util.*;

public class Graph {

    private int id;
    private String name;
    private LinkedHashSet<Node> nodes = new LinkedHashSet<>();
    private LinkedHashSet<Edge> edges = new LinkedHashSet<>();
    // diameter is the largest "shortest distance" between two nodes
    // if all weights are whole numbers the diameter must be too
    private int diameter;
    // if all nodes are connected to each other (directly or indirectly)
    private boolean connected;

    public LinkedHashSet<Node> getNodes() {
        return nodes;
    }

    public LinkedHashSet<Edge> getEdges() {
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
            if (currentNode.getNodeId() == myNodeId) {
                return currentNode;
            } // todo: else statement to return an exception if the node is not found
        }
        return null;
    }

    public void setAdjacentNodes() {
        for (Edge edge : this.edges) {
            edge.getDestinationNode().addAdjacent(edge.getOriginNode(), edge.getWeight());
            edge.getOriginNode().addAdjacent(edge.getDestinationNode(), edge.getWeight());
        }
    }

    public void calculateShortestPathsFrom(Node originNode) {
        // all nodes in the graph are added to unfinalised and removed one by one
        HashSet<Node> unfinalised = new HashSet<Node>(this.nodes);

        initialiseShortestPaths(originNode);

        while (!unfinalised.isEmpty()) {
            Node nodeInProgress = getNodeWithSmallestShortestPath(originNode, unfinalised);
            // if the shortest path length is infinity, the rest of the nodes must not be connected to the origin.
            if (originNode.getShortestPathLengthTo(nodeInProgress) == Integer.MAX_VALUE) {
                this.connected = false;
                return;
            }
            // we check all the adjacent nodes to our nodeInProgress to see if we can make an improvement
            for (Node adjacentToNodeInProgress : nodeInProgress.getAdjacentNodes().keySet()) {
                int currentPathLength = originNode.getShortestPaths().get(adjacentToNodeInProgress);
                int pathLengthViaNodeInProgress = originNode.getShortestPaths().get(nodeInProgress) +
                        nodeInProgress.getAdjacentNodes().get(adjacentToNodeInProgress);
                if (currentPathLength > pathLengthViaNodeInProgress) {
                    originNode.updateShortestPath(adjacentToNodeInProgress, pathLengthViaNodeInProgress);
                    originNode.addCurrentPreviousNodePair(adjacentToNodeInProgress, nodeInProgress);
                }
            }
            unfinalised.remove(nodeInProgress);
        }
        this.connected = true;
    }


    // can be private because only called from Graph.calculateShortestPaths
    private void initialiseShortestPaths(Node originNode) {
        for (Node currentNode : this.nodes) {
            // todo: check if == or .equals() is better. Also check if  ? : syntax is possible
            if (currentNode == originNode) {
                originNode.updateShortestPath(currentNode, 0);
            } else {
                originNode.updateShortestPath(currentNode, Integer.MAX_VALUE);
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
            if (originNode.getShortestPaths().get(currentNode) <= smallestShortestPath) {
                smallestShortestPath = originNode.getShortestPaths().get(currentNode);
                NodeWithSmallestShortestPath = currentNode;
            }
        }
        return NodeWithSmallestShortestPath;
    }


}

