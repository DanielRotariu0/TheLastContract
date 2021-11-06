package Game.States;

import Game.Entities.Medallion.Medallion;
import Game.Graphics.Assets;
import Game.Entities.Enemies.Caretaker;
import Game.Maps.Map;
import Game.RefLinks;
import Game.Tiles.Tile;

import java.awt.*;
import java.sql.SQLException;

public class Level3State extends PlayState{

    public static Map level3;
    public static boolean wasBossDefeated;
    public static boolean wasBossSpawned;
    public static boolean wasBoss2Defeated;
    public static boolean wasBoss2Spawned;
    public static Caretaker enemyCaretaker;
    public static Caretaker enemyCaretaker2;
    public static boolean flag;
    public static boolean flag2;
    public static boolean flag3;

    public Level3State(RefLinks refLink){
        super(refLink);
        level3 = new Map(refLink, 3);
        wasBossDefeated=false;
        wasBossSpawned=false;
        wasBoss2Defeated=false;
        wasBoss2Spawned=false;
        flag=true;
        flag2=true;
        flag3=true;
        try {
            Assets.Level3StateMusic.setVolume(refLink.GetDatabase().getGameVolume()/200.);
        }
        catch (SQLException e)
        {
            System.err.println("Error: Level1State: GameVolume.");
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
        int playerX= (int) (player.GetX()/Tile.TILE_WIDTH)+1;
        int playerY= (int) (player.GetY()/Tile.TILE_HEIGHT)+1;
        level3.Update();

        if(refLink.GetKeyManager().esc)
        {
            State.SetState(refLink.GetGame().getPauseState());
        }

        if(player.isDead()) {
            Assets.Level3StateMusic.stop();
            Assets.Level3StateMusic.reset();
            PlayState.map.tiles[39][2] = 1;
            PlayState.map.tiles[39][3] = 1;
            PlayState.flag=true;
            Level1State.wasBossDefeated=false;
            Level1State.wasBossSpawned=false;
            Level1State.flag=true;
            Level1State.level1.tiles[39][20]=1;
            Level1State.level1.tiles[39][21]=1;
            Level1State.level1.tiles[5][31]=1;
            Level2State.wasBossDefeated=false;
            Level2State.wasBossSpawned=false;
            Level2State.flag=true;
            Level2State.level2.tiles[32][0]=8;

            if(!wasBossDefeated && wasBossSpawned)
                level3.getEntityManager().removeEntity(enemyCaretaker);
            if(!wasBoss2Defeated && wasBoss2Spawned) {
                level3.getEntityManager().removeEntity(enemyCaretaker2);
                level3.setClear(true);
            }
            wasBossDefeated=false;
            wasBossSpawned=false;
            wasBoss2Defeated=false;
            wasBoss2Spawned=false;
            flag=true;
            flag2=true;
            flag3=true;
            canSave=true;
            died(level3,3);
        }
        else
        {
            if(level3.tiles[playerX][playerY]==6 && Assets.qSecondElapsed())
            {
                player.SetActualLife(player.GetActualLife()-1);
                player.SetScore(player.GetScore()-2);
            }
        }

        if(wasBoss2Defeated) {
            if(flag2) {
                level3.getEntityManager().removeEntity(enemyCaretaker2);
                flag2 = false;
            }
        }
        else
        {
            if(wasBoss2Spawned) {
                remote.setCommand(new UpdateCommand(enemyCaretaker2, player));
                remote.executeCommand();
                if (enemyCaretaker2.GetActualLife() <= 0) {
                    player.SetScore(player.GetScore() + 300);
                    wasBoss2Defeated = true;
                }
            }
        }
        if(wasBossDefeated) {
            if(flag)
            {
                level3.getEntityManager().removeEntity(enemyCaretaker);
                flag=false;
            }
            if(wasBoss2Defeated) {
                if(flag3) {
                    flag3 = false;
                    level3.tiles[0][2] = 3;
                    level3.tiles[0][3] = 3;
                    for(int i=0;i<40;i++)
                        for(int j=0;j<32;j++)
                            if(level3.tiles[i][j]==6)
                                level3.tiles[i][j]=3;
                    level3.setClear(true);
                    Assets.Level3StateMusic.stop();
                    Assets.Level3StateMusic.reset();
                    Assets.PlayStateMusic.play();
                    canSave=true;
                    player.SetScore(player.GetScore()+20*player.GetActualLife());
                }
                if ((int) (player.GetX() / Tile.TILE_WIDTH) == -1) {
                    if ((int) (player.GetY() / Tile.TILE_HEIGHT) == 1 || (int) (player.GetY() / Tile.TILE_HEIGHT) == 2) {
                        player.SetX(1250);
                        player.SetY(80);
                        State.SetState(refLink.GetGame().getPlayState());
                        refLink.SetMap(PlayState.map);
                    }
                }
            }
        }
        else
        {
            if(wasBossSpawned) {
                if (enemyCaretaker.GetActualLife() <= 0) {
                    player.SetScore(player.GetScore() + 300);
                    wasBossDefeated = true;
                }
                remote.setCommand(new UpdateCommand(enemyCaretaker, player));
                remote.executeCommand();
                if(enemyCaretaker.GetActualLife()<= 0.5* enemyCaretaker.GetLife() && !wasBoss2Spawned)
                {
                    wasBoss2Spawned=true;
                    addMedallions();
                    try {
                        int diff = refLink.GetDatabase().getDifficulty();
                        enemyCaretaker2 = new Caretaker(refLink, 704, 640, 64, 64, 1.5f + 0.2f * diff, 10+5*diff);
                        level3.getEntityManager().addEntity(enemyCaretaker2);
                    }
                    catch (SQLException e)
                    {
                        System.err.println("Error: Level3State: Difficulty.");
                    }
                }
            }
            else
            {
                if(refLink.GetKeyManager().space)
                {
                    if(((int)(player.GetX()/ Tile.TILE_WIDTH) == 22 || (int)(player.GetX()/ Tile.TILE_WIDTH) == 23 || (int)(player.GetX()/ Tile.TILE_WIDTH) == 24) &&
                            ((int)(player.GetY()/Tile.TILE_HEIGHT) == 17 || (int)(player.GetY()/Tile.TILE_HEIGHT) == 18 || (int)(player.GetY()/Tile.TILE_HEIGHT) == 19))
                    {
                        Assets.PlayStateMusic.stop();
                        Assets.PlayStateMusic.reset();
                        Assets.Level3StateMusic.play();
                        canSave=false;
                        try {
                            int diff=refLink.GetDatabase().getDifficulty();
                            if(diff<3) {
                                player.SetActualLife(player.GetLife());
                            }
                            enemyCaretaker = new Caretaker(refLink, 640, 640, 64, 64, 1.5f + 0.2f * diff, 15+5*diff);
                            level3.getEntityManager().addEntity(enemyCaretaker);
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

        level3.Draw(g);

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
        level3.setMedallionManager();
        level3.setClear(false);
        level3.getMedallionManager().addMedallion(new Medallion(18, 6));
        level3.getMedallionManager().addMedallion(new Medallion(26, 14));
        level3.getMedallionManager().addMedallion(new Medallion(30, 5));
        level3.getMedallionManager().addMedallion(new Medallion(23, 5));
        level3.getMedallionManager().addMedallion(new Medallion(12, 17));
        level3.getMedallionManager().addMedallion(new Medallion(15, 23));
        level3.getMedallionManager().addMedallion(new Medallion(32, 25));
        level3.getMedallionManager().addMedallion(new Medallion(23, 27));
        level3.getMedallionManager().addMedallion(new Medallion(19, 20));
        level3.getMedallionManager().addMedallion(new Medallion(34, 19));
    }

}