import java.lang.Integer;
import java.lang.String;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class JavaSESumofnumbersGeneratedClass {
  private static volatile Integer a;

  private static volatile Integer b;

  public static void main(String[] args) {
    ExecutorService executorService = Executors.newFixedThreadPool(5);
    Lock lock = new ReentrantLock();
    a = 1;
    b = 1;
    while(a > b) {
    	Runnable task = () -> {
    	lock.lock();
    b = b + b;
    b = b + b;
    b = b + b;
    	lock.unlock();
      };
    executorService.execute(task);

    }
    executorService.shutdown(); 

    System.out.println(b);
  }
}
