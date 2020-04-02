package UsingThreadClass;

public class Toilet {
	public static void main(String[]args) {
		
		ToiletThread toilet = new ToiletThread();
		
		Thread t1 = new Thread(toilet,"Achyut");
		Thread t2 = new Thread(toilet,"Ankit");
		Thread t3 = new Thread(toilet,"Panjree");
		
		t1.start();
		t2.start();
		t3.start();
	}
	
	
}
class ToiletThread extends Thread {
	
	@Override
	public void run() {
		synchronized(this){
			try {
				String name = Thread.currentThread().getName();
				System.out.println(name + " entering to toilet");
				Thread.sleep(100);
				System.out.println(name+" using toilet");
				Thread.sleep(100);
				System.out.println(name+" coming out of Toilet");
				Thread.sleep(100);
				System.out.println();
			} catch(InterruptedException ie) {
				ie.printStackTrace();
			}
		}
	}
}