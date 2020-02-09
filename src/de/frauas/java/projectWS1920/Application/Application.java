//Java group 26
package de.frauas.java.projectWS1920.Application;

import de.frauas.java.projectWS1920.Logger.Implementation.MyLogger;


public class Application {

    public static void main(String[] args) throws Exception {
        var logger = new MyLogger();
        var app = new CommandLineApplication(logger);
        app.run(args);
    }
}









