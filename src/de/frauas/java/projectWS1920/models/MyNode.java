package de.frauas.java.projectWS1920.models;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class MyNode {

    private int nodeId;
    // key is an adjacent node, value is the weight of connecting edge
    private HashMap<MyNode, Integer> adjacentNodes = new HashMap<>();
    // key is a connected node, value is the length of the path
    private HashMap<MyNode, Integer> shortestPathLengths = new HashMap<MyNode, Integer>();
    // Key is a connected node, value is a list all possible previous nodes in the different shortest paths
    // Set during the Graph.calculateShortestPathsFrom(Node originNode)
    // Required to work out the directions
    private HashMap<MyNode, LinkedList<MyNode>> previousNodes = new HashMap<>();
    // Key is a connected node. Value is a list of the shortest paths to that node. Each shortest path is a list of nodes.
    private HashMap<MyNode, LinkedList<LinkedList<MyNode>>> directions = new HashMap<>();
    private double betweennessCentrality;


    // CONSTRUCTORS

    public MyNode(int nodeId) {
        this.nodeId = nodeId;
    }

    // GETTERS AND SETTERS

    public int getNodeId() { return this.nodeId; }
    public Map<MyNode, Integer> getAdjacentNodes() {
        return adjacentNodes;
    }
    public HashMap<MyNode, Integer> getShortestPathLengths() {
        return shortestPathLengths;
    }
    public int getShortestPathLengthTo(MyNode destinationNode) {
        return shortestPathLengths.get(destinationNode);
    }
    public HashMap<MyNode, LinkedList<LinkedList<MyNode>>> getDirections() {
        return directions;
    }
    public LinkedList<LinkedList<MyNode>> getDirectionsTo(MyNode destinationNode) {
        return directions.get(destinationNode);
    }
    public void setBetweennessCentrality(double betweennessCentrality) {
        this.betweennessCentrality = betweennessCentrality;
    }
    public double getBetweennessCentrality() {
        return betweennessCentrality;
    }
    // METHODS FOR SHORTEST PATH LENGTHS AND ADJACENT NODES

    /** Before the shortest paths are calculation, the adjacent nodes map needs to be filled
     * @param node an adjacent node to this node
     * @param weight weight of the edge from this node to the adjacent one
     */
    public void addAdjacent(MyNode node, int weight) {
        this.adjacentNodes.put(node, weight);
    }

    /** Updates/creates the shortest path lengths map whenever a shorter path is found.
     * @param destinationNode key in shortest path lengths map
     * @param pathLength value in shortest path lengths map
     */
    public void updateShortestPath(MyNode destinationNode, int pathLength) {
        this.shortestPathLengths.put(destinationNode, pathLength);
    }


    // FOR POPULATING THE PREVIOUS NODES MAP

    /** If a shorter or equal path length is found, the previous nodes map has to be updated
     * @param currentNode key in previous nodes map
     * @param previousNode node to be added to the list of previous nodes (the list is the value in the map)
     * @param replace if true the existing nodes in the list are first removed
     */
    public void addPreviousNode(MyNode currentNode, MyNode previousNode, boolean replace) {
        // if the key doesn't exist yet, a new list for previous nodes has to be created and added as the value
        if (!this.previousNodes.containsKey(currentNode)) {
            LinkedList<MyNode> previousNodeList = new LinkedList<>();
            this.previousNodes.put(currentNode, previousNodeList);
        }
        if (replace) {
            this.previousNodes.get(currentNode).clear();
        }
        this.previousNodes.get(currentNode).add(previousNode);
    }


    // METHODS FOR PUTTING TOGETHER THE DIRECTIONS (LIST OF NODES TRAVERSED IN SHORTEST PATHS)

    /** Initiates the recursive putting-together of the shortest paths from the information in the previousNodes map
     * First checks if there is a shortest path at all
     * @param destinationNode a key in the directions map (the ultimate destination)
     */
    public void calculateDirectionsTo(MyNode destinationNode)  {
            if (this.getShortestPathLengthTo(destinationNode) == Integer.MAX_VALUE) {
                return;
            }
            LinkedList<LinkedList<MyNode>> shortestPaths = new LinkedList<LinkedList<MyNode>>();
            LinkedList<MyNode> firstPath = new LinkedList<MyNode>();
            shortestPaths.add(firstPath);
            this.directions.put(destinationNode, shortestPaths);
            addNodeToPath(destinationNode, firstPath);
    }

    /** The recursive algorithm for putting the paths together
     * There are three important cases :
     * 1. We have already reached the origin node so we can stop. Only the first if (termination) condition is carried out
     * 2. There is only one previous node to consider. No new paths need to be created
     * (the first for-loop and the else-condition in the second for-loop are never carried out)
     * 3. There are several previous nodes so new paths need to be created
     * @param nodeToAdd the node which we add to the front of the tail
     * @param tail the current path leading to the destination node
     */
    private void addNodeToPath(MyNode nodeToAdd, LinkedList<MyNode> tail) {
        tail.addFirst(nodeToAdd);

        if (nodeToAdd.equals(this)) {
            return;
        }

        // any extra paths required need to be created before we call the addNodeToPath method again
        // so that we can add the current tail to all paths before altering the tail
        int numberPreviousNodes = this.previousNodes.get(nodeToAdd).size();
        LinkedList<LinkedList<MyNode>> extraPaths = new LinkedList<>();
        for (int i = 0; i < numberPreviousNodes-1; i++) {
            LinkedList<MyNode> newPath = new LinkedList<MyNode>(tail);
            extraPaths.add(newPath);
        }

        for (int i = 0; i < numberPreviousNodes; i++) {
            if (i == 0) {
                addNodeToPath(this.previousNodes.get(nodeToAdd).get(i), tail);
            } else {
                // if there is more than one previous node, we need to use an extra path per node as the tail
                // the last node in the tail is always the ultimate destination
                this.directions.get(tail.getLast()).add(extraPaths.get(i-1));
                addNodeToPath(this.previousNodes.get(nodeToAdd).get(i), extraPaths.get(i-1));
            }
        }
    }

    public void print(){
        System.out.println("Node Id: " + nodeId);
    }

}





