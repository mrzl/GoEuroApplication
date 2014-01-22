package goeuro.application;

/**
 * Author: mrzl
 * Date: 22.01.14
 * Time: 21:24
 * Project: GoEuroTest
 */
public class GeoEuroTestMain {

    public static void exitWithErrorMessage( String errorMessage ) {
        System.err.println( errorMessage );
        System.exit( 1 );
    }

    public static void main( String[] args ) {
        String cityName = "";
        try {
            cityName = CommandLineParser.parseCommandLineInput( args );
        } catch ( Exception e ) {
            exitWithErrorMessage( "Couldn't parse command line input." );
        }


        JSONReader reader = new JSONReader();
        String jsonString = "";
        try {
            jsonString = reader.getInfoByCity( cityName );
        } catch ( Exception e ) {
            exitWithErrorMessage( "Couldn't load JSON." );
        }

        JSONParser parser = new JSONParser( jsonString );
    }
}
