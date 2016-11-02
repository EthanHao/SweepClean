/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.se459grp4.project.cleansweep;

import edu.se459grp4.project.Graph.TilesGraph;
import edu.se459grp4.project.simulator.types.Direction;
import edu.se459grp4.project.simulator.types.TileStatus;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Eisen
 */
public class ControlSystem implements Runnable{
    private CleanSweep mCleanSweep;
    private TilesGraph mTileGraph = new TilesGraph();
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
                //detect tilestatus ,Set Visit
                TileStatus lTileStatus = mCleanSweep.DetectSurfaceType();
                int nDirtVal = mCleanSweep.DetectDirtValue();
                while( (lTileStatus == TileStatus.BARE_FLOOR ||
                        lTileStatus == TileStatus.LOW_CARPET ||
                        lTileStatus == TileStatus.HIGH_CARPET ) && nDirtVal > 0)
                {
                    boolean lbRet = mCleanSweep.SweepUp(10);
                    Thread.sleep(20);
                    if(lbRet == false)
                        break;
                }
               
                //Visit
                mTileGraph.Visit(mCleanSweep.GetX(),mCleanSweep.GetY(),lTileStatus);
                //Get all the path it can go
                List<Direction> lList = mCleanSweep.GetAllDirectionCanGo();
                if(lList.isEmpty())
                    break;  
                boolean lbFindAWayToUnVisitedNode = false;
                List<Direction> lListAfterMatched = new ArrayList<Direction>();
                for(Direction item :lList) 
                {
                    int x = mCleanSweep.GetX();
                    int y = mCleanSweep.GetY();
                    //Add Node and path to the graph
                    if(item == Direction.Left) x--;
                    if(item == Direction.Right) x++;
                    if(item == Direction.Up) y--;
                    if(item == Direction.Down) y++;
                    mTileGraph.AddEdge(x,y,mCleanSweep.GetX(),mCleanSweep.GetY(), TileStatus.HIGH_CARPET);
                     
                    if(mTileGraph.IsVisited(x,y) == false)
                        lListAfterMatched.add(item);
                }
                
                //decide which one should go
                if(lListAfterMatched.isEmpty())
                {
                    //Pick a random direction from lList
                    Random lRandom = new Random();
                    int n = lRandom.nextInt(lList.size());
                    mCleanSweep.MoveOneStep(lList.get(n));
                }
                else
                    mCleanSweep.MoveOneStep(lListAfterMatched.get(0));
                 Thread.sleep(1000);
            }
        }
        catch(Exception e)
        {
            return;
        }
    }

    
        
    
}
