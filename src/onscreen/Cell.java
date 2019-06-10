package onscreen;

import java.awt.*;
import java.awt.event.*;
import main.*;
import java.util.concurrent.ThreadLocalRandom;

public class Cell implements MouseObserver {
	public int x;
	public int y;
	public int color = ThreadLocalRandom.current().nextInt(1, 4);
	public Color P = new java.awt.Color(10*color + 50,10*color + 50,0);
	Rectangle bounds;
	Color drawColour;

  public Cell(){x = 0; y = 0;}
  public Cell(int x, int y) {
    this.x = x;
    this.y = y;
    this.bounds = new Rectangle(x*35+10,y*35+10,35,35);
    drawColour = P;
  }

  public void mouseLeft(MouseEvent e){
    drawColour = P;
  }

  public void mouseEntered(MouseEvent e){
    drawColour = Color.DARK_GRAY;
  }

  public void mouseClicked(MouseEvent e){}
  
	@Override
	public Rectangle getBounds() {
		return bounds;
	}

  public void draw(Graphics g){
    g.setColor(drawColour);
    g.fillRect(x*35+10,y*35+10,35,35);
  }

  public Point getTopLeft(){
    return new Point(x*35+10, y*35+10);
  }

  public int distanceTo(Cell other){
    return Math.abs(this.x - other.x) + Math.abs(this.y - other.y);
  }



}