package uk.gov.dwp.uc.pairtest.domain;

public interface TicketRequestCalculator{

    int calculateTotalPrice(TicketTypeRequest... ticketTypeRequests);
    int calculateTotalSeats(TicketTypeRequest... ticketTypeRequests);
}
