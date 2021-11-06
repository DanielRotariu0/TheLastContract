package Game.Tiles;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile
{
    private static final int NO_TILES   = 32;
    public static final Tile[] tiles    = new Tile[NO_TILES];

    private final static TileFactory tileFactory = new TileFactory();

    public static final Tile grassTile              = tileFactory.createTile(0);
    public static final Tile rockTile               = tileFactory.createTile(1);
    public static Tile waterTile                    = tileFactory.createTile(2);
    public static Tile soilTile                     = tileFactory.createTile(3);
    public static Tile bridgeleftTile               = tileFactory.createTile(4);
    public static Tile bridgerightTile              = tileFactory.createTile(5);
    public static Tile keyTile                      = tileFactory.createTile(6);
    public static Tile SwampSoilTile                = tileFactory.createTile(7);
    public static Tile SwampWaterTile               = tileFactory.createTile(8);
    public static Tile SwampBridgeHorizontalTile    = tileFactory.createTile(9);
    public static Tile SwampBridgeVerticalTile      = tileFactory.createTile(10);

    public static final int TILE_WIDTH  = 32;
    public static final int TILE_HEIGHT = 32;

    protected final BufferedImage img;
    protected final int id;

    public Tile(BufferedImage image, int idd)
    {
        img = image;
        id = idd;

        tiles[id] = this;
    }

    public void Update() {

    }

    public void Draw(Graphics g, int x, int y)
    {
        g.drawImage(img, x, y, TILE_WIDTH, TILE_HEIGHT, null);
    }

    public boolean IsSolid()
    {
        return false;
    }
}