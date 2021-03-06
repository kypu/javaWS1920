package de.frauas.java.projectWS1920.DataAccessObject;

import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Graph;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.impls.tg.TinkerGraph;
import com.tinkerpop.blueprints.util.io.graphml.GraphMLReader;
import com.tinkerpop.blueprints.util.io.graphml.GraphMLWriter;
import java.io.*;
import java.lang.StringBuilder;
import de.frauas.java.projectWS1920.BusinessControl.Validate;
import de.frauas.java.projectWS1920.Exceptions.ValidationException;
import de.frauas.java.projectWS1920.Models.MyEdge;
import de.frauas.java.projectWS1920.Models.MyGraph;
import de.frauas.java.projectWS1920.Models.MyNode;


public class GraphML {

    /**
     * Imports given graphml file and converts to custom entities.
     * @param path Location of file.
     * @return Custom graph with data, that has been read in.
     * @throws Exception If file can't be accessed, throw exception.
     */
    public static MyGraph importData(String path) throws Exception {
        MyGraph convertedGraph = new MyGraph();

        if (Validate.isFile(path)) {
            Graph tinkerGraph = new TinkerGraph();
            GraphMLReader tinkerReader = new GraphMLReader(tinkerGraph);
            InputStream inputStream = new BufferedInputStream(new FileInputStream(path));

            // set vertex and edge id's and labels
            tinkerReader.setVertexIdKey("v_id");
            tinkerReader.setEdgeIdKey("e_id");
            tinkerReader.setEdgeLabelKey("e_weight");

            tinkerReader.inputGraph(inputStream);

            Iterable<Vertex> vertices = tinkerGraph.getVertices();
            Iterable<Edge> edges = tinkerGraph.getEdges();

            // convert given vertices to own type
            for (Vertex vertex : vertices) {
                //Integer nodeId = (Integer) vertex.getId();
                Integer nodeId = Integer.parseInt(vertex.getId().toString());

                MyNode nodeToAdd = new MyNode(nodeId);

                convertedGraph.addNode(nodeToAdd);
            }

            // convert given edges to my type
            for (Edge edge : edges) {
                Integer eId = Integer.parseInt(edge.getId().toString());
                Integer weight = Integer.parseInt(edge.getLabel());
                Vertex target = edge.getVertex(Direction.IN);
                Vertex origin = edge.getVertex(Direction.OUT);

                MyEdge edgeToAdd = new MyEdge(
                        eId,
                        convertedGraph.getNodeById(Integer.parseInt(origin.getId().toString())),
                        convertedGraph.getNodeById(Integer.parseInt(target.getId().toString())),
                        weight
                );
                convertedGraph.addEdge(edgeToAdd);
            }
        }
        return convertedGraph;
    }

    /**
     * Exports data of custom graph to given file location.
     * @throws IOException If data can't be written to given file.
     */
    public static void exportData(String filepath, MyGraph graph) throws IOException, ValidationException {
        try {
            Graph tinkerGraph = new TinkerGraph();
            GraphMLWriter tinkerWriter = new GraphMLWriter(tinkerGraph);
            OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(filepath));

            Iterable<MyNode> nodesToExport = graph.getNodes();
            Iterable<MyEdge> edgesToExport = graph.getEdges();

            for (MyNode node : nodesToExport) {
                //converts the node ID from Integer to String
                String nodeId=Integer.toString(node.getNodeId());

                //adds the vertex to the TinkerGraph
                //the parameter inside addVertex() method sets the vertexID
                //the vertexID is saved as String
                Vertex convertedVertex = tinkerGraph.addVertex("n"+nodeId);

                //Sets the vertex property name and value
                //The property "v_id" is saved as Integer
                convertedVertex.setProperty("v_id", node.getNodeId());
                convertedVertex.setProperty("betweenness_centrality", node.getBetweennessCentrality());

                StringBuilder shortestPathString = new StringBuilder("\nSource node '" + nodeId + "':\n");
                for(MyNode dest_node : nodesToExport){
                    shortestPathString.append("To node '"+dest_node.getNodeId()+"': path = " + node.getDirectionsTo(dest_node) + "; length = " + node.getShortestPathLengthTo(dest_node) + "\n");
                }
                convertedVertex.setProperty("shortestPaths", shortestPathString);
            }

            for (MyEdge edge : edgesToExport) {
                //converts the OriginNodeID from Integer to String
                String sourceId="n"+Integer.toString(edge.getOriginNode().getNodeId());
                //converts the DestinationNodeID from Integer to String
                String targetId="n"+Integer.toString(edge.getDestinationNode().getNodeId());
                //converts the edge weight from Integer to String
                String edgeWeight=Integer.toString(edge.getWeight());

                //adds the edge to the TinkerGraph in the form
                //addEdge(Int edgeId, String sourceNode, String TargetNode, String weight)
                Edge convertedEdge = tinkerGraph.addEdge(edge.getEdgeId(), tinkerGraph.getVertex(sourceId), tinkerGraph.getVertex(targetId), edgeWeight);
                convertedEdge.setProperty("e_weight", edge.getWeight());
                convertedEdge.setProperty("e_id", edge.getEdgeId());
            }
            tinkerWriter.outputGraph(outputStream);
        } catch (IOException e) {
            throw e;
        }
    }
}
