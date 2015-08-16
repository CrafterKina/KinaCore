package com.mods.kina.KinaCore.misclib.utils.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import static com.mods.kina.KinaCore.misclib.utils.render.UtilRender.renderItem;
import static com.mods.kina.KinaCore.misclib.utils.render.UtilRender.worldRenderer;

public class UtilRenderItem{
    private static final Method renderQuads = ReflectionHelper.findMethod(RenderItem.class, renderItem, new String[]{"renderQuads", "func_175032_a"}, WorldRenderer.class, List.class, int.class, ItemStack.class);
    private static final Method renderModel = ReflectionHelper.findMethod(RenderItem.class, renderItem, new String[]{"renderModel", "func_175045_a"}, IBakedModel.class, int.class, ItemStack.class);

    public static void renderQuads(List quads, ItemStack stack){
        if(quads != null && stack != null) try{
            renderQuads.invoke(renderItem, worldRenderer, quads, -1, stack);
        } catch(IllegalAccessException e){
            throw new AssertionError(new IllegalArgumentException(e));
        } catch(InvocationTargetException e){
            throw new AssertionError(new IllegalArgumentException(e));
        }
    }

    public static void renderEffect(IBakedModel model){
        if(model == null) return;
        GlStateManager.depthMask(false);
        GlStateManager.depthFunc(514);
        GlStateManager.disableLighting();
        GlStateManager.blendFunc(768, 1);
        Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("textures/misc/enchanted_item_glint.png"));
        GlStateManager.matrixMode(5890);
        GlStateManager.pushMatrix();
        GlStateManager.scale(8.0F, 8.0F, 8.0F);
        float f = (float) (Minecraft.getSystemTime() % 3000L) / 3000.0F / 8.0F;
        GlStateManager.translate(f, 0.0F, 0.0F);
        GlStateManager.rotate(-50.0F, 0.0F, 0.0F, 1.0F);
        renderModel(model, 0xff8040cc);
        GlStateManager.popMatrix();
        GlStateManager.pushMatrix();
        GlStateManager.scale(8.0F, 8.0F, 8.0F);
        float f1 = (float) (Minecraft.getSystemTime() % 4873L) / 4873.0F / 8.0F;
        GlStateManager.translate(-f1, 0.0F, 0.0F);
        GlStateManager.rotate(10.0F, 0.0F, 0.0F, 1.0F);
        renderModel(model, 0xff8040cc);
        GlStateManager.popMatrix();
        GlStateManager.matrixMode(5888);
        GlStateManager.blendFunc(770, 771);
        GlStateManager.enableLighting();
        GlStateManager.depthFunc(515);
        GlStateManager.depthMask(true);
        Minecraft.getMinecraft().getTextureManager().bindTexture(TextureMap.locationBlocksTexture);
    }

    public static void renderModel(IBakedModel model, int color){
        if(model != null) try{
            renderModel.invoke(renderItem, model, color, null);
        } catch(IllegalAccessException e){
            throw new AssertionError(new IllegalArgumentException(e));
        } catch(InvocationTargetException e){
            throw new AssertionError(new IllegalArgumentException(e));
        }
    }
}
