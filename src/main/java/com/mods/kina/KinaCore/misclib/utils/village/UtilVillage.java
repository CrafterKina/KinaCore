/*
 * Copyright (c) 2015, CrafterKina
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *  Redistributions of source code must retain the above copyright notice,
 *   this list of conditions and the following disclaimer.
 *  Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 *  Neither the name of the CrafterKina nor the　names of its contributors
 *   may be used to endorse or promote products derived from this software
 *   without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL CrafterKina BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.mods.kina.KinaCore.misclib.utils.village;

import com.google.common.base.Predicate;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.village.Village;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

import java.util.List;

public class UtilVillage{
    public static World getVillageWorld(Village village){
        return ObfuscationReflectionHelper.getPrivateValue(Village.class, village, 0);
    }

    public static AxisAlignedBB getVillageAABB(Village village){
        return new AxisAlignedBB((double) (village.getCenter().getX() - village.getVillageRadius()), (double) (village.getCenter().getY() - 4), (double) (village.getCenter().getZ() - village.getVillageRadius()), (double) (village.getCenter().getX() + village.getVillageRadius()), (double) (village.getCenter().getY() + 4), (double) (village.getCenter().getZ() + village.getVillageRadius()));
    }

    @SuppressWarnings("unchecked")
    public static <E extends Entity> List<? extends E> getEntitiesWithin(Village village, Class<E> entityClass, Predicate<? extends Entity> selector){
        return getVillageWorld(village).getEntitiesWithinAABB(entityClass, getVillageAABB(village), selector);
    }

    @SuppressWarnings("unchecked")
    public static <E extends Entity> List<? extends E> getEntitiesWithin(Village village, Class<E> entityClass){
        return getEntitiesWithin(village, entityClass, IEntitySelector.NOT_SPECTATING);
    }

    public static List<? extends EntityVillager> getVillagersWithin(Village village){
        return getEntitiesWithin(village, EntityVillager.class);
    }

    public static List<? extends EntityIronGolem> getIronGolemsWithin(World world, Village village){
        return getEntitiesWithin(village, EntityIronGolem.class);
    }

    public static BiomeGenBase getVillageBiome(World world, Village village){
        return world.getBiomeGenForCoords(village.getCenter());
    }
}