package net.tv.twitch.chrono_fish.numeron;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

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
        boolean[] checked = new boolean[3];
        int[] targetSecretNumbers;

        NuPlayer target = null;

        if (nuGame.isSolo()) {
            // ソロモードの場合、ゲーム側の秘密の数字を使用
            targetSecretNumbers = nuGame.getSoloSecretNumber();
        } else {
            // 通常モードの場合、対戦相手の秘密の数字を使用
            target = (nuGame.getFirstP().equals(this)) ? nuGame.getSecondP() : nuGame.getFirstP();
            targetSecretNumbers = target.getSecretNumbers();
        }

        // Eatの判定
        for (int index = 0; index < 3; index++) {
            if (guessNumber[index] == targetSecretNumbers[index]) {
                eat++;
                checked[index] = true;
            }
        }

        // Biteの判定
        for (int index = 0; index < 3; index++) {
            if (!checked[index]) {
                for (int j = 0; j < 3; j++) {
                    if (!checked[j] && guessNumber[index] == targetSecretNumbers[j]) {
                        bite++;
                        checked[j] = true;
                        break;
                    }
                }
            }
        }

        // 結果の表示
        this.getPlayer().sendMessage("Eat: " + eat + ", Bite: " + bite);

        player.sendMessage("Eat: " + eat + ", Bite: " + bite);
        if (eat == 3) {
            nuGame.setRunning(false);
            if(nuGame.isSolo()){
                player.sendMessage("You success!");
            }else{
                player.sendMessage("You won!");
                target.getPlayer().sendMessage("You lost!");
            }

        }
    }

    public void useItem(ItemStack item) {
        switch(item.getType()) {
            case CROSSBOW:
                // DOUBLE アイテムの処理
                // 例: 2回連続で数字を宣言できる
                break;

            case COMPASS:
                // HIGH_AND_LOW アイテムの処理
                // 例: 相手の数字列がHighかLowかを判定
                break;

            case TARGET:
                // TARGET アイテムの処理
                // 例: 1つの数字がどの桁に入っているかを判定
                break;

            case BUCKET:
                // SLASH アイテムの処理
                // 例: 最大の数字から最小の数字を引いた結果を知る
                break;

            case ELYTRA:
                // SHUFFLE または CHANGE アイテムの処理
                // SHUFFLE: 自分の数字をシャッフルする
                // CHANGE: 数字の一部を入れ替える
                break;

            default:
                // 未知のアイテムの場合
                break;
        }
    }
}
