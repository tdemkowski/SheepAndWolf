package onscreen;

import java.awt.*;
import java.awt.event.*;
import main.*;

public class HighlightedCharacter extends CharacterDecorator{
  Rectangle biggerBounds;

  public HighlightedCharacter(Character c){
    super(c);
    biggerBounds = new Rectangle(character.getLocation().getTopLeft().x - 35, character.getLocation().getTopLeft().y - 35, 105, 105);
  }

  public void draw(Graphics g){
    character.draw(g);
    g.setColor(Color.WHITE);
    g.drawRect(character.getLocation().getTopLeft().x + 5, character.getLocation().getTopLeft().y + 5, 25, 25);

    g.drawRect(biggerBounds.x, biggerBounds.y, biggerBounds.width, biggerBounds.height);
  }

  public Rectangle getBounds(){return biggerBounds;}

  public void mouseClicked(MouseEvent e){
    Stage stage = Stage.getInstance();
    this.setLocation(stage.grid.cellAt(e.getPoint()));
    stage.shepherd = character;
  }
}