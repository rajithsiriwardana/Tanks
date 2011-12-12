
import Controlling.StringDecoder;
import Controlling.StringGenerator;
import Map.Map;
import Networking.Receiver;
import States.CannotConnect;
import States.GameOver;
import States.Play;
import States.Welcome;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ishan
 */
public class Game extends StateBasedGame {

    //server data
    private int outPort;
    private int inPort;
    private String server;
    private Receiver receiver;
    private StringGenerator generator;
    private StringDecoder decoder;
    //map
    private Map map;
    //States
    private Welcome welcome;
    private Play play;
    private GameOver gameOver;
    private CannotConnect cannotConnect;

    public Game() {
        super("Tank Game");

        //server data
        outPort = 6000;
        inPort = 7000;
        server = "localhost";

        //create the string sender
        generator = new StringGenerator(outPort, server);


        //create the map
        map = new Map();

        //create the string decoder
        decoder = new StringDecoder(map);





    }

    @Override
    public void initStatesList(GameContainer gc) throws SlickException {

        //create the string receiver
        receiver = new Receiver(inPort, decoder);
        new Thread(receiver).start();


        //add a reference of the sender to the welcome screen
        welcome = new Welcome();
        welcome.setStringGeneretator(generator);
        welcome.setStringDecoder(decoder);

        play = new Play();
        play.setMap(map);
        //play.setMyPlayer(myPlayer);
        play.setStrGenerator(generator);
        play.setStrDecoder(decoder);


        gameOver = new GameOver();
        cannotConnect=new CannotConnect();


        addState(welcome);
        addState(play);
        addState(gameOver);
        addState(cannotConnect);
    }
}
