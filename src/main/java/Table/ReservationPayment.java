package Table;

public class ReservationPayment extends Reservation {
    private int amountCustomer;

    public ReservationPayment(){

    }

    public ReservationPayment(int amountCustomer) {
        this.amountCustomer = amountCustomer;
    }

    public ReservationPayment(String reservationCode, String customerID, String tourID, int amountCustomer ,String employeeName) {
        super(reservationCode, customerID, tourID, employeeName);
        this.amountCustomer = amountCustomer;
    }

    public int getAmountCustomer() {
        return amountCustomer;
    }

    public void setAmountCustomer(int amountCustomer) {
        this.amountCustomer = amountCustomer;
    }
}
