/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.se459grp4.project.cleansweep;

import edu.se459grp4.project.simulator.types.Direction;
import edu.se459grp4.project.simulator.types.PathStatus;

/**
 *
 * @author Weihua
 */
public class ControlSystem {
    
    private NavigationSensor mLeftSensor = new NavigationSensor(Direction.Left);
    private NavigationSensor mRightSensor = new NavigationSensor(Direction.Right);
    private NavigationSensor mUpSensor = new NavigationSensor(Direction.Up);
    private NavigationSensor mDownSensor = new NavigationSensor(Direction.Down);
    
    //start the control system
    public boolean Start()
    {
        return true;
    }
    
    //start the control system
    public boolean Stop()
    {
        return true;
    }
    
    public PathStatus CheckMove(Direction nDirection,int x, int y)
    {  
        if(nDirection == Direction.Left)
            return mLeftSensor.GetSensorData(x, y);
        if(nDirection == Direction.Right)
            return mRightSensor.GetSensorData(x, y);
        if(nDirection == Direction.Left)
            return mUpSensor.GetSensorData(x, y);
        if(nDirection == Direction.Left)
            return mDownSensor.GetSensorData(x, y);
        
        return PathStatus.UNKNOWN;
    }
   
    
}
