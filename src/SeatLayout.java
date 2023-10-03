import java.util.ArrayList;
import java.util.Scanner;

public class SeatLayout {
	final int ROWS = 5, COLS = 8;
	
	boolean seats[][];
	int availableSeats;

	public SeatLayout() {
		this.availableSeats = 40;
		seats = new boolean[COLS][ROWS];
	}

	public void display(){
		
		seats[0][0] = true;
		System.out.println("          ********** SCREEN **********\n");
		for (int i = 0; i < COLS; i++) {	
			System.out.print((i==COLS-1?"Exit |    ":"     |    "));
			for (int j = 0; j < ROWS; j++) {
				System.out.print("["+String.valueOf((char)(i + 'A'))+(j+1)+(seats[i][j]==true?"*":" ")+"] ");
			}
			System.out.println();
		}
		System.out.println("\nLegend: [Xn ] = available seat, [Xn*] = reserved seat");

		int inputValue = 0;
		int seats;
		
		String str;
		Scanner scan = new Scanner(System.in);
		Ticket ticket = new Ticket();		
		// = new String[40];
		do {
			try {
				System.out.println("Please Input \"1\" or \"2\" to Reserve or Cancel a Seat in the Cinema"
						+ "\n[1] - Reserve"
						+ "\n[2] - Cancel Reservation"
						+ "\n[3] - Exit");
				inputValue = scan.nextInt();
			}catch(Exception e){

			}
		}while(inputValue < 1 || inputValue > 3) ;

		switch(inputValue) {
		case 1:
			System.out.println("Please input seats to be reserved for this transaction:");
			scan.nextLine();
			str = scan.nextLine();
			String[] inputedStrings = str.split(",");
			
			for(String inputs : inputedStrings ) {
				System.out.println(inputs);
			}
			
			
		
		}

	}

	
	//<<<<<<< HEAD
	public void reserve(String[] reservedSeats){



	}
	//=======
	////	public Ticket reserve(){
	////		
	////	}
	//>>>>>>> d6e83258cfa9e6b1fb5ef15267cda98f0c0ac371

	public void inputValidation(ArrayList<String>[] errors){
			
	}

	public void inputCorrection(ArrayList<String>[] errors, int index){
		String selection=null;
		Scanner scan = new Scanner(System.in);

		for(String error: errors[index]) {
			System.out.println(error);
			while(selection==null) {
				System.out.println("Would you like to corrrect or cancel this seat?");
				System.out.print("Correct or cancel: ");
				selection = scan.nextLine();
				if(selection.equalsIgnoreCase("correct")) {

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