package net.justsunnit.quirkcore.Util;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import net.justsunnit.quirkcore.QuirkCore;

import java.util.concurrent.CompletableFuture;

public class QuirkAgumentType implements ArgumentType<QuirkCore.QuirkTypes> {
    @Override
    public QuirkCore.QuirkTypes parse(StringReader reader) throws CommandSyntaxException {
        String input = reader.readUnquotedString().toUpperCase();

        for (QuirkCore.QuirkTypes value : QuirkCore.QuirkTypes.values()) {
            if (value.name().equalsIgnoreCase(input)) {
                return value;
            }
        }
        throw CommandSyntaxException.BUILT_IN_EXCEPTIONS.dispatcherUnknownArgument().createWithContext(reader);
    }

    public static QuirkAgumentType enumArg() {
        return new QuirkAgumentType();
    }

    public static QuirkCore.QuirkTypes getEnum(CommandContext<?> context, String name) {
        return context.getArgument(name, QuirkCore.QuirkTypes.class);
    }

    @Override
    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder) {
        for (QuirkCore.QuirkTypes value : QuirkCore.QuirkTypes.values()) {
            builder.suggest(value.name().toLowerCase());
        }
        return builder.buildFuture();
    }
}
