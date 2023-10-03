import java.time.LocalDate;
import java.time.LocalTime;
<<<<<<< HEAD
import java.util.ArrayList;

public class Ticket {
=======

public class Ticket {

>>>>>>> a36b2bdcbe8e71e2b0a6beadcaea378fc3558249
	private int ticketNum;
	private static int referenceTicketNumber;
	private LocalDate showingDate;
	private LocalTime showingTime;
<<<<<<< HEAD
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
=======
	private String[] reservedSeats;
	private double totalPrice;
	private int cinemaNum;
	private boolean isActive;

>>>>>>> a36b2bdcbe8e71e2b0a6beadcaea378fc3558249
	
	public int getTicketNum() {
		return ticketNum;
	}
<<<<<<< HEAD
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
	
	
=======
	
	public void setTicketNum(int ticketNum) {
		this.ticketNum = ticketNum;
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
	
	public String[] getReservedSeats() {
		return reservedSeats;
	}
	
	public void setReservedSeats(String[] reservedSeats) {
		this.reservedSeats = reservedSeats;
	}
	
	public double getTotalPrice() {
		return totalPrice;
	}
	
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
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
>>>>>>> a36b2bdcbe8e71e2b0a6beadcaea378fc3558249
}
