package UsingThreadClass;

public class SleepdDemo {

	public static void main(String[] args) {
		
		Thread sleep = new Thread(new SleepDemo());
		sleep.start();
	}

}

class SleepDemo extends Thread {

	@Override
	public void run() {
		for(int i=0;i<5;i++) {
			try {
				Thread.sleep(100);
			} catch(InterruptedException ie) {
				ie.printStackTrace();
			}
			System.out.println("Printing after 100 milliSecond");
		}
	}
}
