package com.mods.kina.KinaCore.movelib.O18n;

import com.mods.kina.KinaCore.movelib.config.KinaProp;

public enum O18nField{
    @KinaProp(isInstance = true)
    GENERAL{
        public boolean isEnable(){
            return isEnable;
        }

        @KinaProp
        public boolean isEnable = true;
    },
    @KinaProp(isInstance = true, category = "block")
    BLOCK{
        public String[] getCreativeTab(){
            return creativeTab;
        }

        public String[] getHardness(){
            return hardness;
        }

        public String[] getHarvestLevel(){
            return harvestLevel;
        }

        public String[] getLightLevel(){
            return lightLevel;
        }

        public String[] getLightOpacity(){
            return lightOpacity;
        }

        public String[] getResistance(){
            return resistance;
        }

        public String[] getStepSound(){
            return stepSound;
        }

        @KinaProp(comment = "set light opacity \"Block,Level\"")
        public String[] lightOpacity = {};
        @KinaProp(comment = "set resistance(Explosion) \"Block,Level\"")
        public String[] resistance = {};
        @KinaProp(comment = "set hardness(Mining) \"Block,Level\"")
        public String[] hardness = {};
        @KinaProp(comment = "set light level \"Block,Level\"")
        public String[] lightLevel = {};
        @KinaProp(comment = "set creative tab \"Block,Tab\"Tabs=(Block,Brewing,Combat,Decorations,Food,Materials,Misc,Redstone,Tools,Transport)")
        public String[] creativeTab = {};
        @KinaProp(comment = "set step sound \"Block,Sound\"Sounds=(Anvil,Cloth,Grass,Glass,Gravel,Ladder,Metal,Piston,Sand,Stone,Wood)")
        public String[] stepSound = {};
        @KinaProp(comment = "set harvest level \"Block,Tool,Level\"Tools=(axe,pickaxe,shovel),Levels=(Wood:0,Stone:1,Iron:2,Diamond:3,Gold:0)")
        public String[] harvestLevel = {};
    },
    @KinaProp(isInstance = true, category = "item")
    ITEM{
        public String[] getCreativeTab(){
            return creativeTab;
        }

        public String[] getFull3D(){
            return full3D;
        }

        public String[] getHarvestLevel(){
            return harvestLevel;
        }

        public String[] getMaxDamage(){
            return maxDamage;
        }

        public String[] getMaxStackSize(){
            return maxStackSize;
        }

        public String[] getPotionEffect(){
            return potionEffect;
        }

        @KinaProp(comment = "set creative tab \"Item,Tab\"Tabs=(Block,Brewing,Combat,Decorations,Food,Materials,Misc,Redstone,Tools,Transport)")
        public String[] creativeTab = {};
        @KinaProp(comment = "set durability \"Item,MaxDamage\"")
        public String[] maxDamage = {};
        @KinaProp(comment = "set max stack size \"Item,MaxStackSize\"")
        public String[] maxStackSize = {};
        @KinaProp(comment = "set potion recipe \"Item,VanillaPosition\"Positions=(BlazePowder,FermentedSpiderEye,Glowstone,GoldenCarrot,GoldenMelon,Gunpowder,MagmaCream,NetherWart,Redstone,SpiderEye,Sugar,Pufferfish,RabbitFoot)")
        public String[] potionEffect = {};
        @KinaProp(comment = "set harvest level \"Item,Tool,Level\"Tools=(axe,pickaxe,shovel),Levels=(Wood:0,Stone:1,Iron:2,Diamond:3,Gold:0)")
        public String[] harvestLevel = {};
        @KinaProp(comment = "set hold tool like \"Item\"")
        public String[] full3D = {};
    },
    @KinaProp(isInstance = true, category = "food")
    FOOD{
        public String[] getAlwaysEdible(){
            return alwaysEdible;
        }

        public String[] getPotionEffect(){
            return potionEffect;
        }

        @KinaProp(comment = "set always edible \"Food\"")
        public String[] alwaysEdible = {};
        @KinaProp(comment = "set potion effect \"Food,Effect(name or id),Duration,AmplifierLevel,Probability\"Effects=(Speed,Slowness,Haste,MiningFatigue,Strength,InstantHealth,InstantDamage,JumpBoost,Nausea,Regeneration,Resistance,FireResistance,WaterBreathing,Invisibility,Blindness,NightVision,Hunger,Weakness,Poison,Wither,HealthBoost,Absorption,Saturation)")
        public String[] potionEffect = {};
    },
    @KinaProp(isInstance = true, category = "fluid")
    FLUID{
        public String[] getDensity(){
            return density;
        }

        public String[] getLuminosity(){
            return luminosity;
        }

        public String[] getTemperature(){
            return temperature;
        }

        public String[] getViscosity(){
            return viscosity;
        }

        @KinaProp(comment = "set light level \"Fluid,Level\"")
        public String[] luminosity = {};
        @KinaProp(comment = "set density \"Fluid,Level\"")
        public String[] density = {};
        @KinaProp(comment = "set temperature \"Fluid,Degree\"")
        public String[] temperature = {};
        @KinaProp(comment = "set viscosity \"Fluid,Level\"")
        public String[] viscosity = {};
    },
    @KinaProp(isInstance = true, category = "ore")
    ORE{
        public String[] getRegister(){
            return register;
        }

        @KinaProp(comment = "register OreDictionary \"Item,Name\"")
        public String[] register = {};
    },;

    public boolean isEnable(){
        throw new UnsupportedOperationException();
    }


    public String[] getCreativeTab(){
        throw new UnsupportedOperationException();
    }

    public String[] getHardness(){
        throw new UnsupportedOperationException();
    }

    public String[] getHarvestLevel(){
        throw new UnsupportedOperationException();
    }

    public String[] getLightLevel(){
        throw new UnsupportedOperationException();
    }

    public String[] getLightOpacity(){
        throw new UnsupportedOperationException();
    }

    public String[] getResistance(){
        throw new UnsupportedOperationException();
    }

    public String[] getStepSound(){
        throw new UnsupportedOperationException();
    }

    public String[] getFull3D(){
        throw new UnsupportedOperationException();
    }

    public String[] getMaxDamage(){
        throw new UnsupportedOperationException();
    }

    public String[] getMaxStackSize(){
        throw new UnsupportedOperationException();
    }

    public String[] getPotionEffect(){
        throw new UnsupportedOperationException();
    }

    public String[] getAlwaysEdible(){
        throw new UnsupportedOperationException();
    }

    public String[] getDensity(){
        throw new UnsupportedOperationException();
    }

    public String[] getLuminosity(){
        throw new UnsupportedOperationException();
    }

    public String[] getTemperature(){
        throw new UnsupportedOperationException();
    }

    public String[] getViscosity(){
        throw new UnsupportedOperationException();
    }

    public String[] getRegister(){
        throw new UnsupportedOperationException();
    }
}
