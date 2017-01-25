package com.suatae.tu.common.block;

import java.util.Random;

import com.suatae.tu.lib.Ref;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockNull extends Block{
	
	protected static Random	ran	= new Random();

	protected BlockNull() {
		super(Material.glass);
		this.setTickRandomly(true);
		this.setBlockTextureName(Ref.N.bNull_T);
		this.setBlockName(Ref.N.bNull);
		this.setBlockBounds(0.5F, 0.5F, 0.5F, 0.5F, 0.5F, 0.5F);
		this.setLightLevel(1.0F);
	}

	public boolean isReplaceable(IBlockAccess world, int x, int y, int z)
    {
        return true;
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
    
    public Block setStepSound(Block.SoundType sound)
    {
        this.stepSound = sound;
        return null;
    }
    
    @Override
    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
		if (entity instanceof EntityLivingBase) {
			((EntityLivingBase) entity).addPotionEffect(new PotionEffect(15, 30, ran.nextInt(5)));;
		}
    }
    
	@Override
	public int getRenderType() {
		return -1;
	}
	
	protected boolean canPlaceBlockOn(Block block) {
		return false;
	}
	
	@Override
	public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z, EntityPlayer player) {
		return null;
	}
	
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World world, int x, int y, int z, Random ran)
	{
      super.randomDisplayTick(world, x, y, z, ran);
      
      double d0 = (double)((float)x + 0.5D);
      double d1 = (double)((float)y + 0.11D);
      double d2 = (double)((float)z + 0.5D);
      double d3 = (double)((float)ran.nextFloat());
      double d4 = 0.19438743D;
      
      		world.spawnParticle("mobSpell", d0, d1, d2, 0.0D + d3, 0.0D + (d3 / (d4 - 0.4D)), 0.0D + (d3 / d4));

    }
	
	@Override
	public void onNeighborBlockChange(World world, int X, int Y, int Z, Block block) {

		Block check = world.getBlock(X, Y - 1, Z);
		if ((check != BlockReg.blockUrn)) {
			world.func_147480_a(X, Y, Z, true);
		}
	};
	
	public boolean canCollideCheck(int p_149678_1_, boolean p_149678_2_)
    {
        return false;
    }
	
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World p_149668_1_, int p_149668_2_, int p_149668_3_, int p_149668_4_)
    {
        return null;
    }
	
	 public void dropBlockAsItemWithChance(World world, int x, int y, int z, int p_149690_5_, float p_149690_6_, int p_149690_7_) {}

	
	@Override
	public String getUnlocalizedName() {
		return String.format("tile.%s%s", Ref.MOD_ID.toLowerCase() + ":",
				getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
	}

	protected String getUnwrappedUnlocalizedName(String unlocalizedName) {
		return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
	}
}
