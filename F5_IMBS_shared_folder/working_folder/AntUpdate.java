import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/*
 * Third part of the algorithm. 
 * Updates the database with when the driver has sucessfully requested 
 * time off for holiday. All variables are hard coded snubs and will recieve
 * actual values from other classes.
 */

/**
 *
 * @author Anthony
 */
public class AntUpdate 
{
    public static void main(String[] args)
    {
        //boolen value state if holiday request is valid or not
        boolean x = true;
        //number of requested days off
        int daysOff = 5;
        //Driver ID given when driver logs in to system. 
        int driverID = 2012;
        database.openBusDatabase();
        Date dateTo = new Date(2013, 1,10,0,0,0);
        Date dateFrom = new Date(2013,1,15,0,0,0);
     
        updateHolidayRequest(dateTo, dateFrom, driverID, x);
    }
    
    public static void updateHolidayRequest
            (Date dateTo, Date dateFrom, int driverID, boolean x)
    {
        //if the request is vaild. update the database.
        if(x == true)
        {
            GregorianCalendar currentCal = new GregorianCalendar
                (dateFrom.getYear(), dateFrom.getMonth(), 
                 dateFrom.getDate(), 0, 0, 0);
            
            Date start = new Date
                    (dateTo.getYear(),dateTo.getMonth(),dateTo.getDay(),0,0, 0);
            
            Date end = new Date
                    (dateFrom.getYear(),dateFrom.getMonth(),
                    dateFrom.getDay(),0,0,0);
            
            int daysOff = (int) ((end.getTime() - start.getTime())/ 
                    (1000 *60*60*24));
            //System.out.println("days:" + daysOff);
            
            DriverInfo.setHolidaysTaken(driverID, daysOff);
               
               
            Date currentDate = dateFrom;
            do
            {
                DriverInfo.setAvailable(driverID, currentDate, false);
                
                currentCal.add(Calendar.DATE, 1);
                currentDate.setDate(currentCal.get(Calendar.DATE));
                currentDate.setMonth(currentCal.get(Calendar.MONTH));
                currentDate.setYear(currentCal.get(Calendar.YEAR));
                
            }while(!currentDate.after(dateTo));
                
                // Print message in interface text field
                  System.out.println("Holiday request Successful....");
                 //
            }
            else{
                /*Print message in interface text field
                 * System.out.println("Holiday request could not be completed");
                 */
            }
         }

      
    }

