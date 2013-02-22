
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
        //-------------------------------------------------------------------
        //Test ValidateRequestHoliday.dateAvailable
        database.openBusDatabase();
        Date a = new Date(2013, 0, 10, 0, 0, 0);
        int[] driverIDs = DriverInfo.getDrivers();
        
        
        //Set every driver to be false on 10/01/13
        //except himself
        setAllFalse(a);
        DriverInfo.setAvailable(2012, a, true);
        if(ValidateHolidayRequest.dateAvailable(a, 2012) == 0)
        {
            System.out.println("Test Pass: dateAvailable: All False");
        }
        else
        {
            System.out.println("Test Fail: dateAvailable: All False");
        }//else
        
        //Set every driver to be true on 10/01/13
        setAllTrue(a);
        if(ValidateHolidayRequest.dateAvailable(a, 2012) == 1)
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
        if(ValidateHolidayRequest.dateAvailable(a, 2012) == 1)
        {
            System.out.println("Test Pass: dateAvailable: 9 Drivers false");
        }
        else
        {
            System.out.println("Test Fail: dateAvailable: 9 Driver false");
        }//else
        
        setAllTrue(a);
        //Set 10 driver to be false on 10/01/13
        for (int i=0; i<10; i++)
        {
            DriverInfo.setAvailable(driverIDs[i], a, false);
        }//for
        if(ValidateHolidayRequest.dateAvailable(a, 2012) == 0)
        {
            System.out.println
                    ("Test Pass: dateAvailable: 10 Drivers false");
        }
        else
        {
            System.out.println
                    ("Test Fail: 10 Drivers false");
        }//else
        
        //Set every driver to be false on 10/01/13
        setAllFalse(a);
        if(ValidateHolidayRequest.dateAvailable(a, 2012) == 2)
        {
            System.out.println("Test Pass: dateAvailable: All False");
        }
        else
        {
            System.out.println("Test Fail: dateAvailable: All False");
        }//else
       //-------------------------------------------------------------------
       //Test Driver Interface red and green squares
       Date five = new Date(2013, 0, 5, 0, 0, 0); 
       Date six = new Date(2013, 0, 6, 0, 0, 0);  
       Date seven = new Date(2013, 0, 7, 0, 0, 0);  
       setAllFalse(five);
       setAllFalse(six);
       setAllFalse(seven);
       
       //setAllTrue(five);
       //setAllTrue(six);
       //setAllTrue(seven);
       
       DriverInfo.setAvailable(2013, five , true);
       DriverInfo.setAvailable(2013, six , true);
       DriverInfo.setAvailable(2013, seven , true);
       System.out.println("Test Pass: Green Red square");
       //--------------------------------------------------------------------
       //Test ValidateRequestHoliday.validateRequest()
       Date b, c, d, e;
       a = new Date(2013, 0, 10, 0, 0, 0);
       b = new Date(2013, 0, 11, 0, 0, 0);
       c = new Date(2013, 0, 12, 0, 0, 0);
       d = new Date(2013, 0, 13, 0, 0, 0);
       e = new Date(2013, 0, 14, 0, 0, 0);
       //One day true
       setAllTrue(a);
       if(ValidateHolidayRequest.validateRequest(a, a, 2013))
       {
           System.out.println("Test Pass: validateRequest: One day true");
       }
       else
       {
           System.out.println("Test Fail: validateRequest: One day true");
       }
       //5 days all true
       setAllTrue(a);
       setAllTrue(b);
       setAllTrue(c);
       setAllTrue(d);
       setAllTrue(e);
       if(ValidateHolidayRequest.validateRequest(a, e, 2013))
       {
           System.out.println("Test Pass: validateRequest: 5 days all true");
       }
       else
       {
           System.out.println("Test Fail: validateRequest: 5 days all true");
       }
       
       //4th date is false the rest are true
       setAllFalse(d);
       if(!ValidateHolidayRequest.validateRequest(a, e, 2013))
       {
           System.out.println("Test Pass: validateRequest: 4 trues, 1 fault");
       }
       else
       {
           System.out.println("Test Fail: validateRequest: 4 trues, 1 fault");
       }//else
       
       //Date from is greater than date to
       if(!ValidateHolidayRequest.validateRequest(e, a, 2013))
       {
           System.out.println("Test Pass: start date is after finish date");
       }
       else
       {
           System.out.println("Test Fail: start date is after finish date");
       }//else      
       
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
