package UsingThreadClass;

public class IntroDuctionToThread {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Thread t1 = new Thread(new Thread1());
		Thread t2 = new Thread(new Thread2());
		Thread t3 = new Thread(new Thread3());
		t1.start();
		t2.start();
		t3.start();
		
	}

}

class Thread1 extends Thread{
	@Override
	public void run() {
		String threadName = Thread.currentThread().getName();
		for(int i=0;i<5;i++) {
			try {
				Thread.sleep(100);
			} catch(InterruptedException ie) {
				ie.printStackTrace();
			}
			
			System.out.println(threadName + " prints ");
		
		}
	}
}

class Thread2 extends Thread{
	@Override
	public void run() {
		String threadName = Thread.currentThread().getName();
		for(int i=0;i<5;i++) {
			try {
				Thread.sleep(100);
			} catch(InterruptedException ie) {
				ie.printStackTrace();
			}
			
			System.out.println(threadName + " prints ");
		
		}
	}
}

class Thread3 extends Thread{
	@Override
	public void run() {
		String threadName = Thread.currentThread().getName();
		for(int i=0;i<5;i++) {
			try {
				Thread.sleep(100);
			} catch(InterruptedException ie) {
				ie.printStackTrace();
			}
			
			System.out.println(threadName + " prints ");
		
		}
	}
}