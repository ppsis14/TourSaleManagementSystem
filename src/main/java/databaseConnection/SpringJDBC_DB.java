package databaseConnection;

import Table.*;
import com.sun.rowset.internal.Row;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static saleSystem.SaleManagementUtil.manageableDatabase;

public class SpringJDBC_DB implements ManageableDatabase {

    private JdbcTemplate jdbcTemplate;

    public SpringJDBC_DB(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean checkLogin(String username ,String password){

        String query = "Select * From employee Where Username = " + username + " and Password = " + password ;
        Employee employee = jdbcTemplate.queryForObject(query , new EmployeeRowMapper());
        if (employee == null){
            return false;
        }
        else
            return true;
    }

    @Override
    public String getNameEmployee(String employeeID) {
        String employeeName;
        String query = "Select * From employee Where Employee_ID = " + employeeID;

        Employee employee = jdbcTemplate.queryForObject(query,new EmployeeRowMapper());
        employeeName = employee.getTitleName()+" "+employee.getFirstName()+" "+employee.getLastName();

        return employeeName;
    }

    @Override
    public String getLastInvoiceNo(String invoiceType) {

        String lastInvoiceNo;
        List<Invoice> invoiceList = getAllInvoice(invoiceType);

        if (invoiceList.isEmpty())
            return "0";
        else {

            lastInvoiceNo = invoiceList.get(invoiceList.size() - 1).getInvoiceNo();
            return lastInvoiceNo;
        }
    }

    @Override
    public String getTourID(String tourName) {
        String tourID;

        //tourName=tourName.replaceAll(" ","% %");
        String query = "Select * From tour_package Where TourName = "+"'"+tourName+"'";

        TourPackage tourPackage = jdbcTemplate.queryForObject(query,new TourPackageRowMapper());
        tourID = String.valueOf(tourPackage.getTourID());

        return tourID;
    }


    @Override
    public void insertData(Customer customer) {

        String query = "insert into customer (Customer_ID,TitleNameTH, FirstNameTH, LastNameTH, TitleNameENG, FirstNameENG, LastNameENG ," +
                "Gender, Age, Occupation ,Date_of_birth, Passport_no, Expire_passport_date," +
                "Contact_address, Cell_phone, Home_Tel, Fax, Email_address, Disease, Food_allergy, Eat_beef, More_detail, HearAboutUs ) " +
                "values( ?, ? , ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Object[] data = new Object[]{
                customer.getCustomerID(), customer.getTitleNameTH(), customer.getFirstNameTH(), customer.getLastNameTH(),
                customer.getTitleNameENG(), customer.getFirstNameENG(), customer.getLastNameENG(),
                customer.getGender(), customer.getAge(), customer.getOccupation(),
                customer.getDateOfBirth(), customer.getPassport_no(),customer.getExp_passport(),
                customer.getContactAddress(), customer.getCell_phone(),customer.getHome_Tel(),
                customer.getFax(), customer.getEmail(), customer.getDisease(),
                customer.getFoodAllergy(), customer.getEatBeef(), customer.getMoreDetail(),
                customer.getHearAboutUs()};
        jdbcTemplate.update(query, data);
    }

    @Override
    public void updateData(Customer customer) {
        String updateQuery = "update customer set TitleNameTH = ? , FirstNameTH = ?, LastNameTH = ?, TitleNameENG = ?, FirstNameENG = ?, LastNameENG = ?," +
                "Gender = ?, Age = ?, Occupation = ? , Date_of_birth = ?, Passport_no = ?, Expire_passport_date = ?, Contact_address = ?,Cell_phone = ?, Home_Tel = ?, Fax = ?, " +
                "Email_address = ?, Disease = ?, Food_allergy = ?,Eat_beef = ?,More_detail = ?, HearAboutUs = ? where customer_id";
        Object[] data = new Object[]{
                customer.getTitleNameTH(), customer.getFirstNameTH(), customer.getLastNameTH(),
                customer.getTitleNameENG(), customer.getFirstNameENG(), customer.getLastNameENG(),
                customer.getGender(), customer.getAge(), customer.getOccupation(),
                customer.getDateOfBirth(), customer.getPassport_no(),customer.getExp_passport(),
                customer.getContactAddress(), customer.getCell_phone(),
                customer.getHome_Tel(), customer.getFax(), customer.getEmail(),
                customer.getDisease(), customer.getFoodAllergy(),
                customer.getEatBeef(), customer.getMoreDetail(), customer.getHearAboutUs()};
        jdbcTemplate.update(updateQuery, data);
    }

    @Override
    public void deleteData(Customer customer) {
        String deleteQuery = "Delete From customer Where customer_id = ?";
        jdbcTemplate.update(deleteQuery, customer.getCustomerID());
    }

    @Override
    public List<Customer> getAllCustomer() {
        String query = "select * from customer";

        List<Customer> customerList = jdbcTemplate.query(query, new CustomerRowMapper());
        return customerList;
    }

    @Override
    public Customer getOneCustomer(String name) {
        return null;
    }

    @Override
    public String getLastCustomerID() {
        List<Customer> listCustomer = getAllCustomer();

        String lastCustomerID;
        if(listCustomer.isEmpty())
            lastCustomerID = "0";
        else
            lastCustomerID =  listCustomer.get(listCustomer.size()-1).getCustomerID();

        return lastCustomerID;
    }

    @Override
    public void insertData(Reservation reservation) {
        String query = "insert into reservation_customer (Reservation_code, Customer_ID, Tour_ID, Employee_ID)" +
                "values(?, ?, ?, ?)";
        Object[] data = new Object[]{
                reservation.getReservationCode(),
                reservation.getCustomerID(),
                reservation.getTourID(),
                reservation.getEmployeeID()};
        jdbcTemplate.update(query, data);

    }

    @Override
    public void updateData(Reservation reservation) {

    }

    @Override
    public void deleteData(Reservation reservation) {
        String deleteQuery = "Delete From reservation Where reservation_code = ?";
        jdbcTemplate.update(deleteQuery, reservation.getReservationCode());


    }

    @Override
    public List<Reservation> getAllReservation() {
        return null;
    }

    @Override
    public void insertData(ReservationPayment reservationPayment) {
        String query = "insert into reservation_payment (Reservation_code, Customer_ID, Tour_ID,Amount_customer, Employee_ID)" +
                "values(?, ?, ?, ?,?)";
        Object[] data = new Object[]{
                reservationPayment.getReservationCode(),
                reservationPayment.getCustomerID(),
                reservationPayment.getTourID(),
                reservationPayment.getAmountCustomer(),
                reservationPayment.getEmployeeID()};
        jdbcTemplate.update(query, data);

    }

    @Override
    public void updateData(ReservationPayment reservationPayment) {

    }

    @Override
    public void deleteData(ReservationPayment reservationPayment) {

        String deleteQuery = "Delete From reservation_payment Where reservation_code = ?";
        jdbcTemplate.update(deleteQuery, reservationPayment.getReservationCode());
    }

    @Override
    public List<ReservationPayment> getAllReservationPayment() {
        String query = "Select * From reservation_payment";
        List<ReservationPayment> reservationPaymentList = jdbcTemplate.query(query , new ReservationPaymentRowMapper());

        return reservationPaymentList;
    }

    @Override
    public void insertData(Invoice invoice ,String invoiceType) {

        String query = "insert into " + invoiceType + " (Reservation_code, Invoice_no, Amount, Employee_name ,Invoice_status , Tour_ID)" +
                "values(?, ?, ?, ?, ?, ?)";

        Object[] data = new Object[]{
                invoice.getReservationCode(),
                invoice.getInvoiceNo(),
                invoice.getAmount(),
                invoice.getEmployeeName(),
                invoice.getInvoiceStatus(),
                invoice.getTourID()
        };
        jdbcTemplate.update(query, data);
    }

    @Override
    public void updateData(Invoice invoice ,String invoiceType) {

    }

    @Override
    public void deleteData(Invoice invoice, String invoiceType) {

        String deleteQuery = "Delete From " + invoiceType + " Where reservation_code = ?";
        jdbcTemplate.update(deleteQuery, invoice.getReservationCode());
    }

    @Override
    public List<Invoice> getAllInvoice(String invoiceType) {

        String query = "Select * From " + invoiceType ;
        List<Invoice> invoiceList = jdbcTemplate.query(query , new InvoiceRowMapper());

        return invoiceList;
    }

    @Override
    public List<Invoice> getAllInvoiceInTourName(String invoiceType, String tourName) {

        String tourID = getTourID(tourName);
        String query = "Select * From " + invoiceType +" Where Tour_ID = " + tourID;
        List<Invoice> invoiceList = jdbcTemplate.query(query , new InvoiceRowMapper());
        return invoiceList;
    }

    @Override
    public Employee getEmployeeLogin(String username , String password) {

        String query = "Select * From employee Where Username" + " = '" + username + "' and Password = '" + password +"'";
        Employee employee = jdbcTemplate.queryForObject(query , new EmployeeRowMapper());

        return employee;
    }

    @Override
    public String getLastReservationCode() {

        String  query  = "SELECT * FROM reservation_payment ";
        List<Reservation> reservationList = jdbcTemplate.query(query , new ReservationRowMapper());

        if(reservationList.isEmpty())
            return "0";
        else{
            String lastReservCode = reservationList.get(reservationList.size()-1).getReservationCode();
            return lastReservCode;
        }
    }

    @Override
    public List<TourPackage> getAllTourPackage() {

        String query = "Select * From tour_package";
        List<TourPackage> tourPackageList = jdbcTemplate.query(query , new TourPackageRowMapper());

        return tourPackageList;
    }

    class InvoiceRowMapper implements RowMapper<Invoice>{
        public Invoice mapRow(ResultSet rs, int i) throws SQLException {
            Invoice invoice = new Invoice(
                rs.getString("Reservation_code"),
                rs.getString("Invoice_no"),
                rs.getInt("Amount"),
                rs.getString("Employee_name"),
                rs.getString("Invoice_status"),
                rs.getString("Tour_ID"));
            return invoice;
        }
    }
    class TourPackageRowMapper implements RowMapper<TourPackage>{

        public TourPackage mapRow(ResultSet rs, int i) throws SQLException {
            TourPackage tourPackage = new TourPackage(
            rs.getInt("Tour_ID"),
            rs.getString("TourName"),
            rs.getInt("Price"),
            rs.getString("Departure_date"),
            rs.getString("Return_date"),
            rs.getString("Deposit_date"),
            rs.getString("Arrears_date"),
            rs.getInt("Amount"),
            rs.getInt("Available"),
            rs.getString("Status"));

            return tourPackage;
        }
    }

    class ReservationRowMapper implements RowMapper<Reservation>{

        public Reservation mapRow(ResultSet rs, int rowNum) throws SQLException {
            Reservation reservation = new Reservation(
                rs.getString("Reservation_code"),
                rs.getString("Customer_ID"),
                rs.getString("Tour_ID"),
                rs.getString("Employee_ID"));

            return  reservation;
        }
    }
    class ReservationPaymentRowMapper implements RowMapper<ReservationPayment>{

        public ReservationPayment mapRow(ResultSet rs, int rowNum) throws SQLException {
            ReservationPayment reservationPayment = new ReservationPayment(
                rs.getString("Reservation_code"),
                rs.getString("Customer_ID"),
                rs.getString("Tour_ID"),
                rs.getInt("Amount_customer"),
                rs.getString("Employee_ID"));

            return  reservationPayment;
        }
    }

    class EmployeeRowMapper implements RowMapper<Employee>{

        public Employee mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            Employee employee = new Employee(
                    resultSet.getString("Employee_ID"),
                    resultSet.getString("Username"),
                    resultSet.getString("Password"),
                    resultSet.getString("Position"),
                    resultSet.getString("TitleName"),
                    resultSet.getString("FirstName"),
                    resultSet.getString("LastName"),
                    resultSet.getString("Email"),
                    resultSet.getString("Mobile_num"));
            return employee;
        }
    }

    class CustomerRowMapper implements RowMapper<Customer> {
        public Customer mapRow(ResultSet rs, int rowNum)
                throws SQLException {
            Customer customer = new Customer(
                    rs.getString("customer_id"),
                    rs.getString("TitleNameTH"),
                    rs.getString("FirstNameTH"),
                    rs.getString("LastNameTH"),
                    rs.getString("TitleNameENG"),
                    rs.getString("FirstNameENG"),
                    rs.getString("lastNameENG"),
                    rs.getString("Gender"),
                    rs.getString("Age"),
                    rs.getString("Occupation"),
                    rs.getString("Date_of_birth"),
                    rs.getString("Passport_no"),
                    rs.getString("Expire_passport_date"),
                    rs.getString("Contact_Address"),
                    rs.getString("Cell_phone"),
                    rs.getString("Home_Tel"),
                    rs.getString("Fax"),
                    rs.getString("Email_address"),
                    rs.getString("Disease"),
                    rs.getString("Food_allergy"),
                    rs.getString("Eat_beef"),
                    rs.getString("More_detail"),
                    rs.getString("HearAboutUs"));

            return customer;
        }
    }
}

