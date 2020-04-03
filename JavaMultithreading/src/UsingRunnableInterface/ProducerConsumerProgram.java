package UsingRunnableInterface;

public class ProducerConsumerProgram {
	
	public static void main(String[]args) {
		Product product = new Product();
		Thread producer = new Thread(new ProducerThread(product));
		Thread consumer = new Thread(new ConsumerThread(product));
		producer.start();
		consumer.start();
	}
	
}
class Product {
	private boolean isBoolean = false;
	private int number;
	
	void setNumber(int number) {
		synchronized(this) {
			while(isBoolean) {
				try {
					wait();
				} catch(InterruptedException ie) {
					ie.printStackTrace();
				}
			}
			
			this.number = number;
			isBoolean  = true;
			notifyAll();
			System.out.println("number is Added Successfully");
		}
	}
	
	void getNumber() {
		synchronized(this) {
			while(!isBoolean) {
				try {
					wait();
				} catch(InterruptedException ie) {
					ie.printStackTrace();
				}
			}
			
			notifyAll();
			isBoolean = false;
			System.out.println("Number is : " +number);
		}
	}
}

class ProducerThread implements Runnable {
	
	Product product;
	
	ProducerThread(Product product){
		this.product = product;
	}
	
	@Override
	public void run() {
		for(int i=0;i<10;i++)
			product.setNumber(i);
	}
}

class ConsumerThread implements Runnable {
	
	Product product;
	
	ConsumerThread(Product product){
		this.product = product;
	}
	
	@Override
	public void run() {
		for(int i=0;i<10;i++) {
			try {
				Thread.sleep(100);
			} catch(InterruptedException ie) {
				ie.printStackTrace();
			}
			product.getNumber();
		}
	}
}