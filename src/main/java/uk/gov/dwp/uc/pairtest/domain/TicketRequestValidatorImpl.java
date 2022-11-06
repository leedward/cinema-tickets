package uk.gov.dwp.uc.pairtest.domain;

import uk.gov.dwp.uc.pairtest.exception.AdultRequiredException;
import uk.gov.dwp.uc.pairtest.exception.InvalidAccountException;
import uk.gov.dwp.uc.pairtest.exception.InvalidNegativeTicketsException;
import uk.gov.dwp.uc.pairtest.exception.InvalidNoOfTicketsException;

import java.util.Arrays;

import static uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest.Type.*;

public class TicketRequestValidatorImpl implements TicketRequestValidator {

    public TicketRequestValidatorImpl() {
    }

    @Override
    public boolean isAccountValid(Long accountId) throws InvalidAccountException {
        if (accountId > 0L) {
            return true;
        } else {
            throw new InvalidAccountException();
        }
    }

    @Override
    public boolean isTicketNoValid(TicketTypeRequest... ticketTypeRequests)
            throws InvalidNegativeTicketsException, InvalidNoOfTicketsException {
        if (Arrays.stream(ticketTypeRequests).anyMatch(ticketTypeRequest ->
                (ticketTypeRequest.getNoOfTickets() < 0))) {
            throw new InvalidNegativeTicketsException();
        } else {
            int ticketCount = Arrays.stream(ticketTypeRequests).mapToInt(TicketTypeRequest::getNoOfTickets).sum();
            if (ticketCount > 20 || ticketCount <= 0) {
                throw new InvalidNoOfTicketsException();
            } else {
                return true;
            }
        }
    }

    @Override
    public boolean doesTicketTypeRequestsContainValidAdultRatio(TicketTypeRequest... ticketTypeRequests)
            throws AdultRequiredException {
        int adultTicketCount = Arrays.stream(ticketTypeRequests)
                .filter(ticketTypeRequest ->
                        (ticketTypeRequest.getTicketType() == ADULT))
                .mapToInt(TicketTypeRequest::getNoOfTickets).sum();

        int childAndInfantTicketCount = Arrays.stream(ticketTypeRequests)
                .filter(ticketTypeRequest ->
                        (ticketTypeRequest.getTicketType() == CHILD ||
                                ticketTypeRequest.getTicketType() == INFANT))
                .mapToInt(TicketTypeRequest::getNoOfTickets).sum();

        if (childAndInfantTicketCount > adultTicketCount) {
            throw new AdultRequiredException();
        } else {
            return true;
        }
    }
}
