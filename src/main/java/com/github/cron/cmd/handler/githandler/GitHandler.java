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
    private static Hashtable<String, Object> responseMap;
    private static String inline;

    public void requestHandler(String gitLink) throws IOException {
        String gistID = gitLink.split("/")[gitLink.split("/").length - 1];
        String GET_URL = "https://api.github.com/gists" + "/" + gistID;
        URL url = new URL(GET_URL);
        inline = "";

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("accept", "application/json");
        connection.connect();
        int responseCode = connection.getResponseCode();
        if (responseCode != 200) {
            throw new RuntimeException("HttpResponseCode : " + responseCode);
        } else {
            Scanner scanner = new Scanner(url.openStream());
            while (scanner.hasNext()) {
                inline += scanner.nextLine();
            }
            scanner.close();

        }
        connection.disconnect();
    }

    public void responseParser() throws ParseException {
        responseMap = new Hashtable<String, Object>();
        JSONParser jsonParser = new JSONParser();
        JSONObject responseJSON = (JSONObject) jsonParser.parse(inline);
        JSONObject files = (JSONObject) responseJSON.get("files");
        JSONObject file = (JSONObject) files.get("ideas.docx");
        String gistID = (String) responseJSON.get("id");
        String raw_url = (String) file.get("raw_url");
        String content = (String) file.get("content");
        String updated_at = (String) responseJSON.get("updated_at");
        String title = (String) responseJSON.get("description");
        JSONArray history = (JSONArray) responseJSON.get("history");
        responseMap.put("gistID", gistID);
        responseMap.put("raw_url", raw_url);
        responseMap.put("content", content);
        responseMap.put("updated_at", updated_at);
        responseMap.put("title", title);
        responseMap.put("history", history);
    }

    public Hashtable<String, Object> controller(String gitLink) throws IOException, ParseException {
        //  call requestHandler() and store `response`
        requestHandler(gitLink);
        //  `response` is passed to responseParse()
        responseParser();
        return responseMap;

    }
}
