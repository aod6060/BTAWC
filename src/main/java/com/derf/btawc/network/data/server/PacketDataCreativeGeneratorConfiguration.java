package com.derf.btawc.network.data.server;

import com.derf.btawc.network.data.IPacketData;
import com.derf.btawc.network.data.IPacketDataCallback;
import com.derf.btawc.network.data.IPacketDataServer;
import com.derf.btawc.tileentity.generators.TileEntityCreativeGenerator;
import com.derf.btawc.util.NBTUtils;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;

public class PacketDataCreativeGeneratorConfiguration implements IPacketDataServer {
	private BlockPos pos;
	private int insantity = 0;
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		this.pos = NBTUtils.loadBlockPos(compound);
		this.insantity = compound.getInteger("insantity");
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		NBTUtils.saveBlockPos(compound, pos);
		compound.setInteger("insantity", this.insantity);
		return compound;
	}

	@Override
	public void updatePacketData() {}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "creative_generator_configuration";
	}

	@Override
	public void updatePacketData(int dimension) {
		World world = DimensionManager.getWorld(dimension);
		if(world != null) {
			TileEntity entity = world.getTileEntity(this.pos);
			if(entity instanceof TileEntityCreativeGenerator) {
				TileEntityCreativeGenerator generator = (TileEntityCreativeGenerator)entity;
				generator.insantity = this.insantity;
			}
		}
	}
	
	
	public BlockPos getPos() {
		return pos;
	}

	public void setPos(BlockPos pos) {
		this.pos = pos;
	}

	public int getInsantity() {
		return insantity;
	}

	public void setInsantity(int insantity) {
		this.insantity = insantity;
	}


	private static class Callback implements IPacketDataCallback {
		private BlockPos pos;
		private int insantity = 0;
		
		public Callback() {}
		
		public Callback(BlockPos pos, int insantity) {
			this.pos = pos;
			this.insantity = insantity;
		}
		
		@Override
		public void call(IPacketData dataPacket) {
			if(dataPacket instanceof PacketDataCreativeGeneratorConfiguration) {
				PacketDataCreativeGeneratorConfiguration conf = (PacketDataCreativeGeneratorConfiguration)dataPacket;
				conf.setPos(this.pos);
				conf.setInsantity(insantity);
			}
			
		}
	}	
	
	public static IPacketDataCallback createCallback(BlockPos pos, int insantity) {
		return new Callback(pos, insantity);
	}
}
