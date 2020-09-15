package com.github.cron.cmd.handler.destinationdeltahandler;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Hashtable;

public class DestinationDeltaHandler {
    //  declaring destination path
    private static String destinationPath;
    //  declaring Hashtable for storing parsed HTTP Response
    private static Hashtable<String, Object> responseMap;
    //  declaring `PrintWriter` object
    protected PrintWriter printWriter;

    public DestinationDeltaHandler(Hashtable<String, Object> responseMap, String destinationPath) {
        //  DestinationDeltaHandler constructor
        //  initializing destinationPath to store destination path
        DestinationDeltaHandler.destinationPath = destinationPath;
        //  initializing responseMap to store response map
        DestinationDeltaHandler.responseMap = responseMap;
        //  initializing printWriter as null
        printWriter = null;
    }

    private boolean initializeDestinationPath(String destinationPath) {
        //  function to initialize destination path
        //  initializing a `File` object
        File file = new File(destinationPath);
        //  declaring file creation result
        boolean fileCreationResult = false;
        //  declaring destination path exists
        boolean destinationPathExists = file.exists();
        if (!destinationPathExists) {
            try {
                //  creating the file
                fileCreationResult = file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //  returning OR result of file creation result and destination path exists
        return (destinationPathExists || fileCreationResult);
    }

    private void writeToDestination(String content, String destinationPath) {
        //  function to write to destination path
        try {
            //  creating `PrintWriter` object
            printWriter = new PrintWriter(new FileWriter(destinationPath));
            //  writing `content` into destination path
            printWriter.println(content);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //  closing the PrintWriter
            if(printWriter != null) {
                printWriter.close();
            }
        }
    }

    public void controller() throws IOException {
        // make sure the destination exists, if not then create one
        if (initializeDestinationPath(destinationPath)) {
            String content = (String) responseMap.get("content");
            // write content to destination
            writeToDestination(content, destinationPath);
        }
        System.out.println("Write successful !");
    }
}
