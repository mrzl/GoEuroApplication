package goeuro.application;

import goeuro.application.exceptions.JsonParserException;
import goeuro.application.io.CsvExporter;
import goeuro.application.io.JSONReader;
import goeuro.application.parser.CommandLineParser;
import goeuro.application.parser.JSONParser;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Author: mrzl
 * Date: 22.01.14
 * Time: 21:24
 * Project: GoEuroTest
 */
public class GeoEuroTestMain {


    /**
     * This is the entry point of the program- main function.
     *
     * @param args should have only one parameter. the city name of which the information is to be retrieved.
     */
    public static void main( String[] args ) {

        // parses the command line
        String cityName  = parseInput( args );

        // reads JSON from the GoEuro API
        String jsonString = loadJSON( cityName );

        // parses the JSON data
        ArrayList< CityInformationField > parsedFields = parseJSON( jsonString );

        // exports the data to a CSV file
        exportCSV( parsedFields, cityName );
    }

    /**
     * This method parses the input from the command line
     *
     * @param args the entire command line input
     *
     * @return a String containing the parsed city name
     */
    private static String parseInput( String [] args ) {
        String cityName = "";
        try {
            // parsing the command line parameters and returning the string of the city name
            cityName = CommandLineParser.parseCommandLineInput( args );
        } catch ( Exception e ) {
            // if the command line parsing went wrong, print the message and exit
            exitWithErrorMessage( e.getMessage() );
        }

        return cityName;
    }

    /**
     * loads the JSON data from the GoEuro API and returns the entire json data as a String
     *
     * @param cityName the city name for which information shall be loaded
     *
     * @return a String containing the entire JSON data
     */
    private static String loadJSON( String cityName ) {
        JSONReader reader = new JSONReader();
        try {
            reader.retrieveJsonData( cityName );
        } catch ( Exception e ) {
            // if something goes wrong, print the message and exit
            System.out.println( e.getMessage() );
            exitWithErrorMessage( "Couldn't load JSON from the GoEuro JSON API. Aborting." );
        }

        return reader.getJsonString();
    }

    /**
     * This parses the JSON String and returns all parsed fields
     *
     * @param jsonString the entire JSON String which is being parsed
     *
     * @return an {@link java.util.ArrayList} with all parsed city fields
     */
    private static ArrayList< CityInformationField > parseJSON( String jsonString ) {
        JSONParser parser = new JSONParser();
        try {
            // parse the string
            parser.parse( jsonString );
        } catch ( JSONException e ) {
            // if something with the parsing goes wrong, print the message and exit
            exitWithErrorMessage( e.getMessage() + " Aborting." );
        } catch ( JsonParserException e ) {
            exitWithErrorMessage( e.getMessage() );
        }

        return parser.getParsedFields();
    }

    /**
     * exports all data to a CSV file
     *
     * @param parsedFields an {@link java.util.ArrayList} containing all parsed city fields
     *
     * @param cityName the name of the city. necessary for the name of the file
     */
    private static void exportCSV( ArrayList< CityInformationField > parsedFields, String cityName ) {
        String exportedCsvFileName = cityName + ".csv";
        CsvExporter csvExport = new CsvExporter( exportedCsvFileName );
        try {
            // write the parsed fields to the csv file
            csvExport.exportCsv( parsedFields );
        } catch ( IOException e ) {
            // if something goes wrong with the csv export, print the message and exit.
            exitWithErrorMessage( e.getMessage() + " Aborting." );
        }
    }

    /**
     * This method prints an error message to the error console and exits.
     *
     * @param errorMessage the message which is being printed to the error console before the program quits.
     */
    private static void exitWithErrorMessage( String errorMessage ) {
        System.err.println( errorMessage );
        System.exit( 1 );
    }
}
