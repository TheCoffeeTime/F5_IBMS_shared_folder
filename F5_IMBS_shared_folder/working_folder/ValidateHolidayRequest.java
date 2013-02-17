import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Date;

public class ValidateHolidayRequest
{
  public static HolidayRequestExceptionMsg HREM 
                  = new HolidayRequestExceptionMsg();
  //Check if a given interval is possible to be a driver holiday
  //Written by: Oak. Last modified: 17/02/13
  public static boolean validateRequest
    (Date dateFrom, Date dateTo, int driverID)
  {
    //validate holiday lenght
    if(dateTo.getYear() > dateFrom.getYear()) //Over the year
    {
      System.out.println("OVER THE YEAR REQUEST");
      Date endOfThisYear
        = new Date(dateFrom.getYear(), 11, 31, 0, 0, 0);
      Date startOfNextYear 
        = new Date(dateTo.getYear(), 0, 1, 0, 0, 0);
      if(!validateHolidayLength(dateFrom, endOfThisYear))
        return false;
      else if(!validateHolidayLength(startOfNextYear, dateTo))
        return false;        
    }//if
    if(!validateHolidayLength(dateFrom, dateTo))
      return false;
    
    //check if max driver at each DATE interval
    if(!checkDateInterval(dateFrom, dateTo))
      return false;
      
    System.out.println("The request is valid");
    return true;  
  }//validateRequest
  
  //For a given interval, check if each date in the interval has
  //been booked by less than 10 drivers. 
  //Written by: Oak. Last modified: 17/02/13
  public static boolean checkDateInterval(Date dateFrom, Date dateTo)
  {
    GregorianCalendar currentCal = new GregorianCalendar
      (dateFrom.getYear(), dateFrom.getMonth(), 
      dateFrom.getDate(), 0, 0, 0);
    
    //WARNING: dateFrom cannot be reused after the loop.
    Date currentDate = dateFrom;
    do
    {
      System.out.println("Checking: " + currentDate.getDate());
      if(!dateAvailable(currentDate)) 
        return false;     
      
      currentCal.add(Calendar.DATE, 1);
      currentDate.setDate(currentCal.get(Calendar.DATE));
      currentDate.setMonth(currentCal.get(Calendar.MONTH));
      currentDate.setYear(currentCal.get(Calendar.YEAR));
    }while(!currentDate.after(dateTo));//While not after
    return true;
  }//checkDateInterval
  
  //Check if a given date is possible to be a driver holiday
  //Written by: Oak. Last modified: 17/02/13
  public static boolean dateAvailable(Date givenDate)
  {
    int[] driverIDs = DriverInfo.getDrivers();
    int notAvailable = 0;
    for (int i=0; i<driverIDs.length; i++)
    {
      if(!(DriverInfo.isAvailable(driverIDs[i], givenDate)))
        notAvailable++;     
    }//for
    if(notAvailable > 9) 
    {
      HREM.dateExceptionMsg = ("Date: " + givenDate.getDate() + "/" +
                              givenDate.getMonth() + "/" + 
                              givenDate.getYear() + 
                              " is NOT available");
      return false;
    }
    else 
      return true;
  }//dateAvailable
  
  //Check if the requested holiday length is valid
  //Written by: Oak. Last modified: 17/02/13
  public static boolean validateHolidayLength
    (Date dateFrom, Date dateTo)
  {
    /*
    int interval = Nathan method
    //How many days does the driver have left for his holiday
    int maxHoliday = DriverInto.getHolidaysTaken(driverID) - 25;
    if(interval > maxHoliday)
    {
      HREM.lengthExceptionMsg = 
        ("You have requested " + interval + 
         (interval > 1? "days ": "day ") + "for " + dateTo.getYear() +
         ", while the maximum is " + maxHoliday + 
         (maxHoliday > 1? "days ": "day "));    
      return false;
    }
    */
    return true;
  }//validateHolidayLength
  
  //Written by: Nathan. Last modified: ?
  //public static boolean checkDateLength()
  
}//class
