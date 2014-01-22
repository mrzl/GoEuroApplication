package goeuro.application;

/**
 * Author: mrzl
 * Date: 22.01.14
 * Time: 23:14
 * Project: GoEuroTest
 */
public class Field {

    private long id;
    private String _type, name, type;
    private double[] geo_position;

    public Field( String _type, long id, String name, String type, double[] geo_position ) {
        this._type = _type;
        this.id = id;
        this.name = name;
        this.type = type;
        this.geo_position = geo_position;
    }

    public long getId() {
        return id;
    }

    public void setId( long id ) {
        this.id = id;
    }

    public String get_type() {
        return _type;
    }

    public void set_type( String _type ) {
        this._type = _type;
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType( String type ) {
        this.type = type;
    }

    public double[] getGeoPosition() {
        return geo_position;
    }

    public void setGeoPosition( double[] geo_position ) {
        this.geo_position = geo_position;
    }

    public double getLatitude() {
        return this.getGeoPosition() [ 0 ];
    }

    public double getLongitude() {
        return this.getGeoPosition() [ 1 ];
    }
}
