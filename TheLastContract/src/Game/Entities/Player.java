package Game.Entities;

import java.awt.*;
import java.awt.image.BufferedImage;

import Game.Graphics.Animation;
import Game.RefLinks;
import Game.Graphics.Assets;


public class Player extends Character
{
    private final Animation animDown, animUp, animLeft, animRight;
    private final Animation attackDown, attackUp, attackLeft, attackRight;
    private Animation lastAnim;
    private String isFacing = "right";
    private boolean attack;
    private int score;
    private static Player instance = null;

    private Player(RefLinks refLink, float x, float y)
    {
        super(refLink, x,y, Character.DEFAULT_PLAYER_WIDTH, Character.DEFAULT_PLAYER_HEIGHT);

        score=0;

        animDown = new Animation(75, Assets.playerDown);
        animUp = new Animation(75, Assets.playerUp);
        animLeft = new Animation(75, Assets.playerLeft);
        animRight = new Animation(75, Assets.playerRight);
        lastAnim = new Animation(75, Assets.playerRight);

        attackDown = new Animation(60, Assets.playerAttackDown);
        attackUp = new Animation(60, Assets.playerAttackUp);
        attackLeft = new Animation(60, Assets.playerAttackLeft);
        attackRight = new Animation(60, Assets.playerAttackRight);

        bounds.x = 16;
        bounds.y = 16;
        bounds.width = 16;
        bounds.height = 26;

        attackBounds.x = 26;
        attackBounds.y = 10;
        attackBounds.width = 54;
        attackBounds.height = 32;
        try
        {
           life = life / refLink.GetDatabase().getDifficulty();
           actualLife=life;
        }
        catch (java.sql.SQLException e)
        {
            System.err.println("Error: Player: Database: Difficulty)");
        }
    }

    public static Player getInstance(RefLinks refLink, float x, float y)
    {
        if(instance==null)
        {
            instance = new Player(refLink,x,y);
        }
        return instance;
    }

    @Override
    public void Update()
    {
        if(!isDead()) {

            if(isFacing.equals("right"))
            {
                attackBounds.x = 26;
                attackBounds.y = 10;
                attackBounds.width = 54;
                attackBounds.height = 32;
            }
            if(isFacing.equals("left"))
            {
                attackBounds.x = -26;
                attackBounds.y = 10;
                attackBounds.width = 54;
                attackBounds.height = 32;
            }
            if(isFacing.equals("up"))
            {
                attackBounds.x = -10;
                attackBounds.y = -12;
                attackBounds.width = 70;
                attackBounds.height = 40;
            }
            if(isFacing.equals("down"))
            {
                attackBounds.x = -10;
                attackBounds.y = 16;
                attackBounds.width = 70;
                attackBounds.height = 40;
            }

            animDown.Update();
            animUp.Update();
            animRight.Update();
            animLeft.Update();
            attackDown.Update();
            attackUp.Update();
            attackRight.Update();
            attackLeft.Update();

            GetInput();
            Move();
        }
    }

    private void GetInput()
    {
        xMove = 0;
        yMove = 0;
        if(!attack) {
            if (refLink.GetKeyManager().up) {
                yMove = -speed;
            }
            if (refLink.GetKeyManager().down) {
                yMove = speed;
            }
            if (refLink.GetKeyManager().left) {
                xMove = -speed;
            }
            if (refLink.GetKeyManager().right) {
                xMove = speed;
            }
            if (refLink.GetKeyManager().space) {
                xMove = 0;
                yMove = 0;
                attack = true;
                attackUp.resetIndex();
                attackDown.resetIndex();
                attackLeft.resetIndex();
                attackRight.resetIndex();
            }
        }
    }

    @Override
    public void Draw(Graphics g)
    {
        if(!this.isDead()) {
            if(attack)
            {
                if(isFacing.equals("up"))
                {
                    g.drawImage(attackUp.getCurrentFrame(), (int) x-48, (int) y-34, 144, 138, null);
                }
                if(isFacing.equals("down"))
                {
                    g.drawImage(attackDown.getCurrentFrame(), (int) x-48, (int) y-42, 144, 138, null);
                }
                if(isFacing.equals("left"))
                {
                    g.drawImage(attackLeft.getCurrentFrame(), (int) x-48, (int) y-39, 144, 138, null);
                }
                if(isFacing.equals("right"))
                {
                    g.drawImage(attackRight.getCurrentFrame(), (int) x-48, (int) y-48, 144, 138, null);
                }
                if(Assets.PlayerAttackTimeElapsed()) {
                    attack = false;
                }
            }
            else
            {
                g.drawImage(getCurrentAnimationFrame(), (int) x, (int) y, width, height, null);
            }

            g.drawImage(Assets.hpBackground, (int) x, (int) y, 50, 8, null);
            g.drawImage(Assets.hpForeground, (int) x, (int) y, (50 * actualLife * 10) / (life * 10), 8, null);
            g.drawImage(Assets.hpBorder, (int) x, (int) y, 50, 8, null);
            //g.setColor(Color.blue);
            //g.fillRect((int)(x + bounds.x), (int)(y + bounds.y), bounds.width, bounds.height);
            //g.drawRect((int)(x + attackBounds.x), (int)(y + attackBounds.y), attackBounds.width, attackBounds.height);
        }
    }

    private BufferedImage getCurrentAnimationFrame(){
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

    public boolean isDead()
    {
        return actualLife <= 0;
    }

    public int GetScore() { return this.score;}

    public void SetScore(int score) { this.score=score; }

}