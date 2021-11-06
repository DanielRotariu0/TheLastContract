package Game.Tiles;

import Game.Graphics.Assets;

public class RockTile extends Tile{

    public RockTile(int id)
    {
        super(Assets.rock, id);
    }

    @Override
    public boolean IsSolid()
    {
        return true;
    }
}