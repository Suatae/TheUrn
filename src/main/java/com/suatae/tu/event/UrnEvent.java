package com.suatae.tu.event;

import java.util.Iterator;

import com.suatae.tu.common.block.BlockReg;
import com.suatae.tu.common.block.TileEntityUrn;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntitySkull;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerDropsEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;

public class UrnEvent {

public UrnEvent() {
	}
	
	@SubscribeEvent(priority = EventPriority.LOWEST)
	public void onPlayerDrops(PlayerDropsEvent event) {
		boolean saveItems = false;
		int counter = 0;
		
		
		for (Iterator<EntityItem> iterator = event.drops.iterator(); iterator.hasNext();) {
		    EntityItem droppedStack = iterator.next();
		    	saveItems = true;
		}
		
		if(saveItems == true) {
			int x = MathHelper.floor_double(event.entityPlayer.posX);
			int y = MathHelper.floor_double(event.entityPlayer.posY);
			int z = MathHelper.floor_double(event.entityPlayer.posZ);
			
			World world = event.entityPlayer.worldObj;
			
			ItemStack skull = new ItemStack(Items.skull, 1, 3);
			NBTTagCompound nametag = new NBTTagCompound();
            nametag.setString("SkullOwner", event.entityPlayer.getDisplayName());
            skull.writeToNBT(nametag);
			
			world.setBlock(x, y, z, BlockReg.blockUrn);
			world.setBlock(x, y + 1, z, BlockReg.blockNull);
			
			TileEntityUrn urn = (TileEntityUrn) world.getTileEntity(x, y, z);
			
			for(EntityItem droppedItemEntity : event.drops) {
				counter++;
				ItemStack droppedItem = droppedItemEntity.getEntityItem();
				
				
				if(counter > urn.getSizeInventory()) {
					return;
				} else {
					urn.setInventorySlotContents(counter -1, droppedItem);
					urn.setInventorySlotContents(1, skull);
					droppedItemEntity.setDead();
				}
			}
		}
	}
	
//	public void onPlayerDeath(LivingDeathEvent event){
//		if(event.entity instanceof EntityPlayerMP){
//			EntityPlayer player = (EntityPlayer)event.entity;	
//		}
//		
//	}

}
