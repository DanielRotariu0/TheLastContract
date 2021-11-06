package Game.Graphics;

import java.awt.image.BufferedImage;

public class SpriteSheet
{
    private final BufferedImage       spriteSheet;
    private final int    tileWidth  ;
    private final int    tileHeight ;

    public SpriteSheet(BufferedImage buffImg, int width, int height)
    {
        spriteSheet = buffImg;

        this.tileHeight=height;
        this.tileWidth=width;
    }

    public BufferedImage crop(int x, int y)
    {
        return spriteSheet.getSubimage(x * tileWidth, y * tileHeight, tileWidth, tileHeight);
    }
}