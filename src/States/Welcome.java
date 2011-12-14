/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package States;

import Controlling.StringDecoder;
import Controlling.StringGenerator;
import Networking.ServerConfigParser;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author ishan
 *
 * This state is for the welcome screen which will be shown before the game starts.
 * It includes a way to send the JOIN# message, Exit the game.
 *
 * Also it will show notifications for PLAYERS_FULL#, ALREADY_ADDED#,
 * and GAME_ALREADY_STARTED#
 */
public class Welcome extends BasicGameState {

    /** The ID given to this state */
    public static final int id = 1;
    //The string generator which will be used to start the game
    private StringGenerator generator;
    //the string decoder which is used to change the game states
    private StringDecoder decoder;
    
    //The server config
    private ServerConfigParser scp;
    
    //for music
    Music backMusic;

    @Override
    public int getID() {
        return id;
    }

    /*
     * All the persistent data that are needed for the game should be
     * loaded in here and added to the relavant locations.
     */
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {

        //set the current game
        decoder.setCurrentGame(sbg);


        ///set the server config parser
        scp=new ServerConfigParser();
        ///
       // generator.join();
        ///

        //System.out.println(scp.getServerAddress());
        
        //load music
        backMusic = new Music("data"+System.getProperty("file.separator") +"music"+System.getProperty("file.separator") +"welcome.wav");
        // backMusic.play();
       

    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {

        grphcs.setColor(Color.green);
        grphcs.drawString("Welcome", 650, 100);
        
        

        grphcs.setColor(Color.white);
        grphcs.drawString("Press Enter to start the game", 550, 300);
        
        grphcs.drawString("Current server setup:", 100, 400);
        grphcs.drawString("Server: "+scp.getServerAddress(), 120, 450);
        grphcs.drawString("In port: "+scp.getServerInPort(), 120, 470);
        grphcs.drawString("Out port: "+scp.getServerOutPort(), 120, 490);
        
        
        
        
        
       
    }

    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        
        if (sbg.getCurrentStateID() == id && !backMusic.playing()) {
            backMusic.play();
        } else if (sbg.getCurrentStateID() != id) {

            backMusic.stop();
        }
    }

    @Override
    public void keyReleased(int key, char c) {

        if (key == Input.KEY_ENTER) {

            generator.join();

        }
    }

    public void setStringGeneretator(StringGenerator generator) {

        this.generator = generator;
    }

    public void setStringDecoder(StringDecoder decoder) {

        this.decoder = decoder;
    }
}
