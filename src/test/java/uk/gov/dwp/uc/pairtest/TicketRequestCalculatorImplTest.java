package uk.gov.dwp.uc.pairtest;

import org.junit.Before;
import org.junit.Test;
import uk.gov.dwp.uc.pairtest.domain.TicketRequestCalculatorImpl;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;

import static org.junit.Assert.assertEquals;
import static uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest.Type.*;

public class TicketRequestCalculatorImplTest {

    TicketRequestCalculatorImpl ticketRequestCalculator;

    @Before
    public void setup() {
        ticketRequestCalculator = new TicketRequestCalculatorImpl();
    }

    @Test
    public void calculate1AdultTicket() {
        TicketTypeRequest ticketTypeRequest = new TicketTypeRequest(ADULT, 1);
        assertEquals(20, ticketRequestCalculator.calculateTotalPrice(ticketTypeRequest));
        assertEquals(1, ticketRequestCalculator.calculateTotalSeats(ticketTypeRequest));
    }

    @Test
    public void calculate3AdultTickets() {
        TicketTypeRequest ticketTypeRequest = new TicketTypeRequest(ADULT, 3);
        assertEquals(60, ticketRequestCalculator.calculateTotalPrice(ticketTypeRequest));
        assertEquals(3, ticketRequestCalculator.calculateTotalSeats(ticketTypeRequest));
    }

    @Test
    public void calculate3AdultTicketsAnd3ChildTickets() {
        TicketTypeRequest ticketAdultRequest = new TicketTypeRequest(ADULT, 3);
        TicketTypeRequest ticketChildRequest = new TicketTypeRequest(CHILD, 3);
        assertEquals(90, ticketRequestCalculator.calculateTotalPrice(ticketAdultRequest,ticketChildRequest));
        assertEquals(6, ticketRequestCalculator.calculateTotalSeats(ticketAdultRequest,ticketChildRequest));
    }

    @Test
    public void calculate6AdultTicketsAnd3ChildTicketsAnd3InfantTickets() {
        TicketTypeRequest ticketAdultRequest = new TicketTypeRequest(ADULT, 6);
        TicketTypeRequest ticketChildRequest = new TicketTypeRequest(CHILD, 3);
        TicketTypeRequest ticketInfantRequest = new TicketTypeRequest(INFANT, 3);
        assertEquals(150,
                ticketRequestCalculator.calculateTotalPrice(ticketAdultRequest,ticketChildRequest,ticketInfantRequest));
        assertEquals(9,
                ticketRequestCalculator.calculateTotalSeats(ticketAdultRequest,ticketChildRequest,ticketInfantRequest));
    }



}
