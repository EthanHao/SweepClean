package edu.se459grp4.project.cleansweep;

import edu.se459grp4.project.simulator.Simulator;
import edu.se459grp4.project.simulator.types.Direction;
import edu.se459grp4.project.simulator.types.PathStatus;
import edu.se459grp4.project.simulator.types.TileStatus;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class CleanSweep extends Observable {

    //define the location tile coordinate
    private int mnID;
    private int mx;
    private int my;

    public CleanSweep(int nID,int nx,int ny)
    {
        mnID = nID;
        mx = nx;
        my = ny;
    }
   
    //public 
    //
    //  each clean sweep has 4 navigation sensor
    private NavigationSensor mLeftSensor = new NavigationSensor(Direction.Left);
    private NavigationSensor mRightSensor = new NavigationSensor(Direction.Right);
    private NavigationSensor mUpSensor = new NavigationSensor(Direction.Up);
    private NavigationSensor mDownSensor = new NavigationSensor(Direction.Down);

    private DirtSensor mDirtSensor = new DirtSensor();
    private SurfaceSensor mSurfaceSensor = new SurfaceSensor();
    //get the x coordinate of this sweep
    public int GetX() {
        return mx;
    }

    //get the y coordinate of this sweep
    public int GetY() {
        return my;
    }
    public int GetID()
    {
        return mnID;
    }
    public List<Direction> GetAllDirectionCanGo()
    {
        List<Direction> lRetList = new ArrayList<Direction>();
        if (PathStatus.Open == CheckMove(Direction.Left))
            lRetList.add(Direction.Left);
        if (PathStatus.Open == CheckMove(Direction.Up))
            lRetList.add(Direction.Up);
        if (PathStatus.Open == CheckMove(Direction.Right))
            lRetList.add(Direction.Right);
        
        if (PathStatus.Open == CheckMove(Direction.Down))
            lRetList.add(Direction.Down);
        return lRetList;
              
    }
    public PathStatus CheckMove(Direction nDirection) {
        if (nDirection == Direction.Left) {
            return mLeftSensor.GetSensorData(mx, my);
        }
        if (nDirection == Direction.Right) {
            return mRightSensor.GetSensorData(mx, my);
        }
        if (nDirection == Direction.Up) {
            return mUpSensor.GetSensorData(mx, my);
        }
        if (nDirection == Direction.Down) {
            return mDownSensor.GetSensorData(mx, my);
        }

        return PathStatus.UNKNOWN;
    }

    public boolean MoveOneStep(Direction nDirection) {
        if (PathStatus.Open == CheckMove(nDirection)) {

            if (nDirection == Direction.Left) {
                mx--;
            } else if (nDirection == Direction.Right) {
                mx++;
            } else if (nDirection == Direction.Up) {
                my--;
            } else if (nDirection == Direction.Down) {
                my++;
            }

            setChanged();
            notifyObservers();
            return true;
        }
        return false;
    }

   
    public TileStatus DetectSurfaceType()
    {
       return mSurfaceSensor.GetSensorData(mx, my);
    }
    public int DetectDirtValue()
    {
        return mDirtSensor.GetSensorData(mx, my);
    }
    public boolean SweepUp(int nVal)
    {
        return Simulator.getInstance().SweepUp(mx, my, nVal);
    }
    
}
