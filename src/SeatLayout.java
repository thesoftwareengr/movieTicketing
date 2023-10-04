import java.util.ArrayList;
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

		seats[0][0] = true;
		System.out.println("          ********** SCREEN **********\n");
		for (int i = 0; i < ROWS; i++) {	
			System.out.print((i==ROWS-1?"Exit |    ":"     |    "));
			for (int j = 0; j < COLS; j++) {
				System.out.print("["+String.valueOf((char)(i + 'A'))+(j+1)+(seats[i][j]==true?"*":" ")+"] ");
			}
			System.out.println();
		}

	}


	//return Ticket
	public void reserve(){
		


	}


	
	
	public void inputValidation(ArrayList<String>[] errors, String seat, ArrayList<String> reservations){
		if(seat.length()==2 && (seat.charAt(0)>= 'A' && seat.charAt(0) <= 'H') && (((seat.charAt(1)) - '0') > 0 && (seat.charAt(1)-'0') <= 5))  {
			
			if(!seats[(int) seat.charAt(0) -'A'][seat.charAt(1) - '0'-1]) {
				reservations.add(seat);
				//numSeats++;
				
				// do not forget to add this back to the block where the tickets is to be generated
//				seats[(int) seat.charAt(0) -'A'][seat.charAt(1) - '0'-1]=true;
			}else {
				System.out.println("inValid 1");
				errors[1].add(seat);
			}
		}else {
			System.out.println("inValid 0");
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



	public void cancel(){

	}


}