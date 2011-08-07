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

    //stores the location of the bullet
    private float x;
    private float y;

    //stores the map drawn place
    private float mapX;
    private float mapY;

    //stores the tile size
    private float tileSize;

    public Bullet(int intX, int intY) {

        //map drawn locations
        mapX=0f;
        mapY=0f;

        //tile size
        tileSize=35f;

        //set the bullet location in float
        x=intX*tileSize+mapX;
        y=intY*tileSize+mapY;

    }




    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }


}
