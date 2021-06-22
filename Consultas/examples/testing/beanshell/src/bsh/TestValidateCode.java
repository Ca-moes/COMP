package bsh;
import org.junit.Test;
import java.io.StringReader;
public class TestValidateCode {
    @Test(expected=ParseException.class) public void parseGibberish() throws ParseException {
        String code = "fizzle";
        Parser p = new Parser(new StringReader(code));
        p.Line();
    }
    @Test public void parseGoodCode() throws ParseException {
        String code = "int x = 42;";
        Parser p = new Parser(new StringReader(code));
        p.Line();
    }
}
