package com.derf.btawc.blocks.witherproof;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block.SoundType;

public class BlockWitherProofGlass extends BlockWitherProof {

	public BlockWitherProofGlass(String name, float lightlevel, SoundType type) {
		super(name, lightlevel, type);
		// TODO Auto-generated constructor stub
	}
	
	@SideOnly(Side.CLIENT)
	public int getRenderBlockPass() {
		return 1;
	}
	
	public boolean renderAsNormalBlock() {
		return false;
	}
}
