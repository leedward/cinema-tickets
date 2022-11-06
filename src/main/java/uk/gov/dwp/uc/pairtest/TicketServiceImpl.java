package uk.gov.dwp.uc.pairtest;

import thirdparty.paymentgateway.TicketPaymentServiceImpl;
import thirdparty.seatbooking.SeatReservationServiceImpl;
import uk.gov.dwp.uc.pairtest.domain.TicketRequestCalculatorImpl;
import uk.gov.dwp.uc.pairtest.domain.TicketRequestValidatorImpl;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;
import uk.gov.dwp.uc.pairtest.exception.InvalidPurchaseException;


public class TicketServiceImpl implements TicketService {

    TicketPaymentServiceImpl ticketPaymentService = new TicketPaymentServiceImpl();
    SeatReservationServiceImpl seatReservationService = new SeatReservationServiceImpl();

    TicketRequestValidatorImpl ticketRequestValidator = new TicketRequestValidatorImpl();
    TicketRequestCalculatorImpl ticketRequestCalculator = new TicketRequestCalculatorImpl();

    /**
     * Should only have private methods other than the one below.
     */
    @Override
    public void purchaseTickets(Long accountId, TicketTypeRequest... ticketTypeRequests) throws InvalidPurchaseException {
        if (validateTransaction(accountId, ticketTypeRequests)){
            executeTransaction(accountId, ticketTypeRequests);
        }else{
            throw new InvalidPurchaseException();
        }

    }

    private boolean validateTransaction(Long accountId, TicketTypeRequest... ticketTypeRequests) {
        return ticketRequestValidator.isAccountValid(accountId) &&
                ticketRequestValidator.isTicketNoValid(ticketTypeRequests) &&
                ticketRequestValidator.doesTicketTypeRequestsContainValidAdultRatio(ticketTypeRequests);
    }

    private void executeTransaction(Long accountId, TicketTypeRequest... ticketTypeRequests){
        ticketPaymentService.makePayment(accountId, ticketRequestCalculator.calculateTotalPrice(ticketTypeRequests));
        seatReservationService.reserveSeat(accountId,ticketRequestCalculator.calculateTotalSeats(ticketTypeRequests));
    }

}
