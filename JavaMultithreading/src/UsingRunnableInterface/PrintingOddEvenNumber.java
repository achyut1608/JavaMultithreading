package UsingRunnableInterface;

public class PrintingOddEvenNumber {
	
	public static void main(String[]args) {
		OddEvenNumber number = new OddEvenNumber();
		
		Thread odd = new Thread(new OddThread(number));
		Thread even = new Thread(new EvenThread(number));
		
		odd.start();
		even.start();
	}
	
}

class OddEvenNumber {
	boolean isOdd = true;
	
	
	void setOdd(int number) {
		synchronized(this) {
			while(isOdd) {
				try {
					wait();
				} catch(InterruptedException ie) {
					ie.printStackTrace();
				}
			}
			isOdd = true;
			notifyAll();
			System.out.println("Odd Number : "+number);
		}
	}
	
	void setEven(int number) {
		synchronized(this) {
			while(!isOdd) {
				try {
					wait();
				} catch(InterruptedException ie) {
					ie.printStackTrace();
				}
			}
			isOdd = false;
			notifyAll();
			System.out.println("Even Number : " + number);
		}
	}
	
}


class OddThread implements Runnable {
	OddEvenNumber number;
	
	OddThread(OddEvenNumber number){
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

class EvenThread implements Runnable {
	OddEvenNumber number;
	
	EvenThread(OddEvenNumber number){
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