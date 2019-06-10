package onscreen;

import java.awt.Color;

import behaviours.Behaviour;

public class Sheep extends Character {
	

	
    public Sheep(Cell location, Behaviour behaviour){
        super(location, new java.awt.Color(255,255,255), new java.awt.Color(224, 224, 224), behaviour);
    }

}
