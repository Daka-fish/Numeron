package net.tv.twitch.chrono_fish.numeron;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public enum NuItem {
    DOUBLE("DOUBLE", Material.CROSSBOW,
            "§c[攻撃系]§f", "自分のターンに2回数字列を宣言できる。",
            "ただし、相手に指定された桁の数字を開示しなければならない。",
            "この数字の開示は数字列の宣言より先に行う。"),

    HIGH_AND_LOW("HIGH_AND_LOW", Material.COMPASS,
            "§c[攻撃系]§f",
            "相手の数字列について、", "左からそれぞれHighかLowかを知ることができる。"),

    TARGET("Target", Material.TARGET,
            "§c[攻撃系]§f",
            "数字を1つ宣言する。", "その数字が相手の数字列に含まれている場合、",
            "どの桁に入っているかを知ることができる。"),

    SLASH("SLASH", Material.BUCKET,
            "§c[攻撃系]§f",
            "相手の数字列において、", "最大の数字から最小の数字を減算した",
            "「スラッシュナンバー」を知ることができる。"),

    SHUFFLE("SHUFFLE", Material.ELYTRA,
            "§9[防御系]§f",
            "自分の数字列をシャッフルできる。", "ただし、新たに他の数字を設定することはできない。",
            "必ずしも場所を入れ替える必要は無い。"),

    CHANGE("CHANGE", Material.ELYTRA,
            "§9[防御系]§f",
            "自分の数字列から一つだけ他の数字と入れ替える。",
            "ただし、その数字の桁とHighまたはLowを開示しなければならない。",
            "さらに、交換する数字はHigh同士またはLow同士である必要がある。");

    private final String name;
    private final Material material;
    private final String[] loreLines;

    NuItem(String name, Material material, String... loreLines) {
        this.name = name;
        this.material = material;
        this.loreLines = loreLines;
    }

    public ItemStack getItem() {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        ArrayList<Component> lore = new ArrayList<>();
        for (String line : loreLines) {
            lore.add(Component.text(line));
        }
        meta.displayName(Component.text(name));
        meta.lore(lore);
        item.setItemMeta(meta);
        return item;
    }
}
