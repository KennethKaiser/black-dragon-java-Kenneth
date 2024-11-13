package dk.acto.blackdragon.implementation;

import dk.acto.blackdragon.service.DataFetcher;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DataFetcherImpl implements DataFetcher {

    /**
     * Author: Kenneth Kaiser
     *
     * Simply gets the content from the URL, reads it line by line and appends to stringbuilder with a new line.
     *
     * @return the fetched content as a String, with any trailing newline removed
     */
    @Override
    public String fetchData(URL url) {
        StringBuilder result = new StringBuilder();
        try {
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line).append("\n"); //Adding a line too much in the end
            }
            result = new StringBuilder(result.toString().trim()); // Trimming the result to remove excess line (there is probably a better approach)
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.toString();
    }
}
