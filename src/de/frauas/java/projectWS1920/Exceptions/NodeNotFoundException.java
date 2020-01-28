package de.frauas.java.projectWS1920.Exceptions;

public class NodeNotFoundException extends Exception {

    public NodeNotFoundException(int nodeId) {
        super("No node found with id " + nodeId);
    }
}
