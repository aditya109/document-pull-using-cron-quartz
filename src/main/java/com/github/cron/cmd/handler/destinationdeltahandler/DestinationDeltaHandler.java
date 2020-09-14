package com.github.cron.cmd.handler.destinationdeltahandler;

import java.util.Hashtable;

public class DestinationDeltaHandler {
    private boolean checkDestinationExists() {
        return false;
    }
    private void writeToDestination() {}
    public void controller(Hashtable<String, Object> responseMap) {
        // make sure the destination exists, if not then create one
        // write content to destination
    }
}
