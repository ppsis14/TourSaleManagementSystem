package Table;

public class TourPackage {
    private int tourID;
    private  String tourName;
    private  String price;
    private  String departureDate;
    private  String returnDate;
    private  String depositDate;
    private  String arrearsDate;
    private  int amount;
    private  int available;

    public TourPackage(){}
    public TourPackage(String tourName, String price, String departureDate, String returnDate, String depositDate, String arrearsDate, int amount, int available) {
        this.tourName = tourName;
        this.price = price;
        this.departureDate = departureDate;
        this.returnDate = returnDate;
        this.depositDate = depositDate;
        this.arrearsDate = arrearsDate;
        this.amount = amount;
        this.available = available;
    }

    public String getTourName() {
        return tourName;
    }

    public void setTourName(String tourName) {
        this.tourName = tourName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public String getDepositDate() {
        return depositDate;
    }

    public void setDepositDate(String depositDate) {
        this.depositDate = depositDate;
    }

    public String getArrearsDate() {
        return arrearsDate;
    }

    public void setArrearsDate(String arrearsDate) {
        this.arrearsDate = arrearsDate;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }
}
