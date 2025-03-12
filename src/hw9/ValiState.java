package hw9;

import java.util.Set;

public class ValiState extends State {
    private int idx;
    private State state;

    public ValiState(State s, int idx) {
        this.state = s;
        this.idx = idx;
    }

    public Set<State> nextState(char c) {
        return state.getNextState(c);
    }

    public Set<State> nextEpsilon() {
        return state.getNextEpsilon();
    }

    public State getState() {
        return state;
    }

    public int getIdx() {
        return idx;
    }
}
