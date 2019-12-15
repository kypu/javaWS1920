package de.frauas.java.projectWS1920.application;

import de.frauas.java.projectWS1920.models.Edge;
import de.frauas.java.projectWS1920.models.Graph;
import de.frauas.java.projectWS1920.models.Node;

public class MockData {

    public static Graph createMockGraph() {

        // We can add more nodes and edges to make it more complicated

        Graph mockGraph = new Graph();


        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);

        Edge edge1 = new Edge(1, node1, node2, 3);
        Edge edge2 = new Edge(2, node1, node3, 15);
        Edge edge3 = new Edge(3, node1, node4, 1);
        Edge edge4 = new Edge(4, node3, node4, 4);

        mockGraph.addNode(node1);
        mockGraph.addNode(node2);
        mockGraph.addNode(node3);
        mockGraph.addNode(node4);

        mockGraph.addEdge(edge1);
        mockGraph.addEdge(edge2);
        mockGraph.addEdge(edge3);
        mockGraph.addEdge(edge4);

        mockGraph.setAdjacentNodes();

        return mockGraph;
    }

}
