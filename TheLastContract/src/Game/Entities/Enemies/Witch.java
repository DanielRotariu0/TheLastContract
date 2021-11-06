package Game.Entities.Enemies;

import Game.Entities.Player;
import Game.Graphics.Animation;
import Game.Graphics.Assets;
import Game.RefLinks;

import java.awt.*;

public class Witch extends Enemy {

    private int tick=0;
    private boolean attack=false;
    private final Animation animAttack;

    public Witch(RefLinks refLink, float x, float y, int width, int height, float speed, int life) {
        super(refLink, x, y, width, height, speed, life);

        bounds.x = 24;
        bounds.y = 28;
        bounds.width = 16;
        bounds.height = 30;

        attackBounds.x = -28;
        attackBounds.y = -32;
        attackBounds.width = 0;
        attackBounds.height = 0;

        animDown = new Animation(100, Assets.witchDown);
        animUp = new Animation(100, Assets.witchUp);
        animLeft = new Animation(100, Assets.witchLeft);
        animRight = new Animation(100, Assets.witchRight);
        lastAnim = new Animation(100, Assets.witchRight);

        animAttack = new Animation(20, Assets.witchAttack);

    }

    @Override
    public void Update(Player player){
        animAttack.Update();
        tick++;
        followPlayer(player);
        if(!attack) {
            gettingHit(player);
        }
        if(tick>=180) {
            if(!attack) {
                speed = 4 * speed;
                attack=true;
                attackBounds.width=128;
                attackBounds.height=128;
                animAttack.resetIndex();
            }
            attackPlayer(player);
        }
        if(tick>=300 && attack) {
            tick = 0;
            speed= (float) (0.25*speed);
            attack=false;
            attackBounds.width=0;
            attackBounds.height=0;
        }
    }

    @Override
    public void Draw(Graphics g) {
        if(attack)
        {
            g.drawImage(this.animAttack.getCurrentFrame(), (int) x-60, (int) y-56, 192, 186, null);
        }
        else
        {
            g.drawImage(Assets.hpBackground, (int) x, (int) y-4, 60, 8, null);
            g.drawImage(Assets.enemyhpForeground, (int) x, (int) y-4, (60 * actualLife * 10) / (life * 10), 8, null);
            g.drawImage(Assets.hpBorder, (int) x, (int) y-4, 60, 8, null);
            g.drawImage(getCurrentAnimationFrame(), (int) x, (int) y, width, height, null);
        }

        //g.setColor(Color.blue);
        //g.drawRect((int)(x + bounds.x), (int)(y + bounds.y), bounds.width, bounds.height);
        //g.drawRect((int)(x + attackBounds.x), (int)(y + attackBounds.y), attackBounds.width, attackBounds.height);
    }

    private void attackPlayer(Player player) {
            if (player.getCollisionBounds(0, 0).intersects(getAttackBounds(0,0))) {
                if(Assets.EnemyAttackTimeElapsed() && player.GetActualLife() > 0) {
                    player.SetActualLife(player.GetActualLife() - damage);
                    player.SetScore(player.GetScore()-5);
                }
            }
    }

}