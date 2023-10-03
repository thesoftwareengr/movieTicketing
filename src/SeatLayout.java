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
	
//	public Ticket reserve(){
//		
//	}
	
	public void inputValidation(ArrayList<String>[] errors, String seat, ArrayList<String> reservations, int numSeats){
		if(seat.length()==2 && (seat.charAt(0)>= 'A' && seat.charAt(0) <= 'E') && (Integer.valueOf(seat.charAt(1)) > 0 && Integer.valueOf(seat.charAt(1)) <= 5))  {
			System.out.println("vaid");
			if(seats[(int) seat.charAt(0)][Integer.valueOf(seat.charAt(1))]) {
				reservations.add(seat);
				numSeats++;
			}else {
				errors[1].add(seat);
			}
		}else {
			errors[0].add(seat);
		}
	}
	
	public void inputCorrection(ArrayList<String>[] errors, int index, ArrayList<String> reservations, int numSeats){
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
					inputValidation(errors, correctSeat, reservations, numSeats);
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
	
	public void cancel(){
		
	}
	
	
}
