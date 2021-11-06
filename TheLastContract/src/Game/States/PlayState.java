package Game.States;

import Game.Entities.Enemies.Enemy;
import Game.Graphics.Assets;
import Game.Entities.Player;
import Game.RefLinks;
import Game.Maps.Map;
import Game.Tiles.Tile;

import java.awt.*;
import java.sql.SQLException;

interface Command
{
    void execute();
}

class UpdateCommand implements Command
{
    Enemy enemy;
    Player player;

    public UpdateCommand(Enemy enemy, Player player)
    {
        this.enemy = enemy;
        this.player = player;
    }
    public void execute()
    {
        enemy.Update(player);
    }
}

class RemoteControl
{
    Command slot;

    public RemoteControl()
    {
    }

    public void setCommand(Command command)
    {
        slot = command;
    }

    public void executeCommand()
    {
        slot.execute();
    }
}

public class PlayState extends State
{
    protected final Player player;
    public static Map map;
    public static boolean flag;
    protected RemoteControl remote;
    protected static boolean canSave;


    public PlayState(RefLinks refLink){
        super(refLink);
        player = Player.getInstance(refLink,512,928);
        map    = new Map(refLink,0);
        refLink.SetMap(map);
        flag=true;
        canSave=true;
        try {
            Assets.PlayStateMusic.setVolume(refLink.GetDatabase().getGameVolume()/200.);
        }
        catch (SQLException e)
        {
            System.err.println("Error: PlayState: GameVolume.");
        }
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
        map.Update();

        if(refLink.GetKeyManager().esc)
        {
            State.SetState(refLink.GetGame().getPauseState());
        }
        if(Level2State.wasBossDefeated) {
            if (flag) {
                flag = false;
                map.tiles[39][2] = 3;
                map.tiles[39][3] = 3;
            }
            if ((int) (player.GetX() / Tile.TILE_WIDTH) == 40)
                if ((int) (player.GetY() / Tile.TILE_WIDTH) == 1 || (int) (player.GetY() / Tile.TILE_HEIGHT) == 2) {
                    player.SetX(0);
                    player.SetY(80);
                    State.SetState(refLink.GetGame().getLevel3State());
                    refLink.SetMap(Level3State.level3);
                }
        }

        if ((int) (player.GetX() / Tile.TILE_WIDTH) == -1)
            if ((int) (player.GetY() / Tile.TILE_HEIGHT) == 19 || (int) (player.GetY() / Tile.TILE_HEIGHT) == 20) {
                player.SetX(1216);
                player.SetY(640);
                State.SetState(refLink.GetGame().getLevel1State());
                refLink.SetMap(Level1State.level1);
            }
        if(Level3State.wasBossDefeated)
        {
            if((int)(player.GetX()/ Tile.TILE_WIDTH)==15 && (int)(player.GetY()/Tile.TILE_HEIGHT)==29)
            {
                try{
                    refLink.GetDatabase().updateHighestScore(player.GetScore());
                    EndState.highestScore=refLink.GetDatabase().getHighestScore();
                }
                catch (SQLException e)
                {
                    System.err.print("Error: PlayState: Load score from database");
                }
                Assets.PlayStateMusic.stop();
                Assets.PlayStateMusic.reset();
                Assets.EndStateMusic.play();
                State.SetState(refLink.GetGame().getEndState());
            }
        }
    }

    @Override
    public void Draw(Graphics g) {
        g.setColor(Color.black);
        g.setFont(new Font("Dosis", Font.PLAIN, 48));

        map.Draw(g);

        g.drawString("Score:",refLink.GetWidth()-220,refLink.GetHeight()-100);
        g.drawString(Integer.toString(player.GetScore()),refLink.GetWidth()-220,refLink.GetHeight()-50);
    }

    protected void retry()
    {
        player.SetX(512);
        player.SetY(928);
        player.SetActualLife(player.GetLife());
        player.SetScore(0);
        refLink.SetMap(map);
        Assets.PlayStateMusic.reset();
        Assets.PlayStateMusic.play();
    }

    protected void drawHoverSword(Graphics g)
    {
        if(refLink.GetMouseManager().getMouseX() >= 548 && refLink.GetMouseManager().getMouseX() <= 732)
        {
            if(refLink.GetMouseManager().getMouseY() >= 423 && refLink.GetMouseManager().getMouseY() <= 498)
            {
                g.drawImage(Assets.hoverSword,736,430,381,100,null);
            }
            if(refLink.GetMouseManager().getMouseY() >= 561 && refLink.GetMouseManager().getMouseY() <= 620)
            {
                g.drawImage(Assets.hoverSword,736,540,381,100,null);
            }
        }
    }

    protected void died(Map map, int mapNr){
        try {
            if (refLink.GetDatabase().getAutoRetry() == 1) {
                retry();
                map.LoadWorld(mapNr);
                State.SetState(refLink.GetGame().getPlayState());
            }
        }
        catch (SQLException e)
        {
            System.err.println("Error: PlayState: AutoRetry.");
        }
        if (refLink.GetMouseManager().getMouseX() >= 548 && refLink.GetMouseManager().getMouseX() <= 732) {
            if (refLink.GetMouseManager().getMouseY() >= 423 && refLink.GetMouseManager().getMouseY() <= 498) {
                if (refLink.GetMouseManager().leftClickPressed()) {
                    retry();
                    map.LoadWorld(mapNr);
                    State.SetState(refLink.GetGame().getPlayState());
                }
            }
            if (refLink.GetMouseManager().getMouseY() >= 561 && refLink.GetMouseManager().getMouseY() <= 620) {
                if (refLink.GetMouseManager().leftClickPressed()) {

                    System.exit(0);
                }
            }
        }
    }

    public static void resetLevels()
    {
        map.tiles[39][2] = 1;
        map.tiles[39][3] = 1;
        flag=true;

        if(!Level1State.wasBossDefeated && Level1State.wasBossSpawned)
            Level1State.level1.getEntityManager().removeEntity(Level1State.enemyWerewolf);
        Level1State.wasBossDefeated=false;
        Level1State.wasBossSpawned=false;
        Level1State.flag=true;
        Level1State.level1.tiles[39][20]=1;
        Level1State.level1.tiles[39][21]=1;
        Level1State.level1.tiles[5][31]=1;
        Level1State.level1.setClear(true);

        if(!Level2State.wasBossDefeated && Level2State.wasBossSpawned)
            Level2State.level2.getEntityManager().removeEntity(Level2State.enemyWitch);
        Level2State.wasBossDefeated=false;
        Level2State.wasBossSpawned=false;
        Level2State.flag=true;
        Level2State.level2.setClear(true);

        if(!Level3State.wasBossDefeated && Level3State.wasBossSpawned)
            Level3State.level3.getEntityManager().removeEntity(Level3State.enemyCaretaker);
        if(!Level3State.wasBoss2Defeated && Level3State.wasBoss2Spawned) {
            Level3State.level3.getEntityManager().removeEntity(Level3State.enemyCaretaker2);
            Level3State.level3.setClear(true);
        }
        Level3State.wasBossDefeated=false;
        Level3State.wasBossSpawned=false;
        Level3State.wasBoss2Defeated=false;
        Level3State.wasBoss2Spawned=false;
        Level3State.flag=true;
        Level3State.flag2=true;
        Level3State.flag3=true;
        Level3State.level3.setClear(true);
        for(int i=0;i<40;i++)
            for(int j=0;j<32;j++)
                if(Level3State.level3.tiles[i][j]==6)
                    Level3State.level3.tiles[i][j]=3;

    }
}