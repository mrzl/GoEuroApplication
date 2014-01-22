package goeuro.application;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * Author: mrzl
 * Date: 22.01.14
 * Time: 22:10
 * Project: GoEuroTest
 */
public class JSONReader {
    private String jsonUrl;
    public JSONReader() {
        this.jsonUrl = "https://api.goeuro.com/api/v1/suggest/position/en/name/";
    }

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public String getInfoByCity( String cityName ) throws Exception {
        String fullUrl = this.jsonUrl += cityName;
        try {
            InputStream is = new URL( fullUrl ).openStream();
            BufferedReader rd = new BufferedReader( new InputStreamReader(is, Charset.forName( "UTF-8" )));
            String jsonText = readAll(rd);
            is.close();
            return jsonText;
        } catch ( IOException e ) {
            throw e;
        }
    }
}