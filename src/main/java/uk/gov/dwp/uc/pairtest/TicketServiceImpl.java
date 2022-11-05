package uk.gov.dwp.uc.pairtest;

import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;
import uk.gov.dwp.uc.pairtest.exception.*;

import java.util.Arrays;
import static uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest.Type.*;


public class TicketServiceImpl implements TicketService {

    /**
     * Should only have private methods other than the one below.
     */
    @Override
    public void purchaseTickets(Long accountId, TicketTypeRequest... ticketTypeRequests) throws InvalidPurchaseException {
        if (isAccountValid(accountId)) {
            if (isTicketNoValid(ticketTypeRequests)) {
                if (doesTicketTypeRequestsContainValidAdultRatio(ticketTypeRequests)) {
                    System.out.println("purchasing");
                }
            }
        }

    }

    private boolean isAccountValid(Long accountId) {
        if (accountId > 0L) {
            return true;
        } else {
            throw new InvalidAccountException();
        }
    }

    private boolean isTicketNoValid(TicketTypeRequest... ticketTypeRequests) {
        if (Arrays.stream(ticketTypeRequests).anyMatch(ticketTypeRequest -> (ticketTypeRequest.getNoOfTickets() < 0))) {
            throw new InvalidNegativeTicketsException();
        } else {
            int ticketCount = Arrays.stream(ticketTypeRequests).mapToInt(TicketTypeRequest::getNoOfTickets).sum();
            if (ticketCount > 20 || ticketCount < 0) {
                throw new InvalidNoOfTicketsException();
            } else {
                return true;
            }
        }
    }

    private boolean doesTicketTypeRequestsContainValidAdultRatio(TicketTypeRequest... ticketTypeRequests) {
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
