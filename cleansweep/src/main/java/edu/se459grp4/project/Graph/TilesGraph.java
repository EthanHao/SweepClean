/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.se459grp4.project.Graph;


import edu.se459grp4.project.simulator.types.TileStatus;
import java.util.*;
/**
 *
 * @author whao
 * this is a simple graph class
 * this class only support simply storing graph data and getting the shortest path
 * We use adjacency list to store the graph data
 * 
 * 
 * 
 */
public class TilesGraph {
   
   
    
    private HashMap<String,TileNode> mNodeMap = new HashMap<String,TileNode>();
    //the whole graph, string is the incoming node name, and the associated hasmap with this node
    private HashMap<String, HashMap<String,Double>>  mGraphMap = new  HashMap<String, HashMap<String,Double>>(); 
    
    public boolean IsVisited(int x,int y)
    {
        TileNode lNode = mNodeMap.get(TileNode.GenerateKeyString(x, y));
        if(lNode != null && lNode.NodeStatus() == NodeStatus.eNodeVisited)
            return true;
        return false;
    }
    public boolean Visit(int x,int y,TileStatus nTileStatus)
    {
        TileNode lNode = mNodeMap.get(TileNode.GenerateKeyString(x, y));
        if(lNode == null)
        {
            lNode = new TileNode(x,y,nTileStatus,NodeStatus.eNodeVisited);
            mNodeMap.put(lNode.toString(), lNode);
            
            HashMap<String, Double> lSubmap = new HashMap<String, Double>();
            mGraphMap.put(lNode.toString(), lSubmap);
        }
        else
        {
            lNode.SetTileStatus(nTileStatus);
            lNode.SetNodeStatus(NodeStatus.eNodeVisited);
        }
        //Update Weight
        
        HashMap<String, Double> lSubmap = mGraphMap.get(lNode.toString());
        
        
        for(Map.Entry<String, Double> entry : lSubmap.entrySet())
        {
            TileNode lDestNode = mNodeMap.get(entry.getKey());
            
            
            double ldbWeight = TileStatus.Weight(lNode.TileStatus()) / 2 + 
                           TileStatus.Weight(lDestNode.TileStatus()) / 2;
            lSubmap.put(entry.getKey(), ldbWeight);
            
            //Modify the reverse edge
            mGraphMap.get(lDestNode.toString()).put(entry.getKey(), ldbWeight);
        }
        
        return true;
    }
    //Get the Shortest Path function
    //return : >0 && < Double.MAX_VALUE means We get a shortest way answer
    //         Double.MAX_VALUE means we can not find a way
  
   /* public boolean GetShortestPath( String nsInputNode,  //Source Node
            String nsDestinationNode,             //Destination Node
            ArrayList<String> nArrayPath )
         
    {
        if(nsInputNode == null || nsDestinationNode == null || 
                nsInputNode.isEmpty() || nsDestinationNode.isEmpty() || 
                nArrayPath == null )
           return false;
        
        
        nsInputNode = nsInputNode.toUpperCase();
        nsDestinationNode = nsDestinationNode.toUpperCase();
        if(!mGraphMap.containsKey(nsInputNode))
           return false;
        if(!mGraphMap.containsKey(nsDestinationNode))
           return false;
         
        if(nsInputNode == nsDestinationNode)
           return false;
        //We are going to use Dijkstra's Algorithm to find the shortest path
       
        //Construct the line from the GraphMap
        HashMap<String, Node> lRecRow = new HashMap<String, Node>();
        {
            Set set = mGraphMap.entrySet();
            Iterator lIte = set.iterator();
            while (lIte.hasNext()) {
                Map.Entry me = (Map.Entry) lIte.next();
                String lsKey = me.getKey().toString();
                if (lsKey != nsInputNode) {
                    lRecRow.put(lsKey, new Node(nsInputNode, Double.MAX_VALUE, NodeStatus.eNodeNoVisited));
                }
            }
        }

        //We use a Queue to store those node waiting for handling
        LinkedList<Node> lQueue = new LinkedList <Node>();
        lQueue.add(new Node(nsInputNode,0.00,NodeStatus.eNodeNoVisited));
        
      
        while(!lQueue.isEmpty())
        {
            Node lTempNode = lQueue.removeFirst();
            String lsFromNode = lTempNode.msNodeName;
            Double ldbShortestDistande = lTempNode.Weight();
            //Iterate the output edge from this node
            //check the minimum weight
            {
                HashMap<String, Node> lRow = mGraphMap.get(lsFromNode);
                Set set = lRow.entrySet();
                Iterator lIte = set.iterator();
                while (lIte.hasNext()) {
                    Map.Entry me = (Map.Entry) lIte.next();
                    String lsToNodeKey = me.getKey().toString();
                    Double lsToWeight = ((Node) me.getValue()).Weight();
                    Double ldbTempWeight = ldbShortestDistande + lsToWeight;

                    //Do not deal the node with the same name of original source node
                    if (lsToNodeKey != nsInputNode) {
                        if (ldbTempWeight < lRecRow.get(lsToNodeKey).Weight()) {
                            lRecRow.get(lsToNodeKey).SetWeight(ldbTempWeight);
                            lRecRow.get(lsToNodeKey).SetName(lsFromNode);
                        }
                    }
                }
            }

            //Find the mininum and Nonvisited Node to Enqueue
            {
                Double ldbMinimunWeight = Double.MAX_VALUE;
                String lsMinWeightNodeName = "";
                Set set = lRecRow.entrySet();
                Iterator lIte = set.iterator();
                while (lIte.hasNext()) {
                    Map.Entry me = (Map.Entry) lIte.next();
                    String lsToNodeKey = me.getKey().toString();
                    Node lTemp = (Node) me.getValue();
                    if (lTemp.Status() == NodeStatus.eNodeNoVisited && lTemp.Weight() < ldbMinimunWeight) {
                        ldbMinimunWeight = lTemp.Weight();
                        lsMinWeightNodeName = lsToNodeKey;
                    }
                }

                if (!lsMinWeightNodeName.isEmpty()) {
                    lRecRow.get(lsMinWeightNodeName).SetStatus(NodeStatus.eNodeVisited);
                    lQueue.add(new Node(lsMinWeightNodeName, ldbMinimunWeight, NodeStatus.eNodeInVisitingQueue));
                }
            }

        }
        
        //Generate the path
        nArrayPath.add(nsDestinationNode);
        Node lFinalNode = lRecRow.get(nsDestinationNode);
        Double ndbShortestWeight = lFinalNode.mdbWeight;
        String lsTempName = lFinalNode.Name();
        do
        {
            nArrayPath.add(lsTempName);
            lFinalNode = lRecRow.get(lsTempName);
            lsTempName = lFinalNode.Name();
        }while(lsTempName != nsInputNode);
        nArrayPath.add(nsInputNode);
        //Reverse the array
        Collections.reverse(nArrayPath);
        
        return true;
    }
    
    */
    //This is the main way to construce a graph
    //We need to add edges one by one
    //Note: this is an undirect graph, so we need to add a converse edge simultaneously
    public Boolean AddEdge(int nFromX,int nFromY,
            int nDestX,int nDestY,TileStatus nTileStatus)
            
    {
        
        if(nFromX == nDestX && nFromY == nDestY)
            return false;
        TileNode lSourceNode = mNodeMap.get(TileNode.GenerateKeyString(nDestX, nDestY));
        if(lSourceNode == null)
            return false;
        
        Boolean lbRet = true;
        
        TileNode lDestNode = mNodeMap.get(TileNode.GenerateKeyString(nDestX, nDestY));
        if(lDestNode == null)
        {
            lDestNode = new TileNode(nDestX,nDestY,nTileStatus,NodeStatus.eNodeVisited);
            mNodeMap.put(lDestNode.toString(), lDestNode);
        }
        else
        {
            lDestNode.SetTileStatus(nTileStatus);
        }
        
        double ldbWeight = TileStatus.Weight(lSourceNode.TileStatus()) / 2 + 
                           TileStatus.Weight(lDestNode.TileStatus()) / 2;
       
        //Check the node of input if it exists in the GraphMap  
        if (mGraphMap.containsKey(lSourceNode.toString())) {
            //If existed then get the submap
            HashMap<String, Double> lSubmap = mGraphMap.get(lSourceNode.toString());
            lSubmap.put(lDestNode.toString(), ldbWeight);

            
        } else {
            //this is a new node,we should create all
            HashMap<String, Double> lSubmap = new HashMap<String, Double>();
            lSubmap.put(lDestNode.toString(), ldbWeight);
            mGraphMap.put(lSourceNode.toString(), lSubmap);
        }

        //Check the dest node of input if it exists in the GraphMap  
        if (mGraphMap.containsKey(lDestNode.toString())) {
            //If existed then get the submap
            HashMap<String, Double> lSubmap = mGraphMap.get(lDestNode.toString());
            lSubmap.put(lSourceNode.toString(), ldbWeight);
            
        } else {
            //this is a new node,we should create all
            HashMap<String, Double> lSubmap = new HashMap<String, Double>();
            lSubmap.put(lSourceNode.toString(), ldbWeight);
            mGraphMap.put(lDestNode.toString(), lSubmap);
        }
        return lbRet;
    }
     
}
