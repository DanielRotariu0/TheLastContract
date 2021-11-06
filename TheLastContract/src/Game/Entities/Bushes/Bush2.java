package Game.Entities.Bushes;

import java.awt.*;

import Game.Entities.Entity;
import Game.RefLinks;
import Game.Graphics.Assets;
import Game.Tiles.Tile;

public class Bush2 extends Entity {

    public Bush2(RefLinks refLink, float x, float y) {
        super(refLink, x*32, y*32, Tile.TILE_WIDTH, Tile.TILE_HEIGHT);
        bounds.x = 12;
        bounds.y = 20;
        bounds.height = 8;
        bounds.width = 8;
    }

    @Override
    public void Update() {

    }

    @Override
    public void Draw(Graphics g) {

        g.drawImage(Assets.bush2, (int)x, (int)y, width, height, null);
        //g.setColor(Color.blue);
        //g.fillRect((int)(x + bounds.x), (int)(y + bounds.y), bounds.width, bounds.height);
    }
}