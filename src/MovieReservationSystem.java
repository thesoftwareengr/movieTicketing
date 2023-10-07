import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class MovieReservationSystem {
	public ArrayList<Movie> movies;
	public ArrayList<LocalDate> showingDates;
	public HashMap<String, Screening> screenings;

	public static void main(String[] args) {	
		boolean inf = true;
		String MovieID = "";
		String showingDateInput=null;
		int showingDateIndex=0;
		LocalDate dateSelected=null;
		Scanner scan = new Scanner(System.in);

		MovieReservationSystem mrs = new MovieReservationSystem();
		mrs.readCSV();
		mrs.readGeneratedCSVs();

		SortedSet<Entry<String, Screening>> sortedEntries = mrs.screenings.entrySet()
				.stream()
				.sorted(Map.Entry.comparingByKey())
				.collect(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(Entry::getKey))));

		while(inf==true){

			System.out.println("\n                         ********** MAIN MENU **********");		

			while(true){
				System.out.println("\nPlease select date of showing (EXIT to exit the application)");
				Iterator<LocalDate> iterator = mrs.showingDates.iterator();
				int dateCtr=1;
				while(iterator.hasNext()){
					System.out.println("["+dateCtr+"] "+iterator.next());
					dateCtr++;
				}
				System.out.print("Input: ");
				showingDateInput = scan.nextLine();
				if(showingDateInput.equalsIgnoreCase("EXIT")) {
					break;
				}else{
					try{
						showingDateIndex = Integer.parseInt(showingDateInput);
						if(showingDateIndex<=mrs.showingDates.size()) {
							showingDateIndex--;
							dateSelected = mrs.showingDates.get(showingDateIndex);
							System.out.println("Date ["+dateSelected+"] has been selected");
							break;
						}else {
							System.out.println("Invalid Integer Value! Exceeds maximum value");
						}
					}catch(Exception e) {
						System.out.println("Invalid InputValue! Please Input a Valid Integer to Proceed");
					}
				}
			}
			if(showingDateInput!=null && showingDateInput.equalsIgnoreCase("EXIT")) {
				break;
			}

			int cinemaNumber = 0;
			for (Map.Entry<String, Screening> entry : sortedEntries) {
				String key = entry.getKey();
				Screening data = mrs.screenings.get(entry.getKey());
				if(key.substring(2, key.length()).equals(dateSelected.toString())){
					if(Character.getNumericValue(key.charAt(0)) != cinemaNumber) {
						cinemaNumber = Character.getNumericValue(key.charAt(0));
						System.out.println("\nCINEMA " + cinemaNumber);
						System.out.println("ID        Movies              Time      Seats Available  Premiere");
					}
					System.out.printf("%-2s   %-15s...  %-5s - %-5s    %-12s      %-3s\n", key.substring(0,2), (data.getMovieShowing().getName().length()>15? data.getMovieShowing().getName().substring(0,15): data.getMovieShowing().getName()),data.getStartTime(), Screening.endTimeCalc(data.getStartTime(), data.getMovieShowing().getLength()),(data.getSeatLayout().getAvailableSeats()>0?"["+data.getSeatLayout().getAvailableSeats()+"] Seat(s)":"[00] Full"),(data.getMovieShowing().getIsPremier()? "Yes":"No"));
				}

			}


			int ticketID=0;
			boolean returnCinema = false;
			//while(true) {

			do {
				System.out.println("Pick a movie ID to view the seat layout: (QUIT to exit)");
				System.out.print("Input: ");
				MovieID = scan.nextLine().toUpperCase()+dateSelected;

				if(mrs.screenings.containsKey(MovieID)) {
					break;
				}else if(MovieID.equalsIgnoreCase("QUIT")) {
					inf=false;
					break;
				} else if(!inf) {
					inf=true;
					break;
				} else {
					System.out.println("\n!!Invalid MovieID! Please Input a valid MovieID within the Cinemas!!");
				}
			}while(true);
			if(inf==false) {
				break;
			}

			do {
				Screening selectedScreening = mrs.screenings.get(MovieID);

				System.out.println("\nCINEMA " + MovieID.charAt(0));
				System.out.println("Seat Layout for "+selectedScreening.getMovieShowing().getName() + " @ " + selectedScreening.getStartTime() + " - " + Screening.endTimeCalc(selectedScreening.getStartTime(), selectedScreening.getMovieShowing().getLength()));
				System.out.println("Premier: " + (selectedScreening.getMovieShowing().getIsPremier()? "Yes":"No"));

				selectedScreening.getSeatLayout().display();
				System.out.println();
				int inputValue = 0;
				do {

					try {
						System.out.println("Legend: [Xn ] = available seat, [Xn*] = reserved seat");
						System.out.println("Please Input \"1\" or \"2\" to Reserve or Cancel a Seat in the Cinema"
								+ "\n[1] - Reserve"
								+ "\n[2] - Cancel Reservation"
								+ "\n[3] - Exit");
						System.out.print("Input: ");
						inputValue = Integer.parseInt(scan.nextLine());
					}catch(Exception e){
						inputValue = 0;
					}
					if(inputValue >= 1 && inputValue <= 3) {
						break;
					} else {
						System.out.println("\nInvalid InputValue! Please Input a Valid Integer to Proceed");
					}
				}while(true);

				switch(inputValue) {
					case 1:
						Ticket ticket = selectedScreening.getSeatLayout().reserve(selectedScreening);
						if(ticket!=null) {
							selectedScreening.getSoldTickets().add(ticket);
						}
	
						do {
							try {
								System.out.println("Please Input \"1\" or \"2\" to Proceed back to Cinema "+MovieID.charAt(0)+" or the Main Menu"
										+ "\n[1] - CINEMA " + MovieID.charAt(0)
										+ "\n[2] - Exit");
								System.out.print("Input: ");
								inputValue = Integer.parseInt(scan.nextLine());
							}catch(Exception e){
								inputValue = 0;
							}
						}while(inputValue < 1 || inputValue > 2) ;
	
						switch(inputValue) {
						case 1: 
							returnCinema = true;
							break;
						case 2:
							returnCinema = false;
							break;
						}
						mrs.generateReservationsCSV();
						break;
					case 2 :
						Ticket foundTicket=null;
						do {
							try {
								System.out.println("Please ticket number to be cancelled");
								System.out.print("Input: ");
								ticketID = Integer.parseInt(scan.nextLine());
								for (Ticket ticketIter : selectedScreening.getSoldTickets()) {
									if (ticketIter.getTicketNum()==ticketID) {
										foundTicket = ticketIter;
										break; // Stop searching once the ticket is found
									}
								}
								if(foundTicket==null) {
									ticketID = -1;	
								}
							}catch(Exception e){
								ticketID = -1;
							}
						}while(ticketID<=0);

						if(foundTicket!=null) {
							System.out.println("Ticket found");
							selectedScreening.getSeatLayout().cancel(foundTicket);
						}else {
							System.out.println("Ticket not found");
						}
						mrs.generateReservationsCSV();
						break;
					case 3:
						returnCinema=false;
						break;
				}
			}while(returnCinema);
		}
		mrs.generateReservationsCSV();
		scan.close();
		System.out.println("                   ********** Application End **********");	

	}

	MovieReservationSystem(){
		movies = new ArrayList<Movie>();
		showingDates = new ArrayList<LocalDate>();
		screenings = new HashMap<String, Screening>();
	}

	public void readCSV() {
		Movie movieData;
		Screening screeningData;
		String compositeKey, line;
		String[] values;
		int i, movieCtr;

		try (BufferedReader br = new BufferedReader(new FileReader("MovieSchedule.csv"))) {
			while ((line = br.readLine()) != null) {
				i=0;
				movieCtr=0;
				values = line.split(",");

		        // CSV indexes
		        // 0 - date				1 - cinema num
		        // 2 - start time		3 - isPremiere
		        // 4 - title			5 - duration

				for(int ndx=0; ndx<values.length;ndx++) {
					// System.out.println(values[ndx]);
					values[ndx] = values[ndx].substring(1, values[ndx].length()-1);
				}
				LocalDate dateOfShowing = LocalDate.parse(values[0], DateTimeFormatter.ofPattern("yyyy-MM-dd"));

				if(!showingDates.contains(dateOfShowing)){
					showingDates.add(dateOfShowing);
					System.out.println("Date ["+dateOfShowing+"] has been added to the system");
				}

				movieData= new Movie(values[4], Double.parseDouble(values[5]), dateOfShowing, Boolean.valueOf(values[3]));

				for(; i< movies.size(); i++) {
					// break loop if movie with same name and dateOfShowing is found
					if(movies.get(i).getName().equals(values[4]) && movies.get(i).getShowingDate()==dateOfShowing) {
						break;
					}
				}
				if(i==movies.size()) { //unique, empty or new movie
					movies.add(movieData);
				}

				LocalTime startTime = LocalTime.parse(values[2], DateTimeFormatter.ofPattern("HH:mm"));
				movies.get(i).addStartingTime(startTime);

				screeningData = new Screening(movies.get(i), Integer.valueOf((values[1])), startTime);
				Set<String> keys = screenings.keySet();
				for(String key: keys) {
					if(screenings.get(key).getMovieShowing().getShowingDate().isEqual(dateOfShowing) && screenings.get(key).getCinemaNum()==Integer.valueOf(values[1])) {
						movieCtr++;
					}
				}

				compositeKey=values[1]+(char) ('A'+movieCtr)+dateOfShowing;
				if(!screenings.containsKey(compositeKey)) {
					screenings.put(compositeKey, screeningData);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void readGeneratedCSVs(){
		String line;
		String[] values;
		ArrayList<String> csvData, seats;
		int ctr=0, tot=0;
		try (BufferedReader br = new BufferedReader(new FileReader("reservations.csv"))) {
			while ((line = br.readLine()) != null) {
				csvData = new ArrayList<String>();
				seats = new ArrayList<String>();
				
				// CSV indexes
				// 0 - ticketNum	1 - date
				// 2 - cinemaNum	3 - start time
				// 4 - seats (dynamic length)	
				// 5 - price		6 - isActive

				values = line.split(",");

				csvData.add(values[0]);
				csvData.add(values[2]);
				csvData.add(values[3]);
				csvData.add(values[values.length-2]);
				csvData.add(values[values.length-1]);
				
				for (int i = 4; i < values.length-2; i++) {
					seats.add(values[i].substring(1,3));
				}
				for (int i =0 ; i < csvData.size(); i++) {
					String string = csvData.get(i);
					csvData.set(i, string.substring(1, string.length() - 1));
				}

				LocalDate dateOfShowing = LocalDate.parse(values[1].substring(1, values[1].length()-1), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
				Screening scr;
				Ticket ticket;
				Set<String> keys = screenings.keySet();
				for(String k: keys){
					scr = screenings.get(k);
					if(scr.getMovieShowing().getShowingDate().equals(dateOfShowing) && scr.getCinemaNum()==Integer.parseInt(csvData.get(1)) && scr.getStartTime().equals(LocalTime.parse(csvData.get(2), DateTimeFormatter.ofPattern("HH:mm")))){
						ticket = new Ticket(csvData, seats, scr.getMovieShowing());
						scr.getSoldTickets().add(ticket);
						scr.getSeatLayout().setAvailableSeats(scr.getSeatLayout().getAvailableSeats()-seats.size());
						for(String seat:seats){
							scr.getSeatLayout().setSeat((int) seat.charAt(0) -'A',seat.charAt(1) - '0'-1,true);
						}
						ctr++;
					}
				}
				tot++;
			}
			System.out.println("Imported ["+ctr+"/"+tot+"] tickets");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void generateReservationsCSV() {
		// https://blog.gitnux.com/code/java-csv-write/
		try {
			OutputStreamWriter fileWriter = new OutputStreamWriter(new FileOutputStream("reservations.csv"));
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

			//No header, just like sir Ryan's sample

			//Not sorted in ascending order because the data structure used is HashMap
			Set<String> keys = screenings.keySet();
			for(String key: keys) {
				for(Ticket ticket: screenings.get(key).getSoldTickets()) {
					bufferedWriter.write(ticket.toString());
					bufferedWriter.newLine();
				}
			}
			bufferedWriter.close();
			System.out.println("CSV file created successfully.");

		} catch (Exception e) {
			System.out.println("Error occurred while writing to the CSV file.");
			e.printStackTrace();
		}
	}

}
