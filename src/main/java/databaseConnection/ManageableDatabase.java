package databaseConnection;

import Table.*;

import java.util.List;

public interface ManageableDatabase {
    void insertData(Customer customer);
    void updateData(Customer customer);
    void deleteData(Customer customer);
    List<Customer> getAllCustomer();
    Customer getOneCustomer(String name);
    String getLastCustomerID();

    void insertData(Reservation reservation);
    void updateData(Reservation reservation);
    void deleteData(Reservation reservation);
    List<Reservation> getAllReservation();

    void insertData(ReservationPayment reservationPayment);
    void updateData(ReservationPayment reservationPayment);
    void deleteData(ReservationPayment reservationPayment);
    List<ReservationPayment> getAllReservationPayment();

    void insertData(Invoice invoice,String invoiceType);
    void updateData(Invoice invoice,String invoiceType);
    void deleteData(Invoice invoice,String invoiceType);
    List<Invoice> getAllInvoice(String invoiceType);
    List<Invoice> getAllInvoiceInTourName(String invoiceType,String tourName);


    Employee getEmployeeLogin(String username, String password);
    String getLastReservationCode();
    boolean checkLogin(String username ,String password);
    String getNameEmployee(String employeeID);
    String getLastInvoiceNo(String invoiceType);
    String getTourID(String tourName);

    List<TourPackage> getAllTourPackage();
}
