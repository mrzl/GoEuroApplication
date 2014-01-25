package goeuro.application;

import goeuro.application.CityInformationField;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Author: mrzl
 * Date: 25.01.14
 * Time: 18:57
 * Project: GoEuroTest
 */
public class CityInformationFieldTest {

    private CityInformationField field;

    @Before
    public void setUp() throws Exception {
        field = new CityInformationField( "field:_type", 1234567, "field:name", "field:type", new double[]{1.1212, 2.2234 } );
    }

    @Test
    public void testGetId() throws Exception {
        assertEquals( "Testing for field \"id\"", 1234567, field.getId() );
    }

    @Test
    public void testGet_type() throws Exception {
        assertEquals( "Testing for field \"_type\"", "field:_type", field.get_type() );
    }

    @Test
    public void testGetName() throws Exception {
        assertEquals( "Testing for field \"name\"", "field:name", field.getName() );
    }

    @Test
    public void testGetType() throws Exception {
        assertEquals( "Testing for field \"type\"", "field:type", field.getType() );
    }

    @Test
    public void testGetLatitude() throws Exception {
        assertEquals( "Testing for latitude", 1.1212, field.getLatitude() );
    }

    @Test
    public void testGetLongitude() throws Exception {
        assertEquals( "Testing for longitude", 2.2234, field.getLongitude() );
    }
}
