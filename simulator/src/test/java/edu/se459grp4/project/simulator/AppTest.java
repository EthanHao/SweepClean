package edu.se459grp4.project.simulator;

import edu.se459grp4.project.simulator.models.*;
import edu.se459grp4.project.simulator.types.*;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import java.io.*;
import java.util.Random;
/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testCreateAFloorPlan()
    {
        int nCount = 10;
        FloorPlan lFloorPlan = new FloorPlan(nCount,30,10,30);
        Random rand = new Random();
        for(int i = 0; i < nCount; i++)
            for(int j= 0; j < nCount;j++)
                lFloorPlan.AddTile(i, j, TileStatus.BARE_FLOOR , 50);
          
        
        lFloorPlan.AddWall(true, 4, 0, 4);
        lFloorPlan.AddWall(false, 4, 0, 4);
        
        lFloorPlan.AddDoor(true, 4, 0, 0, true);
        lFloorPlan.AddDoor(false, 4, 0, 1, true);
        
        lFloorPlan.SetTileSatus(0, 0, TileStatus.CHARGING_STATION);
        lFloorPlan.SetTileSatus(0, 5, TileStatus.STAIRS);
        lFloorPlan.SetTileSatus(0, 9, TileStatus.CHARGING_STATION);
           
        lFloorPlan.SetTileSatus(4, 5, TileStatus.LOW_CARPET);
        lFloorPlan.SetTileSatus(4, 6, TileStatus.LOW_CARPET);
        lFloorPlan.SetTileSatus(4, 7, TileStatus.LOW_CARPET);
        lFloorPlan.SetTileSatus(5, 5, TileStatus.HIGH_CARPET);
        lFloorPlan.SetTileSatus(5, 6, TileStatus.HIGH_CARPET);
        lFloorPlan.SetTileSatus(5, 7, TileStatus.HIGH_CARPET);
        lFloorPlan.SetTileSatus(6, 5, TileStatus.HIGH_CARPET);
        lFloorPlan.SetTileSatus(6, 6, TileStatus.HIGH_CARPET);
        lFloorPlan.SetTileSatus(6, 7, TileStatus.HIGH_CARPET);
        //Serilization
       try {
         FileOutputStream fileOut = new FileOutputStream("./example.flr");
         ObjectOutputStream out = new ObjectOutputStream(fileOut);
         out.writeObject(lFloorPlan);
         out.close();
         fileOut.close();
         System.out.printf("Serialized data is saved in /floorplan/example.flr");
      }catch(IOException i) {
         i.printStackTrace();
      }
       
       //Deserilazation
      FloorPlan lLoadedFloorPlan = null;
      try {
         FileInputStream fileIn = new FileInputStream("./example.flr");
         ObjectInputStream in = new ObjectInputStream(fileIn);
         lLoadedFloorPlan = (FloorPlan) in.readObject();
         in.close();
         fileIn.close();
      }catch(IOException i) {
         i.printStackTrace();
         return;
      }catch(ClassNotFoundException c) {
         System.out.println("./example.flr class not found");
         c.printStackTrace();
         return;
      }
        assertTrue( lLoadedFloorPlan.GetDirtVal(5, 5)  == 50);
    }
    
      /**
     * Rigourous Test :-)
     */
    public void testCreateAFloorPlan1()
    {
        int nCount = 15;
        FloorPlan lFloorPlan = new FloorPlan(nCount,30,10,30);
        Random rand = new Random();
        for(int i = 0; i < nCount; i++)
            for(int j= 0; j < nCount;j++)
                lFloorPlan.AddTile(i, j, TileStatus.BARE_FLOOR , rand.nextInt(50) );
          
        
        lFloorPlan.AddWall(true, 4, 0, 4);
        lFloorPlan.AddWall(false, 4, 0, 4);
        
        lFloorPlan.AddDoor(true, 4, 0, 0, true);
        lFloorPlan.AddDoor(false, 4, 0, 1, true);
        
        lFloorPlan.SetTileSatus(0, 0, TileStatus.CHARGING_STATION);
        lFloorPlan.SetTileSatus(0, 5, TileStatus.STAIRS);
        lFloorPlan.SetTileSatus(0, 14, TileStatus.CHARGING_STATION);
        lFloorPlan.SetTileSatus(13, 14, TileStatus.CHARGING_STATION);
           
        lFloorPlan.SetTileSatus(4, 5, TileStatus.LOW_CARPET);
        lFloorPlan.SetTileSatus(4, 6, TileStatus.LOW_CARPET);
        lFloorPlan.SetTileSatus(4, 7, TileStatus.LOW_CARPET);
        lFloorPlan.SetTileSatus(5, 5, TileStatus.HIGH_CARPET);
        lFloorPlan.SetTileSatus(5, 6, TileStatus.HIGH_CARPET);
        lFloorPlan.SetTileSatus(5, 7, TileStatus.HIGH_CARPET);
        lFloorPlan.SetTileSatus(6, 5, TileStatus.HIGH_CARPET);
        lFloorPlan.SetTileSatus(6, 6, TileStatus.HIGH_CARPET);
        lFloorPlan.SetTileSatus(6, 7, TileStatus.HIGH_CARPET);
        //Serilization
       try {
         FileOutputStream fileOut = new FileOutputStream("./example1.flr");
         ObjectOutputStream out = new ObjectOutputStream(fileOut);
         out.writeObject(lFloorPlan);
         out.close();
         fileOut.close();
         System.out.printf("Serialized data is saved in /floorplan/example1.flr");
      }catch(IOException i) {
         i.printStackTrace();
      }
       
       //Deserilazation
      FloorPlan lLoadedFloorPlan = null;
      try {
         FileInputStream fileIn = new FileInputStream("./example1.flr");
         ObjectInputStream in = new ObjectInputStream(fileIn);
         lLoadedFloorPlan = (FloorPlan) in.readObject();
         in.close();
         fileIn.close();
      }catch(IOException i) {
         i.printStackTrace();
         return;
      }catch(ClassNotFoundException c) {
         System.out.println("./example.flr class not found");
         c.printStackTrace();
         return;
      }
       assertTrue( lLoadedFloorPlan.GetTileSatus(5, 5)  == TileStatus.HIGH_CARPET);
    }
}
