import java.io.*;
public class LiteralsRunner {
  public static void main(String[] args) {
    StringReader r = new StringReader(args[0]);
    SimpleCharStream s = new SimpleCharStream(r);
    LiteralsTokenManager ltm = new LiteralsTokenManager(s);
    Token t = ltm.getNextToken();
    System.out.println("Got a '" + t.image + "' token");
  }
}
