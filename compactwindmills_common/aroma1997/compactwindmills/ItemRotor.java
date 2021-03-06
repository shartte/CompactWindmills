/*******************************************************************************
 * Copyright (c) 2013 Aroma1997.
 * All rights reserved. This program and other files related to this program are
 * licensed with a extended GNU General Public License v. 3
 * License informations are at:
 * https://github.com/Aroma1997/CompactWindmills/blob/master/license.txt
 ******************************************************************************/

package aroma1997.compactwindmills;


import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * 
 * @author Aroma1997
 * 
 */
public class ItemRotor extends Item implements IItemRotor {
	
	private int tierMin;
	
	private int tierMax;
	
	private float efficiency;
	
	private boolean isInfinite;
	
	public ItemRotor(int id) {
		super(id + Reference.ItemIDDifference);
		setCreativeTab(CompactWindmills.creativeTabCompactWindmills);
		setMaxStackSize(1);
		setNoRepair();
	}
	
	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer,
		List par3List, boolean par4) {
		if (!isInfinite) {
			int leftOverTicks = par1ItemStack.getMaxDamage() - par1ItemStack.getItemDamage();
			par3List.add(leftOverTicks + " ticks left over on this Rotor.");
		}
		else {
			par3List.add("Infinite");
		}
		par3List.add("Efficiency: "
			+ (int) (((IItemRotor) par1ItemStack.getItem()).getEfficiency() * 100) + "%");
	}
	
	public boolean doesRotorFitInWindmill(WindType type) {
		return type.ordinal() <= tierMax && type.ordinal() >= tierMin;
	}
	
	public float getEfficiency() {
		return efficiency;
	}
	
	public int getMaxTier() {
		return tierMax;
	}
	
	public int getMinTier() {
		return tierMin;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {
		
		itemIcon = iconRegister.registerIcon(Reference.ModID + ":" + this.getUnlocalizedName());
	}
	
	public ItemRotor setEfficiency(float efficiency) {
		this.efficiency = efficiency;
		return this;
	}
	
	public ItemRotor setMinMaxTier(WindType type) {
		tierMin = type.ordinal();
		tierMax = type.ordinal();
		return this;
	}
	
	public ItemRotor setMinMaxTier(WindType typeMin, WindType typeMax) {
		tierMin = typeMin.ordinal();
		tierMax = typeMax.ordinal();
		return this;
	}

	@Override
	public boolean isInfinite() {
		return this.isInfinite;
	}

	@Override
	public String getRenderTexture() {
		return "/mods/" + Reference.ModID + "/textures/renderers/" + this.getUnlocalizedName() + ".png";
	}
	
	@Override
	public ItemRotor setMaxDamage(int maxDamage) {
		super.setMaxDamage(maxDamage);
		if (maxDamage == 0) this.isInfinite = true;
		return this;
	}

	@Override
	public void tickRotor(ItemStack rotor, TileEntityWindmill tileEntity, World worldObj) {
		return;
	}
	
}
