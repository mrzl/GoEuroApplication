package goeuro.application;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * Author: mrzl
 * Date: 22.01.14
 * Time: 22:10
 * Project: GoEuroTest
 *
 * This class reads JSON information from the GoEuro API.
 */
public class JSONReader {
    // the String which contains the url to the GoEuroAPI
    private String jsonUrl;
    // the String where the entire JSON result data is being saved in
    private String jsonString;

    /**
     * The constructor of the JSONReader saves the API url as a global variable.
     */
    public JSONReader() {
        this.jsonUrl = "https://api.goeuro.com/api/v1/suggest/position/en/name/";
        this.jsonString = "";
    }

    /**
     * This is the heart of this class. It actually downloads the JSON data from the GoEuro API.
     *
     * @param cityName a {@link java.lang.String} which contains the name of the city. this String is being appended to the API url.
     *
     * @throws IOException If something goes wrong with the data retrieval, an exception is thrown
     */
    public void retrieveJsonFromGoEuroApi( String cityName ) throws IOException {
        // appends the city name to the API string
        String fullUrl = this.jsonUrl += cityName;
        // opens the stream to the API
        InputStream is = new URL( fullUrl ).openStream();
        // creates a reader, which gets all data from the size
        BufferedReader rd = new BufferedReader( new InputStreamReader( is, Charset.forName( "UTF-8" ) ) );
        // saves all information from the reader to a String
        String jsonText = readAll( rd );
        // globally saves the String to this class
        this.setJsonString( jsonText );
        // closes the input stream
        is.close();
    }

    /**
     * Returns the entire JSON data as a String.
     *
     * @return a String which contains all JSON data from the API. Can be empty if no data was loaded beforehand.
     */
    public String getJsonString() {
        return this.jsonString;
    }

    /**
     * Sets the jsonString to some value
     * @param jsonString the value which is going to be saved in the global jsonString var
     */
    private void setJsonString( String jsonString ) {
        this.jsonString = jsonString;
    }

    /**
     * Reads all data from the passed Reader.
     *
     * @param rd a {@link java.io.Reader} that read all information from the API
     *
     * @return returns a {@link java.lang.String} that contains the entire JSON data
     *
     * @throws IOException if something goes wrong, an {@link java.io.IOException} is begin thrown
     */
    private String readAll( Reader rd ) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ( ( cp = rd.read() ) != -1 ) {
            sb.append( ( char ) cp );
        }
        return sb.toString();
    }
}
