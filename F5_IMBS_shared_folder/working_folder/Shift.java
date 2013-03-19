import java.util.Date;
/*
  An object which is the 'main' part of a roaster object. 
*/
public class Shift
{
  int driverID, busID, timeFrom, timeTo, routeID;
  Date date;
  
  public Shift(int dID, int bID, int TF, int TT) //, int r, Date d
  {
    driverID = dID;
    busID = bID;
    timeFrom = TF;
    timeTo = TT;
    //routeID = r; // Date & route are used to generete ArrayOfDurations and are not stored in it,
    //date = d;    // I've commented them as I've used only ArrayOfDurations to generate ArrayOfShifts
  }//Shift
  
  public int getDriverID()
  {
    return driverID;
  }//getDriverID
  
  public void setDriverID(int dID)
  {
    driverID = dID;
  }//setDriverID
  
  public int getTimeFrom()
  {
      return timeFrom;
  }
  
  public int getTimeTo()
  {
      return timeTo;
  }
  
  public int getBusID()
  {
      return busID;
  }
}//Shift
