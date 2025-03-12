package hw9;

import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NFA {
    private State startState;
    private Set<State> finalState = new HashSet<>();

    public NFA(State startState, State finalState) {
        this.startState = startState;
        this.finalState.add(finalState);
    }

    public boolean matches(String input) {
        return simulate(input);
    }

    private boolean simulate(String input) {
        char[] in = input.toCharArray();
        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        Queue<ValiState> queue = new ConcurrentLinkedQueue<>();
        queue.add(new ValiState(startState, 0));

        while (!queue.isEmpty()) {
            ValiState state = queue.poll();

            if ((state.getIdx() == in.length) && finalState.contains(state.getState())) {
                executor.shutdownNow();
                return true;
            }
            if (state.getIdx() == in.length)
                return false;

            Set<State> nextState = state.nextEpsilon();
            for (State s : nextState)
                queue.add(new ValiState(s, state.getIdx()));
            nextState = state.nextState(in[state.getIdx()]);
            for (State s : nextState)
                queue.add(new ValiState(s, state.getIdx() + 1));
        }

        return false;
    }

    public void concat(NFA b) {
        for (State s : this.finalState) {
            s.addEpsilonState(b.startState);
        }
        this.finalState = b.finalState;
    }

    public void alternation(NFA b) {
        this.startState.addEpsilonState(b.startState);
        this.finalState.addAll(b.finalState);
    }

    public void optional() {
        this.finalState.add(this.startState);
    }

    // This feels like its going to create infinite loops
    public void kleeneStar() {
        for (State s : this.finalState) {
            s.addEpsilonState(this.startState);
        }
        State newState = new State();
        this.startState.addEpsilonState(newState);
        this.finalState.add(this.startState);
        this.finalState.add(newState);
    }
}
