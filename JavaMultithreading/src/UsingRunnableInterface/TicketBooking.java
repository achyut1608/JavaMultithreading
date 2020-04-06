package UsingRunnableInterface;

import java.util.Scanner;

public class TicketBooking {
	public static void main(String[]args) {
		BookTicket bookTicket = new BookTicket();
		Thread user1 = new Thread(new UserBookTicket(bookTicket),"Achyut");
		Thread user2 = new Thread(new UserBookTicket(bookTicket),"Panjree");
		Thread user3 = new Thread(new UserBookTicket(bookTicket),"Test User");
		
		user1.start();
		user2.start();
		user3.start();
	}
}

class BookTicket {
	int availableTicket = 10;
	boolean isTicketAvailable;
	
	int getNumberOfTicketAvailable() {
		return availableTicket;
	}
	
	void bookTicket() {
		synchronized(this) {
			Scanner scan = new Scanner(System.in);
			System.out.println("Enter number of tickets do you want to buy");
			int numberOfTicket = scan.nextInt();
			String name = Thread.currentThread().getName();
			if(getNumberOfTicketAvailable()>numberOfTicket) {
				availableTicket-=numberOfTicket;
				System.out.println(name +" has successfully  booked tickets");
			} else 
				System.out.println("Sorry!!! "+name + " we don't have "+numberOfTicket + " tickets please try again!!!");
		}
	}
}
class UserBookTicket implements Runnable {
	
	BookTicket bookTicket;
	
	UserBookTicket(BookTicket bookTicket){
		this.bookTicket = bookTicket;
	}
	
	@Override
	public void run() {
		bookTicket.bookTicket();
	}
	
}