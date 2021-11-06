package Game.Entities.Enemies;

import Game.Entities.Player;
import Game.Graphics.Animation;
import Game.Graphics.Assets;
import Game.RefLinks;

public class Werewolf extends Enemy {

    public Werewolf(RefLinks refLink, float x, float y, int width, int height, float speed, int life) {
        super(refLink, x, y, width, height, speed, life);
        bounds.x = 24;
        bounds.y = 28;
        bounds.width = 16;
        bounds.height = 30;

        attackBounds.x = 0;
        attackBounds.y = 0;
        attackBounds.width = 54;
        attackBounds.height = 58;

        animDown = new Animation(75, Assets.werewolfDown);
        animUp = new Animation(75, Assets.werewolfUp);
        animLeft = new Animation(75, Assets.werewolfLeft);
        animRight = new Animation(75, Assets.werewolfRight);
        lastAnim = new Animation(75, Assets.werewolfRight);

        attackDown = new Animation(60, Assets.werewolfAttackDown);
        attackUp = new Animation(60, Assets.werewolfAttackUp);
        attackLeft = new Animation(60, Assets.werewolfAttackLeft);
        attackRight = new Animation(60, Assets.werewolfAttackRight);
    }

    @Override
    public void Update(Player player){
        attackDown.Update();
        attackUp.Update();
        attackRight.Update();
        attackLeft.Update();

        followPlayer(player);
        attackPlayer(player);
        gettingHit(player);

        if(isFacing.equals("right"))
        {
            attackBounds.x = 26;
            attackBounds.y = 10;
            attackBounds.width = 48;
            attackBounds.height = 48;
        }
        if(isFacing.equals("left"))
        {
            attackBounds.x = -26;
            attackBounds.y = 10;
            attackBounds.width = 48;
            attackBounds.height = 48;
        }
        if(isFacing.equals("up"))
        {
            attackBounds.x = 5;
            attackBounds.y = -12;
            attackBounds.width = 60;
            attackBounds.height = 40;
        }
        if(isFacing.equals("down"))
        {
            attackBounds.x = 5;
            attackBounds.y = 30;
            attackBounds.width = 60;
            attackBounds.height = 40;
        }
    }

    void attackPlayer(Player player) {
        if(xMove==0 && yMove==0)
        {
            attack=true;
            if (player.getCollisionBounds(0, 0).intersects(getAttackBounds(0,0))) {
                if(Assets.EnemyAttackTimeElapsed() && player.GetActualLife() > 0) {
                    player.SetActualLife(player.GetActualLife() - damage);
                    player.SetScore(player.GetScore()-5);
                    attack=false;
                }
            }
        }
    }
}