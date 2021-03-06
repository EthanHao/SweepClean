/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.se459grp4.project.Graph;

import edu.se459grp4.project.simulator.types.TileStatus;

/**
 *
 * @author Eisen
 */
public class TileNode extends Object {

    private int mx;
    private int my;
    private TileStatus meTileStatus;

    private NodeStatus meStatus;  //the visiting status of this node 

    public TileNode(int x, int y, TileStatus nStatus, NodeStatus neValue) {
        mx = x;
        my = y;
        meTileStatus = nStatus;
        meStatus = neValue;
    }

    public int GetX() {
        return mx;
    }

    public int GetY() {
        return my;
    }

    public NodeStatus NodeStatus() {
        return meStatus;
    }

    public void SetNodeStatus(NodeStatus neVal) {
        meStatus = neVal;
    }

    public TileStatus TileStatus() {
        return meTileStatus;
    }

    public void SetTileStatus(TileStatus neVal) {
        meTileStatus = neVal;
    }

    @Override
    public String toString() {
        return GenerateKeyString(mx, my);
    }

    public static String GenerateKeyString(int x, int y) {
        return "X" + x + "Y" + y;
    }
}
