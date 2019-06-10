package onscreen;

import behaviours.Behaviour;
import lib.Rabbit;
import main.Direction;
import main.Stage;

import java.awt.*;

public class RabbitAdaptor extends Character {
    private Rabbit rabbit = new Rabbit();

    public RabbitAdaptor(Cell location) {
        super(location, Color.LIGHT_GRAY, Color.BLUE, null);
        this.behaviour = new RabbitBehaviour();
    }

    /**
     * An inner class has to have a containing instance of the outer class.
     * That is, each RabbitBehaviour has to belong to a RabbitAdaptor - and can't be assigned to, say, a wolf.
     * Note that we can refer to the fields of the containing class directly, as in rabbit.nextMove()
     */
    private class RabbitBehaviour implements Behaviour {

        @Override
        public Cell execute(Cell location) {
            Stage stage = Stage.getInstance();
            switch(rabbit.nextMove()) {
                case 0: return stage.getAdjacent(location, Direction.W); //Left
                case 1: return stage.getAdjacent(location, Direction.E); //Right
                case 2: return stage.getAdjacent(location, Direction.N); //Up
                case 3: return stage.getAdjacent(location, Direction.S); //Down
                default: return location;
            }
        }
    }
}