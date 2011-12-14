/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package States;

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
 */
public class GameOver extends BasicGameState {

    /** The ID given to this state */
    public static final int id = 3;
    //to store the current game
    private StateBasedGame game;
    //for sound
    Music backMusic;

    @Override
    public int getID() {

        return id;
    }

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        this.game = sbg;

        //load music
        backMusic = new Music("data"+System.getProperty("file.separator") +"music"+System.getProperty("file.separator") +"3.wav");
        // backMusic.play();

    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {

        grphcs.drawString("GAME OVER", 650, 350);
        
        grphcs.drawString("B - Move back to the map", 100, 500);
        

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
        if (key == Input.KEY_B) {
            game.enterState(Play.id);

        }
    }
}
