package main;

import java.awt.*;
import java.util.Iterator;

import onscreen.*;

public class Grid implements Iterable<Cell> {
	Cell[] cells = new Cell[400];
	
	public Grid(){
		for(int i = 0; i < 400; i++) cells[i] = new Cell(i / 20, i % 20); //mathematical!
	}

	public void draw(Graphics g){
		for(Cell c : this) c.draw(g);
	}

	public Cell getCell(int i, int j) {
		if (i < 0 || i > 19 || j < 0 || j > 19) throw new ArrayIndexOutOfBoundsException(i + ", " + j);
		return cells[i * 20 + j];
	}

	public Cell giveMeRandomCell(){
		int x = java.util.concurrent.ThreadLocalRandom.current().nextInt(0, 20);
		int y = java.util.concurrent.ThreadLocalRandom.current().nextInt(0, 20);
		return cells[x*20+y];
	}

	public Cell cellAt(Point point){
		for(int i = 0; i < 20; i++){
		  	for(int j = 0; j < 20; j++){
		  		Cell current = cells[i*20 + j];
		  		if (current.getBounds().contains(point)) {
		  			return current;
	        }
		  	}
		}
		return null;
	}

	public Iterator<Cell> iterator() {
		return new GridIterator();
	}

	private class GridIterator implements Iterator<Cell> {
		private int index;
		public boolean hasNext() { return index < cells.length; }
		public Cell next() { return cells[index++]; }
	}
}