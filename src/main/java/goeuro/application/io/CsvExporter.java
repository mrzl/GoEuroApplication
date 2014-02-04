package goeuro.application.io;

import goeuro.application.CityInformationField;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Author: mrzl
 * Date: 23.01.14
 * Time: 00:01
 * Project: GoEuroTest
 *
 * This class exports data to a CSV file. It's adopted to work with special GoEuro data.
 */
public class CsvExporter {
    // some Strings which specify the selected delimiter and the file name
    private String delimiter, exportedCsvFileName;

    /**
     * The constructor of the CSV file exporter. It's not doing a lot except from
     * setting a couple of global variables
     *
     * @param exportedCsvFileName a {@link java.lang.String} that sets the file name of the exported CSV file
     */
    public CsvExporter( final String exportedCsvFileName ) {
        this.exportedCsvFileName = exportedCsvFileName;
        // delimiter needs to be a semicolon, because the field 'name' contains commas
        this.delimiter = ";";
    }

    /**
     * This is the core of the class. it takes an {@link java.util.ArrayList} of {@link goeuro.application.CityInformationField}
     * and saves the content of this list into a CSV file with the specified file name and the specified delimiter
     *
     * @param fieldsToExport an {@link java.util.ArrayList} containing {@link goeuro.application.CityInformationField} which are being saved to the file
     * @throws IOException if something with the writing goes wrong, it throws an {@link java.io.IOException}
     */
    public void exportCsv( final ArrayList<CityInformationField> fieldsToExport ) throws IOException {
        // creates a new FileWriter stating the file name
        FileWriter writer = new FileWriter( this.exportedCsvFileName );
        // writes the header of the CSV file to the file
        writeCsvHeaderToFile( writer );
        // loops through all passed fields and saves it to the file
        for ( CityInformationField f : fieldsToExport ) {
            writeFieldToFile( writer, f );
        }

        // actually flushes the data to the harddrive
        writer.flush();
        // and closes the output
        writer.close();

        System.out.println( "Sucessfully exported " + fieldsToExport.size() + " fields to " + this.exportedCsvFileName + "." );
    }

    /**
     * Writes a typical header to the CSV file.
     *
     * @param writer a {@link java.io.FileWriter} the writer, where the header is to be written to
     *
     * @throws IOException if something goes wrong, an exception is thrown
     */
    private void writeCsvHeaderToFile( final FileWriter writer ) throws IOException {
        writer.append( "_type" + this.delimiter + "_id" + this.delimiter + "name" + this.delimiter + "type" + this.delimiter + "latitude" + this.delimiter + "longitude" + System.lineSeparator() );
    }

    /**
     * Writes one line of data to the CSV file. This data is being specified by one {@link goeuro.application.CityInformationField} object
     *
     * @param writer the {@link java.io.FileWriter} where the field is being written to
     * @param cityInformationField an instance representing the data of one line of the CSV file.
     *
     * @throws IOException if something goes wrong, an {@link java.io.IOException} is being thrown
     */
    private void writeFieldToFile( final FileWriter writer, final CityInformationField cityInformationField ) throws IOException {
        String lineToAdd =
                cityInformationField.get_type() + this.delimiter +
                        cityInformationField.getId() + this.delimiter +
                        cityInformationField.getName() + this.delimiter +
                        cityInformationField.getType() + this.delimiter +
                        cityInformationField.getLatitude() + this.delimiter +
                        cityInformationField.getLongitude() + System.lineSeparator();

        writer.append( lineToAdd );
    }
}
