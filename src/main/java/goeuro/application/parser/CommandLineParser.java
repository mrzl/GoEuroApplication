package goeuro.application.parser;

import goeuro.application.exceptions.InvalidParameterNameException;
import goeuro.application.exceptions.TooManyParametersException;

/**
 * Author: mrzl
 * Date: 22.01.14
 * Time: 22:12
 * Project: GoEuroTest
 *
 * This class parses the command line input.
 */
public class CommandLineParser {

    /**
     * This method parses the entire command line input. if more than one parameter was specified, it throws a {@link goeuro.application.exceptions.TooManyParametersException}.
     * If only one parameter was passed, but it contains special characters like numbers,slashes,stars etc a {@link goeuro.application.exceptions.InvalidParameterNameException} exception is
     * thrown.
     * @param args the entire command line input
     * @return a string containing only the city name
     * @throws Exception depending on the input error, either a {@link goeuro.application.exceptions.TooManyParametersException} or a {@link goeuro.application.exceptions.InvalidParameterNameException} is thrown
     */
    public static String parseCommandLineInput( String[] args ) throws Exception {
        // is there only one command line parameter specified?
        if ( args.length == 1 ) {
            // match the passed name in order to avoid unwanted typos etc. here we are ignoring cities with special characters like öäüß. but i doubt the api supports it anyways
            String pattern = "[a-zA-z]+";
            String cityName = args[ 0 ];
            if ( cityName.matches( pattern ) ) {
                // if the regular expression is true, return the parameter
                return cityName;
            } else {
                // if it doesnt match it, throw a new exception stating that the one passed parameter contains wrong characters
                throw new InvalidParameterNameException( "Please pass a valid city name as a parameter. Aborting." );
            }
        }
        else {
            // if more than one or less than one parameter was passed, throw an exception stating that a wrong number of parametes was passed.
            throw new TooManyParametersException( "Input parameters not correctly specified. Please only specify one parameter. Aborting." );
        }
    }
}
