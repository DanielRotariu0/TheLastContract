package Game.Entities.Enemies;

import Game.Entities.Character;
import Game.Entities.Player;
import Game.Graphics.Animation;
import Game.Graphics.Assets;
import Game.RefLinks;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.sql.SQLException;

public class Enemy  extends Character {

    protected Animation animDown, animUp, animLeft, animRight, lastAnim;
    protected Animation attackDown, attackUp, attackLeft, attackRight;
    protected String isFacing = "right";
    protected boolean attack=false;
    protected int damage=1;

    public Enemy(RefLinks refLink, float x, float y, int width, int height, float speed, int life) {
        super(refLink, x, y, width, height, speed, life);
        try {
            int diff = refLink.GetDatabase().getDifficulty();
            if(diff>=2)
                damage = diff-1;
        }
        catch (SQLException e){
            System.err.println("Error: Enemy: Database");
        }
    }

    @Override
    public void Update() {
        animDown.Update();
        animUp.Update();
        animRight.Update();
        animLeft.Update();
    }

    public void Update(Player player) {
        Update();
    }

    protected void followPlayer(Player player) {
        if (!player.isDead()) {
            xMove = 0;
            yMove = 0;
            if(Math.abs(player.GetX() - x) >= 32) {
                if (x < player.GetX()) {
                    xMove = speed;
                    attack = false;
                }
                if (x > player.GetX()) {
                    xMove = -speed;
                    attack = false;
                }
            }
            if (y < player.GetY() && Math.abs(player.GetY() - y) >= 44) {
                yMove = speed;
                attack = false;
            }
            if (y > player.GetY() && Math.abs(player.GetY() - y) >= 18) {
                yMove = -speed;
                attack = false;
            }
            Move();
        }
    }
    protected void gettingHit(Player player) {
        if (!player.isDead()) {
            if (getCollisionBounds(0,0).intersects(player.getAttackBounds(0,0))) {
                if (refLink.GetKeyManager().space && Assets.PlayerAttackTimeElapsed()) {
                    actualLife = actualLife - 1;
                }
            }
        }
    }

    @Override
    public void Draw(Graphics g) {
        if(attack)
        {
            if(isFacing.equals("up"))
            {
                g.drawImage(this.attackUp.getCurrentFrame(), (int) x, (int) y, width, height, null);
            }
            if(isFacing.equals("down"))
            {
                g.drawImage(this.attackDown.getCurrentFrame(), (int) x, (int) y, width, height, null);
            }
            if(isFacing.equals("left"))
            {
                g.drawImage(this.attackLeft.getCurrentFrame(), (int) x, (int) y, width, height, null);
            }
            if(isFacing.equals("right"))
            {
                g.drawImage(this.attackRight.getCurrentFrame(), (int) x, (int) y, width, height, null);
            }
            if(Assets.EnemyAttackTimeElapsed()) {
                attack = false;
            }
        }
        else
        {
            g.drawImage(getCurrentAnimationFrame(), (int) x, (int) y, width, height, null);
        }
        g.drawImage(Assets.hpBackground, (int) x, (int) y+8, 70, 8, null);
        g.drawImage(Assets.enemyhpForeground, (int) x, (int) y+8, (70 * actualLife * 10) / (life * 10), 8, null);
        g.drawImage(Assets.hpBorder, (int) x, (int) y+8, 70, 8, null);
        //g.setColor(Color.blue);
        //g.drawRect((int)(x + bounds.x), (int)(y + bounds.y), bounds.width, bounds.height);
        //g.drawRect((int)(x + attackBounds.x), (int)(y + attackBounds.y), attackBounds.width, attackBounds.height);
    }

    BufferedImage getCurrentAnimationFrame() {
        if(xMove < 0){
            lastAnim = animLeft;
            isFacing = "left";
            return  animLeft.getCurrentFrame();
        }else if(xMove > 0){
            lastAnim = animRight;
            isFacing = "right";
            return  animRight.getCurrentFrame();
        }else if(yMove < 0){
            lastAnim = animUp;
            isFacing = "up";
            return  animUp.getCurrentFrame();
        }else if(yMove>0){
            lastAnim = animDown;
            isFacing = "down";
            return  animDown.getCurrentFrame();
        } else {
            return lastAnim.getFirstFrame();
        }
    }
}