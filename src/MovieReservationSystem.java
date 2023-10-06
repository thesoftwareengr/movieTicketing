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
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class MovieReservationSystem {
	public ArrayList<Movie> movies;
	public HashMap<String, Screening> screenings;

	public static void main(String[] args) {	
		boolean inf = true;
		String MovieID = "";
		Scanner scan = new Scanner(System.in);
		MovieReservationSystem mrs = new MovieReservationSystem();
		mrs.readCSV();

		Set<String> keys = mrs.screenings.keySet();
		SortedSet<Entry<String, Screening>> sortedEntries = mrs.screenings.entrySet()
				.stream()
				.sorted(Map.Entry.comparingByKey())
				.collect(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(Entry::getKey))));

		while(inf==true){

			System.out.println("\n                         ********** MAIN MENU **********");		

			int cinemaNumber = 0;
			for (Map.Entry<String, Screening> entry : sortedEntries) {
				String key = entry.getKey();
				Screening data = mrs.screenings.get(entry.getKey());
				if(Character.getNumericValue(key.charAt(0)) != cinemaNumber) {
					cinemaNumber = Character.getNumericValue(key.charAt(0));
					System.out.println("CINEMA " + cinemaNumber);
					System.out.println("ID        Movies              Time      Seats Available  Premiere");
				}
				System.out.printf("%-2s   %-15s...  %-5s - %-5s    %-12s      %-3s\n", key, (data.getMovieShowing().getName().length()>15? data.getMovieShowing().getName().substring(0,15): data.getMovieShowing().getName()),data.getStartTime(), Screening.endTimeCalc(data.getStartTime(), data.getMovieShowing().getLength()),(data.getSeatLayout().getAvailableSeats()>0?"["+data.getSeatLayout().getAvailableSeats()+"] Seat(s)":"[00] Full"),(data.getMovieShowing().getIsPremier()? "Yes":"No"));

			}


			int ticketID;
			boolean returnCinema = false;
			//while(true) {

			do {
				System.out.println("Pick a movie ID to view the seat layout: (QUIT to exit)");
				System.out.print("Input: ");
				MovieID = scan.nextLine();

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
						selectedScreening.getSoldTickets().add(ticket);
	
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
						do {
							try {
								System.out.println("Please ticket number to be cancelled");
								System.out.print("Input: ");
								ticketID = Integer.parseInt(scan.nextLine());
								Ticket foundTicket=null;
								for (Ticket ticketIter : selectedScreening.getSoldTickets()) {
									if (ticketIter.getTicketNum()==ticketID) {
										foundTicket = ticketIter;
										break; // Stop searching once the ticket is found
									}
								}
								if(foundTicket!=null) {
									System.out.println("Ticket found");
									selectedScreening.getSeatLayout().cancel(foundTicket);
								}else {
									System.out.println("Ticket not found");
								}
							}catch(Exception e){
								ticketID = 0;
							}
						}while(ticketID==0);
						mrs.generateReservationsCSV();
						break;
					case 3:
						break;
				}
			}while(returnCinema);
		}
		scan.close();
		System.out.println("                   ********** Application End **********");	

	}

	MovieReservationSystem(){
		movies = new ArrayList<Movie>();
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

//		        CSV indexes
//		        0 - date			1 - cinema num
//		        2 - start time		3 - isPremiere
//		        4 - title			5 - duration

				for(int ndx=0; ndx<values.length;ndx++) {
					values[ndx] = values[ndx].substring(1, values[ndx].length()-1);
				}

				movieData= new Movie(values[4], Double.parseDouble(values[5]), LocalDate.parse(values[0], DateTimeFormatter.ofPattern("yyyy-MM-dd")), Boolean.valueOf(values[3]));

				for(; i< movies.size(); i++) {
					if(movies.get(i).getName().equals(values[4])) {
						break;
					}
				}
				if(i==movies.size()) { //empty or new movie
					movies.add(movieData);
				}

				LocalTime startTime = LocalTime.parse(values[2], DateTimeFormatter.ofPattern("HH:mm"));
				movies.get(i).addStartingTime(startTime);

				screeningData = new Screening(movies.get(i), Integer.valueOf((values[1])), startTime);
				Set<String> keys = screenings.keySet();
				for(String key: keys) {
					if(screenings.get(key).getCinemaNum()==Integer.valueOf(values[1])) {
						movieCtr++;
					}
				}

				compositeKey=values[1]+(char) ('A'+movieCtr);
				if(!screenings.containsKey(compositeKey)) {
					screenings.put(compositeKey, screeningData);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	public void generateReservationsCSV() {
		//		https://blog.gitnux.com/code/java-csv-write/
		String outputPath = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))+"-reservations.csv";

		try {
			OutputStreamWriter fileWriter = new OutputStreamWriter(new FileOutputStream(outputPath));
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

			//No header, just like sir Ryan's sample

			//Not sorted in ascending order but my organization in the HashMap
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
			e.printStackTrace();
			System.out.println("Error occurred while writing to the CSV file.");
		}
	}

}
