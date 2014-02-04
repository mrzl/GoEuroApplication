package goeuro.application.parser;

import goeuro.application.CityInformationField;
import goeuro.application.exceptions.JsonParserException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Author: mrzl
 * Date: 22.01.14
 * Time: 23:05
 * Project: GoEuroTest
 *
 * This class JSONParser parses a single string according to its JSON field specification. It saves all parsed fields into an {@link java.util.ArrayList} for later usage.
 */
public class JSONParser {

    // this arraylist contains all parsed information
    private ArrayList<CityInformationField> parsedFields;

    /**
     * The constructor of the JSON parser class simply initializes the {@link java.util.ArrayList} which holds all parsed information.
     */
    public JSONParser(  ) {
        this.parsedFields = new ArrayList<CityInformationField>();
    }

    /**
     * This is the core method of this class. It parses a passed JSON string and stores all fields into the global variable parsedFields.
     *
     * @param jsonString This String contains the entire JSON data, which needs to be parsed
     * @throws JSONException throws a {@link org.json.JSONException} if some parsing goes wrong. Its a pretty generic exception here, but does the job i guess.
     */
    public void parse( String jsonString ) throws JSONException, JsonParserException {
        // creates a JSONObject from the String containing the entire JSOn structure
        JSONObject jsonObject = new JSONObject( jsonString );
        // gets a JSONArray from the root symbol "results"
        JSONArray jsonArray = jsonObject.getJSONArray( "results" );
        // loops through all found elements, which are under the results-tree
        for ( int i = 0; i < jsonArray.length(); i++ ) {
            // create a JSONObject from each sub-structure of the JSON data ( this is where all data of each individual city-information lies )
            JSONObject cityInformationJsonObject = jsonArray.getJSONObject( i );
            // retrieves all information separately from each JSONObject
            long id = getID( cityInformationJsonObject );
            String _type = get_Type( cityInformationJsonObject );
            String name = getName( cityInformationJsonObject );
            String type = getType( cityInformationJsonObject );
            double[] geo_position = getGeoLocation( cityInformationJsonObject );

            // adds a new CityInformationField object to the global list that contains all parsed city informations
            this.parsedFields.add( new CityInformationField( _type, id, name, type, geo_position ) );
        }
    }

    /**
     * This returns all successfully parsed city information fields
     *
     * @return a {@link java.util.ArrayList} that contains all parsed city information fields
     */
    public ArrayList<CityInformationField> getParsedFields() {
        return this.parsedFields;
    }

    /**
     * Retrieves the "id" field from the passed {@link org.json.JSONObject} that contains the entire JSON structure of one city information tree
     *
     * @param jsonObject a {@link org.json.JSONObject} that contains the entire JSON structure of one city information tree
     *
     * @return a {@link java.lang.Long} containing the parsed ID of a city
     *
     * @throws JsonParserException if the parsing goes wrong, throw an exception which contains the field, which couldn't be parsed.
     */
    private Long getID( JSONObject jsonObject ) throws JsonParserException {
        String fieldName = "_id";
        return Long.valueOf( getFieldByName( jsonObject, fieldName ) );
    }

    /**
     * Retrieves the "_type" field from the passed {@link org.json.JSONObject} that contains the entire JSON structure of one city information tree
     *
     * @param jsonObject a {@link org.json.JSONObject} that contains the entire JSON structure of one city information tree
     *
     * @return a {@link java.lang.String} containing the parsed "_type" field of the city
     *
     * @throws JsonParserException if the parsing goes wrong, throw an exception which contains the field, which couldn't be parsed.
     */
    private String get_Type( JSONObject jsonObject ) throws JsonParserException {
        String fieldName = "_type";
        return this.getFieldByName( jsonObject, fieldName );
    }

    /**
     * Retrieves the "name" field from the passed {@link org.json.JSONObject} that contains the entire JSON structure of one city information tree
     *
     * @param jsonObject a {@link org.json.JSONObject} that contains the entire JSON structure of one city information tree
     *
     * @return a {@link java.lang.String} containing the parsed "name" field of the city
     *
     * @throws JsonParserException if the parsing goes wrong, throw an exception which contains the field, which couldn't be parsed.
     */
    private String getName( JSONObject jsonObject ) throws JsonParserException {
        String fieldName = "name";
        return this.getFieldByName( jsonObject, fieldName );
    }

    /**
     * Retrieves the "type" field from the passed {@link org.json.JSONObject} that contains the entire JSON structure of one city information tree
     *
     * @param jsonObject a {@link org.json.JSONObject} that contains the entire JSON structure of one city information tree
     *
     * @return a {@link java.lang.String} containing the parsed "type" field of the city
     *
     * @throws JsonParserException if the parsing goes wrong, throw an exception which contains the field, which couldn't be parsed.
     */
    private String getType( JSONObject jsonObject ) throws JsonParserException {
        String fieldName = "type";
        return this.getFieldByName( jsonObject, fieldName );
    }

    /**
     * Retrieves the "geo_position" field from the passed {@link org.json.JSONObject} that contains the entire JSON structure of one city information tree
     * Since the geo_position field is split up in two values for longitude and latitude, this method does some special processing.
     *
     * @param jsonObject a {@link org.json.JSONObject} that contains the entire JSON structure of one city information tree
     *
     * @return a double[] containing the parsed "geo_position" field of the city
     *
     * @throws JsonParserException if the parsing goes wrong, throw an exception which contains the field, which couldn't be parsed.
     */
    private double[] getGeoLocation( JSONObject jsonObject ) throws  JsonParserException {
        String fieldName = "geo_position";

        try {
            JSONObject geoLocationJsonObject = jsonObject.getJSONObject( fieldName );

            String latitudeFieldName = "latitude";
            String longitudeFieldName = "longitude";
            Double latitude = Double.valueOf( this.getFieldByName( geoLocationJsonObject, latitudeFieldName ) );
            Double longitude = Double.valueOf( this.getFieldByName( geoLocationJsonObject, longitudeFieldName ) );

            return new double[]{ latitude, longitude };

        } catch ( JSONException e ) {
            throw new JsonParserException( "Couldn't parse '" + fieldName + "' field. Exiting." );
        }
    }

    /**
     * retrieves the value of a field from the passed {@link org.json.JSONObject}
     * @param jsonObject the {@link org.json.JSONObject} from which the value should be parsed
     * @param fieldName the {@link java.lang.String} which indicates the identifier of the field name, of which the value should be parsed
     *
     * @return the content of the field which was specified by the fieldName parameter
     *
     * @throws JsonParserException if the parsing goes wrong, throw an exception which contains the field, which couldn't be parsed.
     */
    private String getFieldByName( JSONObject jsonObject, String fieldName ) throws JsonParserException{
        try {
            return jsonObject.getString( fieldName );
        } catch ( JSONException e ) {
            throw new JsonParserException( "Couldn't parse '" + fieldName + "' field. Exiting." );
        }
    }
}
