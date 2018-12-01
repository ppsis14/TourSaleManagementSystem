package Table;

public class Invoice {

    private String reservationCode;
    private String invoiceNo;
    private int amount;
    private String employeeName;
    private String invoiceStatus;
    private String tourID;


    public Invoice(){

    }

    public Invoice(String reservationCode, String invoiceNo, int amount, String employeeName, String invoiceStatus, String tourID) {
        this.reservationCode = reservationCode;
        this.invoiceNo = invoiceNo;
        this.amount = amount;
        this.employeeName = employeeName;
        this.invoiceStatus = invoiceStatus;
        this.tourID = tourID;
    }

    public String getReservationCode() {
        return reservationCode;
    }

    public void setReservationCode(String reservationCode) {
        this.reservationCode = reservationCode;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getInvoiceStatus() {
        return invoiceStatus;
    }

    public void setInvoiceStatus(String invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
    }

    public String getTourID() {
        return tourID;
    }

    public void setTourID(String tourID) {
        this.tourID = tourID;
    }
}
