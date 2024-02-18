package edu.psgv.sweng861;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;


public class NewsMashup {

    public static void main(String[] args) throws IOException {
        // Sports-related code
    	getSportsSchedule();
    	
    	// News-related code
    	getNews();
    	
    	// Weather-related code
    	getWeather();
    }
    
    private static void getSportsSchedule() throws IOException {
    	String url = "https://www.espn.com/nba/team/schedule/_/name/cle";

        // Make the HTTP request
        Document doc = Jsoup.connect(url)
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36")
                .get();

        // Select the entire table as the wrapper (adjust if structure changes)
        Element table = doc.select("table.Table").first();

        // Check if the table exists
        if (table == null) {
            System.out.println("Error: Could not find the table element.");
            return;
        }

        // Extract schedule data from each row
        Elements rows = table.select("tr[data-idx]");
        int i = 0;
        
        // Print title for readability
        System.out.println("Cleveland Cavaliers 2023-24 Schedule");
        System.out.println("----------------------------------------");

        for (Element row : rows) {
            if (i++ < 2) {
                continue; // Skip the first two rows of the table
            }

            // Extract data from each column (adjust based on actual order)
            String date = row.select("td:nth-child(1)").text();
            String opponent = row.select("td:nth-child(2)").text();
            String result = row.select("td:nth-child(3)").text();
            String winLoss = row.select("td:nth-child(4)").text();
            String hiPoints = row.select("td:nth-child(5)").text();
            String hiRebounds = row.select("td:nth-child(6)").text();
            String hiAssists = row.select("td:nth-child(7)").text();

            // Do something with the extracted data (e.g., store in a list, print)
            System.out.println("Date: " + date);
            System.out.println("Opponent: " + opponent);
            System.out.println("Result: " + result);
            System.out.println("Win-Loss: " + winLoss);
            System.out.println("HI Points: " + hiPoints);
            System.out.println("HI Rebounds: " + hiRebounds);
            System.out.println("HI Assists: " + hiAssists);
            System.out.println("-----------------------");
        }
    }
    
    private static void getNews() throws IOException {
    	String rssUrl = "https://www.wkyc.com/feeds/syndication/rss/news";
    			
    	Document doc = Jsoup.connect(rssUrl).get();
        Element rss = doc.selectFirst("rss"); // Or "feed" depending on your feed format

        Elements items = rss.select("item"); // Adjust selector if needed

        System.out.println("\nNews Headlines:");
        System.out.println("--------------------");

        for (Element item : items) {
            String title = item.selectFirst("title").text();
            String description = item.selectFirst("description").text();
            String link = item.selectFirst("link").attr("href");

            System.out.println("Title: " + title);
            System.out.println("Description: " + description);
            System.out.println("Link: " + link);
            System.out.println("--------------------");
        }
    }
    
    private static void getWeather() throws IOException {
    	String rssUrl = "https://www.wkyc.com/feeds/syndication/rss/weather";
    	
    	Document doc = Jsoup.connect(rssUrl).get();
        Element rss = doc.selectFirst("rss"); // Or "feed" depending on your feed format

        Elements items = rss.select("item"); // Adjust selector if needed

        System.out.println("\nWeather:");
        System.out.println("--------------------");

        for (Element item : items) {
            String title = item.selectFirst("title").text();
            String description = item.selectFirst("description").text();
            String link = item.selectFirst("link").attr("href");

            System.out.println("Title: " + title);
            System.out.println("Description: " + description);
            System.out.println("Link: " + link);
            System.out.println("--------------------");
        }
    }
    
}

