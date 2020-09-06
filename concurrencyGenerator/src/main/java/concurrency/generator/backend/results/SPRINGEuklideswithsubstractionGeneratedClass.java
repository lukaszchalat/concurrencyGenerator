package concurrency.generator.backend.results;

import java.lang.Integer;
import java.lang.String;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

public class SPRINGEuklideswithsubstractionGeneratedClass {
  private static volatile Integer a;

  private static volatile Integer b;

  public static void main(String[] args) {
    Lock lock = new ReentrantLock();
    ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
    taskExecutor.setCorePoolSize(4);;
    taskExecutor.setMaxPoolSize(4);;
    taskExecutor.initialize();;
    a = 1000;
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
    taskExecutor.execute(task);

    }
    taskExecutor.shutdown(); 

    System.out.println(a);
  }
}
