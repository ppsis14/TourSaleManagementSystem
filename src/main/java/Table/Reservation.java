package Table;

import java.util.List;

public class Reservation {
    private String reservationCode;
    private String tourID;
    private int amountCustomer;
    private String employeeName;
    private List<Customer> customerReservationList;


    public Reservation(String reservation_code, String tour_id, int amount_customer, String employee_name){
        this.reservationCode = reservationCode;
        this.tourID = tourID;
        this.amountCustomer = amountCustomer;
        this.employeeName = employeeName;
        this.customerReservationList = null;
    }

    public Reservation(String reservationCode, String tourID, int amountCustomer, String employeeName, List<Customer> customerReservationList) {
        this.reservationCode = reservationCode;
        this.tourID = tourID;
        this.amountCustomer = amountCustomer;
        this.employeeName = employeeName;
        this.customerReservationList = customerReservationList;
    }

    public String getReservationCode() {
        return reservationCode;
    }

    public void setReservationCode(String reservationCode) {
        this.reservationCode = reservationCode;
    }

    public String getTourID() {
        return tourID;
    }

    public void setTourID(String tourID) {
        this.tourID = tourID;
    }

    public int getAmountCustomer() {
        return amountCustomer;
    }

    public void setAmountCustomer(int amountCustomer) {
        this.amountCustomer = amountCustomer;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public List<Customer> getCustomerReservationList() {
        return customerReservationList;
    }

    public void setCustomerReservationList(List<Customer> customerReservationList) {
        this.customerReservationList = customerReservationList;
    }

    public void addCustomerToReservationList(Customer customer){
        customerReservationList.add(customer);

    }
}
