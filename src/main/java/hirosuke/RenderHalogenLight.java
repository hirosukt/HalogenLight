package hirosuke;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;

public class RenderHalogenLight extends Block implements ISimpleBlockRenderingHandler {

    protected RenderHalogenLight(Material p_i45394_1_) {
        super(p_i45394_1_);
    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        if (modelId == this.getRenderId())
        {
            this.setBlockBoundsBasedOnState(world, x, y, z);

            maxX = this.getBlockBoundsMaxX();
            minX = this.getBlockBoundsMinX();
            maxY = this.getBlockBoundsMaxY();
            minY = this.getBlockBoundsMinY();
            maxZ = this.getBlockBoundsMaxZ();
            minZ = this.getBlockBoundsMinZ();

            renderer.setRenderBounds(minX, minY, minZ, maxX, maxY, maxZ);
            renderer.renderStandardBlock(block, x, y, z);
            return true;
        }
        return false;
    }

    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z) {

        int l = world.getBlockMetadata(x, y, z);
        if (l == 0) {
            this.setBlockBounds(0.4375f, 1 - 0.125f, 0.0f, 0.5625f, 1f, 1.0f);
        } else if (l == 6) {
            this.setBlockBounds(0.4375f, 0.0f, 0.0f, 0.5625f, 0.125f, 1.0f);
        } else if (l == 4) {
            this.setBlockBounds(0f, 0.4375f, 1 - 0.125f, 1f, 0.5625f, 1.0f);
        } else if (l == 3) {
            this.setBlockBounds(0f, 0.4375f, 0f, 1f, 0.5625f, 0.125f);
        } else if (l == 2) {
            this.setBlockBounds(1 - 0.125f, 0.4375f, 0.0f, 1f, 0.5625f, 1.0f);
        } else if (l == 1) {
            this.setBlockBounds(0f, 0.4375f, 0.0f, 0.125f, 0.5625f, 1.0f);
        } else {
            if(l == 5) {
                this.setBlockBounds(0f, 0.0f, 0.4375f, 1f, 0.125f, 0.5625f);
            } else if (l == 7) {
                this.setBlockBounds(0f, 1 - 0.125f, 0.4375f, 1f, 1f, 0.5625f);
            }
        }
    }

    @Override
    public boolean shouldRender3DInInventory(int modelId) {
        return true;
    }

    @Override
    public int getRenderId() {
        return HalogenLight.RenderID;
    }

    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
        if (modelId == this.getRenderId()) {
            Tessellator tessellator = Tessellator.instance;
            renderer.setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 0.75D, 1.0D);

            GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
            tessellator.startDrawingQuads();
            tessellator.setNormal(0.0F, -1.0F, 0.0F);

            renderer.renderFaceYNeg(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 0, 6));
            tessellator.draw();
            tessellator.startDrawingQuads();
            tessellator.setNormal(0.0F, 1.0F, 0.0F);

            renderer.renderFaceYPos(block, 0.0D, -0.5d, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 1, 6));
            tessellator.draw();
            tessellator.startDrawingQuads();
            tessellator.setNormal(0.0F, 0.0F, -1.0F);

            renderer.renderFaceZNeg(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 2, 6));
            tessellator.draw();
            tessellator.startDrawingQuads();
            tessellator.setNormal(0.0F, 0.0F, 1.0F);

            renderer.renderFaceZPos(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 3, 6));
            tessellator.draw();
            tessellator.startDrawingQuads();
            tessellator.setNormal(-1.0F, 0.0F, 0.0F);

            renderer.renderFaceXNeg(block, 0.375d, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 4, 6));
            tessellator.draw();
            tessellator.startDrawingQuads();
            tessellator.setNormal(1.0F, 0.0F, 0.0F);

            renderer.renderFaceXPos(block, -0.375d, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 5, 6));
            tessellator.draw();
            GL11.glTranslatef(0.5F, 0.5F, 0.5F);
        }
    }
}
