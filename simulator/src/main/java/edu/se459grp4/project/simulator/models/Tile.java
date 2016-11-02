/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.se459grp4.project.simulator.models;
import edu.se459grp4.project.simulator.types.TileStatus;
/**
 *
 * @author whao
 */
public class Tile extends Object implements java.io.Serializable{
    private int mx;
    private int my;
    private TileStatus mTileStatus;
    private int mnDirtVal;
    
    public Tile(int x,int y,TileStatus nStatus,int nVal)
    {
        mx = x;
        my = y;
        mTileStatus = nStatus;
        mnDirtVal = nVal;
    }
    public int GetX()
    {
        return mx;
    }
    public int GetY()
    {
        return my;
    }
    public TileStatus GetStatus()
    {
        return mTileStatus;
    }
     public boolean SetStatus(TileStatus nStatus)
    {
        mTileStatus = nStatus;
        return true;
    }
    public int GetDirtVal()
    {
        return mnDirtVal;
    }
    public boolean SetDirtVal(int nVal)
    {
        mnDirtVal = nVal;
        return true;
        
    }
    public boolean Sweep(int nVal)
    {
        if(mnDirtVal == 0)
            return false;
        
        mnDirtVal -= nVal;
        if(mnDirtVal < 0)
            mnDirtVal = 0;
        return true;
    }
    
    @Override
    public String toString()
    {
        return  (mTileStatus == TileStatus.CHARGING_STATION?"ChargeStation":"Tile") + " X "+mx+" Y "+my;
    }
}
