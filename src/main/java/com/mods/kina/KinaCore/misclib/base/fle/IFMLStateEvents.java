package com.mods.kina.KinaCore.misclib.base.fle;

import net.minecraftforge.fml.common.event.*;

public interface IFMLStateEvents{
    /**
     * Modが構築開始時に呼ばれる。
     */
    void construction(FMLConstructionEvent event);

    /**
     * 初期化前に呼ばれる。
     */
    void preInit(FMLPreInitializationEvent event);

    /**
     * 初期化時に呼ばれる。
     */
    void init(FMLInitializationEvent event);

    /**
     * 初期化後に呼ばれる。
     */
    void postInit(FMLPostInitializationEvent event);

    /**
     * Mod構築完了後に呼ばれる。
     */
    void complete(FMLLoadCompleteEvent event);
}
