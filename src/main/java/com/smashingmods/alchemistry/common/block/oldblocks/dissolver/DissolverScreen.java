//package com.smashingmods.alchemistry.common.block.oldblocks.dissolver;
//
//import com.smashingmods.alchemistry.Alchemistry;
//import com.smashingmods.alchemistry.common.block.oldblocks.container.BaseScreen;
//import com.smashingmods.alchemistry.common.block.oldblocks.container.CapabilityEnergyDisplayWrapper;
//import net.minecraft.network.chat.Component;
//import net.minecraft.resources.ResourceLocation;
//import net.minecraft.world.entity.player.Inventory;
//
//public class DissolverScreen extends BaseScreen<DissolverContainer> {
//
//    private static final ResourceLocation textureResourceLocation = new ResourceLocation(Alchemistry.MODID, "textures/gui/dissolver_gui.png");
//
//    public DissolverScreen(DissolverContainer screenContainer, Inventory inv, Component name) {
//        super(screenContainer, inv, name, textureResourceLocation);
//        this.displayData.add(new CapabilityEnergyDisplayWrapper(13, 31, 16, 60, screenContainer));
//    }
//}
