package Game.Entities.Trees;

import java.awt.*;

import Game.Entities.Entity;
import Game.RefLinks;
import Game.Graphics.Assets;
import Game.Tiles.Tile;

public class SwampLog extends Entity {

    public SwampLog(RefLinks refLink, float x, float y) {
        super(refLink, x*32, y*32, Tile.TILE_WIDTH*2, Tile.TILE_HEIGHT);

        bounds.x = 4;
        bounds.y = 12;
        bounds.height = 12;
        bounds.width = 56;
    }

    @Override
    public void Update() {

    }

    @Override
    public void Draw(Graphics g) {

        g.drawImage(Assets.swampLog, (int)x, (int)y, width, height, null);
        //g.setColor(Color.blue);
        //g.fillRect((int)(x + bounds.x), (int)(y + bounds.y), bounds.width, bounds.height);
    }
}