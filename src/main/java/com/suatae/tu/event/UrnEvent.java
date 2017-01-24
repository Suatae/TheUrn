package com.suatae.tu.event;

import java.util.Iterator;

import com.suatae.tu.common.block.BlockReg;
import com.suatae.tu.common.block.TileEntityUrn;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerDropsEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;

public class UrnEvent {

public UrnEvent() {
	}
	
	@SubscribeEvent
	public void onPlayerDrops(PlayerDropsEvent event) {
		boolean saveItems = false;
//		int exp = event.entityPlayer.experienceTotal;
		int counter = 0;
		
//		if(event.entityPlayer.experienceTotal > 0){
//			event.entityPlayer.addExperienceLevel(exp);
//			
//		}
		
		for (Iterator<EntityItem> iterator = event.drops.iterator(); iterator.hasNext();) {
		    EntityItem droppedStack = iterator.next();
		    	saveItems = true;
		}
		
		if(saveItems == true) {
			int x = MathHelper.floor_double(event.entityPlayer.posX);
			int y = MathHelper.floor_double(event.entityPlayer.posY);
			int z = MathHelper.floor_double(event.entityPlayer.posZ);
			
			World world = event.entityPlayer.worldObj;
			
			world.setBlock(x, y, z, BlockReg.blockUrn);
			
			TileEntityUrn urn = (TileEntityUrn) world.getTileEntity(x, y, z);
			
			for(EntityItem droppedItemEntity : event.drops) {
				counter++;
				ItemStack droppedItem = droppedItemEntity.getEntityItem();
				
				
				if(counter > urn.getSizeInventory()) {
					return;
				} else {
					//urn.writeToNBT(nbt);
					urn.setInventorySlotContents(counter -1, droppedItem);
					droppedItemEntity.setDead();
				}
			}
		}
	}

}
