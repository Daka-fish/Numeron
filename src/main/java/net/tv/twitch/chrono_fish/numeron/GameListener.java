package net.tv.twitch.chrono_fish.numeron;

import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class GameListener implements Listener {

    private final Numeron numeron;

    public GameListener(Numeron numeron){
        this.numeron = numeron;
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e){
        NuPlayer nuPlayer = numeron.getNuPlayer(e.getPlayer());
        if(nuPlayer != null){
            if(nuPlayer.getNuGame().isRunning()){
                if(e.getClickedBlock() != null){
                    switch (e.getClickedBlock().getType()){
                        case ACACIA_SIGN:
                            Sign sign = (Sign) e.getClickedBlock();
                            int index = 0;
                            int currentNumber = 0;
                            try {
                                index = Integer.parseInt(sign.getLine(0));
                                currentNumber = Integer.parseInt(sign.getLine(1));
                            } catch (NumberFormatException ex) {
                                currentNumber = 0;
                            }
                            int next = currentNumber + 1;
                            sign.setLine(1, String.valueOf(next));
                            sign.update();

                            nuPlayer.setNumber(index, currentNumber);
                            break;

                        case STONE_BUTTON:
                            nuPlayer.attack();
                            break;

                        default:
                    }
                }
            }
        }
    }
}
