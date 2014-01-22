package goeuro.application;

import java.util.ArrayList;

/**
 * Author: mrzl
 * Date: 23.01.14
 * Time: 00:01
 * Project: GoEuroTest
 */
public class CsvExporter {

    private ArrayList< Field > fieldsToExport;

    public CsvExporter( ArrayList<Field> fieldsToExport ) {
        this.fieldsToExport = fieldsToExport;
    }
}
