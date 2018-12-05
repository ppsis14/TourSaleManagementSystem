package junitTest;

import Table.TourPackage;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

class TourPackageTest {

    TourPackage japanTour;
    TourPackage indiaTour;
    TourPackage koreaTour;

    @BeforeEach
    void setUp() {
        //public TourPackage(String tourID, String tourName, int price,String departureDate,String returnDate,
        //String depositDate, String arrearsDate, int amount, int available, String status)
        japanTour = new TourPackage("JPN-4D3N-000001","JAPAN TOKYO WINTER TOUR",35000,"23-11-2018","27-11-2018","3-11-2018","2-11-2018",50,50,"open");
        indiaTour = new TourPackage("IND-5D4N-000002","INDIA GREAT EXPERIENCE TOUR",30000,"29-05-2018","3-06-2018","9-05-2018","19-05-2018",70,70,"open");
        koreaTour = new TourPackage("KOR-10D9N-000004","KOREA CHEJU ISLAND TOUR",50000,"20-07-2018","30-07-2018","31-06-2018","10-07-2018",100,100,"open");
    }
}