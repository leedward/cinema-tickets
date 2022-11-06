package uk.gov.dwp.uc.pairtest.domain;

import uk.gov.dwp.uc.pairtest.exception.*;

public interface TicketRequestValidator {

    boolean isAccountValid(Long accountId) throws InvalidAccountException;

    boolean isTicketNoValid(TicketTypeRequest... ticketTypeRequests)
            throws InvalidNegativeTicketsException, InvalidNoOfTicketsException;

    boolean doesTicketTypeRequestsContainValidAdultRatio(TicketTypeRequest... ticketTypeRequests)
            throws AdultRequiredException;

}
