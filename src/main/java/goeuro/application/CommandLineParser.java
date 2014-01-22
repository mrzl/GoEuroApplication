package goeuro.application;

/**
 * Author: mrzl
 * Date: 22.01.14
 * Time: 22:12
 * Project: GoEuroTest
 */
public class CommandLineParser {
    // TODO: check per regex if city name contains only letters

    public static String parseCommandLineInput( String[] args ) throws Exception {
        if ( args.length == 1 ) {
            String pattern = "[a-zA-z]+";
            String cityName = args[ 0 ];
            if ( cityName.matches( pattern ) ) {
                return args[ 0 ];
            } else {
                throw new Exception( "Please pass a valid city name as a parameter. Aborting." );
            }
        }
        throw new Exception( "Input parameters not correctly specified. Please only specify one parameter. Aborting." );
    }
}
