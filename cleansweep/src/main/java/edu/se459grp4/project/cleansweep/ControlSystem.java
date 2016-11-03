/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.se459grp4.project.cleansweep;

import com.sun.jmx.remote.internal.ArrayQueue;
import edu.se459grp4.project.Graph.TileNode;
import edu.se459grp4.project.Graph.TilesGraph;
import edu.se459grp4.project.simulator.types.Direction;
import edu.se459grp4.project.simulator.types.TileStatus;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

/**
 *
 * @author Eisen
 */
public class ControlSystem implements Runnable {

    private CleanSweep mCleanSweep;
    private TilesGraph mTileGraph = new TilesGraph();
    private Queue<TileNode> mMissionQueue = new LinkedList<TileNode>();
    public ControlSystem(CleanSweep nCleanSweep) {
        mCleanSweep = nCleanSweep;
    }

    public void run() {
        //smart control run
        try {
            while (mCleanSweep != null) {
                //Check if there is a next mission, it 
                if(!mMissionQueue.isEmpty())
                {
                    TileNode lMovetoNode = mMissionQueue.poll();
                    mCleanSweep.MoveTo(lMovetoNode.GetX(),lMovetoNode.GetY());
                    continue;
                }
                
                //detect tilestatus and sweep ,Set Visit
                TileStatus lTileStatus = mCleanSweep.DetectSurfaceType();
                int nDirtVal = mCleanSweep.DetectDirtValue();
                while ((lTileStatus == TileStatus.BARE_FLOOR
                        || lTileStatus == TileStatus.LOW_CARPET
                        || lTileStatus == TileStatus.HIGH_CARPET) && nDirtVal > 0) {
                    boolean lbRet = mCleanSweep.SweepUp(10);
                    Thread.sleep(20);
                    if (lbRet == false) {
                        break;
                    }
                }

                //Visit
                mTileGraph.Visit(mCleanSweep.GetX(), mCleanSweep.GetY(), lTileStatus);
                
                
                
                //Get all the path it can go
                List<Direction> lListCanGo = mCleanSweep.GetAllDirectionCanGo();
                if (lListCanGo.isEmpty()) {
                    break;
                }

                List<Direction> lListAfterMatched = new ArrayList<Direction>();
                for (Direction item : lListCanGo) {
                    int x = mCleanSweep.GetX();
                    int y = mCleanSweep.GetY();
                    //Add Node and path to the graph
                    if (item == Direction.Left) {
                        x--;
                    }
                    if (item == Direction.Right) {
                        x++;
                    }
                    if (item == Direction.Up) {
                        y--;
                    }
                    if (item == Direction.Down) {
                        y++;
                    }
                    mTileGraph.AddEdge(mCleanSweep.GetX(), mCleanSweep.GetY(),x,y, TileStatus.HIGH_CARPET);

                    if (mTileGraph.IsVisited(x, y) == false) {
                        lListAfterMatched.add(item);
                    }
                }
                //If find some way I can not go because of closing door, then we need to update the graph
                List<Direction> lListCannotGo = mCleanSweep.GetAllDirectionCanGo();
                for(Direction item : lListCannotGo)
                {
                     int x = mCleanSweep.GetX();
                    int y = mCleanSweep.GetY();
                    //Add Node and path to the graph
                    if (item == Direction.Left) {
                        x--;
                    }
                    if (item == Direction.Right) {
                        x++;
                    }
                    if (item == Direction.Up) {
                        y--;
                    }
                    if (item == Direction.Down) {
                        y++;
                    }
                    mTileGraph.DeleteEdge(x, y, mCleanSweep.GetX(), mCleanSweep.GetY());

                }

                //decide which one should go
                if (lListAfterMatched.isEmpty()) {
                    //check if has unvisited node
                    List<TileNode> lListUnvisitedTileNode = mTileGraph.GetUnvisitedNode();
                    if (!lListUnvisitedTileNode.isEmpty()) {
                        //pich a shortest one
                        List<String> lRetPath = new ArrayList<String>();
                        Double ldb = ChooseShortestPath(mCleanSweep.GetX(),mCleanSweep.GetY(),lListUnvisitedTileNode,lRetPath);
                        if(0 != Double.compare(ldb, Double.MAX_VALUE))
                        {
                            //check the power and add the mission
                            for(String ls:lRetPath)
                            {
                                mMissionQueue.add(mTileGraph.GetTileNode(ls));
                            }
                        }
                    } else {
                        //Pick a random direction from lList
                        Random lRandom = new Random();
                        int n = lRandom.nextInt(lListCanGo.size());
                        mCleanSweep.MoveOneStep(lListCanGo.get(n));
                        
                        //go to charge station
                        //break
                    }
                } else {
                    mCleanSweep.MoveOneStep(lListAfterMatched.get(0));
                }

                //Let people can show the move, dont't be so fast
                Thread.sleep(200);
            }
        } catch (Exception e) {
            return;
        }
    }

    private double ChooseShortestPath(int x,int y, List<TileNode> nListNode, List<String> nretPath)
    {
        List<Double> ldbList = new ArrayList<Double>();
        Double ldb = Double.MAX_VALUE;
        for(TileNode item : nListNode)
        {
            List<String> nArrayPath = new ArrayList<String>();
            Double lTemp = mTileGraph.GetShortestPath(x, y, item.GetX(), item.GetY(), nArrayPath);
            if(lTemp < ldb)
            {
                ldb = lTemp;
                nretPath.clear();
                nretPath.addAll(nArrayPath);
               // nretPath = nArrayPath;
            }
        }
        
        return ldb;
    }
}
