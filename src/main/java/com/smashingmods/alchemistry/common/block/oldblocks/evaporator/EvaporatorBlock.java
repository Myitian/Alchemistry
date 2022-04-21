//package com.smashingmods.alchemistry.common.block.oldblocks.evaporator;
//
//import com.smashingmods.alchemistry.common.block.oldblocks.blockentity.BaseEntityBlock;
//import net.minecraft.core.BlockPos;
//import net.minecraft.network.chat.Component;
//import net.minecraft.world.item.ItemStack;
//import net.minecraft.world.item.TooltipFlag;
//import net.minecraft.world.level.BlockGetter;
//import net.minecraft.world.level.Level;
//import net.minecraft.world.level.block.Block;
//import net.minecraft.world.level.block.entity.BlockEntity;
//import net.minecraft.world.level.block.entity.BlockEntityTicker;
//import net.minecraft.world.level.block.entity.BlockEntityType;
//import net.minecraft.world.level.block.state.BlockState;
//import net.minecraft.world.level.material.Material;
//import net.minecraft.world.phys.shapes.Shapes;
//import net.minecraft.world.phys.shapes.VoxelShape;
//
//import javax.annotation.Nonnull;
//import javax.annotation.Nullable;
//import java.util.List;
//
//public class EvaporatorBlock extends BaseEntityBlock {
//    public EvaporatorBlock() {
//        super(Block.Properties.of(Material.METAL).strength(2.0f), EvaporatorBlockEntity::new, EvaporatorContainer::new);
//    }
//
//
//    public static final VoxelShape boundingBox = Block.box(1, 1, 1, 15, 12, 15);
//    public static final VoxelShape boundingBox2 = Block.box(4, 0.0, 4, 12, 1, 12);
//    public static final VoxelShape BOX = Shapes.or(boundingBox, boundingBox2);
//
//
//    @Override
//    @Nonnull
//    @SuppressWarnings("deprecation")
//    public VoxelShape getOcclusionShape(@Nonnull BlockState state, @Nonnull BlockGetter reader, @Nonnull BlockPos pos) {
//        return BOX;
//    }
//
//    @Override
//    public void appendHoverText(@Nonnull ItemStack stack, @Nullable BlockGetter getter, @Nonnull List<Component> tooltips, @Nonnull TooltipFlag flag) {
//        super.appendHoverText(stack, getter, tooltips, flag);
//        //tooltip.add(new TextComponent(I18n.get("tooltip.alchemistry.evaporator",50)));
//    }
//
//    @Nullable
//    @Override
//    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(@Nonnull Level pLevel, @Nonnull BlockState pState, @Nonnull BlockEntityType<T> pBlockEntityType) {
//        if (pLevel.isClientSide()) return null;
//        return (lvl, pos, blockState, t) -> {
//            if (t instanceof EvaporatorBlockEntity) {
//                ((EvaporatorBlockEntity) t).tickServer();
//            }
//        };
//    }
//}