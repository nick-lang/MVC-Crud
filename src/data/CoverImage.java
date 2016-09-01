package data;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CoverImage {
    private String location = "https://www.googleapis.com/books/v1/volumes?q=isbn:";
    String coverImage = "";
    
    public String getCoverImage(String isbn) throws IOException {
    		location = location + isbn;
    	//	System.out.println("location: " + location);
        URL url = new URL(location);
        URLConnection conn = url.openConnection();
        InputStream reader = conn.getInputStream();

     // char[] charArray = new char[conn.getContentLength()];
        byte[] charArray = new byte[2048];
        ByteArrayOutputStream ba = new ByteArrayOutputStream();
        int n;
        while ((n = reader.read(charArray)) != -1) {
        		ba.write(charArray, 0, n);
        }
        reader.close();

        String json = ba.toString();
     // System.out.println("json: " + json);
        
        Pattern p = Pattern.compile("\"thumbnail\": \"(.*?)\"");
        Matcher m = p.matcher(json);
        if (m.find()){
        		coverImage = m.group(1);
      //  	System.out.println("found: " + coverImage);
        	}
        return coverImage;

    }
}