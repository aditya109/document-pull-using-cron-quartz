package com.github.cron.cmd.handler.githandler;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Hashtable;
import java.util.Scanner;

public class GitHandler {
    //  declaring Hashtable for storing parsed HTTP Response
    private static Hashtable<String, Object> responseMap;
    //  declaring inline for storing response as String
    private static String inline;
    //  declaring gitLink for storing GitHub Gist Link as String
    private static String gitLink;

    public GitHandler(String gitLink) {
        //  GitHandler constructor
        //  initializing GitLink from the formal parameters
        GitHandler.gitLink = gitLink;
        //  initializing responseMap to store response map
        GitHandler.responseMap = new Hashtable<String, Object>();
    }

    public void requestHandler(String gitLink) throws IOException {
        //  function to store response from HTTPRequest
        //  storing gistID from the `gitLink`
        String gistID = gitLink.split("/")[gitLink.split("/").length - 1];
        String GET_URL = "https://api.github.com/gists" + "/" + gistID;
        //  creating URL object passing `GET_URL`
        URL url = new URL(GET_URL);
        inline = "";

        //  creating `HttpURLConnection` object
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        //  setting request method in `HttpURLConnection` object
        connection.setRequestMethod("GET");
        //  setting request property in `HttpURLConnection` object
        connection.setRequestProperty("accept", "application/json");
        //  initiating the connection using `HttpURLConnection` object
        connection.connect();
        //  getting the response code
        int responseCode = connection.getResponseCode();
        if (responseCode != 200) {
            throw new RuntimeException("HttpResponseCode : " + responseCode);
        } else {
            //  creating `Scanner` object
            Scanner scanner = new Scanner(url.openStream());
            //  reading response
            while (scanner.hasNext()) {
                inline += scanner.nextLine();
            }
            //  closing `Scanner` object
            scanner.close();

        }
        //  disconnecting the connection
        connection.disconnect();
    }

    public void responseParser() throws ParseException {
        //  function to parse JSONResponse
        JSONParser jsonParser = new JSONParser();
        JSONObject responseJSON = (JSONObject) jsonParser.parse(inline);
        JSONObject files = (JSONObject) responseJSON.get("files");
        JSONObject file = (JSONObject) files.get("ideas.docx");
        //  extracting the specific fields from JSONResponse
        String gistID = (String) responseJSON.get("id");
        String raw_url = (String) file.get("raw_url");
        String content = (String) file.get("content");
        String updated_at = (String) responseJSON.get("updated_at");
        String title = (String) responseJSON.get("description");
        JSONArray history = (JSONArray) responseJSON.get("history");
        //  putting specific fields into `responseMap`
        responseMap.put("gistID", gistID);
        responseMap.put("raw_url", raw_url);
        responseMap.put("content", content);
        responseMap.put("updated_at", updated_at);
        responseMap.put("title", title);
        responseMap.put("history", history);
    }

    public Hashtable<String, Object> controller() throws IOException, ParseException {
        //  call requestHandler() and store `response`
        requestHandler(gitLink);
        //  `response` is passed to responseParse()
        responseParser();
        //  returning `responseMap`
        return responseMap;

    }
}
