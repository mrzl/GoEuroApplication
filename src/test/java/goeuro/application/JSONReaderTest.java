package goeuro.application;

import goeuro.application.JSONReader;
import org.junit.Before;
import org.junit.Test;

/**
 * Author: mrzl
 * Date: 25.01.14
 * Time: 21:03
 * Project: GoEuroTest
 */
public class JSONReaderTest {

    private JSONReader reader;

    @Before
    public void setUp() throws Exception {
        reader = new JSONReader();
    }

    @Test
    public void testRetrieveJsonFromGoEuroApi() throws Exception {
        reader.retrieveJsonFromGoEuroApi( "Berlin" );
    }
}
