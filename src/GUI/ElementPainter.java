/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Map.Brick;
import Map.CoinPack;
import Map.LifePack;
import Map.Map;
import Map.MapElement;
import Map.Player;
import Map.Stone;
import Map.Water;
import java.util.LinkedList;
import java.util.ListIterator;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import java.awt.Font;
import org.newdawn.slick.Color;
import org.newdawn.slick.UnicodeFont;

/**
 *
 * @author ishan
 */
public class ElementPainter {

    /*
     * For animation testing
     */
    private int delta;

    /*
     *
     */
    private Map map;
    //images of the map
    private Image brickImage;
    private Image stoneImage;
    private Image waterImage;
    private Image lpImage;
    private Image cpImage;
    //players images
    private Image myPlayerImage;
    private Image up;
    private Image down;
    private Image left;
    private Image right;
    //sets the tile size
    private int tileSize = 35;
    private Player myPlayer;
    //sprite animation details
    Image[] movementUp;
    Image[] movementDown;
    Image[] movementLeft;
    Image[] movementRight;
    int[] duration;
    //to draw graphics ( like strings)
    private Graphics graphics;

    public ElementPainter(Map map, Image brickImage, Image stoneImage, Image waterImage, Image myPlayerImg, Player myPlayer, Image lpImage, Image cpImage) {
        this.map = map;
        this.brickImage = brickImage;
        this.stoneImage = stoneImage;
        this.waterImage = waterImage;
        this.myPlayer = myPlayer;
        this.myPlayerImage = myPlayerImg;
        this.lpImage = lpImage;
        this.cpImage = cpImage;

        /*
         * For animation
         */

        delta = 0;

        /*
         *
         */



    }

    public void setMyPlayer(Player myPlayer) {
        this.myPlayer = myPlayer;
    }

    //draws the map
    public void draw(int delta, Graphics graphics) {


        /*
         * for animation
         */
        this.delta = delta;
        /*
         *
         */

        this.graphics = graphics;


//        this.graphics.setFont(uFont);
//        this.graphics.drawString("TEST", 10f, 10f);

        //Draw bricks
        LinkedList<Brick> bricks = map.getBricks();

        ListIterator iterator = bricks.listIterator();


        if (iterator.hasNext()) {

            Brick brick = (Brick) iterator.next();


            while ((brick) != null) {

                if (!brick.isDestroyed()) {
                    drawBrick(brick);
                }


                if (iterator.hasNext()) {
                    brick = (Brick) iterator.next();

                } else {

                    break;
                }


            }


        }

        //Draw stones
        LinkedList<Stone> stones = map.getStones();

        ListIterator stoneIter = stones.listIterator();


        if (stoneIter.hasNext()) {

            Stone stone = (Stone) stoneIter.next();


            while ((stone) != null) {

                drawStone(stone);


                if (stoneIter.hasNext()) {
                    stone = (Stone) stoneIter.next();

                } else {

                    break;
                }


            }


        }

        //Draw water
        LinkedList<Water> sea = map.getWater();

        ListIterator waterIter = sea.listIterator();


        if (waterIter.hasNext()) {

            Water water = (Water) waterIter.next();


            while ((water) != null) {

                drawWater(water);


                if (waterIter.hasNext()) {
                    water = (Water) waterIter.next();

                } else {

                    break;
                }


            }


        }




        //Draw life packs

        LinkedList<LifePack> lifePacks = map.getLifePacks();

        ListIterator lpIter = lifePacks.listIterator();

        LifePack lifePack;

        if (lpIter.hasNext()) {
            lifePack = (LifePack) lpIter.next();

            while ((lifePack) != null) {

                if (lifePack.isAvailable()) {
                    drawLP(lifePack);
                }


                if (lpIter.hasNext()) {
                    lifePack = (LifePack) lpIter.next();

                } else {

                    break;
                }


            }


        }

        //draw coin packs
        LinkedList<CoinPack> coinPacks = map.getCoinPacks();

        ListIterator iter = coinPacks.listIterator();

        CoinPack coinPack;

        if (iter.hasNext()) {
            coinPack = (CoinPack) iter.next();

            while (coinPack != null) {

                if (coinPack.isAvailable()) {
                    drawCoinPack(coinPack);
                }

                if (iter.hasNext()) {
                    coinPack = (CoinPack) iter.next();
                } else {
                    break;
                }

            }
        }


        //Draw players
        LinkedList<Player> players = map.getContestants();

        ListIterator playerIter = players.listIterator();

        Player player;

        if (playerIter.hasNext()) {
            player = (Player) playerIter.next();

            while ((player) != null) {

                if (player.isAlive()) {
                    drawPlayer(player);
                }




                if (playerIter.hasNext()) {
                    player = (Player) playerIter.next();

                } else {

                    break;
                }


            }


        }


        myPlayer = map.getMyPlayer();
        //Draw my player
        if (myPlayer != null) {

            //draw only if the player is alive
            if (myPlayer.isAlive()) {

                drawPlayer(myPlayer);
            }

        }




    }

    //draws the player
    private void drawPlayer(Player Player) {


        /*
         *
         * Rewrite this method to support 5 different players
         */

        /*
         * to support animation
         */

        //to control the speed of the animation
        float speed = 0.03f;

        //to control the error of the animation
        float error = 2f;

        //the true position of the sprite
        float trueX = 0f, trueY = 0f;

        //the current position of the sprite

        float currentX = 0f, currentY = 0f;
        /*
         *
         */


        //checks whether the player exists
        if (Player != null) {
            //System.out.println("1");
            //System.out.println("draw player");

            // myPlayerImage.draw(converter(Player.getPlayerX()), converter(Player.getPlayerY()));

            //set the direction of the sprite
            if (Player.getPlayerDir() == 0) {
                myPlayerImage = up;


            } else if (Player.getPlayerDir() == 1) {
                myPlayerImage = right;



            } else if (Player.getPlayerDir() == 2) {
                myPlayerImage = down;

            } else if (Player.getPlayerDir() == 3) {
                myPlayerImage = left;

            }
//////
//////            myPlayerImage.draw(playerDataConverter(Player.getPlayerX()), playerDataConverter(Player.getPlayerY()));

            //set the true location of the player
            trueX = playerDataConverter(Player.getPlayerX());
            trueY = playerDataConverter(Player.getPlayerY());

            /*get the earlier location of the player
             * (The location that the sprite was drawn)
             */

            currentX = Player.getEarlierX();
            currentY = Player.getEarlierY();

            //if the player has gone up
            if (trueY < currentY - error) {
                //Sprite.update?? :s

                // y-= delta* 0.1f;
                myPlayerImage = up;
                currentY -= delta * speed;
                Player.setDrawnY(currentY);
                // System.out.println("UP");

            } //if the player has gone down
            else if (trueY > currentY + error) {
                //Sprite.update?? :s

                // y-= delta* 0.1f;
                myPlayerImage = down;
                currentY += delta * speed;
                Player.setDrawnY(currentY);
                // System.out.println("DOWN");

            } //if the player has gone right
            else if (trueX > currentX + error) {
                //Sprite.update?? :s

                // y-= delta* 0.1f;
                myPlayerImage = right;
                currentX += delta * speed;
                Player.setDrawnX(currentX);
                // System.out.println("RIGHT:"+trueX+","+currentX);
                // System.out.println("RIGHT");

            } //if the player has gone left
            else if (trueX < currentX - error) {
                //Sprite.update?? :s

                // y-= delta* 0.1f;
                myPlayerImage = left;
                currentX -= delta * speed;
                Player.setDrawnX(currentX);
                // System.out.println("LEFT:"+trueX+","+currentX);
                // System.out.println("LEFT");

            }

            myPlayerImage.draw(currentX, currentY);

            //System.out.println("DRAW");

        }

    }

    /**
     * draws sea in the map
     * @param water
     */
    private void drawWater(Water water) {

        waterImage.draw(converter(water.getxLocation()), converter(water.getyLocation()));
    }

    /**
     * draws life packs in the map
     * @param lp
     */
    private void drawLP(LifePack lp) {

        float x = converter(lp.getxLocation());
        float y = converter(lp.getyLocation());
        lpImage.draw(x + 10, y + 10);
        graphics.setColor(Color.black);
        graphics.drawString("" + lp.timeLeft(), x, y);
        graphics.setColor(Color.white);
    }

    /**
     * draws coin packs in the map
     * @param cp
     */
    private void drawCoinPack(CoinPack cp) {

        float x = converter(cp.getxLocation());
        float y = converter(cp.getyLocation());
        cpImage.draw(x, y);



        graphics.drawString("" + cp.getAmount(), x, y);


        //graphics.setFont(uFont);

        graphics.drawString("" + cp.timeLeft(), x, y + 10);

    }

    /*
     * draws a brick in the map
     */
    private void drawBrick(Brick brick) {


        float x = converter(brick.getxLocation());
        float y = converter(brick.getyLocation());
        brickImage.draw(x, y);
        graphics.drawString("" + brick.getHealth(), x + 10f, y + 10f);

    }

    private void drawStone(Stone stone) {
        stoneImage.draw(converter(stone.getxLocation()), converter(stone.getyLocation()));

    }

    /**
     * converts int values to float
     * @param value
     * @return
     */
    private float converter(int value) {

        return (float) value * tileSize;

    }

    /*
     * convert int values to float (for players)
     */
    private float playerDataConverter(int value) {
        return (float) value * tileSize - 8f;

    }


    /*
     *
     * Should modify the below two methods to add multiplayer support
     */
    public void setPlayerImages(Image up, Image down, Image left, Image right) {

        this.up = up;
        this.down = down;
        this.left = left;
        this.right = right;

    }

    public void setMyPlayerImage(Image myPlayerImage) {
        this.myPlayerImage = myPlayerImage;
    }
}
