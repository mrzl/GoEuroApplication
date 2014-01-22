package goeuro.application;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Author: mrzl
 * Date: 23.01.14
 * Time: 00:01
 * Project: GoEuroTest
 */
public class CsvExporter {

    private String delimiter, exportedCsvFileName;

    public CsvExporter( String exportedCsvFileName ) {
        this.exportedCsvFileName = exportedCsvFileName;
        // delimiter needs to be a semicolon, because the field 'name' contains commas
        this.delimiter = ";";
    }

    public void exportCsv( ArrayList<Field> fieldsToExport ) {
        try {
            FileWriter writer = new FileWriter( this.exportedCsvFileName );
            writeCsvHeaderToFile( writer );

            for ( Field f : fieldsToExport ) {
                writeFieldToFile( writer, f );
            }

            writer.flush();
            writer.close();
        } catch ( IOException e ) {
            e.printStackTrace();
        } finally {
            System.out.println( "Sucessfully exported " + fieldsToExport.size() + " fields to " + this.exportedCsvFileName + "." );
        }

    }

    private void writeCsvHeaderToFile( FileWriter writer ) throws IOException {
        writer.append( "_type" + this.delimiter + "_id" + this.delimiter + "name" + this.delimiter + "type" + this.delimiter + "latitude" + this.delimiter + "longitude" + System.lineSeparator() );
    }

    private void writeFieldToFile( FileWriter writer, Field field ) throws IOException {
        String lineToAdd =
                field.get_type() + this.delimiter +
                        field.getId() + this.delimiter +
                        field.getName() + this.delimiter +
                        field.getType() + this.delimiter +
                        field.getLatitude() + this.delimiter +
                        field.getLongitude() + System.lineSeparator();

        writer.append( lineToAdd );
    }
}
