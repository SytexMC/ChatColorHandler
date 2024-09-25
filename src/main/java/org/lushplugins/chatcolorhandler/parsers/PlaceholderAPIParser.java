package org.lushplugins.chatcolorhandler.parsers;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class PlaceholderAPIParser implements Parser {
    protected static final PlaceholderAPIParser INSTANCE = new PlaceholderAPIParser();

    @Override
    public String getType() {
        return ParserTypes.PLACEHOLDER;
    }

    @Override
    public String parseString(@NotNull String string, @NotNull OutputType outputType) {
        return PlaceholderAPI.setPlaceholders(null, string);
    }

    @Override
    public String parseString(@NotNull String string, @NotNull OutputType outputType, Player player) {
        return PlaceholderAPI.setPlaceholders(player, string);
    }
}