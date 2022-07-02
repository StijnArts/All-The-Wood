package Net.Drai.AllTheWood.tileentitites.renderer;


import Net.Drai.AllTheWood.*;
import Net.Drai.AllTheWood.block.blocks.*;
import Net.Drai.AllTheWood.block.enums.*;
import Net.Drai.AllTheWood.material.*;
import Net.Drai.AllTheWood.modules.*;
import Net.Drai.AllTheWood.tileentitites.*;
import com.google.common.collect.*;
import com.mojang.blaze3d.matrix.*;
import com.mojang.blaze3d.vertex.*;
import it.unimi.dsi.fastutil.floats.*;
import it.unimi.dsi.fastutil.ints.*;
import net.minecraft.block.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.model.*;
import net.minecraft.client.renderer.tileentity.*;
import net.minecraft.state.properties.*;
import net.minecraft.tileentity.*;
import net.minecraft.util.*;
import net.minecraft.util.math.vector.*;
import net.minecraft.world.*;
import net.minecraftforge.fml.*;
import net.minecraftforge.registries.*;

import java.util.*;

public class ATWChestTileEntityRenderer extends TileEntityRenderer<ATWChestTileEntity> {
    public static final HashMap<Block, RenderType[]> LAYERS = Maps.newHashMap();
    private static RenderType[] defaultLayer = new RenderType[]{RenderType.entitySolid(new ResourceLocation("entity/chest/normal.png")), RenderType.entitySolid(new ResourceLocation("entity/chest/normal_left.png")), RenderType.entitySolid(new ResourceLocation("entity/chest/normal_right.png"))};
    private static final int ID_NORMAL = 0;
    private static final int ID_LEFT = 1;
    private static final int ID_RIGHT = 2;
    private final ModelRenderer partA;
    private final ModelRenderer partC = new ModelRenderer(64, 64, 0, 19);
    private final ModelRenderer partB;
    private final ModelRenderer partRightA;
    private final ModelRenderer partRightC;
    private final ModelRenderer partRightB;
    private final ModelRenderer partLeftA;
    private final ModelRenderer partLeftC;
    private final ModelRenderer partLeftB;

    public ATWChestTileEntityRenderer(TileEntityRendererDispatcher blockEntityRenderDispatcher) {
        super(blockEntityRenderDispatcher);
        this.partC.addBox(1.0F, 0.0F, 1.0F, 14.0F, 10.0F, 14.0F, 0.0F);
        this.partA = new ModelRenderer(64, 64, 0, 0);
        this.partA.addBox(1.0F, 0.0F, 0.0F, 14.0F, 5.0F, 14.0F, 0.0F);
        this.partA.y = 9.0F;
        this.partA.z = 1.0F;
        this.partB = new ModelRenderer(64, 64, 0, 0);
        this.partB.addBox(7.0F, -1.0F, 15.0F, 2.0F, 4.0F, 1.0F, 0.0F);
        this.partB.y = 8.0F;
        this.partRightC = new ModelRenderer(64, 64, 0, 19);
        this.partRightC.addBox(1.0F, 0.0F, 1.0F, 15.0F, 10.0F, 14.0F, 0.0F);
        this.partRightA = new ModelRenderer(64, 64, 0, 0);
        this.partRightA.addBox(1.0F, 0.0F, 0.0F, 15.0F, 5.0F, 14.0F, 0.0F);
        this.partRightA.y = 9.0F;
        this.partRightA.z = 1.0F;
        this.partRightB = new ModelRenderer(64, 64, 0, 0);
        this.partRightB.addBox(15.0F, -1.0F, 15.0F, 1.0F, 4.0F, 1.0F, 0.0F);
        this.partRightB.y = 8.0F;
        this.partLeftC = new ModelRenderer(64, 64, 0, 19);
        this.partLeftC.addBox(0.0F, 0.0F, 1.0F, 15.0F, 10.0F, 14.0F, 0.0F);
        this.partLeftA = new ModelRenderer(64, 64, 0, 0);
        this.partLeftA.addBox(0.0F, 0.0F, 0.0F, 15.0F, 5.0F, 14.0F, 0.0F);
        this.partLeftA.y = 9.0F;
        this.partLeftA.z = 1.0F;
        this.partLeftB = new ModelRenderer(64, 64, 0, 0);
        this.partLeftB.addBox(0.0F, -1.0F, 15.0F, 1.0F, 4.0F, 1.0F, 0.0F);
        this.partLeftB.y = 8.0F;
    }

    public void render(ATWChestTileEntity entity, float tickDelta, MatrixStack matrices, IRenderTypeBuffer vertexConsumers, int light, int overlay) {
        World world = entity.getLevel();
        boolean worldExists = world != null;
        BlockState blockState = worldExists ? entity.getBlockState() : (BlockState)Blocks.CHEST.defaultBlockState().setValue(ChestBlock.FACING, Direction.SOUTH);
        ChestType chestType = blockState.hasProperty(ChestBlock.TYPE) ? (ChestType)blockState.getValue(ChestBlock.TYPE) : ChestType.SINGLE;
        Block block = blockState.getBlock();
        if (block instanceof AbstractChestBlock) {
            AbstractChestBlock<?> abstractChestBlock = (AbstractChestBlock)block;
            boolean isDouble = chestType != ChestType.SINGLE;
            float f = ((Direction)blockState.getValue(ChestBlock.FACING)).toYRot();
            matrices.pushPose();
            matrices.translate(0.5D, 0.5D, 0.5D);
            matrices.mulPose(Vector3f.YP.rotationDegrees(-f));
            matrices.translate(-0.5D, -0.5D, -0.5D);
            TileEntityMerger.ICallbackWrapper <? extends ChestTileEntity> propertySource;
            if (worldExists) {
                propertySource = abstractChestBlock.combine(blockState, world, entity.getBlockPos(), true);
            } else {
                propertySource = TileEntityMerger.ICallback::acceptNone;
            }

            float pitch = ((Float2FloatFunction)propertySource.apply(ChestBlock.opennessCombiner(entity))).get(tickDelta);
            pitch = 1.0F - pitch;
            pitch = 1.0F - pitch * pitch * pitch;
            int blockLight = ((Int2IntFunction)propertySource.apply(new DualBrightnessCallback())).applyAsInt(light);
            IVertexBuilder vertexConsumer = getConsumer(vertexConsumers, block, chestType);
            if (isDouble) {
                if (chestType == ChestType.LEFT) {
                    this.renderParts(matrices, vertexConsumer, this.partLeftA, this.partLeftB, this.partLeftC, pitch, blockLight, overlay);
                } else {
                    this.renderParts(matrices, vertexConsumer, this.partRightA, this.partRightB, this.partRightC, pitch, blockLight, overlay);
                }
            } else {
                this.renderParts(matrices, vertexConsumer, this.partA, this.partB, this.partC, pitch, blockLight, overlay);
            }

            matrices.popPose();
        }

    }

    private void renderParts(MatrixStack matrices, IVertexBuilder vertices, ModelRenderer modelPart, ModelRenderer modelPart2, ModelRenderer modelPart3, float pitch, int light, int overlay) {
        modelPart.xRot = -(pitch * 1.5707964F);
        modelPart2.xRot = modelPart.xRot;
        modelPart.render(matrices, vertices, light, overlay);
        modelPart2.render(matrices, vertices, light, overlay);
        modelPart3.render(matrices, vertices, light, overlay);
    }

    private static RenderType getChestTexture(ChestType type, RenderType[] layers) {
        switch(type) {
            case LEFT:
                return layers[1];
            case RIGHT:
                return layers[2];
            case SINGLE:
            default:
                return layers[0];
        }
    }

    public static IVertexBuilder getConsumer(IRenderTypeBuffer provider, Block block, ChestType chestType) {
        RenderType[] layers = (RenderType[])LAYERS.getOrDefault(block, defaultLayer);
        return provider.getBuffer(getChestTexture(chestType, layers));
    }

    static {

    }
}
