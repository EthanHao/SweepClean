/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.se459grp4.project.cleansweep;

//import edu.se459grp4.project.simulator.SensorSimulator;

import edu.se459grp4.project.simulator.Simulator;
import edu.se459grp4.project.simulator.types.Direction;
import edu.se459grp4.project.simulator.types.PathStatus;


public class NavigationSensor  {
    private Direction direction;

    public NavigationSensor( Direction direction) {
        this.direction = direction;
    }

    public PathStatus GetSensorData(int x,int y)
    {
        return Simulator.getInstance().ProvideDirectionSensroData(direction, x, y);
    }
   
}