package UsingRunnableInterface;

public class BankImplements {
	public static void main(String[]args) {
		Bank bank = new Bank();
		
		Thread credit = new Thread(new CreditOperation(bank));
		Thread deposit = new Thread(new WithdrawlOperation(bank));
		
		credit.start();
		deposit.start();
		
	}
}

class Bank {
	private int amount=1000;
	private boolean isCredit = false;
	
	void getCredit(int amount) {
		synchronized(this) {
			while(isCredit) {
				try {
					wait();
				} catch(InterruptedException ie) {
					ie.printStackTrace();
				}
			}
			notifyAll();
			isCredit=true;
			this.amount+=amount;
			System.out.println("current Balance is : " + getBalance());
		}
	}
	
	void getWithdrawl(int amount) {
		synchronized(this) {
			while(!isCredit) {
				try {
					wait();
				} catch(InterruptedException ie) {
					ie.printStackTrace();
				}
				
			}
			notifyAll();
			isCredit = false;
			
			if(amount<getBalance())
				this.amount-=amount;
			else
				System.out.println("Enter Valid amount");
			
			System.out.println("Current Balance is : " + getBalance());
		}
	}
	
	int getBalance() {
		return amount;
	}
}
class CreditOperation implements Runnable {
	
	Bank bank;
	
	CreditOperation(Bank bank){
		this.bank = bank;
	}
	
	@Override
	public void run() {
		for(int i=0;i<5;i++) {
			bank.getCredit(200);
		}
	}
}

class WithdrawlOperation implements Runnable {
	
	Bank bank;
	
	WithdrawlOperation(Bank bank){
		this.bank = bank;
	}
	
	@Override
	public void run() {
		for(int i=0;i<5;i++) {
			try {
				Thread.sleep(100);
			} catch(InterruptedException ie) {
				ie.printStackTrace();
			
			}
			bank.getWithdrawl(200);
		}
	}
}
