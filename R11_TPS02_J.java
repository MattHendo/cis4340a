//Noncompliant Example 
public final class SocketReader implements Runnable { // Thread-safe class
    private final Socket socket;
    private final BufferedReader in;
    private final Object lock = new Object();
   
    public SocketReader(String host, int port) throws IOException {
      this.socket = new Socket(host, port);
      this.in = new BufferedReader(
          new InputStreamReader(this.socket.getInputStream())
      );
    }
   
    // Only one thread can use the socket at a particular time
    @Override public void run() {
      try {
        synchronized (lock) {
          readData();
        }
      } catch (IOException ie) {
        // Forward to handler
      }
    }
   
    public void readData() throws IOException {
      String string;
      try {
        while ((string = in.readLine()) != null) {
          // Blocks until end of stream (null)
        }
      } finally {
        shutdown();
      }
    }
   
    public void shutdown() throws IOException {
      socket.close();
    }
  }
   
  public final class PoolService {
    private final ExecutorService pool;
   
    public PoolService(int poolSize) {
      pool = Executors.newFixedThreadPool(poolSize);
    }
   
    public void doSomething() throws InterruptedException, IOException {
      pool.submit(new SocketReader("somehost", 8080));
      // ...
      List<Runnable> awaitingTasks = pool.shutdownNow();
    }
   
    public static void main(String[] args)
                            throws InterruptedException, IOException {
      PoolService service = new PoolService(5);
      service.doSomething();
    }
  }