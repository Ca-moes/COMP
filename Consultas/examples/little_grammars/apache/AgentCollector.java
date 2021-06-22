import java.io.*;
import java.util.*;
public class AgentCollector {
  
  private Map<String,Integer> userAgentCount = new TreeMap<String,Integer>();
  private File file;

  public AgentCollector(File file) {
    this.file = file;
  }

  public void addUserAgentHit(String image) {
    if (!userAgentCount.containsKey(image)) {
      userAgentCount.put(image, 0);
    }
    userAgentCount.put(image, userAgentCount.get(image).intValue() + 1);
  }

  public void collect() throws IOException, ParseException {
    Reader r = new FileReader(file);
    ApacheLog apacheLog = new ApacheLog(r);
    apacheLog.setAgentCollector(this);
    apacheLog.Log();
  }

  public void printReport() {
    Set<Map.Entry> sortedAgents = new TreeSet<Map.Entry>(new Comparator<Map.Entry>() {
      public int compare(Map.Entry obj1, Map.Entry obj2) {
        return ((Integer)obj2.getValue()).compareTo((Integer)obj1.getValue());
      }
    });
    sortedAgents.addAll(userAgentCount.entrySet());
    for (Map.Entry entry: sortedAgents) {
      if (((Integer)entry.getValue()).intValue() < 10) {
      break;
    }
    System.out.println(entry.getValue() + " hits from " + entry.getKey());
    }
  }

  public static void main(String[] args) throws Exception {
    AgentCollector collector = new AgentCollector(new File(args[0]));
    collector.collect();
    collector.printReport();
  }
}
