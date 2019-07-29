package com.example.myitunesapi.Utility;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class ReadObject {

    //this method is to read all the text from the API line by line, and append to content a StringBuilder
    public static String readUrl(String movieUrl) {
        StringBuilder content = new StringBuilder();

        try {
            URL url = new URL(movieUrl);
            URLConnection connection = url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String data_line;

            while ((data_line = bufferedReader.readLine()) != null) {
                content.append(data_line + "\n");
            }
            bufferedReader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return content.toString();
    }
}
