package com.mods.kina.KinaCore.misclib.base.fle;

import net.minecraftforge.fml.common.event.*;

public interface IFMLStateEvents{
    /**
     * Mod���\�z�J�n���ɌĂ΂��B
     */
    void construction(FMLConstructionEvent event);

    /**
     * �������O�ɌĂ΂��B
     */
    void preInit(FMLPreInitializationEvent event);

    /**
     * ���������ɌĂ΂��B
     */
    void init(FMLInitializationEvent event);

    /**
     * ��������ɌĂ΂��B
     */
    void postInit(FMLPostInitializationEvent event);

    /**
     * Mod�\�z������ɌĂ΂��B
     */
    void complete(FMLLoadCompleteEvent event);
}
