package bsh;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.jaxen.BaseXPath;
import org.jaxen.JaxenException;
import java.io.StringReader;
import java.util.List;

public class TestWithXPath {
    private static final String BEANSHELL_CODE  = "x = 42;";

    @Test public void checkNodeCounts() throws ParseException, JaxenException {
        Parser p = new Parser(new StringReader(BEANSHELL_CODE));
        p.Line();
        Node root = p.popNode();
        String xpath = "//PrimaryExpression";
        List nodes = query(xpath, root);
        assertEquals("Unexpected result from " + xpath, 2, nodes.size());
        xpath = "//PrimaryExpression/Literal";
        nodes = query(xpath, root);
        assertEquals("Unexpected result from " + xpath, 1, nodes.size());
    }

    @Test public void checkNameValue() throws ParseException, JaxenException {
        Parser p = new Parser(new StringReader(BEANSHELL_CODE));
        p.Line();
        String xpath = "//PrimaryExpression/AmbiguousName[@text='x']";
        List nodes = query(xpath, p.popNode());
        assertEquals("Unexpected result from " + xpath, 1, nodes.size());
    }
    private List query(String xpath, Node node) throws JaxenException {
        BaseXPath base = new BaseXPath(xpath, new DocumentNavigator());
        return base.selectNodes(node);
    }
}
