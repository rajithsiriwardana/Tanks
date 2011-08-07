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




    public float getFloatX() {

        //return the current postion
        return initX+speedPerMilliS*(iniTime-System.currentTimeMillis());
    }


    public float getFloatY() {
        return initY;
    }

    public void setY(float y) {
        this.initY = y;
    }

    public int getDirection() {
        return direction;
    }

    



}
