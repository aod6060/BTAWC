package com.derf.btawc.blocks.tileentity.itembuffer;

import java.util.ArrayList;
import java.util.List;

import com.derf.btawc.blocks.tileentity.TileEntityBasic;
import com.derf.btawc.inventory.EnumSixSidedType;
import com.derf.btawc.inventory.ISixSidedInventory;
import com.derf.btawc.util.InventoryUtils;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.util.Constants;

public class TileEntityItemBuffer extends TileEntityBasic implements IInventory, ISixSidedInventory, ITickable {

	// Inventory Portion of the class
	private ItemStack[] inventory = new ItemStack[9];
	private String name;
	// Six Sided Portion of the class
	private EnumFacing[] faces = new EnumFacing[6];
	private EnumSixSidedType[] sided = new EnumSixSidedType[6];
	
	public TileEntityItemBuffer() {
		for(int i = 0; i < sided.length; i++) {
			sided[i] = EnumSixSidedType.OFF;
			faces[i] = EnumFacing.values()[i];
		}
	}
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.hasCustomName()? this.name : "container.item_buffer";
	}

	@Override
	public boolean hasCustomName() {
		// TODO Auto-generated method stub
		return this.name != null && !this.name.isEmpty();
	}
	@Override
	public int getSizeInventory() {
		// TODO Auto-generated method stub
		return this.inventory.length;
	}

	@Override
	public ItemStack getStackInSlot(int index) {
		// TODO Auto-generated method stub
		return this.inventory[index];
	}

	@Override
	public ItemStack decrStackSize(int index, int count) {
		// TODO Auto-generated method stub
		return ItemStackHelper.getAndSplit(this.inventory, index, count);
	}

	@Override
	public ItemStack removeStackFromSlot(int index) {
		// TODO Auto-generated method stub
		return ItemStackHelper.getAndRemove(this.inventory, index);
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		this.inventory[index] = stack;
		
		if(stack != null && stack.stackSize > this.getInventoryStackLimit()) {
			stack.stackSize = this.getInventoryStackLimit();
		}
	}

	@Override
	public int getInventoryStackLimit() {
		// TODO Auto-generated method stub
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		// TODO Auto-generated method stub
		return this.worldObj.getTileEntity(pos) != this? false : player.getDistanceSq(pos.add(0.5, 0.5, 0.5)) <= 64.0;
	}

	@Override
	public void openInventory(EntityPlayer player) {}

	@Override
	public void closeInventory(EntityPlayer player) {}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		return true;
	}

	@Override
	public int getField(int id) {
		return this.sided[id].ordinal();
	}

	@Override
	public void setField(int id, int value) {
		this.sided[id] = EnumSixSidedType.values()[value];
	}

	@Override
	public int getFieldCount() {
		return sided.length;
	}

	@Override
	public void clear() {
		for(int i = 0; i < inventory.length; i++) {
			this.inventory[i] = null;
		}
	}

	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		// Inventory
		InventoryUtils.loadInventory(this, compound);
		
		if(compound.hasKey("CustomName")) {
			this.name = compound.getString("CustomName");
		}
		// Sided Inventory Configuration
		NBTTagList list2 = compound.getTagList("Sided", Constants.NBT.TAG_COMPOUND);
		this.sided = new EnumSixSidedType[6];
		
		for(int i = 0; i < list2.tagCount(); i++) {
			NBTTagCompound comp = list2.getCompoundTagAt(i);
			this.sided[i] = EnumSixSidedType.values()[comp.getInteger("Value")];
		}
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		// Inventory
		InventoryUtils.saveInventory(this, compound);
		
		if(this.hasCustomName()) {
			compound.setString("CustomName", this.name);
		}
		
		// Sided Inventory Configuration
		NBTTagList list2 = new NBTTagList();
		for(int i = 0; i < this.sided.length; i++) {
			NBTTagCompound comp = new NBTTagCompound();
			comp.setInteger("Value", this.sided[i].ordinal());
			list2.appendTag(comp);
		}
		
		compound.setTag("Sided", list2);
		
		return super.writeToNBT(compound);
	}

	@Override
	public void setType(EnumFacing side, EnumSixSidedType type) {
		sided[side.ordinal()] = type;
	}

	@Override
	public EnumSixSidedType getType(EnumFacing side) {
		// TODO Auto-generated method stub
		return sided[side.ordinal()];
	}

	@Override
	public boolean isTypeOff(EnumFacing side) {
		// TODO Auto-generated method stub
		return sided[side.ordinal()] == EnumSixSidedType.OFF;
	}
	
	@Override
	public boolean isTypePull(EnumFacing side) {
		// TODO Auto-generated method stub
		return sided[side.ordinal()] == EnumSixSidedType.PULL;
	}

	@Override
	public boolean isTypePush(EnumFacing side) {
		// TODO Auto-generated method stub
		return sided[side.ordinal()] == EnumSixSidedType.PUSH;
	}

	@Override
	public boolean isTypeDisabled(EnumFacing side) {
		return sided[side.ordinal()] == EnumSixSidedType.DISABLED;
	}

	@Override
	public boolean isInventory(TileEntity entity) {
		// TODO Auto-generated method stub
		return entity instanceof IInventory;
	}
	
	@Override
	public boolean isSixSidedInventory(TileEntity entity) {
		// TODO Auto-generated method stub
		return entity instanceof ISixSidedInventory;
	}
	
	@Override
	public boolean isSidedInventory(TileEntity entity) {
		// TODO Auto-generated method stub
		return entity instanceof ISidedInventory;
	}
	
	@Override
	public void pull(EnumFacing face) {
		EnumFacing opposite = face.getOpposite();
		BlockPos otherEntityPos = pos.add(face.getDirectionVec());
		
		TileEntity entity = worldObj.getTileEntity(otherEntityPos);
		
		if(entity != null) {
			if(this.isInventory(entity)) {
				IInventory inventory = (IInventory)entity;
				
				if(inventory != null) {
					if(this.isSixSidedInventory(entity)) {
						ISixSidedInventory s = (ISixSidedInventory)entity;
						
						if(!s.isTypeDisabled(opposite) && !s.isTypePull(opposite)) {
							handlePull(inventory);
						}
					} else if(this.isSidedInventory(entity)) {
						ISidedInventory s = (ISidedInventory)entity;
						handlePullingSided(s, opposite, inventory);
					} else {
						handlePull(inventory);
					}
				}
			}
		}
	}

	@Override
	public void push(EnumFacing face) {
		EnumFacing opposite = face.getOpposite();
		BlockPos otherEntityPos = pos.add(face.getDirectionVec());
		TileEntity entity = worldObj.getTileEntity(otherEntityPos);
		
		if(entity != null) {
			if(this.isInventory(entity)) {
				IInventory inventory = (IInventory)entity;
				
				if(inventory != null) {
					if(this.isSixSidedInventory(entity)) {
						ISixSidedInventory s = (ISixSidedInventory)entity;
						
						if(!s.isTypeDisabled(opposite) && !s.isTypePush(opposite)) {
							handlePush(inventory);
						}
						
					} else if(this.isSidedInventory(entity)) {
						ISidedInventory s = (ISidedInventory)entity;
						//handlePushSided(s, opposite, inventory);
						handlePush(inventory);
					} else {
						handlePush(inventory);
					}
				}
			}
		}
		
	}

	private void handlePull(IInventory inventory) {
		
		if(!InventoryUtils.isInventoryFull(this)) {
			// Grab Item from inventory
			int from_index = InventoryUtils.getInventoryIndex(inventory);
			
			if(from_index != -1) {
				// Grab ItemStack from inventory
				ItemStack stack = inventory.getStackInSlot(from_index);
				
				//if(stack != null) {
					// Grab index to push to
				int to_index = InventoryUtils.getInventoryIndex(this, stack);
				
				if(to_index != -1) {
					// Move ItemStack to other invnetory
					InventoryUtils.moveToInventory(this, inventory, to_index, from_index, 1);
					// set slot to null if 0
					InventoryUtils.setSlotToNullIfZero(inventory, from_index);
				}
				//}
			}
		}
	}
	
	private void handlePullingSided(ISidedInventory s, EnumFacing opposite, IInventory inventory) {
		
		if(!InventoryUtils.isInventoryFull(this)) {
			int[] slots = s.getSlotsForFace(opposite);
			
			for(int i = 0; i < slots.length; i++) {
				
				ItemStack stack = inventory.getStackInSlot(i);
				
				if(stack != null && s.canExtractItem(i, stack, opposite)) {
					int to_index = InventoryUtils.getInventoryIndex(this, stack);
					if(to_index != -1) {
						InventoryUtils.moveToInventory(this, inventory, to_index, i, 1);
						InventoryUtils.setSlotToNullIfZero(inventory, i);
					}
				}
			}
		}
	}
	
	private void handlePush(IInventory inventory) {
		
		if(!InventoryUtils.isInventoryFull(inventory)) {
			int from_index = InventoryUtils.getInventoryIndex(this);
			if(from_index != -1) {
				// Grab ItemStack from inventory
				ItemStack stack = this.getStackInSlot(from_index);
				// Grab index to push to
				
				
				//if(stack != null) {
				int to_index = InventoryUtils.getInventoryIndex(inventory, stack);
				
				if(inventory.isItemValidForSlot(to_index, stack)) {
					if(to_index != -1) {
						// Move ItemStack to other invnetory
						InventoryUtils.moveToInventory(inventory, this, to_index, from_index, 1);
						// set slot to null if 0
						InventoryUtils.setSlotToNullIfZero(this, from_index);
					}
					//}
				}
			}
		}
	}

	private void handlePushSided(ISidedInventory s, EnumFacing opposite, IInventory inventory) {
		
		if(!InventoryUtils.isInventoryFull(inventory)) {
			int from_index = InventoryUtils.getInventoryIndex(this);
			
			if(from_index != -1) {
				ItemStack stack = this.getStackInSlot(from_index);
				int slots[] = s.getSlotsForFace(opposite);
				
				for(int i = 0; i < slots.length; i++) {
					
					
					if(stack != null && s.canInsertItem(i, stack, opposite) && inventory.isItemValidForSlot(i, stack)) {
						InventoryUtils.moveToInventory(inventory, this, i, from_index, 1);
						InventoryUtils.setSlotToNullIfZero(this, from_index);
					}
				}
			}
		}
	}
	@Override
	public List<EnumSixSidedType> getAllTypes() {
		List<EnumSixSidedType> temp = new ArrayList<EnumSixSidedType>();
		
		for(EnumSixSidedType type : sided) {
			temp.add(type);
		}
		return temp;
	}
	
	@Override
	public void update() {
		
		if(!worldObj.isRemote) {
			for(EnumFacing face : faces) {
				if(this.isTypeOff(face) || this.isTypeDisabled(face)) {
					continue;
				} else {
					if(this.isTypePull(face)) {
						this.pull(face);
					} else if(this.isTypePush(face)) {
						this.push(face);
					}
				}
			}
			
			this.markDirty();
		}
	}
}
