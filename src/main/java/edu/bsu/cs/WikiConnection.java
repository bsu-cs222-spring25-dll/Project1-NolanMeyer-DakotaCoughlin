package edu.bsu.cs;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class WikiConnection {

    public String createRequestUrl(String title){
        String encodedTitle = URLEncoder.encode(title, StandardCharsets.UTF_8);
        encodedTitle = encodedTitle.replace("+","%20");
        return "https://en.wikipedia.org/w/api.php?action=query&format=json&prop=revisions&titles="
                + encodedTitle + "&rvprop=timestamp|user&rvlimit=21&redirects";
    }

    public InputStream getInputStream(String url) throws IOException {
        URL urlConnection = new URL("https://en.wikipedia.org");
        URLConnection connection = urlConnection.openConnection();
        connection.setRequestProperty("User-Agent",
                "Revision Reporter/0.1 (nolan.meyer@bsu.edu)");
       return connection.getInputStream();
    }
}
