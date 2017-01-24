package com.suatae.tu.common.block;

import java.util.Random;

import com.suatae.tu.common.block.TileEntityUrn;
import com.suatae.tu.lib.Ref;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockUrn extends BlockContainer{
	protected Random ran = new Random();
	public BlockUrn() {
		super(Material.rock);
		this.setBlockName(Ref.N.Urn);
		this.setBlockTextureName(Ref.N.Urn_T);
		//this.setCreativeTab(NeerSolusTab.NeerSolus_TAB);
		this.setStepSound(Block.soundTypeStone);
		this.setHardness(1.5F);
		this.setResistance(10.0F);
		this.setBlockBounds(0.125F, 0F, 0.125F, 0.875F, 1F, 0.875F);
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
	public int getRenderType() {
		return -1;
	}
	
	public void breakBlock(World world, int x, int y, int z, Block block, int slot)
    {
        TileEntityUrn teUrn = (TileEntityUrn)world.getTileEntity(x, y, z);

        //if (teUrn != null)
      //  {
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
     //   }

        super.breakBlock(world, x, y, z, block, slot);
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
