import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Ticket {
	private int ticketNum;
	private static int referenceTicketNumber;
	private LocalDate showingDate;
	private LocalTime showingTime;
	private ArrayList<String> reservedSeats;
	private Double totalprice;
	private int cinemaNum;
	private boolean isActive;
	
	public Ticket(int ticketNum, LocalDate showingDate, LocalTime showingTime, ArrayList<String> reservedSeats,
			Double totalprice, int cinemaNum, boolean isActive) {
		super();
		this.ticketNum = ticketNum;
		this.showingDate = showingDate;
		this.showingTime = showingTime;
		this.reservedSeats = reservedSeats;
		this.totalprice = totalprice;
		this.cinemaNum = cinemaNum;
		this.isActive = isActive;
	}
	
	public int getTicketNum() {
		return ticketNum;
	}
	public void setTicketNum(int ticketNum) {
		this.ticketNum = ticketNum;
	}
	public static int getReferenceTicketNumber() {
		return referenceTicketNumber;
	}
	public static void setReferenceTicketNumber(int referenceTicketNumber) {
		Ticket.referenceTicketNumber = referenceTicketNumber;
	}
	public LocalDate getShowingDate() {
		return showingDate;
	}
	public void setShowingDate(LocalDate showingDate) {
		this.showingDate = showingDate;
	}
	public LocalTime getShowingTime() {
		return showingTime;
	}
	public void setShowingTime(LocalTime showingTime) {
		this.showingTime = showingTime;
	}
	public ArrayList<String> getReservedSeats() {
		return reservedSeats;
	}
	public void setReservedSeats(ArrayList<String> reservedSeats) {
		this.reservedSeats = reservedSeats;
	}
	public Double getTotalprice() {
		return totalprice;
	}
	public void setTotalprice(Double totalprice) {
		this.totalprice = totalprice;
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
	
	
}
