package uk.gov.dwp.uc.pairtest.domain;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest.Type.*;

public class TicketTypeRequestTest {

    @Test
    public void requestSingleAdultTicket() {
        TicketTypeRequest ticketTypeRequest = new TicketTypeRequest(ADULT, 1);

        assertEquals(1, ticketTypeRequest.getNoOfTickets());
        assertEquals(ADULT, ticketTypeRequest.getTicketType());
    }

    @Test
    public void requestMultipleAdultTickets() {
        TicketTypeRequest ticketTypeRequest = new TicketTypeRequest(ADULT, 3);

        assertEquals(3, ticketTypeRequest.getNoOfTickets());
        assertEquals(ADULT, ticketTypeRequest.getTicketType());
    }

    @Test
    public void requestSingleInfantTicket() {
        TicketTypeRequest ticketTypeRequest = new TicketTypeRequest(INFANT, 1);

        assertEquals(1, ticketTypeRequest.getNoOfTickets());
        assertEquals(INFANT, ticketTypeRequest.getTicketType());
    }

    @Test
    public void requestMultipleInfantTickets() {
        TicketTypeRequest ticketTypeRequest = new TicketTypeRequest(INFANT, 3);

        assertEquals(3, ticketTypeRequest.getNoOfTickets());
        assertEquals(INFANT, ticketTypeRequest.getTicketType());
    }

    @Test
    public void requestSingleChildTicket() {
        TicketTypeRequest ticketTypeRequest = new TicketTypeRequest(CHILD, 1);

        assertEquals(1, ticketTypeRequest.getNoOfTickets());
        assertEquals(CHILD, ticketTypeRequest.getTicketType());
    }

    @Test
    public void requestMultipleChildTickets() {
        TicketTypeRequest ticketTypeRequest = new TicketTypeRequest(CHILD, 3);

        assertEquals(3, ticketTypeRequest.getNoOfTickets());
        assertEquals(CHILD, ticketTypeRequest.getTicketType());
    }
}
