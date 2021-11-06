package Game.Entities.Other;

import java.awt.*;

import Game.Entities.Entity;
import Game.RefLinks;
import Game.Graphics.Assets;
import Game.Tiles.Tile;

public class Cave extends Entity {

    public Cave(RefLinks refLink, float x, float y) {
        super(refLink, x*32, y*32, Tile.TILE_WIDTH*7, Tile.TILE_HEIGHT*7);

        bounds.x = 0;
        bounds.y = 12;
        bounds.height = 192;
        bounds.width = 224;
    }

    @Override
    public void Update() {

    }

    @Override
    public void Draw(Graphics g) {

        g.drawImage(Assets.cave, (int)x, (int)y, width, height, null);
        //g.setColor(Color.blue);
        //g.fillRect((int)(x + bounds.x), (int)(y + bounds.y), bounds.width, bounds.height);
    }
}