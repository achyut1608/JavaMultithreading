package UsingThreadClass;

public class ProducerConsumer {
	public static void main(String[]args) {
		Product product = new Product();
		Thread producer = new Thread(new Producer(product));
		Thread consumer = new Thread(new Consumer(product));
		
		producer.start();
		consumer.start();
	}
}
class Product {
	boolean isBoolean = true;
	int count=0;
	synchronized void setProduct(int count) {
		
		try {
			while(!isBoolean)
				wait();
		} catch(InterruptedException ie) {
			ie.printStackTrace();
		}
		isBoolean = false;
		this.count = count;
		notify();
	}
	
	synchronized int getProduct() {
		try {
			while(isBoolean)
				wait();
		} catch(InterruptedException ie) {
			ie.printStackTrace();
		}
		isBoolean = true;
		notifyAll();
		return count;
	}
}

class Producer extends Thread {
	Product product;;
	
	Producer(Product product){
		this.product = product;
	}
	
	@Override
	public void run() {
		for(int i=0;i<5;i++)
			product.setProduct(i);
	}
	
}

class Consumer extends Thread {
	
	Product product;
	
	Consumer(Product product){
		this.product = product;
	}
	
	@Override
	public void run() {
		for(int i=0;i<5;i++) {
			try {
				Thread.sleep(100);
			} catch(InterruptedException ie) {
				ie.printStackTrace();
			}
			System.out.println(product.getProduct());
		}
	}
}
