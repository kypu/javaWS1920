package de.frauas.java.projectWS1920.application;
/*
import de.frauas.java.projectWS1920.models.Edge;
import de.frauas.java.projectWS1920.models.Graph;
import de.frauas.java.projectWS1920.models.Node;
 */
import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Graph;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.impls.tg.TinkerGraph;
import com.tinkerpop.blueprints.util.io.graphml.GraphMLReader;


import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Iterator;

public class Application {
    private static final String filepath = "/Users/saman/Documents/git/javaWS1920/src/de/frauas/java/projectWS1920/models/small_graph.graphml";

    public static void main(String[] args) throws Exception {

        //Create a new graph(TinkerGraph)
        Graph graph = new TinkerGraph();

        //Puts our graph into the graph reader
        GraphMLReader reader = new GraphMLReader(graph);

        //Create new FileInputStream
        InputStream is = new BufferedInputStream(new FileInputStream(filepath));

        //Set the ID Key to search for
        reader.setVertexIdKey("v_id");
        reader.setEdgeIdKey("e_id");
        reader.setEdgeLabelKey("e_weight");

        //reads the graph from the Inputstream into our created graph(Tinkergraph)
        reader.inputGraph(is);

        //Declare two Iterables with the vertices and edges of the graph
        Iterable<Vertex> vertices=graph.getVertices();
        Iterable<Edge> edges=graph.getEdges();


        //System.out.println(graph.toString());
        //System.out.println(vertices.toString());
        //System.out.println(edges.toString());



        for(Vertex v : vertices){
            System.out.println(v.toString().substring(2, v.toString().length()-1));
        }


        for(Edge e : edges){
            System.out.println(e.toString().substring(2, e.toString().length()-9));
        }
        /*
        String[] items = edges.toString().substring(1, edges.toString().length() - 1).split("\\]?\\[");
        System.out.println(items[0]);
        System.out.println(items[1]);
        System.out.println(items[2]);
        System.out.println(items[3]);
        System.out.println(items[4]);

        for(Edge e : edges){
            String[] myString=e.toString().split("\\\\[\\\\]");
            System.out.println(myString[0]);
            System.out.println(myString[1]);


        }
        */





















/*
        // load mock data to test before we finish parsing the graph
        Graph mockGraph = MockData.createRandomMockGraph(10);
        // test the dijkstra implementation
        Node testOriginNode = mockGraph.getNodeById(1);
        mockGraph.calculateShortestPathsFrom(testOriginNode);
        for (Node node : mockGraph.getNodes()) {
            int pathLength = testOriginNode.getShortestPathLengthTo(node);
            if (pathLength == Integer.MAX_VALUE) {
                System.out.println(testOriginNode.toString() + " and " + node.toString() + " are not connected.");
            } else {
                System.out.println("Shortest path from " + testOriginNode.getNodeId() + " to " + node.getNodeId() + " is " +
                        testOriginNode.getShortestPathLengthTo(node));
                System.out.println("Directions: " + testOriginNode.getShortestPathDirectionsTo(node));
            }
        }

        // 1. output number of nodes
        System.out.println("----Number of nodes: " + mockGraph.getNodes().size());

        // 2. output number of edges
        System.out.println("Number of edges: " + mockGraph.getEdges().size());
        // 3. output node IDs
        //#print out each nodes and its neighbors
        System.out.println("---------------NODE INFO---------------------");

        for(Node node : mockGraph.getNodes()){
            node.print();
            System.out.println("---");
        }

        // 4. output edge IDs
        System.out.println("---------------EDGE INFO---------------------");
        for(Edge edge : mockGraph.getEdges()){
            edge.print();
            System.out.println("---");
        }
        // 5. is the graph connected?

        // 6. diameter of graph

        // shortest paths ?
*/
    }
}









