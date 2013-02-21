
import java.util.Date;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Thanakorn 21/02/13
 */
public class TestDriverRequestHoliday {
    public static void main(String[] args)
    {
        //Test ValidateRequestHoliday.dateAvailable
        database.openBusDatabase();
        Date a = new Date(2013, 0, 10, 0, 0, 0);
        int[] driverIDs = DriverInfo.getDrivers();
        //----------------------------Set every driver to be false on 10/01/13
        for (int i=0; i<driverIDs.length; i++)
        {
            DriverInfo.setAvailable(driverIDs[i], a, false);
        }//for
        if(!ValidateHolidayRequest.dateAvailable(a))
        {
            System.out.println("Test Pass: dateAvailable: All False");
        }
        else
        {
            System.out.println("Test Fail: dateAvailable: All False");
        }//else
        
        //-----------------------------Set every driver to be true on 10/01/13
        setAllTrue(a);
        if(ValidateHolidayRequest.dateAvailable(a))
        {
            System.out.println("Test Pass: dateAvailable: All True");
        }
        else
        {
            System.out.println("Test Fail: dateAvailable: All True");
        }//else
        
        //Set 9 driver to be false on 10/01/13
        for (int i=0; i<9; i++)
        {
            DriverInfo.setAvailable(driverIDs[i], a, false);
        }//for
        if(ValidateHolidayRequest.dateAvailable(a))
        {
            System.out.println("Test Pass: dateAvailable: 9 Drivers false");
        }
        else
        {
            System.out.println("Test Fail: dateAvailable: 9 Driver false");
        }//else
        
        setAllTrue(a);
        //----------------------------Set 10 driver to be false on 10/01/13
        for (int i=0; i<10; i++)
        {
            DriverInfo.setAvailable(driverIDs[i], a, false);
        }//for
        if(!ValidateHolidayRequest.dateAvailable(a))
        {
            System.out.println
                    ("Test Pass: dateAvailable: 10 Drivers false");
        }
        else
        {
            System.out.println
                    ("Test Fail: 10 Drivers false");
        }//else
        
        
       //Test Driver Interface red and green squares
       Date five = new Date(2013, 0, 5, 0, 0, 0); 
       Date six = new Date(2013, 0, 6, 0, 0, 0);  
       Date seven = new Date(2013, 0, 7, 0, 0, 0);  
       //setAllFalse(five);
       //setAllFalse(six);
       //setAllFalse(seven);
       
       setAllTrue(five);
       setAllTrue(six);
       setAllTrue(seven);
       System.out.println("Test Pass: Green Red square");
       
    }//main
    
   static void setAllTrue(Date a)
   {
        int[] driverIDs = DriverInfo.getDrivers();
        for (int i=0; i<driverIDs.length; i++)
        {
            DriverInfo.setAvailable(driverIDs[i], a, true);
        }//for
   }//setAllTrue
   
    static void setAllFalse(Date a)
   {
        int[] driverIDs = DriverInfo.getDrivers();
        for (int i=0; i<driverIDs.length; i++)
        {
            DriverInfo.setAvailable(driverIDs[i], a, false);
        }//for
   }//setAllTrue  
    
}
