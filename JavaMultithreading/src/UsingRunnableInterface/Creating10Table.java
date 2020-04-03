package UsingRunnableInterface;

public class Creating10Table {
	public static void main(String[]args) {
		Table table = new Table();
		
		Thread table2 = new Thread(new Table1(table,2));
		Thread table5 = new Thread(new Table1(table,5));
		
		table2.start();
		table5.start();
	}
}

class Table {
	void printTable(int number) {
		synchronized(this){
			for(int i=0;i<10;i++) 
				System.out.println(number+"*"+i+"="+(i*number));
			
			System.out.println();
		}
	}
	
	
}

class Table1 implements Runnable {

	Table table;
	int number;
	Table1(Table table,int number){
		this.table = table;
		this.number = number;
	}
	
	@Override
	public void run() {
		try {
			Thread.sleep(100);
		} catch(InterruptedException ie) {
			ie.printStackTrace();
		}
		table.printTable(number);
	}
}

