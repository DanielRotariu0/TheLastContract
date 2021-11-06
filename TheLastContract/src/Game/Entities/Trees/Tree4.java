package Game.Entities.Trees;

import java.awt.*;
import Game.RefLinks;
import Game.Graphics.Assets;
import Game.Tiles.Tile;

public class Tree4 extends Tree{

    public Tree4(RefLinks refLink, float x, float y) {
        super(refLink, x, y, Tile.TILE_WIDTH*6, Tile.TILE_HEIGHT*6);
        bounds.x = 90;
        bounds.y = 136;
        bounds.height = 26;
    }

    @Override
    public void Draw(Graphics g) {

        g.drawImage(Assets.tree4, (int)x, (int)y, width, height, null);
        //g.setColor(Color.blue);
        //g.fillRect((int)(x + bounds.x), (int)(y + bounds.y), bounds.width, bounds.height);
    }
}