package Game.Tiles;

import Game.Graphics.Assets;

public class WaterTile extends Tile
{
    public WaterTile(int id)
    {
        super(Assets.water, id);
    }

    @Override
    public boolean IsSolid()
    {
        return true;
    }
}