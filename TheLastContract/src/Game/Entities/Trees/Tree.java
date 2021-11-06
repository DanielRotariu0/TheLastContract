package Game.Entities.Trees;

import java.awt.*;

import Game.Entities.Entity;
import Game.RefLinks;
import Game.Tiles.Tile;

public class Tree extends Entity {

    public Tree(RefLinks refLink, float x, float y, int width, int height) {
        super(refLink, x*32, y*32, Tile.TILE_WIDTH*width/32, Tile.TILE_HEIGHT*height/32);
        bounds.width = 10;
    }

    @Override
    public void Update() {

    }

    @Override
    public void Draw(Graphics g) {

    }
}