package Game.Entities.Trees;

import java.awt.*;

import Game.RefLinks;
import Game.Graphics.Assets;
import Game.Tiles.Tile;

public class Tree2 extends Tree{

    public Tree2(RefLinks refLink, float x, float y) {
        super(refLink, x, y, Tile.TILE_WIDTH*4, Tile.TILE_HEIGHT*5);

        bounds.x = 56;
        bounds.y = 134;
        bounds.height = 8;
    }

    @Override
    public void Draw(Graphics g) {

        g.drawImage(Assets.tree2, (int)x, (int)y, width, height, null);
        //g.setColor(Color.blue);
        //g.fillRect((int)(x + bounds.x), (int)(y + bounds.y), bounds.width, bounds.height);
    }
}