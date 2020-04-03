package UsingRunnableInterface;

public class Toilet {
	public static void main(String[]args) {
		
		ToiletThread toilet = new ToiletThread();
		
		Thread t1 = new Thread(toilet,"Achyut");
		Thread t2 = new Thread(toilet,"Thaker");
		Thread t3 = new Thread(toilet,"Tesing");
		
		t1.start();
		t2.start();
		t3.start();
	}
}


class ToiletThread implements Runnable {
	@Override
	public void run() {
		synchronized(this) {
			String name = Thread.currentThread().getName();
			try {
				System.out.println(name+" entering to toilet ");
				Thread.sleep(100);
				System.out.println(name+" using toilet");
				Thread.sleep(100);
				System.out.println(name+" coming out of toilet");
				Thread.sleep(100);
				System.out.println();
			} catch(InterruptedException ie) {
				ie.printStackTrace();
			}
		}
	}
}