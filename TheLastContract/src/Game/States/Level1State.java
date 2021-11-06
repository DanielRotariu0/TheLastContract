package Game.States;

import Game.Entities.Medallion.Medallion;
import Game.Graphics.Assets;
import Game.Entities.Enemies.Werewolf;
import Game.Maps.Map;
import Game.RefLinks;
import Game.Tiles.Tile;

import java.awt.*;
import java.sql.SQLException;

public class Level1State extends PlayState{

    public static Map level1;
    public static boolean wasBossDefeated;
    public static boolean wasBossSpawned;
    public static Werewolf enemyWerewolf;
    public static boolean flag;

    public Level1State(RefLinks refLink) {
        super(refLink);
        level1 = new Map(refLink, 1);
        wasBossDefeated=false;
        wasBossSpawned=false;
        flag=true;
        try {
            Assets.Level1StateMusic.setVolume(refLink.GetDatabase().getGameVolume()/200.);
        }
        catch (SQLException e)
        {
            System.err.println("Error: Level1State: GameVolume");
        }
        remote = new RemoteControl();
    }

    @Override
    public void Update(){
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
        level1.Update();
        if(refLink.GetKeyManager().esc) {
            State.SetState(refLink.GetGame().getPauseState());
        }

        if(player.isDead()) {
            Assets.Level1StateMusic.stop();
            Assets.Level1StateMusic.reset();
            level1.getEntityManager().removeEntity(enemyWerewolf);
            wasBossDefeated=false;
            wasBossSpawned=false;
            flag=true;
            level1.tiles[39][20]=1;
            level1.tiles[39][21]=1;
            level1.tiles[5][31]=1;
            level1.setClear(true);
            canSave=true;
            died(level1, 1);
        }

        if(wasBossDefeated) {
            if(flag)
            {
                flag = false;
                level1.tiles[39][20]=3;
                level1.tiles[39][21]=3;
                level1.tiles[5][31]=3;
                level1.getEntityManager().removeEntity(enemyWerewolf);
                level1.setClear(true);
                Assets.Level1StateMusic.stop();
                Assets.Level1StateMusic.reset();
                Assets.PlayStateMusic.play();
                canSave=true;
            }
            if ((int) (player.GetX() / Tile.TILE_WIDTH) == 40) {
                if ((int) (player.GetY() / Tile.TILE_HEIGHT) == 19 || (int) (player.GetY() / Tile.TILE_HEIGHT) == 20) {
                    player.SetX(32);
                    player.SetY(640);
                    State.SetState(refLink.GetGame().getPlayState());
                    refLink.SetMap(PlayState.map);
                }
            }
            if ((int) (player.GetX() / Tile.TILE_WIDTH) == 4) {
                if ((int) (player.GetY() / Tile.TILE_HEIGHT) == 30) {
                    Assets.PlayStateMusic.stop();
                    Assets.PlayStateMusic.reset();
                    Assets.Level2StateMusic.play();
                    player.SetX(1016);
                    player.SetY(32);
                    State.SetState(refLink.GetGame().getLevel2State());
                    refLink.SetMap(Level2State.level2);
                }
            }
        }
        else
        {
            if(wasBossSpawned) {
                remote.setCommand(new UpdateCommand(enemyWerewolf, player));
                remote.executeCommand();
                if (enemyWerewolf.GetActualLife() <= 0) {
                    player.SetScore(player.GetScore() + 100);
                    wasBossDefeated = true;
                }
            }
            else
            {
                if(refLink.GetKeyManager().space)
                {
                    if(((int)(player.GetX()/ Tile.TILE_WIDTH) == 11 || (int)(player.GetX()/ Tile.TILE_WIDTH) == 12 || (int)(player.GetX()/ Tile.TILE_WIDTH) == 13) &&
                            ((int)(player.GetY()/Tile.TILE_HEIGHT) == 14 || (int)(player.GetY()/Tile.TILE_HEIGHT) == 15 || (int)(player.GetY()/Tile.TILE_HEIGHT) == 16))
                    {
                        addMedallions();
                        Assets.PlayStateMusic.stop();
                        Assets.PlayStateMusic.reset();
                        Assets.Level1StateMusic.play();
                        canSave=false;
                        try {
                            int diff=refLink.GetDatabase().getDifficulty();
                            enemyWerewolf = new Werewolf(refLink, 320, 352, 64, 64, 1.5f + 0.2f * diff, 5 * diff);
                            level1.getEntityManager().addEntity(enemyWerewolf);
                        }
                        catch (SQLException e)
                        {
                            System.err.println("Error: Level1State: Difficulty.");
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

        level1.Draw(g);

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
        level1.setMedallionManager();
        level1.setClear(false);
        level1.getMedallionManager().addMedallion(new Medallion(10, 11));
        level1.getMedallionManager().addMedallion(new Medallion(26, 14));
        level1.getMedallionManager().addMedallion(new Medallion(30, 5));
        level1.getMedallionManager().addMedallion(new Medallion(4, 1));
        level1.getMedallionManager().addMedallion(new Medallion(1, 14));
        level1.getMedallionManager().addMedallion(new Medallion(7, 23));
        level1.getMedallionManager().addMedallion(new Medallion(8, 29));
        level1.getMedallionManager().addMedallion(new Medallion(23, 27));
        level1.getMedallionManager().addMedallion(new Medallion(19, 20));
        level1.getMedallionManager().addMedallion(new Medallion(34, 19));
    }

}