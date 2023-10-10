# movieTicketing
This is a Movie Reservation System that uses the command line as the User Interface. The system can reserve and cancel seats in movie theaters and cinemas on a certain date. 

Features: 
1. Can reserve seats in advance.
2. When an error is found in the inputted list to be reserved, they are individually checked and given the option to correct or cancel the incorrect seat.
3. The system generates a csv file of the reserved seats which is then used as a database for reservations.
4. The system is not case sensitive.
5. The system can cancel the reserved movie ticket.
6. The system has a regular pricing and with a 20% discount for seniors.
7. After choosing seats, user prompts to a confirmation input and does not automatically released a ticket
8. Users can also choose a premiere movie without discount regardless if the customer is senior or not.
9. When a ticket is canceled, only the status of the ticket is changed from ‘active’ to ‘canceled’. Ticket itself is not deleted in the CSV, and the ticket number counter remains unchanged.
