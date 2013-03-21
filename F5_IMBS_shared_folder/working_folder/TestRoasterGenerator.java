
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
        Date dStart = new Date(2012, 11, 1, 0, 0, 0);
        Date dEnd = new Date(2012, 11, 7, 0, 0, 0);      
        Roaster r = RoasterGenerator.GenerateRoaster(dStart, dEnd);
    }
}
