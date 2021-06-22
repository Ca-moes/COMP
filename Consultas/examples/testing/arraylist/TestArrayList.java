import static org.junit.Assert.assertEquals;
import org.junit.Test;
import java.util.ArrayList;

public class TestArrayList {
    @Test public void size() {
        ArrayList<String> list = new ArrayList<String>();
        assertEquals("Initial list size should be 0", 0, list.size());
        list.add("Hello");
        assertEquals("After adding an item, size should be 1", 1, list.size());
    }
}
