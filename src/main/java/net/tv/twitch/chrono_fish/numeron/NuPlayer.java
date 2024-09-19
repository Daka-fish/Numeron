package net.tv.twitch.chrono_fish.numeron;

import org.bukkit.entity.Player;

public class NuPlayer {

    private final NuGame nuGame;
    private final Player player;

    private final int[] secretNumbers;
    private final int[] guessNumber;

    public NuPlayer(NuGame nuGame, Player player){
        this.nuGame = nuGame;
        this.player = player;
        this.secretNumbers = new int[3];
        this.guessNumber = new int[3];
    }

    public NuGame getNuGame() {return nuGame;}
    public Player getPlayer() {return player;}

    public int[] getSecretNumbers() {return secretNumbers;}
    public void setNumber(int number, int index) {
        if(number >= 0 && number < 10){
            if(index >= 0 && index < 3){
                this.secretNumbers[index] = number;
            }
        }
    }

    public int[] getGuessNumber() {return guessNumber;}
    public void setGuessNumber(int number, int index) {
        if(number >= 0 && number < 10){
            if(index >= 0 && index < 3){
                this.guessNumber[index] = number;
            }
        }
    }

    public void attack(){
        int eat = 0;
        int bite = 0;
        NuPlayer target = null;
        boolean[] checked = new boolean[3];

        if(nuGame.getFirstP().equals(this)){
            target = nuGame.getSecondP();
        }else{
            target = nuGame.getFirstP();
        }

        for(int index = 0; index<3; index++){
            if(guessNumber[index] == target.getSecretNumbers()[index]){
                eat++;
                checked[index] = true;
            }
        }

        for(int index = 0; index<3; index++){
            if(!checked[index]){
                for(int j=0; j<3; j++){
                    if (!checked[j] && guessNumber[index] == target.getSecretNumbers()[j]) {
                        bite++;
                        checked[j] = true;
                        break;
                    }
                }

            }
        }

        player.sendMessage("Eat: " + eat + ", Bite: " + bite);
        if (eat == 3) {
            nuGame.setRunning(false);
            player.sendMessage("You won!");
            target.getPlayer().sendMessage("You lost!");
        }
    }
}
