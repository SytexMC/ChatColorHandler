package org.lushplugins.chatcolorhandler.parsers.custom;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.lushplugins.chatcolorhandler.parsers.Parser;
import org.lushplugins.chatcolorhandler.parsers.ParserTypes;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SoundParser implements Parser {

    private static final Pattern SOUND_PATTERN =
            Pattern.compile("<sound:([A-Z_]+):([0-9.]+):([0-9.]+)>");

    public static final SoundParser INSTANCE = new SoundParser();

    private SoundParser() {}

    @Override
    public String getType() {
        return ParserTypes.SOUND;
    }

    @Override
    public String parseString(@NotNull String string, @NotNull OutputType outputType) {
        return string;
    }

    @Override
    public String parseString(@NotNull String string, @NotNull OutputType outputType, Player player) {
        if (!string.contains("<sound:")) {
            return string;
        }

        Matcher soundMatcher = SOUND_PATTERN.matcher(string);

        if (!soundMatcher.find()) {
            return string;
        }

        soundMatcher.reset();

        while (soundMatcher.find()) {
            try {
                String soundName = soundMatcher.group(1);
                Sound sound = Sound.valueOf(soundName);

                float volume = Float.parseFloat(soundMatcher.group(2));
                float pitch = Float.parseFloat(soundMatcher.group(3));

                Location playerLoc = player.getLocation();
                player.playSound(playerLoc, sound, volume, pitch);
            } catch (IllegalArgumentException ignored) {
                // ignore IllegalArgumentException
            }
        }

        return soundMatcher.reset().replaceAll("");
    }
}