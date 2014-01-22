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
        if ( args.length != 1 ) {
            throw new Exception( "Can't parse input." );
        } else {
            return args[ 0 ];
        }
    }
}
