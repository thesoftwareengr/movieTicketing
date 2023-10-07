import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Ticket {
	private static int referenceTicketNumber=1;

	private int ticketNum;
	private LocalTime showingTime;
	private ArrayList<String> reservedSeats;
	private double totalPrice;
	private int cinemaNum;
	private boolean isActive;
	private int seniors;
	private Movie movie;

	public Ticket(Movie movie, LocalTime showingTime, ArrayList<String> reservedSeats, double totalPrice, int cinemaNum, int seniors) {
		this.ticketNum = referenceTicketNumber;
		this.showingTime = showingTime;
		this.reservedSeats = reservedSeats;
		this.totalPrice = totalPrice;
		this.cinemaNum = cinemaNum;
		this.isActive = true;
		this.seniors = seniors;
		this.movie = movie;
		referenceTicketNumber++;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public int getSeniors() {
		return seniors;
	}

	public void setSeniors(int seniors) {
		this.seniors = seniors;
	}

	public int getTicketNum() {
		return ticketNum;
	}

	public void setTicketNum(int ticketNum) {
		this.ticketNum = ticketNum;
	}

	public LocalTime getShowingTime() {
		return showingTime;
	}

	public void setShowingTime(LocalTime showingTime) {
		this.showingTime = showingTime;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public static int getReferenceTicketNumber() {
		return referenceTicketNumber;
	}

	public static void setReferenceTicketNumber(int referenceTicketNumber) {
		Ticket.referenceTicketNumber = referenceTicketNumber;
	}

	public ArrayList<String> getReservedSeats() {
		return reservedSeats;
	}

	public void setReservedSeats(ArrayList<String> reservedSeats) {
		this.reservedSeats = reservedSeats;
	}

	public int getCinemaNum() {
		return cinemaNum;
	}

	public void setCinemaNum(int cinemaNum) {
		this.cinemaNum = cinemaNum;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "\""+ticketNum+"\",\""+this.getMovie().getShowingDate().toString()+"\",\""+this.cinemaNum+"\",\""+this.showingTime.toString()+"\",\""+this.reservedSeats+"\",\""+this.totalPrice+"\"";
	}

	public void display() {
		System.out.println("********************************************************************************");
		System.out.println("\nTicket Receipt for "+this.getMovie().getName()+"@ "+this.getShowingTime()+" - "+Screening.endTimeCalc(getShowingTime(), getMovie().getLength())+"\nPremier: "+this.getMovie().getIsPremier()+"\nSeat Numbers:");
		int snr = this.getSeniors();
		int price=350;
		for(String i: this.getReservedSeats()) {
			if(snr>0) {
				System.out.printf("%6s\t350PHP\t%10s\t280PHP\n", i, "20%%(70PHP)");
				snr--;
			}else {
				price=this.getMovie().getIsPremier()==true?500:350;
				System.out.printf("%6s\t%dPHP\t%10s\t%-6s\n",i, price,"          ",price+"PHP");
			}
		}
		System.out.printf("Total:\t      \t          \t%-6s\n", (int)this.getTotalPrice()+"PHP");
		System.out.println("********************************************************************************");
	}
}
