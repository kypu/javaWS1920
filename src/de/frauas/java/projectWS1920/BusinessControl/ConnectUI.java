package de.frauas.java.projectWS1920.BusinessControl;

import de.frauas.java.projectWS1920.DataAccessObject.GraphML;
import de.frauas.java.projectWS1920.Logger.Implementation.MyLogger;
import de.frauas.java.projectWS1920.Models.MyEdge;
import de.frauas.java.projectWS1920.Models.MyGraph;
import de.frauas.java.projectWS1920.Models.MyNode;


public class ConnectUI {
    MyLogger logger;

    public ConnectUI(MyLogger logger){
        this.logger = logger;
    }

    /**
     * Logs graph details to console and file.
     * @param filePath Open file path of graphml file.
     * @throws Exception Throws exception, if file can't be opened.
     */
    public void logBasicGraphAttributes(String filePath) throws Exception {
        try {
            var importedGraph = GraphML.importData(filePath);
            importedGraph.initialiseBasicGraphAttributes();

            //1.
            logger.logToConsole(createNumberOfNodesString(importedGraph));
            //2.
            logger.logToConsole(createNumberOfEdgesString(importedGraph));
            //3.
            logger.logToConsole(createVertexIDString(importedGraph));
            //4.
            logger.logToConsole(createEdgesIDString(importedGraph));
            //5.
            logger.logToConsole(createConnectedString(importedGraph));
            //6.
            logger.logToConsole(createDiameterString(importedGraph));

        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Logs the centrality of given node.
     * @param filePath Graphml input file.
     * @param centralNodeId Node after -b command line option.
     * @throws Exception Throws exception, if file can't be opened.
     */
    public void logBetweennessCentrality(String filePath, Integer centralNodeId) throws Exception {
        StringBuilder stringBuilder = new StringBuilder();

        try {
            var importedGraph = GraphML.importData(filePath);
            MyNode centralNode = importedGraph.getNodeById(centralNodeId);
            importedGraph.initialiseAttributesBetweennessCentrality(centralNode);
            String betweennessCentralityString = Double.toString(centralNode.getBetweennessCentrality());

            stringBuilder.append("Betweenness centrality of " + centralNode.toString() + ": ");
            stringBuilder.append(betweennessCentralityString);
            logger.logToConsole(stringBuilder.toString());
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Exports graph information to output file.
     * @param filePath Graphml input file.
     * @param outputFilePath Graphml output file.
     * @throws Exception Throws exception, if file can't be opened.
     */
    public void logCreateOutputFile(String filePath, String outputFilePath) throws Exception {
        try {
            var importedGraph = GraphML.importData(filePath);
            importedGraph.initialiseAttributesOutputOption();
            GraphML.exportData(outputFilePath, importedGraph);
            logger.logToConsole("Successfully created output file");
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Logs shortest path of given nodes.
     * @param filePath Graphml input file.
     * @param idSourceNode The first node id after -s command line option.
     * @param idDestinationNode The second node id after -s command line option.
     * @throws Exception Throws exception, if file can't be opened.
     */
    public void logShortestPath(String filePath, Integer idSourceNode, Integer idDestinationNode) throws Exception {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            MyGraph importedGraph = GraphML.importData(filePath);
            MyNode sourceNode = importedGraph.getNodeById(idSourceNode);
            MyNode destinationNode = importedGraph.getNodeById(idDestinationNode);
            importedGraph.initialiseAttributesShortestPath(sourceNode, destinationNode);

            Integer shortestPathLength = sourceNode.getShortestPathLengthTo(destinationNode);

            if (shortestPathLength == Integer.MAX_VALUE){
                stringBuilder.append(sourceNode.toString());
                stringBuilder.append(" and " + destinationNode.toString());
                stringBuilder.append(" aren't connected.");
            } else {
                stringBuilder.append("The length of the shortest path from ");
                stringBuilder.append(sourceNode.toString() + " to ");
                stringBuilder.append(destinationNode.toString() + ": ");
                stringBuilder.append(shortestPathLength);

                stringBuilder.append("\r\nThe path is as follows: ");
                stringBuilder.append(sourceNode.getDirectionsTo(destinationNode));
            }
            logger.logToConsole(stringBuilder.toString());
        } catch (Exception e) {
            throw e;
        }
        //initialiseAttributesOutputOption
    }

    private String createNumberOfNodesString(MyGraph graph) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Number of Nodes:\r\n");
        stringBuilder.append(Integer.toString(graph.getNodes().size()));
        stringBuilder.append("\r\n\r\n");
        return stringBuilder.toString();
    }

    private String createNumberOfEdgesString(MyGraph graph) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Number of Edges:\r\n");
        stringBuilder.append(Integer.toString(graph.getEdges().size()));
        stringBuilder.append("\r\n\r\n");
        return stringBuilder.toString();
    }

    private String createVertexIDString(MyGraph graph) {
        StringBuilder stringBuilder = new StringBuilder();
        for (MyNode node : graph.getNodes()) {
            stringBuilder.append(node.toString() + "\r\n");
        }
        stringBuilder.append("\r\n");
        return stringBuilder.toString();
    }

    private String createEdgesIDString(MyGraph graph) {
        StringBuilder stringBuilder = new StringBuilder();
        for (MyEdge edge : graph.getEdges()) {
            stringBuilder.append(edge.toString() + "\r\n");
        }
        stringBuilder.append("\r\n");
        return stringBuilder.toString();
    }

    private String createConnectedString(MyGraph graph) {
        return graph.getConnected() == true ? "Graph is connected\r\n\r\n"
                : "Graph is unconnected\r\n\r\n";
    }

    private String createDiameterString(MyGraph graph) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Diameter of graph is: ");
        stringBuilder.append(graph.getDiameter());
        stringBuilder.append("\r\n\r\n");
        return stringBuilder.toString();
    }
}
