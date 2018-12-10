import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Demo {

    // ExecutorService = incoming queue + worker threads
    // CompletionService = incoming queue + worker threads + output queue


	private final int numberOfThreads = 3;
	
	private final List<String> printRequests = Arrays.asList(
            "1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
        "1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
        "1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
        "1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
        "1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
        "1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
        "1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
        "1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
        "1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
        "1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
        "1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
        "1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
        "1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
        "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"
		);
	
	public static void main(String[] args) {
		//new Demo().normalExecutorService();
		
        System.out.println("Normal Executor Service");
        long start = System.currentTimeMillis();
        new Demo().normalExecutorService();
        System.out.println();
        System.out.println("Execution time : " + (System.currentTimeMillis() - start));

        System.out.println("Completion Service");
        start = System.currentTimeMillis();
        new Demo().normalCompletionService();
        System.out.println();
        System.out.println("Execution time : " + (System.currentTimeMillis() - start));
	}
	
	public void normalExecutorService() {
		ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads);
		try {
			Set<Future<String>> futures = new HashSet<>();
			for(String pp : this.printRequests) {
				futures.add(executor.submit(new Printer(pp)));
			}
			for(Future<String> future : futures) {
				System.out.print(future.get());
			}
		} catch(Exception e) {
			
		} finally {
			if(executor != null) executor.shutdown();
		}
	}
	
	public void normalCompletionService() {
		ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads);
		CompletionService<String> completionService = 
				new ExecutorCompletionService<String>(executor);
		for(String s : this.printRequests) {
			completionService.submit(new Printer(s));
		}
		try {
			for(int i = 0; i < this.printRequests.size(); i++) {
				Future<String> future = completionService.take();
				System.out.print(future.get());
			}
		}catch(Exception e) {
			
		}finally {
			if(executor != null) {
				executor.shutdown();
			}
		}
		
	}
}


class Printer implements Callable<String>{
	private final String toPrint;
	@Override
	public String call() throws Exception {
		// Do something
		return toPrint;
	}
	
	public Printer(String s) {
		toPrint = s;
	}
	
}
