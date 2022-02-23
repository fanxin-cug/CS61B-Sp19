package bearmaps.hw4;

import bearmaps.proj2ab.ArrayHeapMinPQ;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class AStarSolver<Vertex> implements ShortestPathsSolver<Vertex> {
    private SolverOutcome outcome;
    private double solutionWeight;
    private List<Vertex> solution;
    private double timeSpent;
    private int numStatesExplored;

    private HashMap<Vertex, Double> distTo;
    private HashMap<Vertex, Vertex> edgeTo;
    private ArrayHeapMinPQ<Vertex> pq;

    public AStarSolver(AStarGraph<Vertex> input, Vertex start, Vertex end, double timeout) {
        Stopwatch sw = new Stopwatch();
        distTo = new HashMap<>();
        edgeTo = new HashMap<>();
        distTo.put(start,0.0);
        edgeTo.put(start,null);
        pq = new ArrayHeapMinPQ<>();
        pq.add(start, input.estimatedDistanceToGoal(start, end));
        numStatesExplored = 0;
        solutionWeight = 0.0;
        solution = new ArrayList<>();

        while(pq.size()!=0 && !pq.getSmallest().equals(end)) {
            Vertex p = pq.removeSmallest();
            numStatesExplored += 1;
            List<WeightedEdge<Vertex>> edges = input.neighbors(p);
            for(WeightedEdge<Vertex> e : edges) {
                relax(input,e,end);
            }
            timeSpent = sw.elapsedTime();
            if(timeSpent > timeout) {
                outcome = SolverOutcome.TIMEOUT;
                break;
            }
        }

        if(outcome == SolverOutcome.TIMEOUT) {
            return;
        } else if(pq.size() == 0) {
            outcome = SolverOutcome.UNSOLVABLE;
        } else {
            outcome = SolverOutcome.SOLVED;
            solutionWeight = distTo.get(end);
            Stack<Vertex> s = new Stack<>();
            s.push(end);
            Vertex v = end;
            while(edgeTo.get(v) != null) {
                s.push(edgeTo.get(v));
                v = edgeTo.get(v);
            }
            while(!s.empty()) {
                solution.add(s.pop());
            }
        }
        timeSpent = sw.elapsedTime();
    }

    private void relax(AStarGraph<Vertex> input, WeightedEdge<Vertex> e, Vertex end) {
        Vertex p = e.from();
        Vertex q = e.to();
        double w = e.weight();

        if(distTo.get(p) + w < distTo.getOrDefault(q, Double.MAX_VALUE)) {
            distTo.put(q, distTo.get(p) + w);
            edgeTo.put(q, p);
            if(pq.contains(q)) {
                pq.changePriority(q, distTo.get(q) + input.estimatedDistanceToGoal(q, end));
            } else {
                pq.add(q, distTo.get(q) + input.estimatedDistanceToGoal(q, end));
            }
        }
    }

    @Override
    public SolverOutcome outcome() {
        return outcome;
    }

    @Override
    public List<Vertex> solution() {
        return solution;
    }

    @Override
    public double solutionWeight() {
        return solutionWeight;
    }

    @Override
    public int numStatesExplored() {
        return numStatesExplored;
    }

    @Override
    public double explorationTime() {
        return timeSpent;
    }
}
