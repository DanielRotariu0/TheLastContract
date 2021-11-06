package Game.Entities.Other;

import java.awt.*;

import Game.Entities.Entity;
import Game.RefLinks;
import Game.Graphics.Assets;
import Game.Tiles.Tile;

public class House extends Entity {

    public House(RefLinks refLink, float x, float y) {
        super(refLink, x*32, y*32, 210, 192);

        bounds.x = 26;
        bounds.y = 64;
        bounds.height = 122;
        bounds.width = 160;
    }

    @Override
    public void Update() {

    }

    @Override
    public void Draw(Graphics g) {

        g.drawImage(Assets.house, (int)x, (int)y, width, height, null);
        //g.setColor(Color.blue);
        //g.drawRect((int)(x + bounds.x), (int)(y + bounds.y), bounds.width, bounds.height);
    }
}