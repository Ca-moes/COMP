package bsh;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import java.io.StringReader;
public class TestAST {
    @Test public void nullValueSet() throws ParseException {
        String code = "x = 42;";
        Parser p = new Parser(new StringReader(code));
        p.Line();
        Node n = p.popNode();
        assertTrue(
            "Root should be an assignment", 
            n instanceof BSHAssignment);
        assertTrue(
            "First child should be a PrimaryExpression", 
            n.jjtGetChild(0) instanceof BSHPrimaryExpression);
        assertEquals(
            "Root should have two children", 
            2, n.jjtGetNumChildren());
        assertTrue(
            "Second child should be a PrimaryExpression", 
            n.jjtGetChild(1) instanceof BSHPrimaryExpression);
        assertTrue(
            "First grandchild should be an AmbiguousName", 
            n.jjtGetChild(0).jjtGetChild(0) instanceof BSHAmbiguousName);
        assertTrue(
            "Second grandchild should be a Literal", 
            n.jjtGetChild(1).jjtGetChild(0) instanceof BSHLiteral);
        assertEquals(
            "Second grandchild's value should be 42", 
            ((BSHLiteral)n.jjtGetChild(1).jjtGetChild(0)).value.toString(), "42");
    }

}
