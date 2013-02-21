
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Anthony 21/02/13
 */
public class Update {
        
    //By Anthony 21/02/13
    public static void updateHolidayRequest
            (Date dateFrom, Date dateTo, int driverID)
    {
        int holidayLength = 
            ValidateHolidayRequest.calculateInterval(dateFrom, dateTo);
        int oldLength = DriverInfo.getHolidaysTaken(driverID);
        System.out.println("Holiday length update:" + holidayLength+oldLength);
        DriverInfo.setHolidaysTaken(driverID, holidayLength+oldLength);
        
        GregorianCalendar currentCal = new GregorianCalendar
            (dateFrom.getYear(), dateFrom.getMonth(), 
             dateFrom.getDate(), 0, 0, 0);
        
        //WARNING: dateFrom cannot be reused after this method. 
        Date currentDate = dateFrom;
        do
        {
            System.out.println("Current date:" + currentDate.getDate());
            DriverInfo.setAvailable(driverID, currentDate, false);
            currentCal.add(Calendar.DATE, 1);
            currentDate.setDate(currentCal.get(Calendar.DATE));
            currentDate.setMonth(currentCal.get(Calendar.MONTH));
            currentDate.setYear(currentCal.get(Calendar.YEAR));
            System.out.println("UPDATED!");

        }while(!currentDate.after(dateTo));

        // Print message in interface text field
    }//UpdateHolidayRequest
   
}
