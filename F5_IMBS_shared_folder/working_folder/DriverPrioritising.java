
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * Class that will prioritise drivers.
 * @author nathantilsley
 */
public class DriverPrioritising {
    
    
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
    
    // method use date and number of drivers to calculate the correct groups to work on that day
    // along with the correct drivers
    // returns 2D array [group] [workers in that group]
    public static ArrayList<Integer> getDrivers(Date date, int numberOfDrivers)
    {
        int[] groupsWorkingOnDate = getGroupsInAWeekday(date);
        ArrayList<ArrayList<Integer>> groupDrivers = groupDrivers();
        
        int numberOfDriversPerGroup = numberOfDrivers / 5;
        
        if(numberOfDriversPerGroup == 0)
            numberOfDriversPerGroup = 1;
        
        // create an arraylist for each group. use array - 1 because index starts from 0
        
        
        // an arraylist of arraylists that contains arraylists of groups that can work
        ArrayList<ArrayList<Integer>> groupsThatCanWork = new ArrayList<ArrayList<Integer>>();
        
        // prioritise all the drivers in each group
        if(numberOfDrivers >= 5)
        {
          ArrayList<Integer> group1 = (ArrayList) groupDrivers.get(groupsWorkingOnDate[0] - 1); // returns an arraylist
          ArrayList<Integer> group2 = (ArrayList) groupDrivers.get(groupsWorkingOnDate[1] - 1); // returns an arraylist
          ArrayList<Integer> group3 = (ArrayList) groupDrivers.get(groupsWorkingOnDate[2] - 1); // returns an arraylist
          ArrayList<Integer> group4 = (ArrayList) groupDrivers.get(groupsWorkingOnDate[3] - 1); // returns an arraylist
          ArrayList<Integer> group5 = (ArrayList) groupDrivers.get(groupsWorkingOnDate[4] - 1); // returns an arraylist
          group1 = (ArrayList) prioritiseDriversInGroup(group1, numberOfDriversPerGroup, date); // returns an arraylist
          group2 = (ArrayList) prioritiseDriversInGroup(group2, numberOfDriversPerGroup, date); // returns an arraylist
          group3 = (ArrayList) prioritiseDriversInGroup(group3, numberOfDriversPerGroup, date); // returns an arraylist
          group4 = (ArrayList) prioritiseDriversInGroup(group4, numberOfDriversPerGroup, date); // returns an arraylist
          group5 = (ArrayList) prioritiseDriversInGroup(group5, numberOfDriversPerGroup, date); // returns an arraylist
          groupsThatCanWork.add(group1);
          groupsThatCanWork.add(group2);
          groupsThatCanWork.add(group3);
          groupsThatCanWork.add(group4);
          groupsThatCanWork.add(group5);
        }
        else if(numberOfDrivers == 4)
        {          
          ArrayList<Integer> group1 = (ArrayList) groupDrivers.get(groupsWorkingOnDate[0] - 1); // returns an arraylist
          ArrayList<Integer> group2 = (ArrayList) groupDrivers.get(groupsWorkingOnDate[1] - 1); // returns an arraylist
          ArrayList<Integer> group3 = (ArrayList) groupDrivers.get(groupsWorkingOnDate[2] - 1); // returns an arraylist
          ArrayList<Integer> group4 = (ArrayList) groupDrivers.get(groupsWorkingOnDate[3] - 1); // returns an arraylist
          group1 = (ArrayList) prioritiseDriversInGroup(group1, numberOfDriversPerGroup, date); // returns an arraylist
          group2 = (ArrayList) prioritiseDriversInGroup(group2, numberOfDriversPerGroup, date); // returns an arraylist
          group3 = (ArrayList) prioritiseDriversInGroup(group3, numberOfDriversPerGroup, date); // returns an arraylist
          group4 = (ArrayList) prioritiseDriversInGroup(group4, numberOfDriversPerGroup, date); // returns an arraylist
          groupsThatCanWork.add(group1);
          groupsThatCanWork.add(group2);
          groupsThatCanWork.add(group3);
          groupsThatCanWork.add(group4);
        }
        else if(numberOfDrivers == 3)
        {
          ArrayList<Integer> group1 = (ArrayList) groupDrivers.get(groupsWorkingOnDate[0] - 1); // returns an arraylist
          ArrayList<Integer> group2 = (ArrayList) groupDrivers.get(groupsWorkingOnDate[1] - 1); // returns an arraylist
          ArrayList<Integer> group3 = (ArrayList) groupDrivers.get(groupsWorkingOnDate[2] - 1); // returns an arraylist
          group1 = (ArrayList) prioritiseDriversInGroup(group1, numberOfDriversPerGroup, date); // returns an arraylist
          group2 = (ArrayList) prioritiseDriversInGroup(group2, numberOfDriversPerGroup, date); // returns an arraylist
          group3 = (ArrayList) prioritiseDriversInGroup(group3, numberOfDriversPerGroup, date); // returns an arraylist
          groupsThatCanWork.add(group1);
          groupsThatCanWork.add(group2);
          groupsThatCanWork.add(group3);

        }
        else if(numberOfDrivers == 2)
        {
          ArrayList<Integer> group1 = (ArrayList) groupDrivers.get(groupsWorkingOnDate[0] - 1); // returns an arraylist
          ArrayList<Integer> group2 = (ArrayList) groupDrivers.get(groupsWorkingOnDate[1] - 1); // returns an arraylist

          group1 = (ArrayList) prioritiseDriversInGroup(group1, numberOfDriversPerGroup, date); // returns an arraylist
          group2 = (ArrayList) prioritiseDriversInGroup(group2, numberOfDriversPerGroup, date); // returns an arraylist
          groupsThatCanWork.add(group1);
        }
        else if (numberOfDrivers == 1)
        {
           ArrayList<Integer> group1 = (ArrayList) groupDrivers.get(groupsWorkingOnDate[0] - 1); // returns an arraylist
           group1 = (ArrayList) prioritiseDriversInGroup(group1, numberOfDriversPerGroup, date); // returns an arraylist
           groupsThatCanWork.add(group1);
        }
        else
            return null;

        
        ArrayList<Integer> groups = new ArrayList<Integer>();
        
        for(int i = 0;i < groupsThatCanWork.size(); i++)
        {
            ArrayList<Integer> tempArray = groupsThatCanWork.get(i);
            for(int j =0; j < tempArray.size(); j++)
            {
                groups.add(tempArray.get(j));
            }
        }
        
        return groups;
        
         
    }
    
    // author Nikita
    public static ArrayList<Integer> prioritiseDriversInGroup(ArrayList<Integer> group, 
                                                              int numberOfDriversPerGroup, Date date)
    {
        int prioritisedDriver;
        ArrayList<Integer> groupPrioritised = new ArrayList<Integer>();
        
        while(numberOfDriversPerGroup != 0)
        {
          prioritisedDriver = calculatePriority(group, date);
          for (int i=0; i< group.size(); i++) {
          int val = group.get(i);
          //System.out.println(val);
            if (val == prioritisedDriver) {
                groupPrioritised.add(group.get(i));
                group.remove(i);
                break;
            }
          }
          numberOfDriversPerGroup--;
        }
        return groupPrioritised;
    }
    
    public static int[] getGroupsInAWeekday(Date date)
    {                
      Calendar c = Calendar.getInstance();
      c.setTime(date);
      int[] groupsInAWeekday = weekdaysGroups[c.get(Calendar.DAY_OF_WEEK) - 1];
      return groupsInAWeekday;     
    }
    
  // author Nikita
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
  
   // Search through database and prioritise a driver in that group
    public static int calculatePriority(ArrayList<Integer> group, Date date)
    {
        int chosenDriver = - 1;
        
        //
        //
        //
        ArrayList<Integer> available = new ArrayList<Integer>();
        
        // check which drivers in that group are available on that day
        for(int i = 0; i < group.size(); i++)
            if(DriverInfo.isAvailable(group.get(i)))
            {
               available.add(group.get(i));
            }
        
        //
        //
        //
        // check to see which driver has the least hours this week so far
        ArrayList<Integer> leastHoursInWeek = new ArrayList<Integer>();
        
        // start at the max and go down
        int leastHoursWorkedThisWeek = 50;
        
        // find the least hours worked by a driver in that group
        for(int i = 0; i < available.size(); i++)
            if(DriverInfo.getHoursThisWeek(available.get(i)) < leastHoursWorkedThisWeek)
            {
               leastHoursWorkedThisWeek = DriverInfo.getHoursThisWeek(available.get(i));
            }
        
        // check which drivers in that group are available on that day
        for(int i = 0; i < available.size(); i++)
            if(DriverInfo.getHoursThisWeek(available.get(i)) == leastHoursWorkedThisWeek)
            {
               leastHoursInWeek.add(available.get(i));
            }
        
        //
        //
        // Then check which driver has had the least hours this year?
        
        // an array for drivers with least hours
        ArrayList<Integer> leastHoursInYear = new ArrayList<Integer>();
          
        if(leastHoursInWeek.size() > 1)
        {
          
        
          // start at the max hours possible in a year (50 * 52)
          int leastHoursWorkedThisYear = 2600;
        
          // find the least hours worked by a driver in that group
          for(int i = 0; i < leastHoursInWeek.size(); i++)
            if(DriverInfo.getHoursThisYear(leastHoursInWeek.get(i)) < leastHoursWorkedThisYear)
            {
               leastHoursWorkedThisYear = DriverInfo.getHoursThisWeek(leastHoursInWeek.get(i));
            }
        
          // check which drivers that have worked the least hours this year
          for(int i = 0; i < leastHoursInWeek.size(); i++)
            if(DriverInfo.getHoursThisWeek(leastHoursInWeek.get(i)) == leastHoursWorkedThisYear)
            {
               leastHoursInYear.add(leastHoursInWeek.get(i));
            }
        }
        else
         chosenDriver = leastHoursInWeek.get(0);
        
        //
        //
        //
        // if not one driver left, pick a random one, as all are even
        if(leastHoursInYear.size() > 1 && leastHoursInYear.size() > 1)
        {
          // make a random index, multiply it by array length - 1 because its an index
          int randomIndex = (leastHoursInYear.size() - 1) * (int)Math.random();
          chosenDriver = leastHoursInYear.get(randomIndex);
        }
        else
         chosenDriver = leastHoursInYear.get(0);
        
        return chosenDriver;
    }
    
    public static void main (String[] args)
    {
      Date test = new Date(2013, 2, 9);
      ArrayList<Integer> groupsThatCanWork = new ArrayList<Integer>();
      groupsThatCanWork = getDrivers(test, 4);
      
      for (int i = 0; i < groupsThatCanWork.size(); i++)
      {
          System.out.println("Driver that can work: " + groupsThatCanWork.get(i));
      }
      int[] dayGroups = getGroupsInAWeekday(test);
      SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM");
      System.out.println("Groups for " + dateFormat.format(test) + ": ");
      
      for (int i = 0; i < dayGroups.length; i++)
      {
          System.out.print(dayGroups[i] + ", ");
      }
        
    }
}
