/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.se459grp4.project.simulator.models;

/**
 *
 * @author whao
 */
public class Door extends Object implements java.io.Serializable  {
    private boolean mbVertical;
    private int mnBase;
    private int mnFrom;
    private int mnTo;
    private boolean mbOpen;
  
      
    public Door(boolean nVer,int nBase,int nFrom,int nTo,boolean nbOpen)
    {
        mbVertical = nVer;
        mnBase = nBase;
        mnFrom = nFrom;
        mnTo = nTo;
        mbOpen = nbOpen;
    }
     public int GetBase()
    {
        return mnBase;
    }
    public int GetFrom()
    {
        return mnFrom;
    }
    public int GetTo()
    {
        return mnTo;
    }
    public boolean GetVertical()
    {
        return mbVertical;
    }
    public boolean GetIsOpened()
    {
        return mbOpen;
    }
    public boolean Open()
    {
        mbOpen = true;
        return true;
    }
    public boolean Close()
    {
         mbOpen = false;
         return true;
    }
    
    @Override
    public String toString()
    {
        return  "Door"+(mbVertical?"Vertical":"Horizontal") + "Base " + mnBase+" From "+mnFrom+" To "+mnTo;
    }
    
}
