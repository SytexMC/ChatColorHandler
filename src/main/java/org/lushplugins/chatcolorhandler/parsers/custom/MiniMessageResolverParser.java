package org.lushplugins.chatcolorhandler.parsers.custom;

import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.lushplugins.chatcolorhandler.messengers.MiniMessageMessenger;
import org.lushplugins.chatcolorhandler.parsers.Parser;
import org.lushplugins.chatcolorhandler.parsers.ParserTypes;
import org.lushplugins.chatcolorhandler.parsers.Parsers;

public class MiniMessageResolverParser implements Parser {
    public static final MiniMessageResolverParser INSTANCE = new MiniMessageResolverParser();

    private MiniMessageResolverParser() {}

    @Override
    public String getType() {
        return ParserTypes.PLACEHOLDER;
    }

    @Override
    public String parseString(@NotNull String string, @NotNull OutputType outputType) {
        return parseString(string, outputType, null);
    }

    @Override
    public String parseString(@NotNull String string, @NotNull OutputType outputType, Player player) {
        return switch (outputType) {
            case SPIGOT -> {
                string = string.replace('§', '&');

                TagResolver resolver = Parsers.getCombinedResolvers(player instanceof Audience audience ? audience : null);
                yield MiniMessageMessenger.LEGACY_COMPONENT_SERIALIZER.serialize(MiniMessageMessenger.MINI_MESSAGE.deserialize(string, resolver));
            }
            case MINI_MESSAGE -> string;
        };
    }
}
