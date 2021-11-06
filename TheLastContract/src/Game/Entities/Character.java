package Game.Entities;

import Game.RefLinks;
import Game.Tiles.Tile;

public abstract class Character extends Entity
{
    public static int DEFAULT_LIFE            = 30;
    public static final float DEFAULT_SPEED         = 2.0f;
    public static final int DEFAULT_PLAYER_WIDTH  = 48;
    public static final int DEFAULT_PLAYER_HEIGHT = 48;

    protected int life;
    protected int actualLife;
    protected float speed;
    protected float xMove;
    protected float yMove;

    public Character(RefLinks refLink, float x, float y, int width, int height)
    {
        super(refLink, x,y, width, height);
        life    = DEFAULT_LIFE;
        actualLife=life;
        speed   = DEFAULT_SPEED;
        xMove   = 0;
        yMove   = 0;
    }

    public Character(RefLinks refLink, float x, float y, int width, int height, float speed, int life)
    {
        super(refLink, x,y, width, height);
        this.life = life;
        actualLife=life;
        this.speed = speed;
        xMove   = 0;
        yMove   = 0;
    }

    public void Move()
    {
        if(!checkEntityCollisions(xMove, 0f))
            MoveX();
        if(!checkEntityCollisions(0f, yMove))
            MoveY();
    }

    public void MoveX()
    {
        int tx;

        if(xMove>0)
        {
            tx=(int)(x+xMove+bounds.x+bounds.width) / Tile.TILE_WIDTH;
            if(!collision(tx,(int)(y + bounds.y) / Tile.TILE_HEIGHT)  && !collision(tx,(int)(y+bounds.y+bounds.height)/Tile.TILE_HEIGHT))
            {
                x+=xMove;
            }
        }
        else
        {
            tx=(int)(x+xMove+bounds.x) / Tile.TILE_WIDTH;
            if(!collision(tx,(int)(y + bounds.y) / Tile.TILE_HEIGHT)  && !collision(tx,(int)(y+bounds.y+bounds.height)/Tile.TILE_HEIGHT))
            {
                x+=xMove;
            }
        }
    }

    public void MoveY()
    {
        int ty;

        if(yMove<0)
        {
            ty=(int)(y+yMove+bounds.y)/Tile.TILE_HEIGHT;
            if(!collision((int)(x+bounds.x)/Tile.TILE_WIDTH,ty) && !collision((int)(x+bounds.x+bounds.width)/Tile.TILE_WIDTH,ty))
            {
                y+=yMove;
            }
        }
        else if(yMove>=0)
        {
            ty=(int)(y+yMove+bounds.y+bounds.height)/Tile.TILE_HEIGHT;
            if(!collision((int)(x+bounds.x)/Tile.TILE_WIDTH,ty) && !collision((int)(x+bounds.x+bounds.width)/Tile.TILE_WIDTH,ty))
            {
                y+=yMove;
            }

        }
    }

    protected boolean collision(int x, int y)
    {
        return refLink.GetMap().GetTile(x,y).IsSolid();
    }

    public int GetLife()
    {
        return life;
    }

    public float GetSpeed()
    {
        return speed;
    }

    public void SetLife(int life)
    {
        this.life = life;
    }

    public void SetSpeed(float speed) {
        this.speed = speed;
    }

    public float GetXMove()
    {
        return xMove;
    }

    public float GetYMove()
    {
        return yMove;
    }

    public void SetXMove(float xMove)
    {
        this.xMove = xMove;
    }

    public void SetYMove(float yMove)
    {
        this.yMove = yMove;
    }

    public void SetActualLife(int actualLife) { this.actualLife = actualLife; }

    public int GetActualLife() { return this.actualLife; }
    public int GetDefaultLife() { return this.DEFAULT_LIFE; }
}