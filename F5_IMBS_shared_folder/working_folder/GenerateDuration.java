
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author nathantilsley
 */
public class GenerateDuration {   
    
    // this method creates an array of durations, 
    // see duration class for attributes of object, is used for 383 adn 384 services to return durations
    // also used for 358out and 358back to return durations for them
    public static ArrayList<Duration> generateDuration(Date date, int route)
    {
        database.openBusDatabase();
        int noOfServices = TimetableInfo.getNumberOfServices(route, date);
        int[] serviceTimes;
        int[] services = TimetableInfo.getServices(route, TimetableInfo.timetableKind(date));
        ArrayList<Duration> durationArray = new ArrayList<Duration>();
  
        
        int noOfBuses = 0;
        
        // index represents how many services or journeys the bus has made
        int busIndex = 1;
        int[] serviceTimes2 = TimetableInfo.getServiceTimes(route, TimetableInfo.timetableKind(date), 0);
        
        // cretae a temporary duration to compare with other durations to calculate whether start times over run
        Duration tempDuration = new Duration(serviceTimes2[0], serviceTimes2[serviceTimes2.length - 1], noOfBuses, busIndex);
        for(int i = 0; i < noOfServices; i++)
        {
            serviceTimes = TimetableInfo.getServiceTimes(route, TimetableInfo.timetableKind(date), i);
            Duration tempDuration2 = new Duration(serviceTimes[0], serviceTimes[serviceTimes.length - 1], noOfBuses, busIndex);
            
            // when the end time of a service runs into the start time of another service we need another bus
            if(tempDuration.getEndTime() > tempDuration2.getStartTime())
            {
                noOfBuses++;
            }
            // otherwise we dont need another bus so reset bus number and change the comparing duration
            else 
            {
                noOfBuses = 1;
                busIndex++;
                tempDuration = tempDuration2;
                    
            }
            
            Duration newDuration = new Duration(serviceTimes[0], serviceTimes[serviceTimes.length - 1], noOfBuses, busIndex);
            durationArray.add(newDuration);
     
            
        }
        return durationArray;
        
    }
    
    // this method specificaly for 358 service, combines the 358 out journey with the 358 back journey
    // along with correct bus numbers for each journey
    public static ArrayList<Duration> generateDuration(Date date, int route358Out, int route358Back)
    {
        database.openBusDatabase();
        
        ArrayList<Duration> duration358Out = generateDuration(date, route358Out);
        ArrayList<Duration> duration358Back = generateDuration(date, route358Back);
        

        ArrayList<Duration> durationArray = new ArrayList<Duration>();
        
        int noOfBuses = 0;
        int maxBuses = 0;
        int busIndex = 1;
        
        int k = 1;
        
        for(int i = 0; i < duration358Out.size(); i++)
        {      
            durationArray.add(duration358Out.get(i));
            if(i < 19)
            durationArray.add(duration358Back.get(i));
             
        }
        
        Duration tempDuration = durationArray.get(0);
        
        // same principle as in other generateDuration method,
        // when endtime of service over laps we need more buses
        for(int i = 0; i < durationArray.size(); i++)
        {
            Duration tempDuration2 = durationArray.get(i);
            
            if(tempDuration.getEndTime() > tempDuration2.getStartTime())
            {
                 noOfBuses++;
            }
            else 
            {
                noOfBuses = 1;
                busIndex++;
                tempDuration = durationArray.get(i);
                    
            }
            durationArray.get(i).setIndex(busIndex);
            durationArray.get(i).setBusNo(noOfBuses);
        }
           return durationArray; 
       }
        
    
    
     public static void main(String[] args)
     {
        database.openBusDatabase();
        Date date =  new Date(2013, 02, 13);
        ArrayList<Duration> duration358 = generateDuration(date, 67, 68);
        ArrayList<Duration> duration383 = generateDuration(date, 65);
        ArrayList<Duration> duration384 = generateDuration(date, 66);
       
        System.out.println("358 bus durations:");
        
        for(int i = 0; i < duration358.size(); i++)
        {
              System.out.println("Start Time " + duration358.get(i).getHours(duration358.get(i).getStartTime()) + 
                                  duration358.get(i).getMinutes(duration358.get(i).getStartTime()) +" : " + "End Time " +
                                 + duration358.get(i).getHours(duration358.get(i).getEndTime()) + 
                                  duration358.get(i).getMinutes(duration358.get(i).getEndTime()) + " : "
                                 + "Duration " + duration358.get(i).getDuration() + " : " +
                                 "Index "      + duration358.get(i).getIndex() + " : " + 
                                 "Bus No " + duration358.get(i).getBusNo());
   
        }
        
        System.out.println();
        System.out.println("383 bus durations:");
        
        for(int i = 0; i < duration358.size(); i++)
        {
              System.out.println("Start Time " + duration383.get(i).getHours(duration383.get(i).getStartTime()) + 
                                  duration383.get(i).getMinutes(duration383.get(i).getStartTime()) +" : " + "End Time " +
                                 + duration383.get(i).getHours(duration383.get(i).getEndTime()) + 
                                  duration383.get(i).getMinutes(duration383.get(i).getEndTime()) + " : "
                                 + "Duration " + duration383.get(i).getDuration() + " : " +
                                 "Index "      + duration383.get(i).getIndex() + " : " + 
                                 "Bus No " + duration383.get(i).getBusNo());
   
        }
        
        System.out.println();
        System.out.println("384 bus durations:");
        
        for(int i = 0; i < duration358.size(); i++)
        {
              System.out.println("Start Time " + duration384.get(i).getHours(duration384.get(i).getStartTime()) + 
                                  duration384.get(i).getMinutes(duration384.get(i).getStartTime()) +" : " + "End Time " +
                                 + duration384.get(i).getHours(duration384.get(i).getEndTime()) + 
                                  duration384.get(i).getMinutes(duration384.get(i).getEndTime()) + " : "
                                 + "Duration " + duration384.get(i).getDuration() + " : " +
                                 "Index "      + duration384.get(i).getIndex() + " : " + 
                                 "Bus No " + duration384.get(i).getBusNo());
   
        }
    }
}
