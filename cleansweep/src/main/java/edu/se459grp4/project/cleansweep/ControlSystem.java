/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.se459grp4.project.cleansweep;

import edu.se459grp4.project.simulator.types.Direction;

/**
 *
 * @author Eisen
 */
public class ControlSystem implements Runnable{
    private CleanSweep mCleanSweep;
    public ControlSystem(CleanSweep nCleanSweep)
    {
        mCleanSweep = nCleanSweep;
    }
    
    public void run()
    {
        //smart control run
        try
        {
            while( mCleanSweep != null)
            {
                if(mCleanSweep.MoveOneStep(Direction.Left) == false)
                    if(mCleanSweep.MoveOneStep(Direction.Up) == false)
                        if(mCleanSweep.MoveOneStep(Direction.Right) == false)
                            if(mCleanSweep.MoveOneStep(Direction.Down) == false)
                                break;
            
                 Thread.sleep(1000);
            }
        }
        catch(Exception e)
        {
            return;
        }
    }

    
        
    
}
