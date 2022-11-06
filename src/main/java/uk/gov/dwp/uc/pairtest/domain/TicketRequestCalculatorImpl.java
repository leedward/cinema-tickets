package uk.gov.dwp.uc.pairtest.domain;

import java.util.Arrays;

import static uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest.Type.*;

public class TicketRequestCalculatorImpl implements TicketRequestCalculator {

    public TicketRequestCalculatorImpl() {
    }

    @Override
    public int calculateTotalPrice(TicketTypeRequest... ticketTypeRequests) {

        int adultTicketCount = Arrays.stream(ticketTypeRequests)
                .filter(ticketTypeRequest ->
                        (ticketTypeRequest.getTicketType() == ADULT))
                .mapToInt(TicketTypeRequest::getNoOfTickets).sum();

        int adultTotalPrice = adultTicketCount * 20;

        int childTicketCount = Arrays.stream(ticketTypeRequests)
                .filter(ticketTypeRequest ->
                        (ticketTypeRequest.getTicketType() == CHILD))
                .mapToInt(TicketTypeRequest::getNoOfTickets).sum();

        int childTotalPrice = childTicketCount * 10;

        return adultTotalPrice + childTotalPrice;
    }

    @Override
    public int calculateTotalSeats(TicketTypeRequest... ticketTypeRequests) {
        int adultTicketCount = Arrays.stream(ticketTypeRequests)
                .filter(ticketTypeRequest ->
                        (ticketTypeRequest.getTicketType() == ADULT))
                .mapToInt(TicketTypeRequest::getNoOfTickets).sum();

        int childTicketCount = Arrays.stream(ticketTypeRequests)
                .filter(ticketTypeRequest ->
                        (ticketTypeRequest.getTicketType() == CHILD))
                .mapToInt(TicketTypeRequest::getNoOfTickets).sum();

        return adultTicketCount + childTicketCount;
    }
}
