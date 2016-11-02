package edu.se459grp4.project.cleansweep;

import edu.se459grp4.project.simulator.types.Direction;
import edu.se459grp4.project.simulator.types.PathStatus;
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
            } else if (nDirection == Direction.Left) {
                my++;
            }

            setChanged();
            notifyObservers();
            return true;
        }
        return false;
    }

    //start this sweep
    public boolean Start() {
        //mControlSystem.Start();
        //set the inital status.
        //suppose all tiles are dirty
        //and it suppose start from a charge station and power is full and the vacuum capacity is empty.
        //so if the vacuum capacity value is not zero then start will fail.

        return true;
    }

    //stop this sweep
    public boolean Stop() {
        //mControlSystem.Stop();
        return true;
    }

}
