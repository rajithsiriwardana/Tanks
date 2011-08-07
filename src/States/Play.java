/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package States;

import Controlling.StringDecoder;
import Controlling.StringGenerator;
import GUI.ElementPainter;
import Map.Map;
import Map.Player;
import Networking.Receiver;
import java.awt.Font;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

/**
 *
 * @author ishan
 */
public class Play extends BasicGameState {

    /** The ID given to this state */
    public static final int id = 2;
//    private Receiver receiver;
    private StringGenerator generator;
    private StringDecoder decoder;
    //map
    private Map map;
    private TiledMap grassMap;
    //stores map position
    private int mapX;
    private int mapY;
    private ElementPainter painter;
    //images of the map
    private Image brickImage;
    private Image stoneImage;
    private Image waterImage;
    private Image lpImage;
    private Image cpImage;
    //my player
    private Player myPlayer;
    private Image myPlayerImg;
    //load playres images
    private Image up;
    private Image down;
    private Image left;
    private Image right;
    //fonts
    private TrueTypeFont ttf;
    //for animation
    private int delta;
    //music used in the game
    Music backMusic;

    @Override
    public int getID() {

        return id;
    }

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {

        try {

            //set the map location
            mapX = 0;
            mapY = 0;

            //load music
            backMusic = new Music("data/music/play.wav");

            //load map static images
            brickImage = new Image("data/map/brick.png");
            stoneImage = new Image("data/map/stone.png");
            waterImage = new Image("data/map/water.png");
            myPlayerImg = new Image("data/map/water.png");
            lpImage = new Image("data/map/LP.png");
            cpImage = new Image("data/map/coin.png");

            //load player images
            up = new Image("data/sprites/up.png");
            down = new Image("data/sprites/down.png");
            left = new Image("data/sprites/left.png");
            right = new Image("data/sprites/right.png");

            painter = new ElementPainter(map, brickImage, stoneImage, waterImage, myPlayerImg, myPlayer, lpImage, cpImage);
            painter.setPlayerImages(up, down, left, right);

            //load the map
            grassMap = new TiledMap("data/map/map.tmx");


            //load fonts
            ttf = new TrueTypeFont(new java.awt.Font("Verdana", Font.PLAIN, 10), false);




            //update the game of the string decoder
            decoder.setCurrentGame(sbg);




        } catch (SlickException ex) {
            Logger.getLogger(ElementPainter.class.getName()).log(Level.SEVERE, null, ex);
        }



    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {

        grassMap.render(mapX, mapY);
        grphcs.setFont(ttf);
        painter.draw(delta, grphcs);

    }

    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {

        Input input = gc.getInput();



        if (input.isKeyDown(input.KEY_UP)) {
            generator.goUp();


        } else if (input.isKeyDown(input.KEY_DOWN)) {
            generator.goDown();


        } else if (input.isKeyDown(input.KEY_LEFT)) {
            generator.goLeft();

        } else if (input.isKeyDown(input.KEY_RIGHT)) {
            generator.goRight();


        } else if (input.isKeyDown(input.KEY_S)) {
            generator.shoot();

        }

        //for music playback
        if (sbg.getCurrentStateID() == id && !backMusic.playing()) {
            backMusic.play();
            backMusic.loop();
        } else if (sbg.getCurrentStateID() != id) {

            backMusic.stop();
        }



        delta = i;




    }

    public void setMap(Map map) {
        this.map = map;
    }

    public void setMyPlayer(Player player) {
        this.myPlayer = player;
    }

    public void setStrGenerator(StringGenerator generator) {
        this.generator = generator;
    }

    public void setStrDecoder(StringDecoder decoder) {
        this.decoder = decoder;
    }
}
