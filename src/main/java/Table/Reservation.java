package Table;

import java.util.List;

public class Reservation {
    private String reservationCode;
    private String customerID;
    private String tourID;
    private String employeeID;

    public Reservation(){
    }

    public Reservation(String reservationCode, String customerID, String tourID, String employeeID) {
        this.reservationCode = reservationCode;
        this.customerID = customerID;
        this.tourID = tourID;
        this.employeeID = employeeID;
    }

    public String getReservationCode() {
        return reservationCode;
    }

    public void setReservationCode(String reservationCode) {
        this.reservationCode = reservationCode;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getTourID() {
        return tourID;
    }

    public void setTourID(String tourID) {
        this.tourID = tourID;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }
}
