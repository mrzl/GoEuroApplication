package goeuro.application.parser;

import goeuro.application.parser.CommandLineParser;
import goeuro.application.exceptions.InvalidParameterNameException;
import goeuro.application.exceptions.TooManyParametersException;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Author: mrzl
 * Date: 25.01.14
 * Time: 18:04
 * Project: GoEuroTest
 */
public class CommandLineParserTest {

    @Test(expected = TooManyParametersException.class)
    public void testForTooManyParametersException() throws Exception {
        String[] commandLineParameter = {"Berlin", "extra parameter" };
        CommandLineParser.parseCommandLineInput(  commandLineParameter );
    }

    @Test(expected = InvalidParameterNameException.class)
    public void testForInvalidParameterException() throws Exception {
        String[] commandLineParameter = { "Berlin*" };
        CommandLineParser.parseCommandLineInput( commandLineParameter );
    }

    @Test
    public void testForValidMethodCall() throws Exception {
        String[] commandLineParameter = { "Berlin" };
        String cityName = CommandLineParser.parseCommandLineInput( commandLineParameter );

        assertEquals( "Testing for valid input.", "Berlin", cityName );
    }
}
