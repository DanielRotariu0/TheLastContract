package Game.Entities.Trees;

import java.awt.*;

import Game.RefLinks;
import Game.Graphics.Assets;
import Game.Tiles.Tile;

public class Tree0 extends Tree{

    public Tree0(RefLinks refLink, float x, float y) {
        super(refLink, x, y, Tile.TILE_WIDTH*5, Tile.TILE_HEIGHT*5);

        bounds.x = 71;
        bounds.y = 128;
        bounds.height = 8;
        bounds.width = 16;
    }

    @Override
    public void Draw(Graphics g) {

        g.drawImage(Assets.tree0, (int)x, (int)y, width, height, null);
        //g.setColor(Color.blue);
        //g.fillRect((int)(x + bounds.x), (int)(y + bounds.y), bounds.width, bounds.height);
    }
}