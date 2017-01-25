package com.suatae.tu.common.block;

import java.util.Random;

import com.suatae.tu.common.block.BlockReg;
import com.suatae.tu.lib.Ref;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class TileEntityUrn extends TileEntity implements IInventory{
	private ItemStack[] deathStack = new ItemStack[256];
    private Random ran = new Random();
    //protected String field_146020_a;
    private static final String __OBFID = "CL_00000352";
    
    /**
     * Returns the number of slots in the inventory.
     */
    public int getSizeInventory()
    {
        return 256;
    }

    /**
     * Returns the stack in slot i
     */
    public ItemStack getStackInSlot(int slot)
    {
        return this.deathStack[slot];
    }

    /**
     * Removes from an inventory slot (first arg) up to a specified number (second arg) of items and returns them in a
     * new stack.
     */
    public ItemStack decrStackSize(int slot, int decSize)
    {
        if (this.deathStack[slot] != null)
        {
            ItemStack itemstack;

            if (this.deathStack[slot].stackSize <= decSize)
            {
                itemstack = this.deathStack[slot];
                this.deathStack[slot] = null;
                this.markDirty();
                return itemstack;
            }
            else
            {
                itemstack = this.deathStack[slot].splitStack(decSize);

                if (this.deathStack[slot].stackSize == 0)
                {
                    this.deathStack[slot] = null;
                }

                this.markDirty();
                return itemstack;
            }
        }
        else
        {
            return null;
        }
    }

    /**
     * When some containers are closed they call this on each slot, then drop whatever it returns as an EntityItem -
     * like when you close a workbench GUI.
     */
    public ItemStack getStackInSlotOnClosing(int slot)
    {
        if (this.deathStack[slot] != null)
        {
            ItemStack itemstack = this.deathStack[slot];
            this.deathStack[slot] = null;
            return itemstack;
        }
        else
        {
            return null;
        }
    }

//    public int func_146017_i()
//    {
//        int i = -1;
//        int j = 1;
//
//        for (int k = 0; k < this.deathStack.length; ++k)
//        {
//            if (this.deathStack[k] != null && this.ran.nextInt(j++) == 0)
//            {
//                i = k;
//            }
//        }
//
//        return i;
//    }

    /**
     * Sets the given item stack to the specified slot in the inventory (can be crafting or armor sections).
     */
    public void setInventorySlotContents(int slot, ItemStack stack)
    {
        this.deathStack[slot] = stack;

        if (stack != null && stack.stackSize > this.getInventoryStackLimit())
        {
            stack.stackSize = this.getInventoryStackLimit();
        }

        this.markDirty();
    }

//    public int func_146019_a(ItemStack stack)
//    {
//        for (int i = 0; i < this.deathStack.length; ++i)
//        {
//            if (this.deathStack[i] == null || this.deathStack[i].getItem() == null)
//            {
//                this.setInventorySlotContents(i, stack);
//                return i;
//            }
//        }
//
//        return -1;
//    }

    /**
     * Returns the name of the inventory
     */
    public String getInventoryName()
    {
        return BlockReg.blockUrn.getUnlocalizedName() + ".name";
    }

//    public void func_146018_a(String p_146018_1_)
//    {
//        this.field_146020_a = p_146018_1_;
//    }

    /**
     * Returns if the inventory is named
     */
    public boolean hasCustomInventoryName()
    {
        return false;
    }

    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);
        NBTTagList nbttaglist = nbt.getTagList("Items", 10);
        this.deathStack = new ItemStack[this.getSizeInventory()];

        for (int i = 0; i < nbttaglist.tagCount(); ++i)
        {
            NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
            int j = nbttagcompound1.getByte("Slot") & 255;

            if (j >= 0 && j < this.deathStack.length)
            {
                this.deathStack[j] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
        }
    }

    public void writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);
        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < this.deathStack.length; ++i)
        {
            if (this.deathStack[i] != null)
            {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte)i);
                this.deathStack[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }
        nbt.setTag("Items", nbttaglist);

    }

    /**
     * Returns the maximum stack size for a inventory slot.
     */
    public int getInventoryStackLimit()
    {
        return 64;
    }

    /**
     * Do not make give this method the name canInteractWith because it clashes with Container
     */
    public boolean isUseableByPlayer(EntityPlayer player)
    {
        return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : player.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
    }

    public void openInventory() {}

    public void closeInventory() {}

    /**
     * Returns true if automation is allowed to insert the given stack (ignoring stack size) into the given slot.
     */
    public boolean isItemValidForSlot(int slot, ItemStack stack)
    {
        return false;
    }
}
