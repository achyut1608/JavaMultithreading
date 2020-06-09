package UsingRunnableInterface;

public class ProducerConsumerUsingBlockingQueue {
	public static void main(String[]args) {
		BlockingQueue<Integer> queue = new LinkedBlockingQueue<>();
		Thread producer = new Thread(new Producer(queue));
		Thread consumer = new Thread(new Consumer(queue));
		producer.start();
		consumer.start();
	}
}


class ProducerBlockingQueueThread implements Runnable {
	BlockingQueue<Integer> queue;
	ProducerBlockingQueueThread(BlockingQueue<Integer> queue){
		this.queue = queue;
	}
	@Override
	public void run() {
		for(int i=0;i<10;i++){
			try {
				queue.put(i);
				System.out.println("Added successfully");
				Thread.sleep(1000);
			} catch(InterruptedException ie){
				ie.printStackTrace();
			}
		}
	}
}

class ConsumerBlockingQueueThread implements Runnable {
	BlockingQueue<Integer> queue;
	
	ConsumerBlockingQueueThread(BlockingQueue<Integer> queue){
		this.queue = queue;
	}
	@Override
	public void run() {
		try {
			while(true){
				int item = queue.take();
				System.out.println("item :"+item);
			}
		} catch(InterruptedException ie){
			ie.printStackTrace();
		}
	}
}
