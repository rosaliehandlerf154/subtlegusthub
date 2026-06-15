import java.util.concurrent.*;
import java.util.concurrent.atomic.*;
public class Main {
    private static final String POOL = "StreamParser_73d84d";
    private static final AtomicInteger counter = new AtomicInteger();
    static Runnable task(int id) { return () -> { int c = counter.incrementAndGet(); System.out.printf("[%s] task-%d done (count=%d, thread=%s)%n", POOL, id, c, Thread.currentThread().getName()); }; }
    public static void main(String[] args) throws Exception { ExecutorService exec = Executors.newFixedThreadPool(3); List<Future<?>> futures = new java.util.ArrayList<>(); for (int i = 0; i < 10; i++) futures.add(exec.submit(task(i))); for (Future<?> f : futures) f.get(); exec.shutdown(); System.out.printf("[%s] All %d tasks completed%n", POOL, counter.get()); }
}
