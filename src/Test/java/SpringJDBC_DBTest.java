import Table.Customer;
import Table.Employee;
import databaseConnection.ManageableDatabase;
import databaseConnection.SpringJDBC_DB;
import org.junit.jupiter.api.Test;
import tourSaleManagementSystemUtil.DisplayGUIUtil;

import static org.junit.jupiter.api.Assertions.*;
import static tourSaleManagementSystemUtil.DisplayGUIUtil.manageableDatabase;
import static tourSaleManagementSystemUtil.DisplayGUIUtil.manageableDatabaseTest;

class SpringJDBC_DBTest {


    Employee emp = new Employee("EMP000004","5910406451","5910406451","sale","Miss","Wipawadee","Monkhut","wipawadee.mo@ku.th","0831653904");
    @Test
    public void testGetNameEmployeeFromDatabase(){
        assertEquals("Miss Thikamporn Simud",manageableDatabaseTest.getNameEmployee("EMP000001"));
        assertEquals("Miss Piyawad Namtachan",manageableDatabaseTest.getNameEmployee("EMP000002"));
        assertEquals("Miss Kewalee Boonyamarn",manageableDatabaseTest.getNameEmployee("EMP000003"));
        assertEquals("Miss Wipawadee Monkhut",manageableDatabaseTest.getNameEmployee("EMP000004"));
        assertEquals("- - -",manageableDatabaseTest.getNameEmployee("EMP000005"));
    }

    @Test
    public void testGetTourID(){
        assertEquals("GER-5D4N-000001",manageableDatabaseTest.getTourID("ROMANTIC OF GERMANY-CZECH"));
        assertEquals("EUR-5D4N-000002",manageableDatabaseTest.getTourID("EXCLUSIVE OF EAST EUROPE"));
        assertEquals("UAE-5D4N-000003",manageableDatabaseTest.getTourID("DUBAI EXCLUSIVE"));
        assertEquals("JPN-4D3N-000004",manageableDatabaseTest.getTourID("JAPAN NEW YEAR EXCLUSIVE"));
    }

    @Test
    public void testGetTourPrice(){
        assertEquals(175500,manageableDatabaseTest.getTourPrice("GER-5D4N-000001"));
        assertEquals(198800,manageableDatabaseTest.getTourPrice("EUR-5D4N-000002"));
        assertEquals(219900,manageableDatabaseTest.getTourPrice("UAE-5D4N-000003"));
        assertEquals(115900,manageableDatabaseTest.getTourPrice("JPN-4D3N-000004"));
    }

    @Test
    public void testGetAvailableSeatByTourID(){
        assertEquals(38,manageableDatabaseTest.getAvailableByTourID("GER-5D4N-000001"));
        assertEquals(50,manageableDatabaseTest.getAvailableByTourID("EUR-5D4N-000002"));
        assertEquals(0,manageableDatabaseTest.getAvailableByTourID("UAE-5D4N-000003"));
        assertEquals(45,manageableDatabaseTest.getAvailableByTourID("JPN-4D3N-000004"));
    }

    @Test
    public void testGetNameCustomer(){
        assertEquals("Miss Pilin Boonchom",manageableDatabaseTest.getNameCustomer("CS00000013"));
        assertEquals("Mr. Khalid Robinson",manageableDatabaseTest.getNameCustomer("CS00000001"));
        assertEquals("Mr. Shawn Mendes",manageableDatabaseTest.getNameCustomer("CS00000002"));
        assertEquals("Mr. Charlie Puth",manageableDatabaseTest.getNameCustomer("CS00000003"));
        assertEquals("Miss Mali Songlin",manageableDatabaseTest.getNameCustomer("CS00000017"));
        assertEquals("Miss Sirirattana Monkhut",manageableDatabaseTest.getNameCustomer("CS00000020"));
        assertEquals("Mr. Piyachat Namarong",manageableDatabaseTest.getNameCustomer("CS00000023"));
    }

    @Test
    public void testGetFirstNameCustomer(){
        assertEquals("Pilin",manageableDatabaseTest.getFirstNameCustomer("CS00000013"));
        assertEquals("Khalid",manageableDatabaseTest.getFirstNameCustomer("CS00000001"));
        assertEquals("Shawn",manageableDatabaseTest.getFirstNameCustomer("CS00000002"));
        assertEquals("Charlie",manageableDatabaseTest.getFirstNameCustomer("CS00000003"));
        assertEquals("Mali",manageableDatabaseTest.getFirstNameCustomer("CS00000017"));
        assertEquals("Sirirattana",manageableDatabaseTest.getFirstNameCustomer("CS00000020"));
    }

}