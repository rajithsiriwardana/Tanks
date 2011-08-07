/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Map;

/**
 *
 * @author ishan
 */
public class Bullet {

    //stores the initial location of the bullet
    private float initX;
    private float initY;

    //direction of the bullet
    private int direction;

    //stores the map drawn place
    private float mapX;
    private float mapY;

    //stores the tile size
    private float tileSize;

    //bullet speed
    private int speed;
    private float speedPerMilliS;

    //to keep track of the time that the bullet was created
    private long iniTime;

    public Bullet(int intX, int intY, int dir) {

        //map drawn locations
        mapX=0f;
        mapY=0f;

        //tile size
        tileSize=35f;

        //set the bullet location in float
        initX=intX*tileSize+mapX;
        initY=intY*tileSize+mapY;

        //set the location of the bullet
        this.direction=dir;

        //speed of the bullet(tiles per second)
        speed=5;

        //speed of the bullet(floats per milli second)
        speedPerMilliS=(speed*tileSize)/1000;


        //bullet created time
        iniTime=System.currentTimeMillis();
    }




    /**
     * return the current position of the bullet according to the direction
     * @return
     */
    public float getFloatX() {

        float output=0f;

        //if the bullet is headin north
        if(direction==0){
            output= initX;
        }

        //if the bullet is heading East
         else if(direction==1){
             output= initX-speedPerMilliS*(iniTime-System.currentTimeMillis());

         }

        //if the bullet is heading south
         else if(direction==2){
             output= initX;

         }

        //if the bullet is heading West
         else if(direction==3){
             output= initX+speedPerMilliS*(iniTime-System.currentTimeMillis());

         }

        return output;
        
    }

    /**
     * Return the current tile x coordinate
     */

    public int getX(){

        int output=0;

        //if the bullet is headin north
        if(direction==0){
            output= (int) (initX / tileSize);
        }

        //if the bullet is heading East
         else if(direction==1){
             output= (int) ((initX - speedPerMilliS * (iniTime - System.currentTimeMillis())) / tileSize);

         }

        //if the bullet is heading south
         else if(direction==2){
             output= (int) (initX / tileSize);

         }

        //if the bullet is heading West
         else if(direction==3){
             output= (int) ((initX + speedPerMilliS * (iniTime - System.currentTimeMillis())) / tileSize);

         }

        return output;

    }


    /**
     * return the current position of the bullet according to the direction
     * @return
     */
    public float getFloatY() {
        float output=0f;

        //if the bullet is headin north
        if(direction==0){
            output= initY+speedPerMilliS*(iniTime-System.currentTimeMillis());
        }

        //if the bullet is heading East
         else if(direction==1){
             output= initY;

         }

        //if the bullet is heading south
         else if(direction==2){
             output= initY-speedPerMilliS*(iniTime-System.currentTimeMillis());

         }

        //if the bullet is heading West
         else if(direction==3){
             output= initY;

         }

        return output;
    }

    public int getY(){

        int output=0;

        //if the bullet is headin north
        if(direction==0){
            output= (int) ((initY + speedPerMilliS * (iniTime - System.currentTimeMillis())) / tileSize);
        }

        //if the bullet is heading East
         else if(direction==1){
             output= (int) initY;

         }

        //if the bullet is heading south
         else if(direction==2){
             output= (int) ((initY - speedPerMilliS * (iniTime - System.currentTimeMillis())) / tileSize);

         }

        //if the bullet is heading West
         else if(direction==3){
             output= (int) initY;

         }

        return output;
    }

   

    public int getDirection() {
        return direction;
    }

    



}
