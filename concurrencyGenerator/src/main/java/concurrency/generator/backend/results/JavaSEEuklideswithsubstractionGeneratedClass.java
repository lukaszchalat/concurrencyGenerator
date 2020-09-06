package concurrency.generator.backend.results;

import java.lang.Integer;
import java.lang.String;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class JavaSEEuklideswithsubstractionGeneratedClass {
  private static volatile Integer a;

  private static volatile Integer b;

  public static void main(String[] args) {
    ExecutorService executorService = Executors.newFixedThreadPool(5);
    Lock lock = new ReentrantLock();
    a = 64;
    b = 40;
    while(a != b) {
    	Runnable task = () -> {
    	lock.lock();
    if(a > b) { 
    	 a = a - b;
     
    } else { 
    	 b = b - a;
     
    } 
    	lock.unlock();
      };
    executorService.execute(task);

    }
    executorService.shutdown(); 

    System.out.println(a);
  }
}
