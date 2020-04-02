package UsingThreadClass;

public class OddEvenNumber {

	public static void main(String[] args) {
		Number number = new Number();
		
		Thread evenThread = new Thread(new EvenThread(number));
		Thread oddThread = new Thread(new OddThread(number));
		
		evenThread.start();
		oddThread.start();
	}

}

class Number {
	int count=0;
	boolean isBoolean = true;
	void setOdd(int i) {
		synchronized(this) {
			try {
				while(isBoolean)
					wait();
			} catch(InterruptedException ie) {
				ie.printStackTrace();
			}
			System.out.println(i);
			notifyAll();
			isBoolean  = true;
		}
	}
	void setEven(int i) {
		synchronized(this) {
			try {
				while(!isBoolean) {
					wait();
				}
			} catch(InterruptedException ie) {
				ie.printStackTrace();
			}
			System.out.println(i);
			isBoolean = false;
			notifyAll();
		}
	}
}

class EvenThread extends Thread {
	
	Number number;
	
	EvenThread(Number number){
		this.number = number;
	}
	
	@Override
	public void run() {
		for(int i=0;i<=10;i=i+2) {
			try {
				Thread.sleep(100);
			} catch(InterruptedException ie) {
				ie.printStackTrace();
			}
			number.setEven(i);
		}
	}
}

class OddThread extends Thread {
	Number number;
	OddThread(Number number){
		this.number = number;
	}
	@Override
	public void run() {
		for(int i=1;i<=10;i=i+2) {
			try {
				Thread.sleep(100);
			} catch(InterruptedException ie) {
				ie.printStackTrace();
			}
			number.setOdd(i);
		}
			
	}
}

