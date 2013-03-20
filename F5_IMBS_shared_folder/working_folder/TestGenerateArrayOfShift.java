
import java.util.ArrayList;
import java.util.Date;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * Make an array of shifts for each kind of timetable for each service
 * so in total 9 array
 * @author nathantilsley
 */
public class TestGenerateArrayOfShift {
   
    public static void main(String[] args)
    {
        // generate some days to test
        Date weekdayDate =  new Date(2013, 02, 13);
        Date saturdayDate = new Date(2013, 02, 15);
        Date sundayDate = new Date(2013, 02, 16);
        
        // SO GENERATE DURATIONS FOR EACH A GIVEN DATE AND FOR EACH ROUTE, THE GENERATE AN ARRAY OF SHIFTS FOR EACH CORRESPONDING DURATION
        // WE DO THIS 3 TIMES, ONE FOR EACH ROUTE
        // THEN THE WHOLE PROCESS IN TOTOAL 3 TIMES
        
        ArrayList<Duration> duration358Weekday = GenerateDuration.generateDuration(weekdayDate, 67, 68);
        ArrayList<Duration> duration383Weekday = GenerateDuration.generateDuration(weekdayDate, 65);
        ArrayList<Duration> duration384Weekday = GenerateDuration.generateDuration(weekdayDate, 66);
        
        ArrayList<Shift> shift358Weekday = GenerateArrayOfShift.generateDuration(duration358Weekday);
        ArrayList<Shift> shift383Weekday = GenerateArrayOfShift.generateDuration(duration383Weekday);
        ArrayList<Shift> shift384Weekday = GenerateArrayOfShift.generateDuration(duration384Weekday);
        
        ArrayList<Duration> duration358Saturday = GenerateDuration.generateDuration(saturdayDate, 67, 68);
        ArrayList<Duration> duration383Saturday = GenerateDuration.generateDuration(saturdayDate, 65);
        ArrayList<Duration> duration384Saturday = GenerateDuration.generateDuration(saturdayDate, 66);
        
        ArrayList<Shift> shift358Saturday = GenerateArrayOfShift.generateDuration(duration358Saturday);
        ArrayList<Shift> shift383Saturday = GenerateArrayOfShift.generateDuration(duration383Saturday);
        ArrayList<Shift> shift384Saturday = GenerateArrayOfShift.generateDuration(duration384Saturday);
        
        ArrayList<Duration> duration358Sunday = GenerateDuration.generateDuration(sundayDate, 67, 68);
        ArrayList<Duration> duration383Sunday = GenerateDuration.generateDuration(sundayDate, 65);
        ArrayList<Duration> duration384Sunday = GenerateDuration.generateDuration(sundayDate, 66);
        
        ArrayList<Shift> shift358Sunday = GenerateArrayOfShift.generateDuration(duration358Sunday);
        ArrayList<Shift> shift383Sunday = GenerateArrayOfShift.generateDuration(duration383Sunday);
        ArrayList<Shift> shift384Sunday = GenerateArrayOfShift.generateDuration(duration384Sunday);
        
        
        // LOTS AND LOTS OF FOR LOOPS TO PRINT OUT THE ARRAY IF SHIFTS
        // FOR ALL THE 9 SHIFT ARRAYS THAT HAVE BEEN GENERATED
        System.out.println("This is the shitfts for 358 service for a week day a saturday and a sunday");  
        System.out.println("The week day date is " + weekdayDate.toString());
        System.out.println();
        for (int i = 0; i < shift358Weekday.size(); i++)
        {
            System.out.println("Shift number: " + i + " | " +
                               "Start time: " + shift358Weekday.get(i).getTimeFrom() + " | " + 
                               "End time: " + shift358Weekday.get(i).getTimeTo() + " | " +
                               "Driver ID: " + shift358Weekday.get(i).getDriverID() + " | " +
                               "Bus ID: " + shift358Weekday.get(i).getBusID() + " | " +
                               "Shift duration: " + (shift358Weekday.get(i).getTimeTo() - 
                                                     shift358Weekday.get(i).getTimeFrom()));
        }
        
        System.out.println("The saturday date is " + saturdayDate.toString());
        System.out.println();
        for (int i = 0; i < shift358Saturday.size(); i++)
        {
            System.out.println("Shift number: " + i + " | " +
                               "Start time: " + shift358Saturday.get(i).getTimeFrom() + " | " + 
                               "End time: " + shift358Saturday.get(i).getTimeTo() + " | " +
                               "Driver ID: " + shift358Saturday.get(i).getDriverID() + " | " +
                               "Bus ID: " + shift358Saturday.get(i).getBusID() + " | " +
                               "Shift duration: " + (shift358Saturday.get(i).getTimeTo() - 
                                                     shift358Saturday.get(i).getTimeFrom()));
        }
        
        System.out.println("The sunday date is  " + sundayDate.toString());
        System.out.println();
        for (int i = 0; i < shift358Sunday.size(); i++)
        {
            System.out.println("Shift number: " + i + " | " +
                               "Start time: " + shift358Sunday.get(i).getTimeFrom() + " | " + 
                               "End time: " + shift358Sunday.get(i).getTimeTo() + " | " +
                               "Driver ID: " + shift358Sunday.get(i).getDriverID() + " | " +
                               "Bus ID: " + shift358Sunday.get(i).getBusID() + " | " +
                               "Shift duration: " + (shift358Sunday.get(i).getTimeTo() - 
                                                     shift358Sunday.get(i).getTimeFrom()));
        }
        
        System.out.println("This is the shitfts for 383 service for a week day a saturday and a sunday");  
        System.out.println("The week day date is " + weekdayDate.toString());
        System.out.println();
        for (int i = 0; i < shift383Weekday.size(); i++)
        {
            System.out.println("Shift number: " + i + " | " +
                               "Start time: " + shift383Weekday.get(i).getTimeFrom() + " | " + 
                               "End time: " + shift383Weekday.get(i).getTimeTo() + " | " +
                               "Driver ID: " + shift383Weekday.get(i).getDriverID() + " | " +
                               "Bus ID: " + shift383Weekday.get(i).getBusID() + " | " +
                               "Shift duration: " + (shift383Weekday.get(i).getTimeTo() - 
                                                     shift383Weekday.get(i).getTimeFrom()));
        }
        
        System.out.println("The saturday date is " + saturdayDate.toString());
        System.out.println();
        for (int i = 0; i < shift383Saturday.size(); i++)
        {
            System.out.println("Shift number: " + i + " | " +
                               "Start time: " + shift383Saturday.get(i).getTimeFrom() + " | " + 
                               "End time: " + shift383Saturday.get(i).getTimeTo() + " | " +
                               "Driver ID: " + shift383Saturday.get(i).getDriverID() + " | " +
                               "Bus ID: " + shift383Saturday.get(i).getBusID() + " | " +
                               "Shift duration: " + (shift383Saturday.get(i).getTimeTo() - 
                                                     shift383Saturday.get(i).getTimeFrom()));
        }
        
        System.out.println("The sunday date is  " + sundayDate.toString());
        System.out.println();
        for (int i = 0; i < shift383Sunday.size(); i++)
        {
            System.out.println("Shift number: " + i + " | " +
                               "Start time: " + shift383Sunday.get(i).getTimeFrom() + " | " + 
                               "End time: " + shift383Sunday.get(i).getTimeTo() + " | " +
                               "Driver ID: " + shift383Sunday.get(i).getDriverID() + " | " +
                               "Bus ID: " + shift383Sunday.get(i).getBusID() + " | " +
                               "Shift duration: " + (shift383Sunday.get(i).getTimeTo() - 
                                                     shift383Sunday.get(i).getTimeFrom()));
        }
        
      
        System.out.println("This is the shitfts for 384 service for a week day a saturday and a sunday");  
        System.out.println("The week day date is " + weekdayDate.toString());
        System.out.println();
        for (int i = 0; i < shift384Weekday.size(); i++)
        {
            System.out.println("Shift number: " + i + " | " +
                               "Start time: " + shift384Weekday.get(i).getTimeFrom() + " | " + 
                               "End time: " + shift384Weekday.get(i).getTimeTo() + " | " +
                               "Driver ID: " + shift384Weekday.get(i).getDriverID() + " | " +
                               "Bus ID: " + shift384Weekday.get(i).getBusID() + " | " +
                               "Shift duration: " + (shift384Weekday.get(i).getTimeTo() - 
                                                     shift384Weekday.get(i).getTimeFrom()));
        }
        
        System.out.println("The saturday date is " + saturdayDate.toString());
        System.out.println();
        for (int i = 0; i < shift384Saturday.size(); i++)
        {
            System.out.println("Shift number: " + i + " | " +
                               "Start time: " + shift384Saturday.get(i).getTimeFrom() + " | " + 
                               "End time: " + shift384Saturday.get(i).getTimeTo() + " | " +
                               "Driver ID: " + shift384Saturday.get(i).getDriverID() + " | " +
                               "Bus ID: " + shift384Saturday.get(i).getBusID() + " | " +
                               "Shift duration: " + (shift384Saturday.get(i).getTimeTo() - 
                                                     shift384Saturday.get(i).getTimeFrom()));
        }
        
        System.out.println("The sunday date is  " + sundayDate.toString());
        System.out.println();
        for (int i = 0; i < shift384Sunday.size(); i++)
        {
            System.out.println("Shift number: " + i + " | " +
                               "Start time: " + shift384Sunday.get(i).getTimeFrom() + " | " + 
                               "End time: " + shift384Sunday.get(i).getTimeTo() + " | " +
                               "Driver ID: " + shift384Sunday.get(i).getDriverID() + " | " +
                               "Bus ID: " + shift384Sunday.get(i).getBusID() + " | " +
                               "Shift duration: " + (shift384Sunday.get(i).getTimeTo() - 
                                                     shift384Sunday.get(i).getTimeFrom()));
        }
        
        
    }
}
