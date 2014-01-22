package goeuro.application;

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


        JSONReader reader = new JSONReader();
        String jsonString = "";
        try {
            jsonString = reader.getInfoByCity( cityName );
        } catch ( Exception e ) {
            exitWithErrorMessage( "Couldn't load JSON." );
        }

        JSONParser parser = new JSONParser( jsonString );

        String exportedCsvFileName = cityName + ".csv";
        CsvExporter csvExport = new CsvExporter( exportedCsvFileName );
        csvExport.exportCsv( parser.getParsedFields() );

    }
}
