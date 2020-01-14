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
            /*
            Map<String, String> vertexKeyTypes = new HashMap<String, String>();
            vertexKeyTypes.put("v_id", GraphMLTokens.DOUBLE);

            Map<String, String> edgeKeyTypes = new HashMap<String, String>();
            edgeKeyTypes.put("e_weight", GraphMLTokens.DOUBLE);
            */
            GraphMLWriter tinkerWriter = new GraphMLWriter(tinkerGraph);
            /*
            tinkerWriter.setVertexKeyTypes(vertexKeyTypes);
            tinkerWriter.setEdgeKeyTypes(edgeKeyTypes);
            */
            OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(filepath));


            Iterable<de.frauas.java.projectWS1920.models.Node> nodesToExport = graph.getNodes();
            Iterable<de.frauas.java.projectWS1920.models.Edge> edgesToExport = graph.getEdges();

            for (Node node : nodesToExport)
            {
                Vertex convertedVertex = tinkerGraph.addVertex(null);
                convertedVertex.setProperty("v_id", Integer.toString(node.getNodeId()));
                //TODO How to set id??
            }

            // Current problems:
            // - What do .addEdge() parameters stand for?
            // - How to get correct node (as origin and target) when id's are changed before?
            // - How to set header correctly?
            for (de.frauas.java.projectWS1920.models.Edge edge : edgesToExport)
            {
                //Edge convertexEdge = tinkerGraph.addEdge("", "", "", "");
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
