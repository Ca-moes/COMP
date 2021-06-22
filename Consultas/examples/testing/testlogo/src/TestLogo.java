import static org.junit.Assert.*;
import org.junit.Test;
import java.io.StringReader;

    public class TestLogo {
    @Test public void tokenizeMoveCommand() {
        String cmd = "FORWARD 10";
        SimpleCharStream cs = new SimpleCharStream(new StringReader(cmd));
        LogoTokenManager ltm = new LogoTokenManager(cs);
        Token t = ltm.getNextToken();
        assertTrue("Wrong token kind for FORWARD", LogoConstants.FORWARD == t.kind);
        t = ltm.getNextToken();
        assertTrue("Wrong token kind for DIGITS", LogoConstants.DIGITS == t.kind);
    }
    @Test(expected=TokenMgrError.class) public void tokenizeFailure() {
        String cmd = "FORWERD 10";
        SimpleCharStream cs = new SimpleCharStream(new StringReader(cmd));
        LogoTokenManager ltm = new LogoTokenManager(cs);
        ltm.getNextToken();
    }
}
