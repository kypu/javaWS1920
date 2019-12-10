package de.frauas.java.projectWS1920.application;

import de.frauas.java.projectWS1920.models.Edge;
import de.frauas.java.projectWS1920.models.Graph;
import de.frauas.java.projectWS1920.models.Node;

public class MockData {

    public static Graph createMockGraph() {

        // The mock graph looks like this: NODE1--edge13--NODE2--edge15--NODE3
        // We can add more nodes and edges to make it more complicated

        Graph mockGraph = new Graph();


        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);

        Edge edge1 = new Edge(1, node1, node2, 13);
        Edge edge2 = new Edge(2, node2, node3, 15);

        mockGraph.addNode(node1);
        mockGraph.addNode(node2);
        mockGraph.addNode(node3);

        mockGraph.addEdge(edge1);
        mockGraph.addEdge(edge2);

        mockGraph.setAdjacentNodes();

        return mockGraph;
    }

}
