package edu.se459grp4.project.cleansweep;

import edu.se459grp4.project.simulator.types.Direction;
import edu.se459grp4.project.simulator.types.PathStatus;
import java.util.Observable;


public class CleanSweep extends Observable {
    
    
    //define the location tile coordinate
    private int mx;
    private int my;
  
    //each sweep has got a powerful control system
    private ControlSystem mControlSystem= new ControlSystem();
    
    
    //get the x coordinate of this sweep
    public int GetX()
    {
        return mx;
    }
    
    //get the y coordinate of this sweep
    public int GetY()
    {
        return my;
    }
    
    //start this sweep
    public boolean Start()
    {
         mControlSystem.Start();
         //set the inital status.
         //suppose all tiles are dirty
         //and it suppose start from a charge station and power is full and the vacuum capacity is empty.
         //so if the vacuum capacity value is not zero then start will fail.
         
         return true;
    }
    
    //stop this sweep
    public boolean Stop()
    {
          mControlSystem.Stop();
          return true;
    }
    
    
    
   
}
