package com.smashingmods.alchemistry.block.combiner;

import com.smashingmods.alchemistry.Alchemistry;
import com.smashingmods.alchemistry.api.client.BaseScreen;
import com.smashingmods.alchemistry.api.client.CapabilityEnergyDisplayWrapper;
import com.smashingmods.alchemistry.network.CombinerButtonPkt;
import com.smashingmods.alchemistry.network.Messages;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class CombinerScreen extends BaseScreen<CombinerContainer> {

    private Button toggleRecipeLock;// GuiButton
    private Button pauseButton;//: GuiButton
    private CombinerBlockEntity combinerTile;
    private CombinerContainer container;
    private static final ResourceLocation textureResourceLocation = new ResourceLocation(Alchemistry.MODID, "textures/gui/combiner_gui.png");
    //private boolean paused;
    //private boolean recipeLocked;

    public CombinerScreen(CombinerContainer screenContainer, Inventory inv, Component name) {
        super(screenContainer, inv, name, textureResourceLocation);
        this.combinerTile = (CombinerBlockEntity) screenContainer.blockEntity;
        //this.paused = tile.paused;
        //this.recipeLocked = tile.recipeIsLocked;
        this.container = screenContainer;
        this.displayData.add(new CapabilityEnergyDisplayWrapper(8, 8, 16, 60, combinerTile));
    }

    @Override
    protected void init() {
        super.init();
        toggleRecipeLock = new Button(this.getGuiLeft() + 30, this.getGuiTop() + 75, 80, 20, new TextComponent(""),
                press -> {
                    Messages.sendToServer(new CombinerButtonPkt(this.combinerTile.getBlockPos(), true, false));
                    //this.recipeLocked = !this.recipeLocked;
                });
        this.addWidget(toggleRecipeLock);
        pauseButton = new Button(this.getGuiLeft() + 30, this.getGuiTop() + 100, 80, 20, new TextComponent(""),
                press -> {
                   // this.paused = !this.paused;
                    Messages.sendToServer(new CombinerButtonPkt(this.combinerTile.getBlockPos(), false, true));
                });
        this.addWidget(pauseButton);
    }

    public void updateButtonStrings() {
        if (this.combinerTile.recipeIsLocked)
            toggleRecipeLock.setMessage(new TextComponent(I18n.get("alchemistry.container.combiner.unlock_recipe")));
        else toggleRecipeLock.setMessage(new TextComponent(I18n.get("alchemistry.container.combiner.lock_recipe")));

        if (this.combinerTile.paused) pauseButton.setMessage(new TextComponent(I18n.get("alchemistry.container.combiner.resume")));
        else pauseButton.setMessage(new TextComponent(I18n.get("alchemistry.container.combiner.pause")));
    }

    @Override
    public void render(@NotNull PoseStack ps, int mouseX, int mouseY, float partialTicks) {
        super.render(ps, mouseX, mouseY, partialTicks);
        updateButtonStrings();
        toggleRecipeLock.renderButton(ps, mouseX, mouseY, 0.0f);
        pauseButton.renderButton(ps, mouseX, mouseY, 0.0f);

        if (!combinerTile.clientRecipeTarget.getStackInSlot(0).isEmpty()) {
            this.drawItemStack(ps, combinerTile.clientRecipeTarget.getStackInSlot(0), getGuiLeft() + 140, getGuiTop() + 5, I18n.get("alchemistry.container.combiner.target"));
        }
    }


    public void drawItemStack(PoseStack ps, ItemStack stack, int x, int y, String text) {
        //RenderHelper.enableStandardItemLighting();//.enableGUIStandardItemLighting();
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        ps.translate(0.0f, 0.0f, 32.0f);
        //this.setBlitOffset(200);// = 200;
        //this.itemRenderer.blitOffset = 200.0f;
        this.itemRenderer.renderGuiItem(stack, x, y);
        this.itemRenderer.renderGuiItemDecorations(this.font, stack, x, y + 5, text);
        //this.setBlitOffset(0);// = 0;
        //this.itemRenderer.blitOffset = 0.0f;
    }
}