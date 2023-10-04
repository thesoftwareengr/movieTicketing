import java.util.ArrayList;
import java.util.Scanner;

public class SeatLayout {
	final int ROWS = 5, COLS = 8;

	boolean seats[][];
	int availableSeats;
	
	public SeatLayout() {
		this.availableSeats = 40;
		seats = new boolean[ROWS][COLS];
	}

	public void display(){
		for (int i = 0; i < ROWS; i++) {			
			for (int j = 0; j < COLS; j++) {
				System.out.print("["+String.valueOf((char)(i + 'A'))+(j+1)+(seats[i][j]==true?"*":" ")+"]");
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
			scan.close();
			return null;
		}else {
			System.out.println("Please input seats to be reserved for this transaction:");
			scan.nextLine();
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
				scan.close();
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
							System.out.println("---------- error message, more seniors than reserved seats");
						}
					}else {
						System.out.println("----- error message, invalid input");
					}
				}
				price = ((reservations.size()-seniors)*350) + (seniors * 0.2 * 350);
			}else {
				price = reservations.size()*500;
			}
			Ticket ticket = new Ticket(screening.getMovieShowing().getShowingDate(), screening.getStartTime(), (String[]) reservations.toArray(), price, screening.getCinemaNum());
			
			
			String confirm;
			while(true) {
				System.out.println("Confirm this ticket: ");				
				confirm = scan.nextLine();
				if(confirm.equalsIgnoreCase("Yes") || confirm.equalsIgnoreCase("No")){
					scan.close();
					break;
				}
			}
			if(confirm.equalsIgnoreCase("Yes")){
				for(String i: reservations) {
					seats[(int) i.charAt(0) -'A'][i.charAt(1) - '0'-1]=true;
				}
				return ticket;
			}
			return null;
		}
	
	}
	
	public void inputValidation(ArrayList<String>[] errors, String seat, ArrayList<String> reservations){
		if(seat.length()==2 && (seat.charAt(0)>= 'A' && seat.charAt(0) <= 'H') && (((seat.charAt(1)) - '0') > 0 && (seat.charAt(1)-'0') <= 5))  {
			if(!seats[(int) seat.charAt(0) -'A'][seat.charAt(1) - '0'-1]) {
				reservations.add(seat);
				// do not forget to add this back to the block where the tickets is to be generated
				//seats[(int) seat.charAt(0) -'A'][seat.charAt(1) - '0'-1]=true;
			}else {
				errors[1].add(seat);
			}
		}else {
			errors[0].add(seat);
		}
	}
	
	public void inputCorrection(ArrayList<String>[] errors, int index, ArrayList<String> reservations){
		String selection=null, correctSeat;
		Scanner scan = new Scanner(System.in);
		
		for(String error: errors[index]) {
			System.out.println(error);
			while(selection==null) {
				System.out.println("Would you like to corrrect or cancel this seat?");
				System.out.print("Correct or cancel: ");
				selection = scan.nextLine();
				if(selection.equalsIgnoreCase("correct")) {
					System.out.print("Enter the correct seat: ");
					correctSeat=scan.nextLine();
					inputValidation(errors, correctSeat, reservations);
					errors[index].remove(error);
					break;
				}else if(selection.equalsIgnoreCase("cancel")) {
					System.out.println("---cancel transaction error message");
					errors[index].remove(error);
					break;
				}else {
					selection=null;
				}
			}
		}
		scan.close();
	}
	
	public void cancel(Ticket ticket){
		
	}
	
	
}
