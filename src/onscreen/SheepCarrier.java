package onscreen;

import java.awt.*;

public class SheepCarrier extends CharacterDecorator {
    public SheepCarrier(Character c) {
        super(c);
    }

    @Override
    public void draw(Graphics g) {
        character.draw(g);
        Rectangle r = character.getBounds();
        g.setColor(Color.WHITE);
        g.fillOval(r.x + 5, r.y + 5, 25, 25);
    }
}
