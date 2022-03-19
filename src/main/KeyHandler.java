package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean north, south, west, east;

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();

        if(code == KeyEvent.VK_W){
            System.out.println("North pressed");
            north = true;
        }
        if(code == KeyEvent.VK_A){
            System.out.println("West pressed");

            west = true;
        }
        if(code == KeyEvent.VK_S){
            System.out.println("South pressed");

            south = true;
        }
        if(code == KeyEvent.VK_D){
            System.out.println("East pressed");

            east = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_W){
            north = false;
        }
        if(code == KeyEvent.VK_A){
            west = false;
        }
        if(code == KeyEvent.VK_S){
            south = false;
        }
        if(code == KeyEvent.VK_D){
            east = false;
        }
    }
}
