package com.suatae.tu.common.block;

import java.util.Random;

import com.suatae.tu.common.block.TileEntityUrn;
import com.suatae.tu.lib.Ref;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockUrn extends BlockContainer{
	protected Random ran = new Random();
	public BlockUrn() {
		super(Material.rock);
		this.setBlockName(Ref.N.Urn);
		this.setBlockTextureName(Ref.N.Urn_T);
		this.setStepSound(Block.soundTypeStone);
		this.setHardness(1.5F);
		this.setResistance(10.0F);
		this.setBlockBounds(0.125F, 0F, 0.125F, 0.875F, 1F, 0.875F);
		this.setLightLevel(0);
		this.setLightOpacity(0);
	}
	
	public boolean canBlockGrass(){
		return true;
	}
	
	public boolean isOpaqueCube()
    {
        return false;
    }

    public boolean renderAsNormalBlock()
    {
        return false;
    }
    
    @Override
	public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z, EntityPlayer player) {
		return null;
	}
    
	@Override
	public int getRenderType() {
		return -1;
	}
	
	public void breakBlock(World world, int x, int y, int z, Block block, int slot)
    {
        TileEntityUrn teUrn = (TileEntityUrn)world.getTileEntity(x, y, z);
            for (int i1 = 0; i1 < teUrn.getSizeInventory(); ++i1)
            {
                ItemStack itemstack = teUrn.getStackInSlot(i1);

                if (itemstack != null)
                {
                    float f = this.ran.nextFloat() * 0.8F + 0.1F;
                    float f1 = this.ran.nextFloat() * 0.8F + 0.1F;
                    float f2 = this.ran.nextFloat() * 0.8F + 0.1F;

                    while (itemstack.stackSize > 0)
                    {
                        int j1 = this.ran.nextInt(21) + 10;

                        if (j1 > itemstack.stackSize)
                        {
                            j1 = itemstack.stackSize;
                        }

                        itemstack.stackSize -= j1;
                        EntityItem entityitem = new EntityItem(world, (double)((float)x + f), (double)((float)y + f1), (double)((float)z + f2), new ItemStack(itemstack.getItem(), j1, itemstack.getItemDamage()));

                        if (itemstack.hasTagCompound())
                        {
                            entityitem.getEntityItem().setTagCompound((NBTTagCompound)itemstack.getTagCompound().copy());
                        }

                        float f3 = 0.05F;
                        entityitem.motionX = (double)((float)this.ran.nextGaussian() * f3);
                        entityitem.motionY = (double)((float)this.ran.nextGaussian() * f3 + 0.2F);
                        entityitem.motionZ = (double)((float)this.ran.nextGaussian() * f3);
                        world.spawnEntityInWorld(entityitem);
                    }
                }
            }

            world.func_147453_f(x, y, z, block);

        super.breakBlock(world, x, y, z, block, slot);
    }
	
	public int getExpDrop(IBlockAccess world, int metadata, int fortune)
    {
        return 0;
    }
	
	public void dropXpOnBlockBreak(World world, int x, int y, int z, int xp)
    {
        if (!world.isRemote)
        {
            while (xp > 0)
            {
                int i1 = EntityXPOrb.getXPSplit(xp);
                xp -= i1;
                world.spawnEntityInWorld(new EntityXPOrb(world, (double)x + 0.5D, (double)y + 0.5D, (double)z + 0.5D, i1));
            }
        }
    }
	
	@Override
	public String getUnlocalizedName() {
		return String.format("tile.%s%s", Ref.MOD_ID.toLowerCase() + ":",
				getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
	}

	protected String getUnwrappedUnlocalizedName(String unlocalizedName) {
		return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int var2) {
		return new TileEntityUrn();
	}

}
