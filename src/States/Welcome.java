/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package States;

import Controlling.StringDecoder;
import Controlling.StringGenerator;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
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
    StringGenerator generator;
    //the string decoder which is used to change the game states
    StringDecoder decoder;
    ///
    JButton but;
    JPanel pan;

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

        pan = new JPanel(new BorderLayout());


        but = new JButton("s");
        pan.add(but);



    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {

        grphcs.setColor(Color.white);
        grphcs.drawString("Welcome", 140, 100);

        grphcs.setColor(Color.white);
        grphcs.drawString("Press Enter to start the game", 50, 300);
        pan.setVisible(true);
    }

    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
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
