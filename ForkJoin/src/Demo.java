import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class Demo {

	public static void main(String[] args) {
		Demo ob = new Demo();
		Integer[] arr = {5,1,3,6,1, 2, 1,1,2,2,2,2,1,1};
		ForkJoinPool fjp = new ForkJoinPool(8);
		long sum = fjp.invoke(new ForkJoinSum(arr, 0, arr.length));
		System.out.println(sum);
	}

}


class ForkJoinSum extends RecursiveTask<Long> {
	private static final long serialVersionUID = 1L;
	private Integer[] buffer;
	private int start;
	private int end;
	
	public ForkJoinSum(Integer[] buffer, int start, int end) {
		this.buffer = buffer;
		this.start = start;
		this.end = end;
	}
	
	@Override
	protected Long compute() {
		long sum = 0;
		if(end - start <= 5) {
			for(int i = start; i < end; i++) {
				sum += buffer[i];
			}
			return sum;
		}else {
			int mid = (start + end) / 2;
			ForkJoinSum left =  new ForkJoinSum(buffer, start, mid);
			ForkJoinSum right =  new ForkJoinSum(buffer, mid, end);
			left.fork();
			long s = right.compute() + left.join();
			return s;
		}
	}
	
}
