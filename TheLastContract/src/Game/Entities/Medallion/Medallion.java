package Game.Entities.Medallion;


import Game.Graphics.Assets;
import Game.RefLinks;

import java.awt.*;

public class Medallion {
    public static final int width = 48, height = 48;
    protected RefLinks refLink;
    protected Rectangle bounds;
    protected int x, y;
    protected boolean pickedUp = false;

    public Medallion(int x, int y){
        bounds = new Rectangle(x*32,y*32,width,height);
        this.x = x*32;
        this.y = y*32;
    }

    public void Update(){
        if(refLink.GetMap().getEntityManager().getPlayer().getCollisionBounds(0f,0f).intersects(bounds))
        {
            pickedUp = true;
            refLink.GetMap().getEntityManager().getPlayer().SetScore(refLink.GetMap().getEntityManager().getPlayer().GetScore()+20);
        }
    }

    public void Draw(Graphics g){
        Draw(g, x, y);
    }

    public void Draw(Graphics g, int x, int y){
        g.drawImage(Assets.medallion, x, y, width, height,null);
        //g.setColor(Color.blue);
        //g.fillRect((int)(x), (int)(y), bounds.width, bounds.height);
    }

    public RefLinks getReflinks() {
        return refLink;
    }

    public void setRefLinks(RefLinks refLinks) {
        this.refLink = refLinks;
    }

    public boolean isPickedUp() {
        return pickedUp;
    }
}