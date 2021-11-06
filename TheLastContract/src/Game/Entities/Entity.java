package Game.Entities;

import Game.RefLinks;
import java.awt.*;

public abstract class Entity
{
    protected float x;
    protected float y;
    protected final int width;
    protected final int height;
    protected final Rectangle bounds;
    protected final Rectangle attackBounds;
    protected final RefLinks refLink;

    public Entity(RefLinks refLink, float x, float y, int width, int height)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.refLink = refLink;

        bounds = new Rectangle(0, 0, width, height);
        attackBounds = new Rectangle(0, 0, width, height);
    }

    public abstract void Update();
    public abstract void Draw(Graphics g);

    public boolean checkEntityCollisions(float xOffset, float yOffset){
        for(Entity e : refLink.GetMap().getEntityManager().getEntities()){
            if(e.equals(this))
                continue;
            if(e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset)))
                return true;
        }
        return false;
    }

    public Rectangle getCollisionBounds(float xOffset, float yOffset){
        return new Rectangle((int) (x + bounds.x + xOffset), (int) (y + bounds.y + yOffset), bounds.width, bounds.height);
    }

    public Rectangle getAttackBounds(float xOffset, float yOffset){
        return new Rectangle((int) (x + attackBounds.x + xOffset), (int) (y + attackBounds.y + yOffset), attackBounds.width,attackBounds.height);
    }

    public float GetX()
    {
        return x;
    }

    public float GetY()
    {
        return y;
    }

    public int GetWidth()
    {
        return width;
    }

    public int GetHeight()
    {
        return height;
    }

    public void SetX(float x)
    {
        this.x = x;
    }

    public void SetY(float y)
    {
        this.y = y;
    }
}