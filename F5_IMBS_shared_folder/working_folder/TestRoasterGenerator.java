
import java.util.Date;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Thanakorn
 */
public class TestRoasterGenerator {
    public static void main(String[] args)
    {
        database.openBusDatabase();
        Date dStart = new Date(2013, 2, 21, 0, 0, 0);
        Date dEnd = new Date(2013, 2, 27, 0, 0, 0);      
        Roaster r = RoasterGenerator.GenerateRoaster(dStart, dEnd);
        /*
        for(int i = 0; i < r.shift.get(0).size(); i++)
        {
            System.out.println("---------------------------------------------");
            System.out.println("Driver ID = " + r.shift.get(0).get(i).getDriverID());
            System.out.println("Bus ID = " + r.shift.get(0).get(i).getBusID());
            System.out.println("From = " + r.shift.get(0).get(i).getTimeFrom() + 
                               "To " + r.shift.get(0).get(i).getTimeTo());
        }//for
        System.out.println(RoasterGenerator.systemMsg.message);
        */
    }//main
}
