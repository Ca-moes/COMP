import java.io.*;
import visitor.*;
import syntaxtree.*;
public class Runner {
  public static void main(String[] args) {
    Reader sr = new StringReader(args[0]);
    PhoneParser p = new PhoneParser(sr);
    try {
      PhoneList pl = p.PhoneList();
      pl.accept(new TreeDumper());
      System.out.println("");
      pl.accept(new SchemeTreeBuilder());
      System.out.println("");
    } catch (ParseException pe) {
      pe.printStackTrace();
    }
  }
}
