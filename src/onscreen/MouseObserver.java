package onscreen;

import java.awt.*;
import java.awt.event.*;
import main.*;

public interface MouseObserver {
	public void      mouseEntered(MouseEvent e);
	public void      mouseLeft(MouseEvent e);
  public void      mouseClicked(MouseEvent e);
	public Rectangle getBounds();
}
