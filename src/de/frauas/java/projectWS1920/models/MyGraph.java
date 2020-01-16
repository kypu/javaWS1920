package de.frauas.java.projectWS1920.models;

import java.util.*;

public class MyGraph {

    private int id;
    private String name;
    private LinkedHashSet<MyNode> nodes = new LinkedHashSet<>();
    private LinkedHashSet<MyEdge> edges = new LinkedHashSet<>();
    // diameter is the largest "shortest distance" between two nodes
    // if all weights are whole numbers the diameter must be too
    private int diameter;
    // if all nodes are connected to each other (directly or indirectly)
    private boolean connected;


    // GETTERS AND SETTERS

    public LinkedHashSet<MyNode> getNodes() {
        return nodes;
    }

    public MyNode getNodeById(int myNodeId) {
        for (MyNode currentNode : this.nodes) {
            if (currentNode.getNodeId() == myNodeId) {
                return currentNode;
            } // todo: else statement to return an exception if the node is not found
        }
        return null;
    }

    public LinkedHashSet<MyEdge> getEdges() {
        return edges;
    }


    // FOR POPULATING MOCK GRAPHS

    public void addNode(MyNode newNode) {
        this.nodes.add(newNode);
    }
    public void addEdge(MyEdge newEdge) {
        this.edges.add(newEdge);
    }


    // FOR SHORTEST PATH CALCULATION

    /** Main method for calculating shortest paths. Some functionalities have been outsourced into smaller methods
     *  for better readability (setAdjacentNodes, initialiseShortestPaths, getNodeWithSmallestShortestPath)
     * @param originNode node from which all shortest paths should be calculated
     */
    public void calculateShortestPathsFrom(MyNode originNode) {
        // iterates over all edges to fill the adjacent nodes attribute of each node.
        // todo: only needs to be called once per graph - call setAdjacentNodes() from main as soon as graph is read?
        this.setAdjacentNodes();
        // all nodes in the graph are added to unfinalised and removed later one by one
        HashSet<MyNode> unfinalised = new HashSet<MyNode>(this.nodes);
        // distance to origin node is set to 0 and all other nodes to infinity (Integer.MAX_VALUE)
        initialiseShortestPaths(originNode);

        while (!unfinalised.isEmpty()) {
            MyNode nodeInProgress = getNodeWithSmallestShortestPath(originNode, unfinalised);
            // if the shortest path left in unfinalised is infinity, the rest of the nodes must not be connected to the origin.
            if (originNode.getShortestPathLengthTo(nodeInProgress) == Integer.MAX_VALUE) {
                this.connected = false;
                return;
            }
            // we check all the adjacent nodes to our nodeInProgress to see if we can make an improvement
            for (MyNode adjacentToNodeInProgress : nodeInProgress.getAdjacentNodes().keySet()) {
                int currentPathLength = originNode.getShortestPathLengths().get(adjacentToNodeInProgress);
                int pathLengthViaNodeInProgress = originNode.getShortestPathLengths().get(nodeInProgress) +
                        nodeInProgress.getAdjacentNodes().get(adjacentToNodeInProgress);
                // when true, a smaller shortest path has been found and the previous node should be replaced
                if (currentPathLength > pathLengthViaNodeInProgress) {
                    originNode.updateShortestPath(adjacentToNodeInProgress, pathLengthViaNodeInProgress);
                    originNode.addPreviousNode(adjacentToNodeInProgress, nodeInProgress, true);
                }
                // when true, an equal shortest path has been found. Previous node should be added without replacing the old one
                else if (currentPathLength == pathLengthViaNodeInProgress) {
                    originNode.addPreviousNode(adjacentToNodeInProgress, nodeInProgress, false);
                }
            }
            unfinalised.remove(nodeInProgress);
        }
        // if we make it out of the while loop, then a shortest path exists to all nodes so the graph must be connected
        this.connected = true;
    }

    /** Needs public access because is called when making mock graphs
     */
    public void setAdjacentNodes() {
        for (MyEdge edge : this.edges) {
            edge.getDestinationNode().addAdjacent(edge.getOriginNode(), edge.getWeight());
            edge.getOriginNode().addAdjacent(edge.getDestinationNode(), edge.getWeight());
        }
    }

    private void initialiseShortestPaths(MyNode originNode) {
        for (MyNode currentNode : this.nodes) {
            if (currentNode == originNode) {
                originNode.updateShortestPath(currentNode, 0);
            } else {
                originNode.updateShortestPath(currentNode, Integer.MAX_VALUE);
            }
        }
    }

    private MyNode getNodeWithSmallestShortestPath(MyNode originNode, HashSet<MyNode> unfinalisedNodes) {
        // todo: if (unvisitedNodes is empty) { exception thrown }
        // initialise this to something? Are the nodes with 0 id?
        MyNode NodeWithSmallestShortestPath = originNode; // or don't initialise here and check before return;
        int smallestShortestPath = Integer.MAX_VALUE;
        for (MyNode currentNode : unfinalisedNodes) {
            if (originNode.getShortestPathLengths().get(currentNode) <= smallestShortestPath) {
                smallestShortestPath = originNode.getShortestPathLengths().get(currentNode);
                NodeWithSmallestShortestPath = currentNode;
            }
        }
        return NodeWithSmallestShortestPath;
    }

    // METHODS FOR CALCULATING BETWEENNESS CENTRALITY

    /** Returns the betweenness centrality of a given node in the following steps:
     * 1. for every node other than the given node, the shortest paths to all other nodes need to be calculated (first for-loop)
     * 2. then for every combination of nodes which are connected anddo not include the central node,
     * the path directions need to be calculated (second for-loop)
     * 3. then the number of paths which include the central node is divided by the total paths for each combination
     * and the result is added to the betweenness centrality total
     * @param centralNode the node for which we calculate the betweenness centrality
     * @return the betweenness centrality
     */
    public double calculateBetweennessCentralityOf(MyNode centralNode) {
        double betweennessCentrality = 0.0;
        // todo: all shortest paths could be calculated once per graph at the very beginning (using threads?)
        for (MyNode originNode : this.nodes) {
            if (!originNode.equals(centralNode))
                calculateShortestPathsFrom(originNode);
            //only check the destination nodes which are connected because
            // unconnected nodes add 0 to the betweenness centrality and otherwise we divide by 0
            for (MyNode destinationNode : this.nodes) {
                if (!destinationNode.equals(centralNode) && !destinationNode.equals(originNode) &&
                        (originNode.getShortestPathLengthTo(destinationNode)!=Integer.MAX_VALUE)) {
                    // todo: also calculate all directions in the thread
                    originNode.calculateDirectionsTo(destinationNode);
                    betweennessCentrality +=
                            (countPathsIncluding(originNode, destinationNode, centralNode) /
                                    countPaths(originNode, destinationNode)); }
            }
        }
        return betweennessCentrality;
    }

    // must return double not int because we divide with it later
    private double countPaths(MyNode originNode, MyNode destinationNode) {
        // if directions map in originNode doesn't contain the destinationNode key return 0
        if (!originNode.getDirections().containsKey(destinationNode)) { return 0.0; }
        return originNode.getDirections().get(destinationNode).size();
    }

    // must return double not int because we divide with it later
    private double countPathsIncluding(MyNode originNode, MyNode destinationNode, MyNode includedNode) {
        double numberPaths = 0.0;
        for (LinkedList<MyNode> path : originNode.getDirections().get(destinationNode)) {
            if (path.contains(includedNode)) { numberPaths++; }
        }
        return numberPaths;
    }
}
