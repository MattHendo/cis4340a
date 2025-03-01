//Noncompliant Example 
final class Adder {
    private final AtomicReference<BigInteger> first;
    private final AtomicReference<BigInteger> second;
   
    public Adder(BigInteger f, BigInteger s) {
      first  = new AtomicReference<BigInteger>(f);
      second = new AtomicReference<BigInteger>(s);
    }
   
    public void update(BigInteger f, BigInteger s) { // Unsafe
      first.set(f);
      second.set(s);
    }
   
    public BigInteger add() { // Unsafe
      return first.get().add(second.get());
    }
  }
