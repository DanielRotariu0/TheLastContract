package Game.Tiles;

import Game.Graphics.Assets;

public class SwampWaterTile extends Tile
{
    public SwampWaterTile(int id)
    {
        super(Assets.swampWater, id);
    }

    @Override
    public boolean IsSolid()
    {
        return true;
    }
}