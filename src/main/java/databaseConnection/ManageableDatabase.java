package databaseConnection;

import Table.*;

import java.util.List;

public interface ManageableDatabase {


    Employee getEmployeeLogin(String username, String password);
    boolean checkLogin(String username ,String password);
    String getNameEmployee(String employeeID);


    TourPackage getTourPackage(String tourID);
    String getTourID(String tourName);
    int getTourPrice(String tourID);
    int getAvailableByTourID(String tourID);
    List<TourPackage> getAllTourPackage();
    void updateAvailableData(String tourID ,int availableSeat);

    void insertData(Customer customer);
    void updateData(Customer customer);
    void deleteData(Customer customer);
    List<Customer> getAllCustomer();
    Customer getOneCustomer(String customerID);
    String getLastCustomerID();
    String getNameCustomer(String customerID);

    void insertData(Reservation reservation);
    void updateData(Reservation reservation);
    void deleteData(Reservation reservation);
    void deleteDataByReservCode(String reservationCode);

    List<Reservation> getAllReservationByTourID(String tourID);

    void insertData(ReservationPayment reservationPayment);
    void updateData(ReservationPayment reservationPayment);
    void deleteData(ReservationPayment reservationPayment);
    List<ReservationPayment> getAllReservationPaymentByTourID(String tourID);
    String getLastReservationPaymentCode();

    void insertData(Invoice invoice,String invoiceType);
    void updateData(Invoice invoice,String invoiceType);
    void deleteData(Invoice invoice,String invoiceType);
    List<Invoice> getAllInvoice(String invoiceType);
    List<Invoice> getAllInvoiceInTourName(String invoiceType,String tourName);
    String getLastInvoiceNo(String invoiceType);

    void insertData(Receipt receipt,String receiptType);
    void updateData(Receipt receipt,String receiptType);
    void deleteData(Receipt receipt,String receiptType);
    List<Receipt> getAllReceipt(String receiptType);
    List<Receipt> getAllReceiptInTourName(String receiptType,String tourName);
    String getLastReceiptNo(String receiptType);






}
