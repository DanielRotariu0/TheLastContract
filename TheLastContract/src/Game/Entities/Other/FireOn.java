package Game.Entities.Other;

import java.awt.*;

import Game.Entities.Entity;
import Game.RefLinks;
import Game.Graphics.Assets;
import Game.Tiles.Tile;

public class FireOn extends Entity {

    public FireOn(RefLinks refLink, float x, float y) {
        super(refLink, x*32, y*32, Tile.TILE_WIDTH, Tile.TILE_HEIGHT*2);
        bounds.x = 0;
        bounds.y = 32;
        bounds.height = 32;
        bounds.width = 32;
    }

    @Override
    public void Update() {

    }

    @Override
    public void Draw(Graphics g) {

        g.drawImage(Assets.fireOn, (int)x, (int)y, width, height, null);
    }
}