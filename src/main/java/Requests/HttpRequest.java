package Requests;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpRequest {
    private final static String BASE_URL = "http://142.93.134.194:8088/";
    public static String getRemoteData(String requestedData) throws Exception {
        String baseRemoteURL =  BASE_URL + requestedData;

        HttpURLConnection connection = (HttpURLConnection) new URL(baseRemoteURL).openConnection();

        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-Type", "UTF-8");


        int status = connection.getResponseCode();

        BufferedReader dataReader = new BufferedReader(status > 299 ? new InputStreamReader(connection.getErrorStream())
                : new InputStreamReader(connection.getInputStream(), "UTF-8"));
        String line;
        StringBuffer response = new StringBuffer();

        while ((line = dataReader.readLine()) != null) {
            response.append(line);
        }
        dataReader.close();


        return response.toString();
    }

    public static String setRemoteData(String url, String data) throws Exception {
        String baseRemoteURL =  BASE_URL + url;

        HttpURLConnection connection = (HttpURLConnection) new URL(baseRemoteURL).openConnection();

        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json; utf-8");
        connection.setRequestProperty("Accept", "application/json");
        connection.setDoOutput(true);
        OutputStream os = connection.getOutputStream();
        byte[] input = data.getBytes("utf-8");
        os.write(input, 0, input.length);
        BufferedReader br = new BufferedReader(
                new InputStreamReader(connection.getInputStream(), "utf-8"));
        StringBuilder response = new StringBuilder();
        String responseLine = null;
        while ((responseLine = br.readLine()) != null) {
            response.append(responseLine.trim());
        }
        return response.toString();

    }
}