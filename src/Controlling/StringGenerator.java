/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlling;

import Networking.Sender;

/**
 *
 * @author ishan
 */
public class StringGenerator {

    private Sender sender;

    public StringGenerator(int outPort, String server) {
        sender = new Sender(outPort, server);
    }

    public void join() {

        sender.send("JOIN#");


    }

    public void goUp() {
        sender.send("UP#");

    }

    public void goDown() {
        sender.send("DOWN#");
    }

    public void goLeft() {
        sender.send("LEFT#");
    }

    public void goRight() {
        sender.send("RIGHT#");
    }

    public void shoot() {
        sender.send("SHOOT#");
    }
}
