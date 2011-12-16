/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Bot.Main;

import Bot.PathFinder.AStar;
import Bot.PathFinder.Node;
import Bot.PathFinder.Path;
import Controlling.StringGenerator;
import Map.BonusElement;
import Map.CoinPack;
import Map.LifePack;
import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author rajith
 */
public class Commander implements Runnable{
    
    private StringGenerator sGen;
    private BarricadeMap map;
    private States currentStates;
    private boolean stop;
    private AStar pathFinder;
    private PriorityList orderedList;
    private PriorityList goals;
    
    
    public Commander(BarricadeMap map){
        
    this.map=map;        
    this.stop=false;
    pathFinder=new AStar();
        
    }

    public void run() {
        
       
        while(!stop){
            
            if(currentStates.isPlayerReset()){
                
                if(currentStates.getMode()==2){
                    
                    pythagorianDistCoins(currentStates.getCoinPiles());
                    
                    System.out.println("xxxxx");
                    LinkedList g=new LinkedList(currentStates.getCoinPiles());
                         while(!g.isEmpty()){
                        CoinPack d=(CoinPack)g.removeFirst();
                        System.out.print((d).getxLocation()+","+(d).getyLocation());
                        System.out.print(";;");

            
                        }
                    System.out.println("xxxxx");
                    
                    calculatePath();
                    issueCommand();
                    
                }
                else if(currentStates.getMode()==3){
                    
                    pythagorianDistHPacks(currentStates.getHealthPacks());
                    calculatePath();
                    issueCommand();
                    
                }else if(currentStates.getMode()==1){
                    
                }
                else {
                    System.out.println("Idling");
                }
                
                
                
                currentStates.setPlayerReset(false);
               
            }
            else {
               
               
                int i=0;
                 while(i<800){
                     i+=i;
                     i++;
                 }
                
            }
            
            
        }
       
    }
    
    private void issueCommand(){
        
         if(!goals.isEmpty()){
        Path p=(Path)goals.getFirst();
        LinkedList<Node> n=(p).getPath();
        LinkedList<Node> q=new LinkedList((p).getPath());
        

        while(!q.isEmpty()){
            Node d=q.removeFirst();
            System.out.print((d).getX()+","+(d).getY());
            System.out.print(";;");

            
        }
             System.out.println("");
        
        
        Node next=n.getFirst();
        System.out.println("-------"+currentStates.getMe().getPlayerX()+","+currentStates.getMe().getPlayerY());
        System.out.println(next.getX()+","+next.getY()+"++++"+next.getCostFromStart());
        int nextDir=direction(next);
        
        
        if(next.getWeight()>1){                                                                                     //fire brickwalls
            if(currentStates.getMe().getPlayerDir()==nextDir){
                    
                sGen.shoot();
                
            }else {
                giveDirection(nextDir);
            }
        }
        else giveDirection(nextDir);
        
        }else {
            sGen.shoot();                               //caseeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee
        }
        
        
         
    }
        private void giveDirection(int nextDir){
        
        if(nextDir==0){ sGen.goUp(); System.out.println("UP");
        }
        else if(nextDir==1){sGen.goRight(); System.out.println("Right");
        }
        else if(nextDir==2){sGen.goDown(); System.out.println("Down");
        }
        else if(nextDir==3){sGen.goLeft(); System.out.println("Left");
        }
        
    }
    
    
     private void calculatePath(){                      
         
 
         int i=0;
         goals=new PriorityList();
         while(i<5&&(!orderedList.isEmpty())){
             Object ob=orderedList.removeFirst();         
             
             
             Path path=pathFinder.findPath(map.getMap()[currentStates.getMe().getPlayerX()][currentStates.getMe().getPlayerY()], map.getMap()[(((BonusElement)((PythagorianNode)(ob)).getElement()).getxLocation())][(((BonusElement)((PythagorianNode)(ob)).getElement()).getyLocation())]);            
             if(!((currentStates.getMe().getPlayerX()==(((BonusElement)((PythagorianNode)(ob)).getElement()).getxLocation()))&& (currentStates.getMe().getPlayerY()==(((BonusElement)((PythagorianNode)(ob)).getElement()).getyLocation())))){
                
                 
                 //additional
                        LinkedList q=new LinkedList(path.getPath());
                        while(!q.isEmpty()){
                        Node d=(Node)q.removeFirst();
                        System.out.print((d).getX()+","+(d).getY());
                        System.out.print(";;");

            
                        }
                 
                 System.out.println("$$$$$$$$$$$$$$$$$$$$$$$");
                 
                 
                 
                 path.addCost(addDirCost(currentStates.getMe().getPlayerDir(),direction((Node)((path.getPath()).getFirst()))));
                 goals.add(path);
                 i++;
             }
             
             
             
         }
         
         System.out.println("path calculated");
         
         
         
     }
     
    private int addDirCost(int dir1,int dir2){
        
        if(dir1!=dir2) {
            return 1;
        }
        else return 0;
        
    }
    
     public void setSGenerator(StringGenerator sGen){ this.sGen=sGen; }
     public void commanderStop() { stop = true; }

    /**
     * @param currentStates the currentStates to set
     */
    public void setCurrentStates(States currentStates) {
        this.currentStates = currentStates;
    }
    
    
    private void pythagorianDistHPacks(LinkedList <LifePack> cPack){
        Iterator <LifePack> i=cPack.iterator();
        int tempX;
        int tempY;
        int tempDist;
        orderedList =new PriorityList();
        while(i.hasNext()){
            LifePack hPile= i.next();
            tempX=(hPile.getxLocation()-currentStates.getMe().getPlayerX());
            tempY=(hPile.getyLocation()-currentStates.getMe().getPlayerY());
            tempDist=(int)Math.round(Math.sqrt((tempX*tempX)+(tempY*tempY)));
            PythagorianNode nNode=new PythagorianNode(tempDist,hPile);
            orderedList.add(nNode);
            
        }
        //return orderedList;
    }
    
    private void pythagorianDistCoins(LinkedList <CoinPack> cPack){
        Iterator <CoinPack> i=cPack.iterator();
        int tempX;
        int tempY;
        int tempDist;
        orderedList =new PriorityList();
        while(i.hasNext()){
            CoinPack cPile= i.next();
            tempX=(cPile.getxLocation()-currentStates.getMe().getPlayerX());
            tempY=(cPile.getyLocation()-currentStates.getMe().getPlayerY());
            tempDist=(int)Math.round(Math.sqrt((tempX*tempX)+(tempY*tempY)));
            PythagorianNode nNode=new PythagorianNode(tempDist,cPile);
            orderedList.add(nNode);
            
        }
        //return orderedList;
    }
    
        private int direction(Node next){
        
        int x=next.getX()-currentStates.getMe().getPlayerX();
        int y=next.getY()-currentStates.getMe().getPlayerY();
        
        
        if(x==0&&y==-1){ return 0;}                         //north
        else if(x==0&&y==1){return 2;}                      //South
        else if(x==-1&&y==0){return 3;}                     //west
        else if(x==1&&y==0){return 1;}                      //east
        
        else return -1;
        
    }
    
    private class PriorityList extends LinkedList {

        public void add(Comparable object) {
            for (int i=0; i<size(); i++) {
                if (object.compareTo(get(i)) <= 0) {
                    add(i, object);
                    return;
                }
            }
            addLast(object);
        }
    }
    
    private class PythagorianNode implements Comparable{
        
        private int pythagorianDist;
        private BonusElement bElement;
        
        public PythagorianNode(int pythagorianDist,BonusElement bElement){
            
            this.pythagorianDist=pythagorianDist;
            this.bElement=bElement;
            
        }
        
        
        public BonusElement getElement(){
            
            return bElement;
        }

        public int compareTo(Object other) {
           
            int otherValue=((PythagorianNode)other).getPythagorianDist();
            int thisValue = this.getPythagorianDist();

            return sign(thisValue - otherValue);
            
        }
        
         private int sign(int num){
        
        if(num<0){ return -1;}
        else if(num==0){return 0;}
        else return 1;
        
        }
        public int getPythagorianDist(){
            
            return pythagorianDist;
        }
        
        
        
    }
}

     