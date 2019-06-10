package onscreen;

import behaviours.Behaviour;
import main.*;

public class Wolf extends Character {
    public Wolf(Cell location, Behaviour behaviour){
        super(location, new java.awt.Color(255,0,0), new java.awt.Color(153,0,0), behaviour);
    }
}
