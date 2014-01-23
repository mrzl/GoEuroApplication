package goeuro.application;

import org.json.JSONException;

import java.io.IOException;

/**
 * Author: mrzl
 * Date: 22.01.14
 * Time: 21:24
 * Project: GoEuroTest
 */
public class GeoEuroTestMain {

    /**
     * This method prints an error message to the error console and exits.
     *
     * @param errorMessage the message which is being printed to the error console before the program quits.
     */
    public static void exitWithErrorMessage( String errorMessage ) {
        System.err.println( errorMessage );
        System.exit( 1 );
    }

    /**
     * This is the entry point of the program- main function.
     *
     * @param args should have only one parameter. the city name of which the information is to be retrieved.
     */
    public static void main( String[] args ) {
        String cityName = "";
        try {
            // parsing the command line parameters and returning the string of the city name
            cityName = CommandLineParser.parseCommandLineInput( args );
        } catch ( Exception e ) {
            // if the command line parsing went wrong, print the message and exit
            exitWithErrorMessage( e.getMessage() );
        }

        // creates a new JSONReader object
        JSONReader reader = new JSONReader();
        try {
            // retrieves the entire json result as a string from the goeuro json api
            reader.retrieveJsonFromGoEuroApi( cityName );
        } catch ( Exception e ) {
            // if something goes wrong, print the message and exit
            exitWithErrorMessage( "Couldn't load JSON from the GoEuro JSON API. Aborting." );
        }

        JSONParser parser = new JSONParser();
        try {
            // parse the string
            parser.parse( reader.getJsonString() );
        } catch ( JSONException e ) {
            // if something with the parsing goes wrong, print the message and exit
            exitWithErrorMessage( e.getMessage() + " Aborting." );
        }

        String exportedCsvFileName = cityName + ".csv";
        CsvExporter csvExport = new CsvExporter( exportedCsvFileName );
        try {
            // write the parsed fields to the csv file
            csvExport.exportCsv( parser.getParsedFields() );
        } catch ( IOException e ) {
            // if something goes wrong with the csv export, print the message and exit.
            exitWithErrorMessage( e.getMessage() + " Aborting." );
        }
    }
}
