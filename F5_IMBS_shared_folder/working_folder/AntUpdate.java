import java.util.Date;
import java.text.*;
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
        boolean x = false;
        //number of requested days off
        int daysOff = 20;
        //Driver ID given when driver logs in to system. 
        int driverID = 2012;
        
        //converts a string to a date object
        String stringDate = "18/02/2013";
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try
        {
            Date date = dateFormat.parse(stringDate);
       
            //if the request is vaild. update the database.
            if(x == true)
            {
                DriverInfo.setHolidaysTaken(driverID, daysOff);
                DriverInfo.setAvailable(driverID, date, false);
                /* Print message in interface text field
                 * System.out.println("Holiday request Successful.");
                 */
            }
            else{
                /*Print message in interface text field
                 * System.out.println("Holiday request could not be completed");
                 */
            }
         }
        catch(java.text.ParseException e){
            System.out.println("Date parsing exception.");
        }
      
    }
}
