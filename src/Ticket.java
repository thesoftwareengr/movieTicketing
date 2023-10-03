import java.time.LocalDate;
import java.time.LocalTime;

public class Ticket {
	private static int referenceTicketNumber=1;

	private int ticketNum;
	private LocalDate showingDate;
	private LocalTime showingTime;
	private String[] reservedSeats;
	private double totalPrice;
	private int cinemaNum;
	private boolean isActive;

	public Ticket(LocalDate showingDate, LocalTime showingTime, String[] reservedSeats,
			double totalPrice, int cinemaNum) {
		this.ticketNum = referenceTicketNumber;
		this.showingDate = showingDate;
		this.showingTime = showingTime;
		this.reservedSeats = reservedSeats;
		this.totalPrice = totalPrice;
		this.cinemaNum = cinemaNum;
		this.isActive = true;
		referenceTicketNumber++;
	}

	public int getTicketNum() {
		return ticketNum;
	}
	
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

	@Override
	public String toString() {
		return ticketNum+"|"+this.showingDate.toString()+"|"+this.cinemaNum+"|"+this.showingTime.toString()+"|"+this.reservedSeats+"|"+this.totalPrice;
	}
	
}
