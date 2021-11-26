package creatures;

import huglife.*;

import java.awt.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

public class Clorus extends Creature {

    private int r;
    private int g;
    private int b;

    public Clorus(double e) {
        super("clorus");
        r = 34;
        g = 0;
        b = 231;
        energy = e;
    }

    public Color color() {
        return color(r, g, b);
    }

    public void attack(Creature c) {
        energy+=c.energy();
    }

    public void move() {
        energy -= 0.03;
        if(energy<0) {
            energy = 0;
        }
    }

    public void stay() {
        energy -= 0.01;
        if(energy<0) {
            energy = 0;
        }
    }

    public Clorus replicate() {
        energy = 0.5 * energy;
        Clorus babyClorus=new Clorus(energy);
        return babyClorus;
    }

    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        // Rule 1
        Deque<Direction> emptyNeighbors = new ArrayDeque<>();
        for(Direction d:neighbors.keySet()){
            if(neighbors.get(d).name().equals("empty")){
                emptyNeighbors.addLast(d);
            }
        }
        if (emptyNeighbors.isEmpty()) {
            return new Action(Action.ActionType.STAY);
        }

        // Rule 2
        Deque<Direction> plipNeighbors = new ArrayDeque<>();
        for(Direction d:neighbors.keySet()){
            if(neighbors.get(d).name().equals("plip")){
                plipNeighbors.addLast(d);
            }
        }
        if(!plipNeighbors.isEmpty()){
            Direction d= HugLifeUtils.randomEntry(plipNeighbors);
            return new Action(Action.ActionType.ATTACK, d);
        }

        // Rule 3
        if(energy>=1.0){
            Direction d= HugLifeUtils.randomEntry(emptyNeighbors);
            return new Action(Action.ActionType.REPLICATE, d);
        }

        // Rule 4
        Direction d= HugLifeUtils.randomEntry(emptyNeighbors);
        return new Action(Action.ActionType.MOVE, d);
    }
}
