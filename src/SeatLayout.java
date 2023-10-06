import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class SeatLayout {
	final int ROWS = 8, COLS = 5;
	private boolean seats[][];
	private int availableSeats;

	public SeatLayout() {
		this.availableSeats = 40;
		seats = new boolean[ROWS][COLS];
	}

	public void display(){

		
		System.out.println("          ********** SCREEN **********\n");
		for (int i = 0; i < ROWS; i++) {	
			System.out.print((i==ROWS-1?"Exit |    ":"     |    "));
			for (int j = 0; j < COLS; j++) {
				System.out.print("["+String.valueOf((char)(i + 'A'))+(j+1)+(seats[i][j]==true?"*":" ")+"] ");
			}
			System.out.println();
		}

	}

	public Ticket reserve(Screening screening){
		Scanner scan = new Scanner(System.in);
		String str;
		ArrayList<String>[] errors = new ArrayList[2];
		ArrayList<String> reservations = new ArrayList<String>();
		errors[0] = new ArrayList<String>();
		errors[1] = new ArrayList<String>();
		
		if(this.availableSeats<=0) {
			
			return null;
		}else {
			System.out.println("Please input seats to be reserved for this transaction:");
			System.out.print("Input: ");
			
			str = scan.nextLine();
			String[] inputedStrings = str.split(",");
			for(String inputs:inputedStrings) {
				inputValidation(errors, inputs,reservations);
			}
			System.out.println(errors[0].size() + " , " + errors[1].size() +" , "+reservations.size());
			while(!(errors[0].isEmpty() && errors[1].isEmpty())) {
				if(!errors[0].isEmpty()) {
					inputCorrection(errors, 0,reservations);
				}else {
					inputCorrection(errors, 1,reservations);				
				}
			}
			if(reservations.size()<=0) {
				//no seats were added
				System.out.println("------- error message here");
				//scan.close();
				return null;	
			}
			int seniors=0;
			double price;
			if(!screening.getMovieShowing().getIsPremier()) {
				while(true) {
					System.out.print("Please input the number of seniors: ");
					if(scan.hasNextInt()) {
						seniors = scan.nextInt();
						scan.nextLine();
						if(seniors<=reservations.size()) {
							break;
						}else {
							System.out.println("Invalid Integer Value! Exceeds maximum value");
						}
					}else {
						System.out.println("Invalid InputValue! Please Input a Valid Integer to Proceed");
					}
				}
				price = ((reservations.size()-seniors)*350) + (seniors * 0.2 * 350);
			}else {
				price = reservations.size()*500;
			}
			Ticket ticket = new Ticket(screening.getMovieShowing(), screening.getStartTime(), reservations, price, screening.getCinemaNum(), seniors);
			
			String confirm;
			while(true) {
				System.out.println("Confirm this ticket: (Yes or No)");
				System.out.print(reservations.size()+" seats");
				if(seniors>0) {
					System.out.println(" with "+seniors+ "seniors");
				}
				System.out.print("Input: ");
				confirm = scan.nextLine();
				if(confirm.equalsIgnoreCase("Yes") || confirm.equalsIgnoreCase("No")){
					//scan.close();
					break;
				}
			}
			if(confirm.equalsIgnoreCase("Yes")){
				for(String i: reservations) {
					seats[(int) i.charAt(0) -'A'][i.charAt(1) - '0'-1]=true;
				}
				availableSeats-=reservations.size();
				ticket.display();
				return ticket;
			}
			return null;
		}
	
	}
	
	public void inputValidation(ArrayList<String>[] errors, String seat, ArrayList<String> reservations){
		if(seat.length()==2 && (seat.charAt(0)>= 'A' && seat.charAt(0) <= 'H') && (((seat.charAt(1)) - '0') > 0 && (seat.charAt(1)-'0') <= 5))  {
			if(!seats[(int) seat.charAt(0) -'A'][seat.charAt(1) - '0'-1]) {
				reservations.add(seat);
				System.out.println("A seat has been added successfully");
			}else {
				errors[1].add(seat);
			}
		}else {
			errors[0].add(seat);
		}
	}
	
	public void inputCorrection(ArrayList<String>[] errors, int index, ArrayList<String> reservations){
		String selection, correctSeat;
		Scanner scan = new Scanner(System.in);

		Iterator<String> iterator = errors[index].iterator();
        while (iterator.hasNext()) {
        	selection=null;
        	String error = iterator.next();
        	
			System.out.println(error);
			while(true) {
				System.out.println("\nWould you like to correct or cancel this seat? ("+error+")");
				System.out.print("Input (Correct or cancel): ");
				selection = scan.nextLine();
				if(selection.equalsIgnoreCase("correct")) {
					System.out.println("Enter the correct seat: ");
					correctSeat=scan.nextLine();
					inputValidation(errors, correctSeat, reservations);
					iterator.remove();
					break;
				}else if(selection.equalsIgnoreCase("cancel")) {
					System.out.println("---cancel transaction error message");
					iterator.remove();
					break;
				}
			}
		}
		//scan.close();
	}
	
	public void cancel(Ticket ticket){
		if(!ticket.isActive()){
			System.out.println("--------- ticket is already inactive");
		}else {
			Iterator<String> iterator = ticket.getReservedSeats().iterator();
			while(iterator.hasNext()){
				String i = iterator.next();
				ticket.getReservedSeats().remove(i);
				availableSeats++;
			}
			ticket.setActive(false);
		}
	}

	public boolean[][] getSeats() {
		return seats;
	}

	public void setSeats(boolean[][] seats) {
		this.seats = seats;
	}

	public int getAvailableSeats() {
		return availableSeats;
	}

	public void setAvailableSeats(int availableSeats) {
		this.availableSeats = availableSeats;
	}

}