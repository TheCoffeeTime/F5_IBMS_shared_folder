/**
 *
 * @author Thanakorn, nathantilsley, Nikita, Henryka
 */
 
import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.ArrayList;
import java.util.List;

/**
 * A Roaster generator class used to create a roaster object. This is the
 * main class of generator a roaster call many other classes including
 * GenerateDuration, GenerateArrayOfShit, and DriverPrioritising. 
 * @author Thanakorn
 */
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
    ArrayList<ArrayList<Shift>> shift = new ArrayList<ArrayList<Shift>>();
    
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
      shift.set(i, GenerateADayRoaster(currentDate));
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
  public static ArrayList<Shift> GenerateADayRoaster(Date date)
  {
    ArrayList<Duration> duration358Weekday = GenerateDuration.generateDuration(date, 67, 68);
    ArrayList<Duration> duration383Weekday = GenerateDuration.generateDuration(date, 65);
    ArrayList<Duration> duration384Weekday = GenerateDuration.generateDuration(date, 66);
    
    AvailableBuses buses = new AvailableBuses();
    ArrayList<Shift> shift358 = 
            GenerateArrayOfShift.generateDuration(duration358Weekday, buses);
    ArrayList<Shift> shift383 = 
            GenerateArrayOfShift.generateDuration(duration383Weekday, buses);
    ArrayList<Shift> shift384 = 
            GenerateArrayOfShift.generateDuration(duration384Weekday, buses);
    
    AssignDriverToShift(shift358, date);
    AssignDriverToShift(shift383, date);
    AssignDriverToShift(shift384, date);
   
    //Combine all the shift for different route into one. 
    shift358.addAll(shift383.size() - 1, shift384);
    shift358.addAll(shift384.size() - 1, shift384);
    return shift358;
  }//GenerateADayRoaster
  
  
  /*A Method to assigne drivers to shifts.
   * @author Henryka Reszka
   */
  public static ArrayList<Shift> AssignDriverToShift(ArrayList<Shift> driverShift, Date date)
  {
    AssignDummyDriverToShift(driverShift);
    //create an array of drivers  
    int[] drivers = new int[70];
    int noOfDriver=0; 
    
    //in this loop going through driverShift array to
    //calculate the numbers of drivers needed for a given date
    for (int i=0; i<driverShift.size(); i++)
    {
       if (drivers[driverShift.get(i).getDriverID()]==0);
       {
          drivers[driverShift.get(i).getDriverID()]++;
          noOfDriver++;
       } 
    }  
    
    //get drivers'ID
    ArrayList<Integer> driverIDs = DriverPrioritising.getDrivers(date, noOfDriver);
    
    //assign drivers'ID to a driverShift
    for (int i=0; i<driverShift.size(); i++)
    {
        driverShift.get(i).setDriverID(driverIDs.get(driverShift.get(i).getDriverID()));
    }//for
    
    return driverShift;       
  } //AssignDriverToShift  
  
  /* Assign dummy driver to a bus to be used in the AssignDriverToShift method
   * This create an array of workingDriver(so far) and keep extending it.
   * @author Henryka Reszka
   */
  public static void AssignDummyDriverToShift(ArrayList<Shift> driverShift)
  {
      ArrayList<WorkingDriver> workingDriver = new ArrayList<WorkingDriver>();
      //For each shift, assign a driver to it. 
      int driverFakeID = 0;
      for(int i = 0; i < driverShift.size(); i++)
      {
          boolean shiftAssign = false;
          //For each driver in the workingDriver array
          for(int j = 0; j < workingDriver.size(); j++)
          {
            //If driver can still work (< 10 hours) & have a break
            if(workingDriver.get(j).totalHr + driverShift.get(i).getDuration() <= 600
               && driverShift.get(i).timeFrom - workingDriver.get(j).currentEndTime >= 60)
            {
                shiftAssign = true; //So that no new driver needed. 
                //Re-calculate the total number of hours work so far by this driver
                workingDriver.get(j).totalHr += driverShift.get(i).getDuration();
                //Re-calculate the currentEndTime of the selected working driver
                workingDriver.get(j).currentEndTime = driverShift.get(i).getTimeTo();
                //Set that driver shift to contain a fake driver id
                driverShift.get(i).setDriverID(workingDriver.get(j).id);
                break; //Break out if found a driver for this shift. 
            }//if
          }//for
          //If shift not assigned. If no driver assigned from the above for loop.
          if(!shiftAssign)
          {
              //Create a new working driver
              WorkingDriver newDriver = new WorkingDriver();
              //Initialise the new working driverID, totalHR, and currentEndTime
              newDriver.id = driverFakeID;
              newDriver.totalHr = driverShift.get(i).getDuration();
              newDriver.currentEndTime = driverShift.get(i).getTimeTo();
              //Set that driver shift to contain a fake driver id
              driverShift.get(i).setDriverID(newDriver.id);
              driverFakeID++;
              //Add the newly created workingDriver to the workingDriver array. 
              workingDriver.add(newDriver);
          }//if
      }//for
  }
}//class
