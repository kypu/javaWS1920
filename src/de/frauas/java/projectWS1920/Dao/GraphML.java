package de.frauas.java.projectWS1920.Dao;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import com.tinkerpop.blueprints.*;
import com.tinkerpop.blueprints.impls.tg.TinkerGraph;
import com.tinkerpop.blueprints.util.io.graphml.GraphMLReader;
import com.tinkerpop.blueprints.util.io.graphml.GraphMLTokens;
import com.tinkerpop.blueprints.util.io.graphml.GraphMLWriter;

import de.frauas.java.projectWS1920.Bc.Validate;
import de.frauas.java.projectWS1920.models.Node;

/*
TODO:
- implement IGraphML methods
- finish exportData() -> current problems are commented within the method itself
- refactor importData() to satisfy interface
- write integration test for exportData()
- Lots of documentation missing!
- Need to rename all of our models!
 */

public class GraphML //<N, E> implements IGraphML<N, E>
{
    public static de.frauas.java.projectWS1920.models.Graph importData(String path) throws Exception
    {
        de.frauas.java.projectWS1920.models.Graph convertedGraph =
                new de.frauas.java.projectWS1920.models.Graph();

        if (Validate.filepath(path))
        {
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
            for (Vertex vertex : vertices)
            {
                //Integer nodeId = (Integer) vertex.getId();
                Integer nodeId = Integer.parseInt(vertex.getId().toString());

                de.frauas.java.projectWS1920.models.Node node =
                        new de.frauas.java.projectWS1920.models.Node(nodeId);

                convertedGraph.addNode(node);
            }

            // convert given edges to own type
            for (Edge edge : edges)
            {
                Integer eId = Integer.parseInt(edge.getId().toString());
                Integer weight = Integer.parseInt(edge.getLabel());
                //Integer weight = Integer.parseInt(edge.getProperty("e_weight"));
                Vertex target = edge.getVertex(Direction.IN);
                Vertex origin = edge.getVertex(Direction.OUT);

                convertedGraph.addEdge(
                        new de.frauas.java.projectWS1920.models.Edge(
                                eId,
                                convertedGraph.getNodeById(Integer.parseInt(origin.getId().toString())),
                                convertedGraph.getNodeById(Integer.parseInt(target.getId().toString())),
                                weight
                        ));
            }
        } //end of if
        return convertedGraph;
    } //end of method: importData()

    /*
    Exports given Graph to .graphml file.
    DOESN'T WORK YET. TODO.
     */
    public static Boolean exportData(String filepath, de.frauas.java.projectWS1920.models.Graph graph)
    {
        try
        {
            Graph tinkerGraph = new TinkerGraph();
            GraphMLWriter tinkerWriter = new GraphMLWriter(tinkerGraph);
            OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(filepath));


            Iterable<de.frauas.java.projectWS1920.models.Node> nodesToExport = graph.getNodes();
            Iterable<de.frauas.java.projectWS1920.models.Edge> edgesToExport = graph.getEdges();

            for (Node node : nodesToExport)
            {
                //converts the node ID from Integer to String
                String nodeId=Integer.toString(node.getNodeId());

                //adds the vertex to the TinkerGraph
                //the parameter inside addVertex() sets the vertexID
                Vertex convertedVertex = tinkerGraph.addVertex("n"+nodeId);

                //Sets the vertex property name and value
                convertedVertex.setProperty("v_id", convertedVertex.getId());

            }

            // Current problems:
            // - What do .addEdge() parameters stand for?
            // - How to get correct node (as origin and target) when id's are changed before?
            // - How to set header correctly?

            for (de.frauas.java.projectWS1920.models.Edge edge : edgesToExport)
            {
                //converts the edge ID from Integer to String
                String edgeId=Integer.toString(edge.getEdgeId());
                //converts the OriginNodeID from Integer to String
                String sourceId="n"+Integer.toString(edge.getOriginNode().getNodeId());
                //converts the DestinationNodeID from Integer to String
                String targetId="n"+Integer.toString(edge.getDestinationNode().getNodeId());
                //converts the edge weight from Integer to String
                String edgeWeight=Integer.toString(edge.getWeight());

                //adds the edge to the TinkerGraph in the form
                //addEdge(edgeId, sourceNode, TargetNode, weight)
                Edge convertedEdge = tinkerGraph.addEdge(edgeId, tinkerGraph.getVertex(sourceId), tinkerGraph.getVertex(targetId), edgeWeight);
                convertedEdge.setProperty("e_weight", edgeWeight);
                convertedEdge.setProperty("e_id", edgeId);

            }

            tinkerWriter.outputGraph(outputStream);

            return true;
        } catch (IOException e) //add extra FileNotFoundException (subclass of IOException) for better logging?
        {
            e.printStackTrace();
        }
        return false;
    }
}
