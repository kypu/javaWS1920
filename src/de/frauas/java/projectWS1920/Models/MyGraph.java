package de.frauas.java.projectWS1920.Models;

import de.frauas.java.projectWS1920.Exceptions.NodeNotFoundException;
import de.frauas.java.projectWS1920.Threads.ShortestPathThread;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MyGraph {

    private int id;
    private String name;
    private LinkedHashSet<MyNode> nodes = new LinkedHashSet<>();
    private LinkedHashSet<MyEdge> edges = new LinkedHashSet<>();
    // diameter is the largest "shortest distance" between two nodes
    // if all weights are whole numbers the diameter must be too
    private int diameter;
    // if all nodes are connected to each other (directly or indirectly)
    private boolean isConnected;
    public static final int DIAMETER_UNDEFINED = -1;


    // GETTERS AND SETTERS

    public LinkedHashSet<MyNode> getNodes() {
        return nodes;
    }

    public MyNode getNodeById(int myNodeId) throws NodeNotFoundException {
        for (MyNode currentNode : this.nodes) {
            if (currentNode.getNodeId() == myNodeId) {
                return currentNode;
            }
        }
        throw new NodeNotFoundException(myNodeId);
    }

    public LinkedHashSet<MyEdge> getEdges() {
        return edges;
    }

    public boolean getConnected() {
        return isConnected;
    }

    public int getDiameter() {
        return diameter;
    }

    public void setConnected(boolean connected) {
        this.isConnected = connected;
    }

    // FOR POPULATING MOCK GRAPHS

    public void addNode(MyNode newNode) {
        this.nodes.add(newNode);
    }
    public void addEdge(MyEdge newEdge) {
        this.edges.add(newEdge);
    }


    // FOR INITIALISING GRAPH ATTRIBUTES

    /**
     * As soon as a graph is imported, all attributes must be calculated. The order is very important. Do not change!
     */
    public void initialiseGraphAttributes() {
        initialiseAdjacentNodes();
        initialiseAllShortestPaths();
        calculateDiameter();
        for (MyNode node : nodes) {
            calculateBetweennessCentralityOf(node);
        }
    }

    private void initialiseAdjacentNodes() {
        for (MyEdge edge : this.edges) {
            edge.getDestinationNode().addAdjacent(edge.getOriginNode(), edge.getWeight());
            edge.getOriginNode().addAdjacent(edge.getDestinationNode(), edge.getWeight());
        }
    }

    /**
     * Creates a thread pool with one shortestPathThread per node.
     * After shutdown we need to wait with awaitTermination (returns boolean) for all threads to have been completed
     */
    private void initialiseAllShortestPaths() {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(100);
        for (MyNode originNode : this.nodes) {
            ShortestPathThread thread = new ShortestPathThread(this, originNode);
            executor.execute(thread);
        }
        executor.shutdown();
        // waits for 10 seconds to complete all threads. If it takes longer 10 seconds, force shutdown and error message
        try {
            if (!executor.awaitTermination(10, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException ex) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
            // todo: log error information
        }
    }

    /**
     * Diameter D of a Graph is defined as the longest path of the shortest paths between any two nodes.
     * If the graph is not connected, then the diameter is undefined (-1)
     */
    private void calculateDiameter() {
        int maxShortestDistance = 0;
        //choose one node from all nodes
        for (MyNode currentNode : getNodes()) {
            for (MyNode node : getNodes()) {
                int pathLength = currentNode.getShortestPathLengthTo(node);
                if(pathLength>maxShortestDistance) {
                    maxShortestDistance = pathLength;
                }
            }
        }
        //if the diameter length is infinity, then return -1, meaning that there is no diameter
        if(maxShortestDistance==Integer.MAX_VALUE) {
            diameter = DIAMETER_UNDEFINED;
        }
        else { diameter=maxShortestDistance; }
    }

    /** Returns the betweenness centrality of a given node in the following steps:
     * 1. for every node other than the centralNode, the shortest paths to all other nodes need to be calculated (first for-loop)
     * 2. then for every combination of nodes which are connected anddo not include the central node,
     * the path directions need to be calculated (second for-loop)
     * 3. then the number of paths which include the central node is divided by the total paths for each combination
     * and the result is added to the betweenness centrality total
     * @param centralNode the node for which we calculate the betweenness centrality
     * @return the betweenness centrality
     */
    private void calculateBetweennessCentralityOf(MyNode centralNode) {
        double betweennessCentrality = 0.0;
        for (MyNode originNode : nodes) {
            if (originNode.equals(centralNode)) {
                continue;
            }
            //only check the destination nodes which are connected to the originNode because
            //unconnected nodes add 0 to the betweenness centrality anyway and otherwise we would divide by 0
            for (MyNode destinationNode : nodes) {
                if (destinationNode.equals(centralNode) || destinationNode.equals(originNode) ||
                        (originNode.getShortestPathLengthTo(destinationNode)==Integer.MAX_VALUE)) {
                    continue;
                }
                betweennessCentrality +=
                        (countPathsIncluding(originNode, destinationNode, centralNode) /
                                countPaths(originNode, destinationNode)); }
        }
        // divide by 2 because we have counted every pair of nodes twice (AB and BA)
        centralNode.setBetweennessCentrality(betweennessCentrality/2);
    }

    // must return double not int because we divide with it later
    private double countPaths(MyNode originNode, MyNode destinationNode) {
        // if directions map in originNode doesn't contain the destinationNode as a key return 0
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
