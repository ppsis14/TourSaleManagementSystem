package databaseConnection;

import Table.Customer;
import Table.Employee;
import Table.Reservation;
import com.sun.rowset.internal.Row;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SpringJDBC_DB implements ManageableDatabase {

    private JdbcTemplate jdbcTemplate;

    public SpringJDBC_DB(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean checkLogin(String username ,String password){

        String query = "Select * From employee Where Username" + " = '" + username + "' and Password = '" + password +"'";
        Employee employee = jdbcTemplate.queryForObject(query , new EmployeeRowMapper());
        if (employee == null){
            return false;
        }
        else
            return true;
    }

    @Override
    public void insertData(Customer customer) {

        String query = "insert into customer (TitleNameTH, FirstNameTH, LastNameTH, TitleNameENG, FirstNameENG, LastNameENG ," +
                "Gender, Age, Occupation ,Date_of_birth, Passport_no, Expire_passport_date," +
                "Contact_address, Cell_phone, Home_Tel, Fax, Email_address, Disease, Food_allergy, Eat_beef, More_detail, HearAboutUs ) " +
                "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Object[] data = new Object[]{
                customer.getTitleNameTH(), customer.getFirstNameTH(), customer.getLastNameTH(),
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

    @Override //*****not complete
    public Customer getOneCustomer(String name) {
        String query = "select FirstName from customer Where id = ?";
        return null;
    }

    @Override
    public void insertData(Reservation reservation) {

    }

    @Override
    public void updateData(Reservation reservation) {

    }

    @Override
    public void deleteData(Reservation reservation) {

    }

    @Override
    public List<Reservation> getAllReservation() {
        return null;
    }

    @Override
    public Employee getEmployeeLogin(String username , String password) {

        String query = "Select * From employee Where Username" + " = '" + username + "' and Password = '" + password +"'";
        Employee employee = jdbcTemplate.queryForObject(query , new EmployeeRowMapper());

        return employee;
    }

    @Override
    public String getLastReservationCode() {

        String  query  = "SELECT * FROM reservation ";
        List<Reservation> reservationList = jdbcTemplate.query(query , new ReservationRowMapper());

        if(reservationList.isEmpty())
            return "0";
        else{
            String lastReservCode = reservationList.get(reservationList.size()-1).getReservationCode();
            return lastReservCode;
        }
    }


    class ReservationRowMapper implements RowMapper<Reservation>{

        public Reservation mapRow(ResultSet rs, int rowNum) throws SQLException {
            Reservation reservation = new Reservation(
                rs.getString("Reservation_code"),
                rs.getString("Tour_ID"),
                rs.getInt("Amount_customer"),
                rs.getString("Employee_name"));

            return  reservation;
        }
    }

    class EmployeeRowMapper implements RowMapper<Employee>{

        public Employee mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            Employee employee = new Employee(
                    resultSet.getString("Employee_ID"),
                    resultSet.getString("Username"),
                    resultSet.getString("Password"),
                    resultSet.getString("Position"),
                    resultSet.getString("FirstName"),
                    resultSet.getString("lastName"),
                    resultSet.getString("Email"),
                    resultSet.getString("Mobile_num"));
            return employee;
        }
    }

    class CustomerRowMapper implements RowMapper<Customer> {
        public Customer mapRow(ResultSet rs, int rowNum)
                throws SQLException {
            Customer customer = new Customer(
                    rs.getInt("customer_id"),
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
                    rs.getString("Passport_nope"),
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

