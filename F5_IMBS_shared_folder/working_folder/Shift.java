import java.util.Date;
/*
  An object which is the 'main' part of a roaster object. 
*/
public class Shift
{
  int driverID, busID, timeFrom, timeTo, routeID;
  Date date;
  
  public Shift(int dID, int bID, int TF, int TT, int r, Date d)
  {
    driverID = dID;
    busID = bID;
    timeFrom = TF;
    timeTo = TT;
    routeID = r;
    date = d;
  }//Shift
  
  public int getDriverID()
  {
    return driverID;
  }//getDriverID
  
  public void setDriverID(int dID)
  {
    driverID = dID;
  }//setDriverID
}//Shift
