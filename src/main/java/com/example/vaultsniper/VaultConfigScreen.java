package com.example.vaultsniper;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

public class VaultConfigScreen extends Screen {

    public VaultConfigScreen(Text title) {
        super(title);
    }

    @Override
    protected void init() {
        int centerX = this.width / 2;
        int startY = 40;

        this.addDrawableChild(ButtonWidget.builder(Text.literal("Item: Heavy Core"), button -> {
            VaultSniperClient.targetItemId = "heavy_core";
            updateStatusMessage("Ziel: Heavy Core");
        }).dimensions(centerX - 120, startY, 115, 20).build());

        this.addDrawableChild(ButtonWidget.builder(Text.literal("Item: Ench. Golden Apple"), button -> {
            VaultSniperClient.targetItemId = "enchanted_golden_apple";
            updateStatusMessage("Ziel: Verzauberter Apfel");
        }).dimensions(centerX + 5, startY, 115, 20).build());

        this.addDrawableChild(ButtonWidget.builder(Text.literal("Item: Wind Burst Book"), button -> {
            VaultSniperClient.targetItemId = "enchanted_book";
            updateStatusMessage("Ziel: Zauberbuch");
        }).dimensions(centerX - 120, startY + 25, 115, 20).build());

        this.addDrawableChild(ButtonWidget.builder(Text.literal("Item: Trident"), button -> {
            VaultSniperClient.targetItemId = "trident";
            updateStatusMessage("Ziel: Dreizack");
        }).dimensions(centerX + 5, startY + 25, 115, 20).build());

        this.addDrawableChild(ButtonWidget.builder(
                Text.literal("Modus: " + VaultSniperClient.activationMode.getLabel()),
                button -> {
                    VaultSniperClient.ActivationMode[] modes = VaultSniperClient.ActivationMode.values();
                    int nextOrdinal = (VaultSniperClient.activationMode.ordinal() + 1) % modes.length;
                    VaultSniperClient.activationMode = modes[nextOrdinal];
                    button.setMessage(Text.literal("Modus: " + VaultSniperClient.activationMode.getLabel()));
                }
        ).dimensions(centerX - 120, startY + 65, 240, 20).build());

        ButtonWidget toggleBtn = ButtonWidget.builder(
                Text.literal(VaultSniperClient.isActive ? "§cSNIPER DEAKTIVIEREN" : "§2► SNIPER AKTIVIEREN"),
                button -> {
                    VaultSniperClient.isActive = !VaultSniperClient.isActive;
                    this.close();
                }
        ).dimensions(centerX - 120, startY + 105, 240, 22).build();

        this.addDrawableChild(toggleBtn);
    }

    private void updateStatusMessage(String msg) {
        if (this.client != null && this.client.player != null) {
            this.client.player.sendMessage(Text.literal("§e[VaultSniper] " + msg), false);
        }
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        this.renderBackground(context, mouseX, mouseY, delta);
        
        context.drawCenteredTextWithShadow(this.textRenderer, "§lVault Sniper Einstellungen", this.width / 2, 15, 0xFFFFFF);
        context.drawCenteredTextWithShadow(this.textRenderer, "§7Wähle die Bedingung, wann gecatcht wird:", this.width / 2, 90, 0xAAAAAA);
        
        super.render(context, mouseX, mouseY, delta);
    }
}