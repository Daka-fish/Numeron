package net.tv.twitch.chrono_fish.numeron;

public class NuGame {

    private final int id;
    private boolean isRunning;
    private int turn;

    private NuPlayer firstP;
    private NuPlayer secondP;

    public NuGame(int id){
        this.id = id;
        this.isRunning = false;
        this.turn = 0;
    }

    public int getId() {return id;}

    public boolean isRunning() {return isRunning;}
    public void setRunning(boolean running) {isRunning = running;}

    public int getTurn() {return turn;}
    public void setTurn(int turn) {this.turn = turn;}

    public NuPlayer getFirstP() {return firstP;}
    public void setFirstP(NuPlayer firstP) {this.firstP = firstP;}

    public NuPlayer getSecondP() {return secondP;}
    public void setSecondP(NuPlayer secondP) {this.secondP = secondP;}
}
