package databaseConnection;

import Table.Customer;
import Table.Employee;
import Table.Reservation;

import java.util.List;

public interface ManageableDatabase {
    void insertData(Customer customer);
    void updateData(Customer customer);
    void deleteData(Customer customer);
    List<Customer> getAllCustomer();
    Customer getOneCustomer(String name);

    void insertData(Reservation reservation);
    void updateData(Reservation reservation);
    void deleteData(Reservation reservation);
    List<Reservation> getAllReservation();

    Employee getEmployeeLogin(String username, String password);
    String getLastReservationCode();
    boolean checkLogin(String username ,String password);
}
