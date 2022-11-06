package uk.gov.dwp.uc.pairtest;

import org.junit.Test;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;
import uk.gov.dwp.uc.pairtest.exception.*;

import static uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest.Type.*;

public class TicketServiceImplTest {

    @Test(expected = InvalidPurchaseException.class)
    public void invalidAccountPurchase1Ticket() {
        Long account = -1L;
        TicketServiceImpl ticketService = new TicketServiceImpl();
        TicketTypeRequest ticketTypeRequest = new TicketTypeRequest(ADULT, 1);

        ticketService.purchaseTickets(account, ticketTypeRequest);
    }

    @Test
    public void validAccountPurchase1Ticket() {
        Long account = 1L;
        TicketServiceImpl ticketService = new TicketServiceImpl();
        TicketTypeRequest ticketTypeRequest = new TicketTypeRequest(ADULT, 1);

        ticketService.purchaseTickets(account, ticketTypeRequest);
    }

    @Test(expected = InvalidPurchaseException.class)
    public void validAccountPurchase21Ticket() {
        Long account = 1L;
        TicketServiceImpl ticketService = new TicketServiceImpl();
        TicketTypeRequest ticketTypeRequest = new TicketTypeRequest(ADULT, 21);

        ticketService.purchaseTickets(account, ticketTypeRequest);
    }

    @Test(expected = InvalidPurchaseException.class)
    public void validAccountPurchaseLessThan1Ticket() {
        Long account = 1L;
        TicketServiceImpl ticketService = new TicketServiceImpl();
        TicketTypeRequest ticketTypeRequest = new TicketTypeRequest(ADULT, -1);

        ticketService.purchaseTickets(account, ticketTypeRequest);
    }

    @Test(expected = InvalidPurchaseException.class)
    public void validAccountPurchaseValidTicketsAndRequestWithLessThan1InfantTicket() {
        Long account = 1L;
        TicketServiceImpl ticketService = new TicketServiceImpl();
        TicketTypeRequest ticketTypeRequest = new TicketTypeRequest(ADULT, 1);
        TicketTypeRequest ticketInfantRequest = new TicketTypeRequest(INFANT, -1);
        TicketTypeRequest ticketChildRequest = new TicketTypeRequest(CHILD, 1);

        ticketService.purchaseTickets(account, ticketTypeRequest, ticketChildRequest, ticketInfantRequest);
    }

    @Test(expected = InvalidPurchaseException.class)
    public void validAccountPurchaseValidTicketsAndRequestWithLessThan1ChildTicket() {
        Long account = 1L;
        TicketServiceImpl ticketService = new TicketServiceImpl();
        TicketTypeRequest ticketTypeRequest = new TicketTypeRequest(ADULT, 1);
        TicketTypeRequest ticketInfantRequest = new TicketTypeRequest(INFANT, 1);
        TicketTypeRequest ticketChildRequest = new TicketTypeRequest(CHILD, -1);

        ticketService.purchaseTickets(account, ticketTypeRequest, ticketChildRequest, ticketInfantRequest);
    }

    @Test(expected = InvalidPurchaseException.class)
    public void validAccountPurchase21TicketsAcrossAdultAndChildTypes() {
        Long account = 1L;
        TicketServiceImpl ticketService = new TicketServiceImpl();
        TicketTypeRequest ticketAdultRequest = new TicketTypeRequest(ADULT, 11);
        TicketTypeRequest ticketChildRequest = new TicketTypeRequest(CHILD, 10);

        ticketService.purchaseTickets(account, ticketAdultRequest, ticketChildRequest);
    }

    @Test(expected = InvalidPurchaseException.class)
    public void validAccountPurchase21TicketsAcrossAllTypes() {
        Long account = 1L;
        TicketServiceImpl ticketService = new TicketServiceImpl();
        TicketTypeRequest ticketAdultRequest = new TicketTypeRequest(ADULT, 11);
        TicketTypeRequest ticketChildRequest = new TicketTypeRequest(CHILD, 5);
        TicketTypeRequest ticketInfantRequest = new TicketTypeRequest(INFANT, 5);

        ticketService.purchaseTickets(account, ticketAdultRequest, ticketChildRequest, ticketInfantRequest);
    }

    @Test(expected = InvalidPurchaseException.class)
    public void validAccountPurchaseOnlyChildTicketTypes() {
        Long account = 1L;
        TicketServiceImpl ticketService = new TicketServiceImpl();
        TicketTypeRequest ticketInfantRequest = new TicketTypeRequest(INFANT, 1);
        TicketTypeRequest ticketChildRequest = new TicketTypeRequest(CHILD, 1);

        ticketService.purchaseTickets(account, ticketInfantRequest, ticketChildRequest);
    }

    @Test(expected = InvalidPurchaseException.class)
    public void validAccountPurchaseTooManyInfantTicketTypes() {
        Long account = 1L;
        TicketServiceImpl ticketService = new TicketServiceImpl();
        TicketTypeRequest ticketAdultRequest = new TicketTypeRequest(ADULT, 1);
        TicketTypeRequest ticketInfantRequest = new TicketTypeRequest(INFANT, 2);

        ticketService.purchaseTickets(account, ticketAdultRequest, ticketInfantRequest);
    }

    @Test(expected = InvalidPurchaseException.class)
    public void validAccountPurchaseTooManyChildTicketTypes() {
        Long account = 1L;
        TicketServiceImpl ticketService = new TicketServiceImpl();
        TicketTypeRequest ticketAdultRequest = new TicketTypeRequest(ADULT, 1);
        TicketTypeRequest ticketChildRequest = new TicketTypeRequest(CHILD, 2);

        ticketService.purchaseTickets(account, ticketAdultRequest, ticketChildRequest);
    }

    @Test(expected = InvalidPurchaseException.class)
    public void validAccountPurchaseMultipleTicketTypesOfBadRatio() {
        Long account = 1L;
        TicketServiceImpl ticketService = new TicketServiceImpl();
        TicketTypeRequest ticketAdultRequest = new TicketTypeRequest(ADULT, 2);
        TicketTypeRequest ticketInfantRequest = new TicketTypeRequest(INFANT, 2);
        TicketTypeRequest ticketChildRequest = new TicketTypeRequest(CHILD, 2);

        ticketService.purchaseTickets(account, ticketAdultRequest, ticketInfantRequest, ticketChildRequest);
    }

    @Test
    public void validAccountPurchaseChildTicketTypes() {
        Long account = 1L;
        TicketServiceImpl ticketService = new TicketServiceImpl();
        TicketTypeRequest ticketAdultRequest = new TicketTypeRequest(ADULT, 1);
        TicketTypeRequest ticketChildRequest = new TicketTypeRequest(CHILD, 1);

        ticketService.purchaseTickets(account, ticketAdultRequest, ticketChildRequest);
    }

    @Test
    public void validAccountPurchaseInfantTicketTypes() {
        Long account = 1L;
        TicketServiceImpl ticketService = new TicketServiceImpl();
        TicketTypeRequest ticketAdultRequest = new TicketTypeRequest(ADULT, 1);
        TicketTypeRequest ticketInfantRequest = new TicketTypeRequest(INFANT, 1);

        ticketService.purchaseTickets(account, ticketAdultRequest, ticketInfantRequest);
    }

    @Test
    public void validAccountPurchaseMultipleTicketTypesOfCorrectRatio() {
        Long account = 1L;
        TicketServiceImpl ticketService = new TicketServiceImpl();
        TicketTypeRequest ticketAdultRequest = new TicketTypeRequest(ADULT, 2);
        TicketTypeRequest ticketInfantRequest = new TicketTypeRequest(INFANT, 1);
        TicketTypeRequest ticketChildRequest = new TicketTypeRequest(CHILD, 1);

        ticketService.purchaseTickets(account, ticketAdultRequest, ticketInfantRequest, ticketChildRequest);
    }

    @Test
    public void validAccountPurchaseMaximumTicketTypesOfCorrectRatio() {
        Long account = 1L;
        TicketServiceImpl ticketService = new TicketServiceImpl();
        TicketTypeRequest ticketAdultRequest = new TicketTypeRequest(ADULT, 10);
        TicketTypeRequest ticketInfantRequest = new TicketTypeRequest(INFANT, 5);
        TicketTypeRequest ticketChildRequest = new TicketTypeRequest(CHILD, 5);

        ticketService.purchaseTickets(account, ticketAdultRequest, ticketInfantRequest, ticketChildRequest);
    }
}
