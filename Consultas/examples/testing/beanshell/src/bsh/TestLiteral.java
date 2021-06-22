package bsh;

import static org.junit.Assert.*;
import org.junit.Test;
import java.io.StringReader;

public class TestLiteral {
    @Test public void nullValueSet() throws ParseException {
        String code = "void";
        Parser p = new Parser(new StringReader(code));
        p.Literal();
        Node n = p.popNode();
        assertTrue("First node should be a Literal", n instanceof BSHLiteral);
        assertEquals("Type should be void", ((BSHLiteral)n).value, Primitive.VOID);
    }
}
