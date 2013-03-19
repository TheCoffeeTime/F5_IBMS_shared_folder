
import java.util.ArrayList;
import java.util.Date;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Nikita
 */
public class GenerateArrayOfShift {
    
    public static ArrayList<Shift> generateDuration(ArrayList<Duration> Durations)
    {
        database.openBusDatabase();
        int buses[] = BusInfo.getBuses();
        ArrayList<Shift> shiftArray = new ArrayList<Shift>();
        
        int numberOfBuses = 1;
                
        // Find required number of buses (maximum bus number in the List of Durations)
        for (int i = 0; i < Durations.size(); i++)
        {
            if (Durations.get(i).getBusNo() > numberOfBuses)
            {
                numberOfBuses = Durations.get(i).getBusNo();
            }
        }
        
        // Variables which store information about shift
        int startTime;
        int endTime;
        int shiftTime; 
        
        // Loop through all buses
        for (int j = 1; j <= numberOfBuses; j++)
        {
            // Reset shift information
            startTime = 0;
            endTime = 0;
            shiftTime = 0;

            // Loop through all durations            
            for (int i = 0; i < Durations.size(); i++)
            {
              // If current duration is current bus duration
              if (Durations.get(i).getBusNo() == j)
              {
                  // If it's first shift duration
                  if (startTime == 0)
                  {
                      // Set start time
                      startTime =  Durations.get(i).getStartTime();
                  }
                  // If we can add current duration to current shift (shift max time = 5h = 300m) 
                  if (shiftTime + Durations.get(i).getDuration() < 300)
                  {
                      // Add duration and update end Time
                      shiftTime = shiftTime + Durations.get(i).getDuration();
                      endTime = startTime + shiftTime;
                  }
                  else
                  {
                      // Shift is finished -> create object & store it in the Array
                      Shift tempShift = new Shift(0, buses[j-1],
                                                  startTime, endTime);
                      shiftArray.add(tempShift);
                      // Set start & end time of the next shift
                      startTime = Durations.get(i).getStartTime();
                      endTime = Durations.get(i).getEndTime();
                      // Find next shift duration so far
                      shiftTime = endTime - startTime;
                  }
              }
              
              // If it is this bus last duration - finish shift
              if ((i == Durations.size() - 1) && endTime != 0)
              {
                      Shift tempShift = new Shift(0, buses[j-1],
                                                  startTime, endTime);
                      shiftArray.add(tempShift);
              }
            }
        }
        return shiftArray;
    }
    
    // Test GenerateArrayOfShift
    public static void main(String[] args)
    {
        database.openBusDatabase();
        // To Nathan - there might be a bug in duration calculation in your code,
        // have a look at the last three lines of 358 bus durations output
        // in 2 of them duration is longer than 1400 m.
        
        // Nathan's test code from main in GenerateDuration
        Date date =  new Date(2013, 02, 13);
        ArrayList<Duration> duration358 = GenerateDuration.generateDuration(date, 67, 68);
       
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
        
        // Nikita's test code
        ArrayList<Shift> shiftArray = generateDuration(duration358);
        
        for (int i = 0; i < shiftArray.size(); i++)
        {
            System.out.println("Shift number: " + i + " | " +
                               "Start time: " + shiftArray.get(i).getTimeFrom() + " | " + 
                               "End time: " + shiftArray.get(i).getTimeTo() + " | " +
                               "Driver ID: " + shiftArray.get(i).getDriverID() + " | " +
                               "Bus ID: " + shiftArray.get(i).getBusID() + " | " +
                               "Shift duration: " + (shiftArray.get(i).getTimeTo() - 
                                                     shiftArray.get(i).getTimeFrom()));
        }
    }
}
