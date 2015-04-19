package com.mods.kina.KinaCore.misclib;

@Deprecated
public enum EnumColor{
    Black(0, 0, 0),
    Blue(0, 0, 4),
    Brown(2, 1, 0),
    Cyan(0, 2, 2),
    Gray(1, 1, 1),
    Green(0, 4, 0),
    LightBlue(2, 2, 4),
    Lime(2, 4, 2),
    Magenta(4, 0, 4),
    Orange(4, 2, 0),
    Pink(4, 3, 3),
    Purple(3, 0, 4),
    Red(4, 0, 0),
    Silver(3, 3, 3),
    White(4, 4, 4),
    Yellow(4, 4, 0);
    private byte red, green, blue;

    EnumColor(int r, int g, int b){
        red = (byte) r;
        green = (byte) g;
        blue = (byte) b;
    }

    public static int getRGBColor(int r, int g, int b){
        final double five = 63.75;
        return (int) (Math.floor(r * five) * 65536 + Math.floor(g * five) * 256 + Math.floor(b * five));
    }

    public static int getRGBColor(int rgb){
        EnumColor asColor = equalColor(rgb);
        return getRGBColor(asColor.red, asColor.green, asColor.blue);
    }

    public static EnumColor equalColor(int rgb){
        EnumColor result = null;
        for(EnumColor color : EnumColor.values()){
            if(color.getRGB() == rgb){
                result = color;
                break;
            }
        }
        return result;
    }

    public byte getRed(){
        return red;
    }

    public byte getGreen(){
        return green;
    }

    public byte getBlue(){
        return blue;
    }

    public short getRGB(){
        return (short) (red * 100 + green * 10 + blue);
    }
}
