package Game.Entities.Trees;

import java.awt.*;

import Game.RefLinks;
import Game.Graphics.Assets;
import Game.Tiles.Tile;

public class Tree3 extends Tree{

    public Tree3(RefLinks refLink, float x, float y) {
        super(refLink, x, y, Tile.TILE_WIDTH*6, Tile.TILE_HEIGHT*6);
        bounds.x = 87;
        bounds.y = 139;
        bounds.height = 20;
        bounds.width = 16;
    }

    @Override
    public void Draw(Graphics g) {

        g.drawImage(Assets.tree3, (int)x, (int)y, width, height, null);
        //g.setColor(Color.blue);
        //g.fillRect((int)(x + bounds.x), (int)(y + bounds.y), bounds.width, bounds.height);
    }
}