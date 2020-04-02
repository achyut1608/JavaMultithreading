package UsingThreadClass;

public class JoinDemon {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Thread t1= new Thread(new JoinThread(),"Thread-1");
		Thread t2= new Thread(new JoinThread(),"Thread-2");
		Thread t3= new Thread(new JoinThread(),"Thread-3");
		
		try {
			t1.start();
			t1.join();
			t2.start();
			t2.join();
			t3.start();
			t3.join();
		} catch(InterruptedException ie) {
			ie.printStackTrace();
		}
	}

}

class JoinThread extends Thread {
	
	@Override
	public void run() {
		String name = Thread.currentThread().getName();
		for(int i=0;i<5;i++) {
			try {
				Thread.sleep(100);
			} catch(InterruptedException ie) {
				ie.printStackTrace();
			}
			System.out.println(name + " using join thread....");
			
		} 
		System.out.println();
	}
	
}
