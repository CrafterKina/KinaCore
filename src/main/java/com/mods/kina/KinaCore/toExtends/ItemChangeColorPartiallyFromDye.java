/*
package com.mods.kina.KinaCore.toExtends;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import java.util.List;

public abstract class ItemChangeColorPartiallyFromDye extends Item{
    private IIcon overIcon;
    private String overIconName;

    protected ItemChangeColorPartiallyFromDye(String iconName){
        overIconName=iconName;
    }

    @SideOnly(Side.CLIENT)
    public boolean requiresMultipleRenderPasses(){
        return true;
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamageForRenderPass(int damage, int pass){
        return pass > 0 ? overIcon : super.getIconFromDamageForRenderPass(damage, pass);
    }

    @SideOnly(Side.CLIENT)
    public int getColorFromItemStack(ItemStack stack, int pass){
        //return pass==1?EnumColor.getRGBColor(stack.getItemDamage()):16777215;
        return pass==1?ItemDye.field_150922_c[stack.getItemDamage()]:16777215;
    }

    @SuppressWarnings("unchecked")
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs tab, List list) {
        //for(EnumColor color:EnumColor.values())list.add(getColorStack(item,color));
        for(int i = 0; i < 16; i++)list.add(new ItemStack(item,1,i));
    }

    public String getUnlocalizedName(ItemStack par1ItemStack) {
        return super.getUnlocalizedName() + "_" + ItemDye.field_150923_a[par1ItemStack.getItemDamage()];
    }

    public void registerIcons(IIconRegister register){
        super.registerIcons(register);
        overIcon=register.registerIcon(overIconName);
    }

    public int getMetadata(int p_77647_1_)
    {
        return p_77647_1_;
    }
}
*/
