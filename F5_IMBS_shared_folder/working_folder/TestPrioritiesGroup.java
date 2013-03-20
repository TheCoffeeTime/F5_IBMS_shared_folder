/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author nathantilsley
 */
import java.util.ArrayList;
import java.util.Date;

public class TestPrioritiesGroup {
    
    public static void main(String[] args)
    {
        // get an array for rach day
        int[] mondayGroupArray = DriverPrioritising.getGroupsInAWeekday(new Date(2013, 02, 10));
        int[] tuesdayGroupArray = DriverPrioritising.getGroupsInAWeekday(new Date(2013, 02, 12));
        int[] wednesdayGroupArray = DriverPrioritising.getGroupsInAWeekday(new Date(2013, 02, 12));
        int[] thursdayGroupArray = DriverPrioritising.getGroupsInAWeekday(new Date(2013, 02, 13));
        int[] fridayGroupArray = DriverPrioritising.getGroupsInAWeekday(new Date(2013, 02, 14));
        int[] saturdayGroupArray = DriverPrioritising.getGroupsInAWeekday(new Date(2013, 02, 15));
        int[] sundayGroupArray = DriverPrioritising.getGroupsInAWeekday(new Date(2013, 02, 16));
        
        
        // PRIINT OUT ALL THE ARRAYS, AND ALL THE GROUPS WORKING ON EACH DAY OF THE WEEK
        
        System.out.print("Groups working on monday: ");
        for(int i= 0; i < mondayGroupArray.length; i++)
            System.out.print(mondayGroupArray[i] + ", ");
        System.out.println();
        
        System.out.print("Groups working on tueday: ");
        for(int i= 0; i < tuesdayGroupArray.length; i++)
            System.out.print(tuesdayGroupArray[i] + ", ");
        System.out.println();
        
        System.out.print("Groups working on wednesday: ");
        for(int i= 0; i < wednesdayGroupArray.length; i++)
            System.out.print(wednesdayGroupArray[i] + ", ");
        System.out.println();
        
        System.out.print("Groups working on thursday: ");
        for(int i= 0; i < thursdayGroupArray.length; i++)
            System.out.print(thursdayGroupArray[i] + ", ");
        System.out.println();
        
        System.out.print("Groups working on friday: ");
        for(int i= 0; i < fridayGroupArray.length; i++)
            System.out.print(fridayGroupArray[i] + ", ");
        System.out.println();
        
        System.out.print("Groups working on saturday: ");
        for(int i= 0; i < saturdayGroupArray.length; i++)
            System.out.print(saturdayGroupArray[i] + ", ");
        System.out.println();
        
        System.out.print("Groups working on sunday: ");
        for(int i= 0; i < sundayGroupArray.length; i++)
            System.out.print(saturdayGroupArray[i] + ", ");
        System.out.println();
        
        //Some test numbers
        int numberOfDriversPerGroupTest1 = 1;
        int numberOfDriversPerGroupTest2 = 2;
        int numberOfDriversPerGroupTest3 = 3;
        int numberOfDriversPerGroupTest4 = 4;
        int numberOfDriversPerGroupTest5 = 5;
        int numberOfDriversPerGroupTest6 = 6;
        int numberOfDriversPerGroupTest7 = 7;
        
        // get every group and all the drivers in that group
        ArrayList<ArrayList<Integer>> groups = DriverPrioritising.groupDrivers();
        
        // print off every driver in their corresponding group
        for(int i = 0; i < groups.size(); i++)
        {
            ArrayList<Integer> groupRow = groups.get(i);
            System.out.println("This is group number: " + (i + 1) + " the groups"
                    + "the drivers in the group are");
            for(int j = 0; j < groupRow.size(); j++)
                System.out.print(groupRow.get(j) + ", ");
            
            System.out.println();
            System.out.println();
        }
        
        // make arraylist of all drivers working in on a day, after they ahve been prioritised
        // so only drivers who have worked the least horus should be working
        ArrayList<Integer> group1 = DriverPrioritising.prioritiseDriversInGroup(groups.get(0), numberOfDriversPerGroupTest1, new Date(2013, 02, 10));
        ArrayList<Integer> group2 = DriverPrioritising.prioritiseDriversInGroup(groups.get(1), numberOfDriversPerGroupTest2, new Date(2013, 02, 10));
        ArrayList<Integer> group3 = DriverPrioritising.prioritiseDriversInGroup(groups.get(2), numberOfDriversPerGroupTest3, new Date(2013, 02, 10));
        ArrayList<Integer> group4 = DriverPrioritising.prioritiseDriversInGroup(groups.get(3), numberOfDriversPerGroupTest4, new Date(2013, 02, 10));
        ArrayList<Integer> group5 = DriverPrioritising.prioritiseDriversInGroup(groups.get(4), numberOfDriversPerGroupTest5, new Date(2013, 02, 10));
        ArrayList<Integer> group6 = DriverPrioritising.prioritiseDriversInGroup(groups.get(5), numberOfDriversPerGroupTest6, new Date(2013, 02, 10));
        ArrayList<Integer> group7 = DriverPrioritising.prioritiseDriversInGroup(groups.get(6), numberOfDriversPerGroupTest7, new Date(2013, 02, 10));
        
        
        // print off all the drivers that are working in each group
        System.out.println("This is the drivers in group 1 that are working");
        for(int i = 0; i < group1.size(); i++)
        {
            System.out.print(group1.get(i) + ", ");
        }
        System.out.println();
        
        System.out.println("This is the drivers in group 2 that are working");
        for(int i = 0; i < group2.size(); i++)
        {
            System.out.print(group2.get(i) + ", ");
        }
        System.out.println();
        
        System.out.println("This is the drivers in group 3 that are working");
        for(int i = 0; i < group3.size(); i++)
        {
            System.out.print(group3.get(i) + ", ");
        }
        System.out.println();
     
        
        System.out.println("This is the drivers in group 4 that are working");
        for(int i = 0; i < group4.size(); i++)
        {
            System.out.print(group4.get(i) + ", ");
        }
        System.out.println();
        
        System.out.println("This is the drivers in group 5 that are working");
        for(int i = 0; i < group5.size(); i++)
        {
            System.out.print(group5.get(i) + ", ");
        }
        System.out.println();
        
        System.out.println("This is the drivers in group 6 that are working");
        for(int i = 0; i < group6.size(); i++)
        {
            System.out.print(group6.get(i) + ", ");
        }
        System.out.println();
        
        System.out.println("This is the drivers in group 7 that are working");
        for(int i = 0; i < group7.size(); i++)
        {
            System.out.print(group7.get(i) + ", ");
        }
        System.out.println();
        
    }
    
}
