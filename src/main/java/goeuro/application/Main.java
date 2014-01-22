package goeuro.application;

/**
 * Author: mrzl
 * Date: 22.01.14
 * Time: 21:24
 * Project: GoEuroTest
 */
public class Main {

    private static void exitWithErrorMessage( String errorMessage ) {
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
        try {
            System.out.println( reader.getInfoByCity( cityName ) );
        } catch ( Exception e ) {
            exitWithErrorMessage( "Couldn't load JSON." );
        }

    }
}
