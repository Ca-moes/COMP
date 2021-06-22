import static org.junit.Assert.assertEquals;
import org.junit.Test;
import java.util.ArrayList;

public class BadTestArrayList {
    @Test public void improperSize() {
        ArrayList<String> list = new ArrayList<String>();
        assertEquals("Initial list size should be 1 (?)", 1, list.size());
    }
}
