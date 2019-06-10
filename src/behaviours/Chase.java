package behaviours;
import main.*;
import onscreen.*;

public class Chase implements Behaviour {
  onscreen.Character target;
  
  public Chase(onscreen.Character target){this.target = target;}

  public Cell execute(Cell location){

    return Stage.getInstance().oneCellCloserTo(location, target.getLocation());
  }

}