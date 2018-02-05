package com.company;
import java.io.IOException;

import java.net.MalformedURLException;
import java.util.HashMap;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;

import static com.company.Url.*;

public class Main {
    private static final String GOOGLE_URL_SHORT_API = "https://www.googleapis.com/urlshortener/v1/url";
    private static final String GOOGLE_API_KEY = "AIzaSyCHswiOIcysz7jPEvRcUh2w7HKRvT0_kQo";
    public Main(String url)
    {
        String googleShort = Main.shortenUrl(url);
        String LongUrlBack = Main.getActualURLAgainstGoogleShortURL(googleShort);
       Url.display(LongUrlBack);
    }
        public static void main(String[] args) {




       // System.out.println("Short URL by google API :"+googleShort);
      //  System.out.println("Long URL against Short URL by google API :"+LongUrlBack);
	// write your code here
    }
    public static String shortenUrl(String longUrl){

        if (longUrl == null) {
            return longUrl;
        }else if(!longUrl.startsWith("http://") && !longUrl.startsWith("https://")){
            longUrl = "http://"+longUrl;
        }
        try {
            String json = "{\"longUrl\": \""+longUrl+"\"}";
            String apiURL = GOOGLE_URL_SHORT_API+"?key="+GOOGLE_API_KEY;

            HttpPost postRequest = new HttpPost(apiURL);
            postRequest.setHeader("Content-Type", "application/json");
            postRequest.setEntity(new StringEntity(json, "UTF-8"));

            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpResponse response = httpClient.execute(postRequest);
            String responseText = EntityUtils.toString(response.getEntity());

            Gson gson = new Gson();
            @SuppressWarnings("unchecked")
            HashMap<String, String> res = gson.fromJson(responseText, HashMap.class);

            return res.get("id");

        } catch (MalformedURLException e) {
            return "error";
        } catch (IOException e) {
            return "error";
        }
    }

    public static String getActualURLAgainstGoogleShortURL(String shortUrl){

        if (shortUrl == null) {
            return shortUrl;
        }else if(!shortUrl.startsWith("http://") && !shortUrl.startsWith("https://")){
            shortUrl = "http://"+shortUrl;
        }
        try {
            String apiURL =GOOGLE_URL_SHORT_API+"?key="+GOOGLE_API_KEY + "&shortUrl=" + shortUrl;
            HttpGet getRequest = new HttpGet(apiURL);

            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpResponse response = httpClient.execute(getRequest);
            String responseText = EntityUtils.toString(response.getEntity());

            Gson gson = new Gson();
            @SuppressWarnings("unchecked")
            HashMap<String, String> res = gson.fromJson(responseText, HashMap.class);

            return res.get("longUrl");

        } catch (MalformedURLException e) {
            return "error";
        } catch (IOException e) {
            return "error";
        }
    }
}
