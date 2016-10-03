package com.derf.btawc.util;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;

public class Holder {
	private BlockPos pos;
	private EnumFacing direction;
	
	public Holder(BlockPos pos, EnumFacing direction) {
		this.pos = pos;
		this.direction = direction;
	}

	public BlockPos getPos() {
		return pos;
	}

	public void setPos(BlockPos pos) {
		this.pos = pos;
	}

	public EnumFacing getDirection() {
		return direction;
	}

	public void setDirection(EnumFacing direction) {
		this.direction = direction;
	}
	
	public static List<Holder> getHolders(BlockPos pos) {
		List<Holder> temp = new ArrayList<Holder>();
		
		temp.add(new Holder(pos.west(), EnumFacing.WEST));
		temp.add(new Holder(pos.east(), EnumFacing.EAST));
		temp.add(new Holder(pos.north(), EnumFacing.NORTH));
		temp.add(new Holder(pos.south(), EnumFacing.SOUTH));
		temp.add(new Holder(pos.up(), EnumFacing.UP));
		temp.add(new Holder(pos.down(), EnumFacing.DOWN));
		
		return temp;
	}
}
