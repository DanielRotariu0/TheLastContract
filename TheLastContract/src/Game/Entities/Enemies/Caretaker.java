package Game.Entities.Enemies;

import Game.Entities.Player;
import Game.Graphics.Animation;
import Game.Graphics.Assets;
import Game.RefLinks;
import Game.States.Level3State;
import Game.Tiles.Tile;

public class Caretaker extends Enemy {
    private int tick=0;
    private boolean attack=false;

    public Caretaker(RefLinks refLink, float x, float y, int width, int height, float speed, int life) {
        super(refLink, x, y, width, height, speed, life);


        bounds.x = 24;
        bounds.y = 28;
        bounds.width = 16;
        bounds.height = 30;

        attackBounds.x = 0;
        attackBounds.y = 0;
        attackBounds.width = 54;
        attackBounds.height = 58;

        animDown = new Animation(15, Assets.caretakerDown);
        animUp = new Animation(15, Assets.caretakerUp);
        animLeft = new Animation(15, Assets.caretakerLeft);
        animRight = new Animation(15, Assets.caretakerRight);
        lastAnim = new Animation(15, Assets.caretakerRight);

        attackDown = new Animation(15, Assets.caretakerAttackDown);
        attackUp = new Animation(15, Assets.caretakerAttackUp);
        attackLeft = new Animation(15, Assets.caretakerAttackLeft);
        attackRight = new Animation(15, Assets.caretakerAttackRight);
    }

    @Override
    public void Update(Player player){
        Level3State.level3.tiles[(int)(x/ Tile.TILE_WIDTH)+1][(int)(y/Tile.TILE_HEIGHT)+1]=6;
        tick++;
        attackDown.Update();
        attackUp.Update();
        attackRight.Update();
        attackLeft.Update();

        if(!attack) {
            gettingHit(player);
        }
        if(tick>=180) {
            if(!attack) {
                speed = 5 * speed;
                attack=true;
            }
            attackPlayer(player);
        }
        if(tick>=300 && attack) {
            tick = 0;
            speed= (float) (0.2*speed);
            attack=false;
        }
        super.Update(player);
        if(attack) {
            followPlayer(player);
        }
        else {
            xMove=0;
            yMove=0;
        }

        if(isFacing.equals("right"))
        {
            attackBounds.x = 32;
            attackBounds.y = 10;
            attackBounds.width = 48;
            attackBounds.height = 42;
        }
        if(isFacing.equals("left"))
        {
            attackBounds.x = -26;
            attackBounds.y = 10;
            attackBounds.width = 48;
            attackBounds.height = 42;
        }
        if(isFacing.equals("up"))
        {
            attackBounds.x = 5;
            attackBounds.y = -12;
            attackBounds.width = 60;
            attackBounds.height = 24;
        }
        if(isFacing.equals("down"))
        {
            attackBounds.x = 5;
            attackBounds.y = 30;
            attackBounds.width = 60;
            attackBounds.height = 40;
        }
    }

    private void attackPlayer(Player player) {
        if(attack) {
            if (player.getCollisionBounds(0, 0).intersects(getAttackBounds(0, 0))) {
                if (Assets.EnemyAttackTimeElapsed() && player.GetActualLife() > 0) {
                    player.SetActualLife(player.GetActualLife() - damage);
                    player.SetScore(player.GetScore()-5);
                }
            }
        }
    }
}