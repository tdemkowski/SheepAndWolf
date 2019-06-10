package main;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

import behaviours.Behaviour;
import behaviours.Chase;
import behaviours.Passive;
import onscreen.*;
import main.CounterAdaptor;

public class Stage extends    javax.swing.JPanel
                   implements MouseListener,
                              MouseMotionListener {
	private static Stage instance = new Stage();

	public Grid grid;
	public onscreen.Character sheep;
	public onscreen.Character wolf;
	public onscreen.Character shepherd;
	public onscreen.Character pit;
	public onscreen.Character pit2;
	public onscreen.Character pit3;
	public onscreen.Character pit4;
	public onscreen.Character pit5;
	public onscreen.Character pit6;
	public List<onscreen.Character> rabbitHerd;
	public boolean readyToStep(){return CounterAdaptor.depleted();}
	public boolean sheepCaught = false;

	Point lastMouseLoc = new Point(0, 0);

	List<MouseObserver> observers = new ArrayList<MouseObserver>();

	public Stage() {
		grid = new Grid();
		
		for(Cell c : grid) registerMouseObserver(c);
		
		shepherd = new Shepherd(grid.giveMeRandomCell(), new Passive());
		sheep    = new Sheep(grid.giveMeRandomCell(), new Chase(shepherd));
		wolf     = new Wolf(grid.giveMeRandomCell(), new Chase(sheep));
		pit		 = new Pit(grid.giveMeRandomCell(), new Passive());
		pit2	 = new Pit(grid.giveMeRandomCell(), new Passive());
		pit3	 = new Pit(grid.giveMeRandomCell(), new Passive());
		pit4	 = new Pit(grid.giveMeRandomCell(), new Passive());
		pit5	 = new Pit(grid.giveMeRandomCell(), new Passive());
		pit6	 = new Pit(grid.giveMeRandomCell(), new Passive());
		rabbitHerd = new ArrayList<onscreen.Character>(10);
		for(int i = 0; i < 10; i++){ 
			rabbitHerd.add(i, new RabbitAdaptor(grid.giveMeRandomCell()));
		}

		registerMouseObserver(shepherd);

		addMouseListener(this);
		addMouseMotionListener(this);
	}

	public static Stage getInstance() {
		return instance;
	}

	public void paint(Graphics g) {
		draw(g);
	}

	public void draw(Graphics g) {
		grid.draw(g);
		sheep.draw(g);
		wolf.draw(g);
		shepherd.draw(g);
		pit.draw(g);
		pit2.draw(g);
		pit3.draw(g);
		pit4.draw(g);
		pit5.draw(g);
		pit6.draw(g);
		for(onscreen.Character rabbit: rabbitHerd){rabbit.draw(g);}

		if (result()){
			g.setColor(Color.BLACK);
			g.setFont(new Font(Font.DIALOG, Font.BOLD, 36));
			g.drawString("Game Over!", 200,200);
			g.drawString("Game lasted "+sheep.getSteps() +" steps", 800, 250);
		}
		else { if (gameWon()) {
			g.setColor(Color.BLACK);
			g.setFont(new Font(Font.DIALOG, Font.BOLD, 36));
			g.drawString("Game Won!", 200,200);
			g.drawString("Game lasted "+sheep.getSteps() +" steps", 800, 250);
		} }

	}
	// Dispatch thread
	public void step() {
		CounterAdaptor.set(2 + rabbitHerd.size());
		new Thread(sheep).start();
		new Thread(wolf).start();
		for(onscreen.Character rabbit: rabbitHerd){new Thread(rabbit).start();}
		if(!sheepCaught && sheep.getLocation() == shepherd.getLocation()) {
			shepherd = new SheepCarrier(shepherd);
			//sheep.changeColor(Color.GRAY);
			//(Cell location, Color c, Color sc, Behaviour behaviour)
		}
		//else { sheep.changeColor(new java.awt.Color(255,255,255)); }
	}

	public void registerMouseObserver(MouseObserver mo) {
		observers.add(mo);
	}

	public Cell oneCellCloserTo(Cell from, Cell to) {
		int xdiff = to.x - from.x;
		int ydiff = to.y - from.y;
		int newX = from.x + Integer.signum(xdiff);
		int newY = from.y + Integer.signum(ydiff);
		if (cellOccupied(newX, newY))
			return from; //i..e you can't move directly closer, so stay still.
		return grid.getCell(newX, newY);
	}

	public Cell getAdjacent(Cell cell, Direction direction) {
		int newX = cell.x + direction.dx;
		int newY = cell.y + direction.dy;
		if (cellOccupied(newX, newY))
			return cell; // i.e. the adjacent cell in that direction is this very cell (that's a modelling problem)
		try {
			return grid.getCell(newX, newY);
		} catch (ArrayIndexOutOfBoundsException e) {
			return cell; //if the adjacency is off the grid, just return the original cell.
		}
	}

	public boolean cellOccupied(int x, int y){
		for(onscreen.Character rabbit: rabbitHerd){
			if (rabbit.getLocation().x == x && rabbit.getLocation().y == y)
				return true;
		}
		return false;
	}

	// implementation of MouseListener and MouseMotionListener
	public void mouseClicked(MouseEvent e){
		if (shepherd.getBounds().contains(e.getPoint())){
		  shepherd.mouseClicked(e);
		}
	}
	public void mouseEntered(MouseEvent e){}
	public void mouseExited(MouseEvent e){}
	public void mousePressed(MouseEvent e){}
	public void mouseReleased(MouseEvent e){}
	public void mouseDragged(MouseEvent e){}
	public void mouseMoved(MouseEvent e){
		for (MouseObserver mo : observers) {
			Rectangle bounds = mo.getBounds();
			if(bounds.contains(e.getPoint())) {
				mo.mouseEntered(e);
			} else if (bounds.contains(lastMouseLoc)) {
				mo.mouseLeft(e);
			}
		}
		lastMouseLoc = e.getPoint();
	}

  public boolean result(){
  	if (shepherd.getLocation().equals(wolf.getLocation()) || shepherd.getLocation().equals(pit.getLocation()) || shepherd.getLocation().equals(pit2.getLocation())
  			|| shepherd.getLocation().equals(pit3.getLocation()) || shepherd.getLocation().equals(pit4.getLocation()) || shepherd.getLocation().equals(pit5.getLocation())
  			|| shepherd.getLocation().equals(pit6.getLocation())){
  		return true;
  	}else if (wolf.getLocation().equals(sheep.getLocation()) || wolf.getLocation().equals(pit.getLocation()) || wolf.getLocation().equals(pit2.getLocation()) ||
  			wolf.getLocation().equals(pit3.getLocation()) || wolf.getLocation().equals(pit4.getLocation()) || wolf.getLocation().equals(pit5.getLocation())
  			|| wolf.getLocation().equals(pit6.getLocation())){
  		return true;
  	} else {
  		return false;
  	}
  }
  
  public boolean gameWon() {
	  if(wolf.getLocation().equals(pit.getLocation()) || wolf.getLocation().equals(pit2.getLocation())
|| wolf.getLocation().equals(pit3.getLocation()) || wolf.getLocation().equals(pit4.getLocation()) || 
	wolf.getLocation().equals(pit5.getLocation()) || wolf.getLocation().equals(pit6.getLocation()))
		  return true;
	  return false;
  }
  

}
