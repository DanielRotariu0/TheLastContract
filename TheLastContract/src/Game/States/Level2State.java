package Game.States;

import Game.Entities.Medallion.Medallion;
import Game.Entities.Enemies.Witch;
import Game.Graphics.Assets;
import Game.Maps.Map;
import Game.RefLinks;
import Game.Tiles.Tile;

import java.awt.*;
import java.sql.SQLException;

public class Level2State extends PlayState{

    public static Map level2;
    public static boolean wasBossDefeated;
    public static boolean wasBossSpawned;
    public static Witch enemyWitch;
    public static boolean flag;

    public Level2State(RefLinks refLink){
        super(refLink);
        level2 = new Map(refLink, 2);
        wasBossDefeated=false;
        wasBossSpawned=false;
        flag=true;
        try {
            Assets.Level2StateMusic.setVolume(refLink.GetDatabase().getGameVolume()/200.);
        }
        catch (SQLException e)
        {
            System.err.println("Error: Level2State: GameVolume.");
        }
        remote = new RemoteControl();
    }

    @Override
    public void Update() {
        if(State.GetPreviousState()==refLink.GetGame().getMenuState())
        {
            if(MenuState.getLoad())
            {
                try {
                    float playerX = refLink.GetDatabase().getPlayerX();
                    float playerY = refLink.GetDatabase().getPlayerY();
                    int playerLife = refLink.GetDatabase().getPlayerLife();
                    int playerScore = refLink.GetDatabase().getPlayerScore();

                    player.SetX(playerX);
                    player.SetY(playerY);
                    player.SetActualLife(playerLife);
                    player.SetScore(playerScore);

                    MenuState.setLoad(false);
                }
                catch (SQLException e)
                {
                    System.err.print("Error: PlayState: Load from database");
                }
            }
        }
        level2.Update();

        if(refLink.GetKeyManager().esc)
        {
            State.SetState(refLink.GetGame().getPauseState());
        }

        if(player.isDead()) {
            Level1State.wasBossDefeated=false;
            Level1State.wasBossSpawned=false;
            Level1State.flag=true;
            Level1State.level1.tiles[39][20]=1;
            Level1State.level1.tiles[39][21]=1;
            Level1State.level1.tiles[5][31]=1;
            Level1State.level1.setClear(true);
            Assets.Level2StateMusic.stop();
            Assets.Level2StateMusic.reset();
            level2.getEntityManager().removeEntity(enemyWitch);
            wasBossDefeated=false;
            wasBossSpawned=false;
            flag=true;
            level2.setClear(true);
            canSave=true;
            died(level2, 2);
        }

        if(wasBossDefeated) {
            if(flag)
            {
                flag=false;
                level2.tiles[32][0]=10;
                level2.getEntityManager().removeEntity(enemyWitch);
                level2.setClear(true);
                Assets.Level2StateMusic.stop();
                Assets.Level2StateMusic.reset();
                Assets.PlayStateMusic.play();
                canSave=true;
            }
            if ((int) (player.GetX() / Tile.TILE_WIDTH) == 31) {
                if  ((int) (player.GetY() / Tile.TILE_HEIGHT) == 0) {
                    Assets.Level2StateMusic.stop();
                    Assets.Level2StateMusic.reset();
                    Assets.PlayStateMusic.play();
                    player.SetX(154);
                    player.SetY(940);
                    State.SetState(refLink.GetGame().getLevel1State());
                    refLink.SetMap(Level1State.level1);
                }
            }
        }
        else
        {
            if(wasBossSpawned)
            {
                remote.setCommand(new UpdateCommand(enemyWitch, player));
                remote.executeCommand();
                if (enemyWitch.GetActualLife() <= 0) {
                    player.SetScore(player.GetScore() + 200);
                    wasBossDefeated = true;
                }
            }
            else
            {
                if(refLink.GetKeyManager().space)
                {
                    if(((int)(player.GetX()/ Tile.TILE_WIDTH) == 22 || (int)(player.GetX()/ Tile.TILE_WIDTH) == 23 || (int)(player.GetX()/ Tile.TILE_WIDTH) == 24) &&
                            ((int)(player.GetY()/Tile.TILE_HEIGHT) == 26 || (int)(player.GetY()/Tile.TILE_HEIGHT) == 27 || (int)(player.GetY()/Tile.TILE_HEIGHT) == 28))
                    {
                        addMedallions();
                        canSave=false;
                        try {
                            int diff=refLink.GetDatabase().getDifficulty();
                            if(diff<3) {
                                player.SetActualLife(player.GetLife());
                            }
                            enemyWitch = new Witch(refLink, 384, 736, 64, 64, 1.5f + 0.2f * diff, 10+5*diff);
                            level2.getEntityManager().addEntity(enemyWitch);
                        }
                        catch (SQLException e)
                        {
                            System.err.println("Error: Level3State: Difficulty.");
                        }
                        wasBossSpawned = true;
                    }
                }
            }
        }
    }

    @Override
    public void Draw(Graphics g) {
        g.setColor(Color.black);
        g.setFont(new Font("Dosis", Font.PLAIN, 48));

        level2.Draw(g);

        g.drawString("Score:",refLink.GetWidth()-220,refLink.GetHeight()-100);
        g.drawString(Integer.toString(player.GetScore()),refLink.GetWidth()-220,refLink.GetHeight()-50);
        if(player.isDead())
        {
            g.drawImage(Assets.deadState,0,0,refLink.GetWidth(),refLink.GetHeight(),null);
            drawHoverSword(g);
        }

    }

    @Override
    public void retry() {
        super.retry();
    }

    public void addMedallions()
    {
        level2.setMedallionManager();
        level2.setClear(false);
        level2.getMedallionManager().addMedallion(new Medallion(18, 2));
        level2.getMedallionManager().addMedallion(new Medallion(29, 21));
        level2.getMedallionManager().addMedallion(new Medallion(30, 6));
        level2.getMedallionManager().addMedallion(new Medallion(4, 1));
        level2.getMedallionManager().addMedallion(new Medallion(7, 6));
        level2.getMedallionManager().addMedallion(new Medallion(14, 23));
        level2.getMedallionManager().addMedallion(new Medallion(11, 29));
        level2.getMedallionManager().addMedallion(new Medallion(23, 27));
        level2.getMedallionManager().addMedallion(new Medallion(19, 20));
        level2.getMedallionManager().addMedallion(new Medallion(36, 15));
    }

}