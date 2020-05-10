package ai.multimcts;

import action.Action;
import ai.mcts.MctsNode;
import game.GameState;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Node {

    GameState game;
    int visit = 0;
    int value = 0;
    List<Action> past;
    List<Action> future = null;


    public List<Action> getPast() {
        return past;
    }

    public void setPast(List<Action> past) {
        this.past = past;
    }

    public List<Action> getFuture() {
        return future;
    }

    public void setFuture(ArrayList<Action> future) {
        this.future = future;
    }

    public Node(GameState game, int visit,  List<Action> past, int value, List<Action> future){
        this.game = game;
        this.visit = visit;
        this.past = past;
        this.value = value;
        this.future = future;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }


    public void setGame(GameState game) {
        this.game = game;
    }

    public void setVisit(int visit) {
        this.visit += visit;
    }

    public int getVisit() {
        return visit;
    }

    public GameState getGame() {
        return game;
    }

    public boolean isleaf(LinkedList<Node> visited, Node check) {
        if(visited.removeLast() == check){
            return true;
        }else return false;
    }

    public void expand() {
    }

    public Node selectRandom(LinkedList<Node> visited,  Node cur) {
        int index = visited.indexOf(cur);
        int n = 0;
        n = index + (int)(Math.random() * ((visited.size() - index) + 1));
        return visited.get(n);
    }
}
