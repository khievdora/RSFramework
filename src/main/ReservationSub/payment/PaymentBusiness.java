package main.ReservationSub.payment;

/**
 * Created by Gize on 4/20/2017.
 */
public class PaymentBusiness {
    IPayment payment;

    public void setPayment(IPayment pay) {
        this.payment = pay;
    }

    public void pay() {
        payment.makePayment();
    }

}
