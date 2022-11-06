package uk.gov.dwp.uc.pairtest;

import org.junit.Before;
import org.junit.Test;
import uk.gov.dwp.uc.pairtest.domain.TicketRequestValidatorImpl;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;
import uk.gov.dwp.uc.pairtest.exception.AdultRequiredException;
import uk.gov.dwp.uc.pairtest.exception.InvalidAccountException;
import uk.gov.dwp.uc.pairtest.exception.InvalidNegativeTicketsException;
import uk.gov.dwp.uc.pairtest.exception.InvalidNoOfTicketsException;

import static uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest.Type.*;

public class TicketRequestValidatorImplTest {

    TicketRequestValidatorImpl ticketRequestValidator;

    @Before
    public void setup() {
        ticketRequestValidator = new TicketRequestValidatorImpl();
    }

    @Test(expected = InvalidAccountException.class)
    public void invalidAccount() {
        Long account = -1L;
        ticketRequestValidator.isAccountValid(account);
    }

    @Test
    public void validAccount() {
        Long account = 1L;
        ticketRequestValidator.isAccountValid(account);
    }

    @Test(expected = InvalidNoOfTicketsException.class)
    public void validate21Tickets() {
        TicketTypeRequest ticketTypeRequest = new TicketTypeRequest(ADULT, 21);

        ticketRequestValidator.isTicketNoValid(ticketTypeRequest);
    }

    @Test(expected = InvalidNegativeTicketsException.class)
    public void validateLessThan1Ticket() {
        TicketTypeRequest ticketTypeRequest = new TicketTypeRequest(ADULT, -1);
        ticketRequestValidator.isTicketNoValid(ticketTypeRequest);
    }

    @Test(expected = InvalidNoOfTicketsException.class)
    public void validate0Tickets() {
        TicketTypeRequest ticketTypeRequest = new TicketTypeRequest(ADULT, 0);
        ticketRequestValidator.isTicketNoValid(ticketTypeRequest);
    }

    @Test
    public void validate1Ticket() {
        TicketTypeRequest ticketTypeRequest = new TicketTypeRequest(ADULT, 1);
        ticketRequestValidator.isTicketNoValid(ticketTypeRequest);
    }

    @Test
    public void validate20Ticket() {
        TicketTypeRequest ticketTypeRequest = new TicketTypeRequest(ADULT, 20);
        ticketRequestValidator.isTicketNoValid(ticketTypeRequest);
    }


    @Test(expected = InvalidNegativeTicketsException.class)
    public void validateTicketsWithLessThan1InfantTicket() {
        TicketTypeRequest ticketAdultRequest = new TicketTypeRequest(ADULT, 1);
        TicketTypeRequest ticketInfantRequest = new TicketTypeRequest(INFANT, -1);
        TicketTypeRequest ticketChildRequest = new TicketTypeRequest(CHILD, 1);

        ticketRequestValidator.isTicketNoValid(ticketAdultRequest, ticketChildRequest, ticketInfantRequest);

    }

    @Test(expected = InvalidNegativeTicketsException.class)
    public void validateTicketsWithLessThan1ChildTicket() {
        TicketTypeRequest ticketAdultRequest = new TicketTypeRequest(ADULT, 1);
        TicketTypeRequest ticketInfantRequest = new TicketTypeRequest(INFANT, 1);
        TicketTypeRequest ticketChildRequest = new TicketTypeRequest(CHILD, -1);

        ticketRequestValidator.isTicketNoValid(ticketAdultRequest, ticketChildRequest, ticketInfantRequest);

    }

    @Test(expected = InvalidNoOfTicketsException.class)
    public void validate21TicketsAcrossAdultAndChildTypes() {
        TicketServiceImpl ticketService = new TicketServiceImpl();
        TicketTypeRequest ticketAdultRequest = new TicketTypeRequest(ADULT, 11);
        TicketTypeRequest ticketChildRequest = new TicketTypeRequest(CHILD, 10);
        ticketRequestValidator.isTicketNoValid(ticketAdultRequest, ticketChildRequest);

    }

    @Test(expected = InvalidNoOfTicketsException.class)
    public void validate21TicketsAcrossAllTypes() {
        TicketTypeRequest ticketAdultRequest = new TicketTypeRequest(ADULT, 11);
        TicketTypeRequest ticketChildRequest = new TicketTypeRequest(CHILD, 5);
        TicketTypeRequest ticketInfantRequest = new TicketTypeRequest(INFANT, 5);

        ticketRequestValidator.isTicketNoValid(ticketAdultRequest, ticketChildRequest, ticketInfantRequest);
    }

    @Test
    public void validate20TicketsAcrossAllTypes() {
        TicketTypeRequest ticketAdultRequest = new TicketTypeRequest(ADULT, 10);
        TicketTypeRequest ticketChildRequest = new TicketTypeRequest(CHILD, 5);
        TicketTypeRequest ticketInfantRequest = new TicketTypeRequest(INFANT, 5);

        ticketRequestValidator.isTicketNoValid(ticketAdultRequest, ticketChildRequest, ticketInfantRequest);
    }

    @Test(expected = AdultRequiredException.class)
    public void validateOnlyChildTicketTypes() {
        TicketTypeRequest ticketInfantRequest = new TicketTypeRequest(INFANT, 1);
        TicketTypeRequest ticketChildRequest = new TicketTypeRequest(CHILD, 1);

        ticketRequestValidator.doesTicketTypeRequestsContainValidAdultRatio(ticketChildRequest, ticketInfantRequest);
    }

    @Test(expected = AdultRequiredException.class)
    public void validateTooManyInfantTicketTypes() {
        TicketTypeRequest ticketAdultRequest = new TicketTypeRequest(ADULT, 1);
        TicketTypeRequest ticketInfantRequest = new TicketTypeRequest(INFANT, 2);

        ticketRequestValidator.doesTicketTypeRequestsContainValidAdultRatio(ticketAdultRequest, ticketInfantRequest);
    }

    @Test(expected = AdultRequiredException.class)
    public void validateTooManyChildTicketTypes() {
        TicketTypeRequest ticketAdultRequest = new TicketTypeRequest(ADULT, 1);
        TicketTypeRequest ticketChildRequest = new TicketTypeRequest(CHILD, 2);

        ticketRequestValidator.doesTicketTypeRequestsContainValidAdultRatio(ticketAdultRequest, ticketChildRequest);
    }

    @Test(expected = AdultRequiredException.class)
    public void validateMultipleTicketTypesOfBadRatio() {
        TicketTypeRequest ticketAdultRequest = new TicketTypeRequest(ADULT, 2);
        TicketTypeRequest ticketInfantRequest = new TicketTypeRequest(INFANT, 2);
        TicketTypeRequest ticketChildRequest = new TicketTypeRequest(CHILD, 2);

        ticketRequestValidator.doesTicketTypeRequestsContainValidAdultRatio(ticketAdultRequest, ticketChildRequest, ticketInfantRequest);
    }

    @Test
    public void validateChildTicketType() {
        TicketTypeRequest ticketAdultRequest = new TicketTypeRequest(ADULT, 1);
        TicketTypeRequest ticketChildRequest = new TicketTypeRequest(CHILD, 1);

        ticketRequestValidator.doesTicketTypeRequestsContainValidAdultRatio(ticketAdultRequest, ticketChildRequest);
    }

    @Test
    public void validateInfantTicketTypes() {
        TicketTypeRequest ticketAdultRequest = new TicketTypeRequest(ADULT, 1);
        TicketTypeRequest ticketInfantRequest = new TicketTypeRequest(INFANT, 1);

        ticketRequestValidator.doesTicketTypeRequestsContainValidAdultRatio(ticketAdultRequest, ticketInfantRequest);
    }

    @Test
    public void validateMultipleTicketTypesOfCorrectRatio() {
        TicketTypeRequest ticketAdultRequest = new TicketTypeRequest(ADULT, 2);
        TicketTypeRequest ticketInfantRequest = new TicketTypeRequest(INFANT, 1);
        TicketTypeRequest ticketChildRequest = new TicketTypeRequest(CHILD, 1);

        ticketRequestValidator.doesTicketTypeRequestsContainValidAdultRatio(ticketAdultRequest, ticketInfantRequest, ticketChildRequest);
    }

    @Test
    public void validAccountPurchaseMaximumTicketTypesOfCorrectRatio() {
        TicketTypeRequest ticketAdultRequest = new TicketTypeRequest(ADULT, 10);
        TicketTypeRequest ticketInfantRequest = new TicketTypeRequest(INFANT, 5);
        TicketTypeRequest ticketChildRequest = new TicketTypeRequest(CHILD, 5);

        ticketRequestValidator.doesTicketTypeRequestsContainValidAdultRatio(ticketAdultRequest, ticketInfantRequest, ticketChildRequest);
    }
}
