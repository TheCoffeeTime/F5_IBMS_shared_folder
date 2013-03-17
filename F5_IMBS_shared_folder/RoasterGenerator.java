/**
 *
 * @author Thanakorn, nathantilsley, Nikita, Henryka
 */
 
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

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
    Duration[] d = GenerateArrayOfDuration(date);
    return GenerateArrayOfShift(d);
  }//GenerateADayRoaster
}//class
