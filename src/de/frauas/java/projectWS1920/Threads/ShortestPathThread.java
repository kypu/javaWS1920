package de.frauas.java.projectWS1920.Threads;

import de.frauas.java.projectWS1920.models.MyGraph;
import de.frauas.java.projectWS1920.models.MyNode;

import java.util.HashSet;

public class ShortestPathThread implements Runnable{

    private MyGraph graph;
    private MyNode originNode;

    public ShortestPathThread(MyGraph graph, MyNode originNode) {
        this.graph = graph;
        this.originNode = originNode;
    }

    @Override
    public void run() {
        calculateShortestPathsFrom(originNode);
        for (MyNode destinationNode: graph.getNodes()) {
            originNode.calculateDirectionsTo(destinationNode);
        }
    }

    /**
     * Main method for calculating shortest paths. Some functionalities have been outsourced into smaller methods
     *  for better readability (initialiseShortestPaths, getNodeWithSmallestShortestPath)
     * @param originNode node from which all shortest paths should be calculated
     */
    private void calculateShortestPathsFrom(MyNode originNode) {
        // all nodes in the graph are added to unfinalised and removed later one by one
        HashSet<MyNode> unfinalised = new HashSet<MyNode>(graph.getNodes());
        // distance to origin node is set to 0 and all other nodes to infinity (Integer.MAX_VALUE)
        initialiseShortestPaths(originNode);

        while (!unfinalised.isEmpty()) {
            MyNode nodeInProgress = getNodeWithSmallestShortestPath(originNode, unfinalised);
            // if the shortest path left in unfinalised is infinity, the rest of the nodes must not be connected to the origin.
            if (originNode.getShortestPathLengthTo(nodeInProgress) == Integer.MAX_VALUE) {
                graph.setConnected(false);
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
        graph.setConnected(true);
    }

    private void initialiseShortestPaths(MyNode originNode) {
        for (MyNode currentNode : graph.getNodes()) {
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

}
