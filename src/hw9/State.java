package hw9;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class State {
    private final Map<Character, Set<State>> nextState = new ConcurrentHashMap<>();
    private final Set<State> epsilonStates = new HashSet<>();

    public void addNextState(char c, State ns) {
        nextState.computeIfAbsent(c, k -> Collections.synchronizedSet(new HashSet<>())).add(ns);
    }

    public void addEpsilonState(State ns) {
        epsilonStates.add(ns);
    }

    public Set<State> getNextState(char c) {
        return nextState.getOrDefault(c, Collections.emptySet());
    }

    public Set<State> getNextEpsilon() {
        return epsilonStates;
    }
}
