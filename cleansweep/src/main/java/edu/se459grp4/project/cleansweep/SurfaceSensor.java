/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.se459grp4.project.cleansweep;

import edu.se459grp4.project.simulator.Simulator;
import edu.se459grp4.project.simulator.types.PathStatus;
import edu.se459grp4.project.simulator.types.TileStatus;

/**
 *
 * @author Eisen
 */
public class SurfaceSensor {
    public TileStatus GetSensorData(int x,int y)
    {
        return Simulator.getInstance().ProvideSurfaceSensorData(x, y);
    }
}
