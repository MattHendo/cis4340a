//Noncompliant Example 
/* This class is not thread-safe */
public final class CountHits {
    private static int counter;
   
    public void incrementCounter() {
      counter++;
    }
  }
