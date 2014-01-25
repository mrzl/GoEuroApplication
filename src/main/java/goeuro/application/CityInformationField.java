package goeuro.application;

/**
 * Author: mrzl
 * Date: 22.01.14
 * Time: 23:14
 * Project: GoEuroTest
 *
 * This class holds information about one single field from the parsed JSON data.
 */
public class CityInformationField {

    // all different information each city has
    private long id;
    private String _type, name, type;
    private double[] geo_position;

    /**
     * The constructor of this class sets all class variables.
     * @param _type the field "_type" from the JSON data
     * @param id the field "id" from the JSON data
     * @param name the field "name" from the JSON data
     * @param type the field "type" from the JSON data
     * @param geo_position the field "geo_position" from the JSON data
     */
    public CityInformationField( String _type, long id, String name, String type, double[] geo_position ) {
        this._type = _type;
        this.id = id;
        this.name = name;
        this.type = type;
        this.geo_position = geo_position;
    }

    /**
     * Returns the ID of the city
     * @return returns a long which is the ID of the city
     */
    public long getId() {
        return id;
    }

    /**
     * Returns the field "_type" of the JSON data
     * @return a String containing the _type
     */
    public String get_type() {
        return _type;
    }

    /**
     * Returns the field "name" of the JSON data
     * @return a String containing the field name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the field "type" of the JSON data
     * @return a String containing the field "type"
     */
    public String getType() {
        return type;
    }

    /**
     * Returns the latitude of the city
     * @return a double being the latitude
     */
    public double getLatitude() {
        return this.geo_position[ 0 ];
    }

    /**
     * Returns the longitude of the city
     * @return a double being the longitude
     */
    public double getLongitude() {
        return this.geo_position[ 1 ];
    }
}
