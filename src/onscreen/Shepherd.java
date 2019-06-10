package onscreen;

import behaviours.Behaviour;
import behaviours.Passive;
import main.*;
import java.awt.*;
import java.awt.event.*;

public class Shepherd extends Character {
    public Shepherd(Cell location, Behaviour behaviour){
        super(location, new Color(0,153,0), new Color(0,255,0), behaviour);
    }

    public void mouseClicked(MouseEvent e){
        Stage stage = Stage.getInstance();
        stage.shepherd = new HighlightedCharacter(stage.shepherd);
    }

}

