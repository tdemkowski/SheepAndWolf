package onscreen;

import behaviours.Behaviour;

public class Pit extends Character {
    public Pit(Cell location, Behaviour behaviour){
        super(location, new java.awt.Color(0,0,150), new java.awt.Color(0, 0, 150), new behaviours.Passive());
    }
}
