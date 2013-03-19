/**
 *
 * @author Thanakorn, nathantilsley, Nikita, Henryka
 */
 
import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.ArrayList;
import java.util.List;

public class RoasterGenerator
{
  public static SystemMsg systemMsg = new SystemMsg();
   
  /*
    A method that used to generate a roaster given DateFrom and DateTo. 
    A Roaster Object will be created from the dateFrom and dateTo given
  */ 
  public static Roaster GenerateRoaster(Date dateFrom, Date dateTo)
  {
    int interval = ValidateHolidayRequest.calculateInterval(dateFrom, dateTo);
    Shift[][] shift = new Shift[interval][];
    
    //Use calendar to increase the date
    GregorianCalendar currentCal = new GregorianCalendar
      (dateFrom.getYear(), dateFrom.getMonth(), 
      dateFrom.getDate(), 0, 0, 0);    
    
    //A date object to be used in the for loop below. It get the value from 
    //the calendar. 
    Date currentDate = new Date(dateFrom.getYear(), dateFrom.getMonth(), 
                                dateFrom.getDate(), 0, 0, 0);
       
    for(int i = 0; i < interval; i++)
    {
      shift[i] = GenerateADayRoaster(currentDate);
      currentCal.add(Calendar.DATE, 1);
      currentDate.setDate(currentCal.get(Calendar.DATE));
      currentDate.setMonth(currentCal.get(Calendar.MONTH));
      currentDate.setYear(currentCal.get(Calendar.YEAR));
    }//for
    
    //Create a roaster object. 
    Roaster roaster = new Roaster
      (currentCal.get(Calendar.MONTH), currentCal.get(Calendar.YEAR), shift);
    return roaster;
    
  }//GenerateRoaster
  
  /*
    A method that generate an array of shifts. This represents a roaster for 
    one day. 
  */
  public static Shift[] GenerateADayRoaster(Date date)
  {
    ArrayList<Duration> d = GenerateDuration.generateDuration(date, 65);
    return AssignDriverToShift(GenerateArrayOfShift(d));
  }//GenerateADayRoaster
  
  
  /*A Method to assigne drivers to shifts.
   * author: Henryka Reszka
   */
  public static Shift[] AssignDriverToShift(Shift[] driverShift, Date date)
  {
    //create an array of drivers  
    int[] drivers = new int[70];
    int noOfDriver=0; 
    
    //in this loop going through driverShift array to
    //calculate the numbers of drivers needed for a given date
    for (int i=0; i<driverShift.length; i++)
    {
       if (drivers[driverShift[i].getDriverID()]==0);
       {
          drivers[driverShift[i].getDriverID()]++;
          noOfDriver++;
       } 
    }  
    
    //get drivers'ID
    ArrayList<Integer> driverIDs = DriverPrioritising.getDrivers(date, noOfDriver);
    
    //assign drivers'ID to a driverShift
    for (int i=0; i<driverShift.length; i++)
    {
        driverShift[i].setDriverID(driverIDs.get(driverShift[i].getDriverID()));
    }//for
    
    return driverShift;       
  } //AssignDriverToShift       
}//class
