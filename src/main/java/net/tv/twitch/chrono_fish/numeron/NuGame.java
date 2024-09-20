package net.tv.twitch.chrono_fish.numeron;

import java.util.ArrayList;
import java.util.Collections;

public class NuGame {

    private final int id;
    private boolean isRunning;

    private int turn;
    private NuPlayer turnPlayer;

    private NuPlayer firstP;
    private NuPlayer secondP;

    private boolean solo;
    private final int[] soloSecretNumber;

    public NuGame(int id){
        this.id = id;
        this.isRunning = false;
        this.turn = 0;
        this.solo = false;
        this.soloSecretNumber = new int[3];
    }

    public int getId() {return id;}

    public boolean isRunning() {return isRunning;}
    public void setRunning(boolean running) {isRunning = running;}

    public int getTurn() {return turn;}
    public void setTurn(int turn) {this.turn = turn;}

    public NuPlayer getTurnPlayer() {return turnPlayer;}
    public void setTurnPlayer(NuPlayer turnPlayer) {this.turnPlayer = turnPlayer;}

    public NuPlayer getFirstP() {return firstP;}
    public void setFirstP(NuPlayer firstP) {this.firstP = firstP;}

    public NuPlayer getSecondP() {return secondP;}
    public void setSecondP(NuPlayer secondP) {this.secondP = secondP;}

    public boolean isSolo() {return solo;}
    public void setSolo(boolean solo) {this.solo = solo;}

    public int[] getSoloSecretNumber() {return soloSecretNumber;}

    public void start(){
        if(secondP != null){
            secondP.getPlayer().sendMessage("§eソロモード§fでゲームを開始します");
            solo = true;
        }
        if(solo){
            ArrayList<Integer> numbers = new ArrayList<>();
            for(int i=0; i<10; i++){
                numbers.add(i);
            }
            Collections.shuffle(numbers);
            for(int i=0; i<3; i++){
                soloSecretNumber[i] = numbers.get(i);
            }
        }else{
            firstP.getPlayer().sendMessage("§e対戦モード§fでゲームを開始します");
            secondP.getPlayer().sendMessage("§e対戦モード§fでゲームを開始します");
        }
    }

    public void finish(){}
}
