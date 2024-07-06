package io.wispforest.lavendermdtest;

import com.mojang.brigadier.arguments.StringArgumentType;
import io.wispforest.lavendermd.MarkdownProcessor;
import net.minecraft.client.MinecraftClient;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.RegisterClientCommandsEvent;
import net.neoforged.neoforge.common.NeoForge;

import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

@Mod(value = "lavender_md_test", dist = Dist.CLIENT)
public class LavenderMdTest {
    public LavenderMdTest() {
        NeoForge.EVENT_BUS.addListener(RegisterClientCommandsEvent.class, event -> {
            event.getDispatcher().register(literal("parse-md").then(argument("md", StringArgumentType.greedyString()).executes(context -> {
                context.getSource().sendFeedback(() -> MarkdownProcessor.text().process(StringArgumentType.getString(context, "md")), false);
                return 0;
            })));

            event.getDispatcher().register(literal("edit-md").executes(context -> {
                MinecraftClient.getInstance().setScreen(new EditMdScreen());
                return 0;
            }));
        });
    }
}
