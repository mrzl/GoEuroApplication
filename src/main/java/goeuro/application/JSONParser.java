package goeuro.application;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Author: mrzl
 * Date: 22.01.14
 * Time: 23:05
 * Project: GoEuroTest
 */
public class JSONParser {

    private ArrayList<Field> parsedFields;

    public JSONParser( String jsonString ) {
        this.parsedFields = new ArrayList<Field>();

        try {
            JSONObject jsonObject = new JSONObject( jsonString );
            JSONArray jsonArray = jsonObject.getJSONArray( "results" );
            for ( int i = 0; i < jsonArray.length(); i++ ) {
                JSONObject s = jsonArray.getJSONObject( i );

                long id = getID( s );
                String _type = get_Type( s );
                String name = getName( s );
                String type = getType( s );
                double[] geo_position = getGeoLocation( s );

                parsedFields.add( new Field( _type, id, name, type, geo_position ) );
            }

        } catch ( JSONException e ) {

        }
    }

    public ArrayList< Field > getParsedFields() {
        return parsedFields;
    }

    private Long getID( JSONObject jsonObject ) {
        String fieldName = "_id";
        return Long.valueOf( getFieldByName( jsonObject, fieldName ) );
    }

    private String get_Type( JSONObject jsonObject ) {
        String fieldName = "_type";
        return ( String ) getFieldByName( jsonObject, fieldName );
    }

    private String getName( JSONObject jsonObject ) {
        String fieldName = "name";
        return ( String ) getFieldByName( jsonObject, fieldName );
    }

    private String getType( JSONObject jsonObject ) {
        String fieldName = "type";
        return ( String ) getFieldByName( jsonObject, fieldName );
    }

    private double[] getGeoLocation( JSONObject jsonObject ) {
        String fieldName = "geo_position";

        try {
            JSONObject geoLocationJsonObject = jsonObject.getJSONObject( fieldName );

            String latitudeFieldName = "latitude";
            String longitudeFieldName = "longitude";
            Double latitude = Double.valueOf( this.getFieldByName( geoLocationJsonObject, latitudeFieldName ) );
            Double longitude = Double.valueOf( this.getFieldByName( geoLocationJsonObject, longitudeFieldName ) );

            double[] geoLocation = { latitude, longitude };
            return geoLocation;

        } catch ( JSONException e ) {
            GeoEuroTestMain.exitWithErrorMessage( "Couldn't parse '" + fieldName + "' field. Exiting." );
            System.exit( 1 );
        }
        return new double[]{ 0, 0 };
    }

    private String getFieldByName( JSONObject jsonObject, String fieldName ) {
        try {
            System.out.println( jsonObject.getString( fieldName ) );
            return jsonObject.getString( fieldName );
        } catch ( JSONException e ) {
            GeoEuroTestMain.exitWithErrorMessage( "Couldn't parse '" + fieldName + "' field. Exiting." );
            System.exit( 1 );
        }

        return new String();
    }
}
