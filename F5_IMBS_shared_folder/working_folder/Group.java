
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Date;
import java.util.List;



/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Nikita
 */
public class Group {
  
  // Groups timetable for a week
  private static int [][] weekdaysGroups = {
          {1,4,5,6,7},
          {1,2,5,6,7},
          {1,2,3,6,7},
          {1,2,3,4,7},
          {1,2,3,4,5},
          {2,3,4,5,6},
          {3,4,5,6,7}
      };  
  
  // Returns an array of driver groups for a specific Date
  
  public static int[] getGroupsInAWeekday(Date date)
  {                
      Calendar c = Calendar.getInstance();
      c.setTime(date);
      int[] groupsInAWeekday = weekdaysGroups[c.get(Calendar.DAY_OF_WEEK) - 1];
      return groupsInAWeekday;     
  }
  
  // Returns a 2 dimensional ArrayList [Group][List of drivers ID]
  public static ArrayList<ArrayList<Integer>> groupDrivers()
  {
      database.openBusDatabase();
      ArrayList<ArrayList<Integer>> groups = new ArrayList<ArrayList<Integer>>();
      
      for(int i = 0; i < 7; i++)
      {
        groups.add(new ArrayList());
      }
      
      int [] drivers = DriverInfo.getDrivers();
          for (int i = 0; i < drivers.length; i++)
          {
        groups.get((drivers[i] - 2012) % 7).add(drivers[i]);
      }
      return groups;
  }
  
  public static ArrayList<Integer> getDrivers(Date date, int numberOfDrivers)
  {   
      ArrayList<Integer> Drivers = new ArrayList<Integer>();
      
      int[] dayGroups = getGroupsInAWeekday(date);
      
      ArrayList<ArrayList<Integer>> groups = new ArrayList<ArrayList<Integer>>();
      groups = groupDrivers();
      
      int addedDrivers = 0;
      // Print Drivers in all Groups
      boolean is = false;
      for (int i = 0; i < 7; i++)
      {
          for (int j = 0; j < dayGroups.length; j++)
          {
              if (dayGroups[j] == i)
              {
                  is = true;
                  break;
              }    
              else
                  is = false;
          }
          if (is ==true )
          {
            System.out.print("Group Number " + i + ": ");
        
            for( int j = 0; j < groups.get(i).size(); j++)
            {
              if (addedDrivers < numberOfDrivers)
              {
                System.out.print(groups.get(i).get(j) +", ");
                Drivers.add(groups.get(i).get(j));
                addedDrivers++;
              }
            }
            System.out.println();
          }
      }
      return Drivers;
  }
  
  // Some tests
  public static void main(String[] av)
  {     
      ArrayList<ArrayList<Integer>> groups = new ArrayList<ArrayList<Integer>>();
      groups = groupDrivers();
      /*
      // Print Drivers in all Groups
      for (int i = 0; i < 7; i++)
      {
          System.out.print("Group Number " + i + ": ");
          for( int j = 0; j < groups.get(i).size(); j++)
          {
              System.out.print(groups.get(i).get(j) +", ");
          }
          System.out.println();    
      }
      
      // Print Groups for a specific Date
      Date test = new Date(2013, 2, 9);
      int[] dayGroups = getGroupsInAWeekday(test);
      SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM");
      System.out.println("Groups for " + dateFormat.format(test) + ": ");
      for (int i = 0; i < dayGroups.length; i++)
      {
          System.out.print(dayGroups[i] + ", ");
      }
      * */
       Date test = new Date(2013, 2, 9);
       System.out.println("Test get driver:");
       ArrayList<Integer> Drivers = new ArrayList<Integer>();
       Drivers = getDrivers(test, 40);
       System.out.println("Number of drivers: "+ Drivers.size());
       for( int j = 0; j < Drivers.size(); j++)
       {
         System.out.print(Drivers.get(j) +", ");
       }
  }   
}
