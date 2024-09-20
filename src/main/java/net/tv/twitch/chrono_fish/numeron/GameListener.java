package net.tv.twitch.chrono_fish.numeron;

import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

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
                            Sign sign = (Sign) e.getClickedBlock().getState();
                            int index = 0;
                            int currentNumber = 0;
                            try {
                                index = Integer.parseInt(sign.getLine(0));
                                currentNumber = Integer.parseInt(sign.getLine(1));
                            } catch (NumberFormatException ex) {
                                currentNumber = 0;
                            }

                            int next = (currentNumber + 1) % 10;

                            sign.setLine(1, String.valueOf(next));
                            sign.update();

                            nuPlayer.setGuessNumber(currentNumber,index);
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

    @EventHandler
    public void onClick(InventoryClickEvent e){
        NuPlayer nuPlayer = numeron.getNuPlayer((Player) e.getWhoClicked());
        if(nuPlayer != null){
            NuGame nuGame = nuPlayer.getNuGame();
            ItemStack clickedItem = e.getCurrentItem();
            if(nuGame.isRunning()){
                if(clickedItem != null && clickedItem.getType() != Material.AIR){
                    e.setCancelled(true);
                    nuPlayer.useItem(clickedItem);
                }
            }
        }
    }
}
