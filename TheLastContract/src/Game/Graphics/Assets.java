package Game.Graphics;

import java.awt.image.BufferedImage;

public class Assets
{
    static boolean player_passed=false;
    static long player_now;
    static long player_then;

    static boolean q_passed=false;
    static long q_now;
    static long q_then;

    static boolean enemy_passed=false;
    static long enemy_now;
    static long enemy_then;

    public static BufferedImage hoverSword;
    public static BufferedImage[] playerLeft;
    public static BufferedImage[] playerRight;
    public static BufferedImage[] playerUp;
    public static BufferedImage[] playerDown;
    public static BufferedImage[] playerAttackLeft;
    public static BufferedImage[] playerAttackRight;
    public static BufferedImage[] playerAttackUp;
    public static BufferedImage[] playerAttackDown;

    public static BufferedImage[] werewolfLeft;
    public static BufferedImage[] werewolfRight;
    public static BufferedImage[] werewolfUp;
    public static BufferedImage[] werewolfDown;
    public static BufferedImage[] werewolfAttackLeft;
    public static BufferedImage[] werewolfAttackRight;
    public static BufferedImage[] werewolfAttackUp;
    public static BufferedImage[] werewolfAttackDown;

    public static BufferedImage[] witchLeft;
    public static BufferedImage[] witchRight;
    public static BufferedImage[] witchUp;
    public static BufferedImage[] witchDown;
    public static BufferedImage[] witchAttack;

    public static BufferedImage[] caretakerLeft;
    public static BufferedImage[] caretakerRight;
    public static BufferedImage[] caretakerUp;
    public static BufferedImage[] caretakerDown;
    public static BufferedImage[] caretakerAttackLeft;
    public static BufferedImage[] caretakerAttackRight;
    public static BufferedImage[] caretakerAttackUp;
    public static BufferedImage[] caretakerAttackDown;

    public static BufferedImage soil;
    public static BufferedImage grass;
    public static BufferedImage water;
    public static BufferedImage rock;
    public static BufferedImage lava;
    public static BufferedImage bridge_left;
    public static BufferedImage bridge_right;
    public static BufferedImage swampSoil;
    public static BufferedImage swampWater;
    public static BufferedImage swampBridgeVertical;
    public static BufferedImage swampBridgeHorizontal;

    public static BufferedImage tree0;
    public static BufferedImage tree1;
    public static BufferedImage tree2;
    public static BufferedImage tree3;
    public static BufferedImage tree4;
    public static BufferedImage tree5;
    public static BufferedImage tree6;
    public static BufferedImage tree7;

    public static BufferedImage log;
    public static BufferedImage swampLog;
    public static BufferedImage cave;
    public static BufferedImage house;
    public static BufferedImage bush1;
    public static BufferedImage bush2;
    public static BufferedImage medallion;
    public static BufferedImage boulder;
    public static BufferedImage cross;
    public static BufferedImage tombstone;
    public static BufferedImage fireOn;
    public static BufferedImage lily;
    public static BufferedImage wood;

    public static BufferedImage background;
    public static BufferedImage pause;
    public static BufferedImage deadState;
    public static BufferedImage settingsBackground;
    public static BufferedImage infoBackground;
    public static BufferedImage hpBackground;
    public static BufferedImage hpForeground;
    public static BufferedImage enemyhpForeground;
    public static BufferedImage hpBorder;
    public static BufferedImage endStateBackground;

    public static Sound MenuMusic;
    public static Sound PlayStateMusic;
    public static Sound Level1StateMusic;
    public static Sound Level2StateMusic;
    public static Sound Level3StateMusic;
    public static Sound EndStateMusic;

    public static void Init(){
        SpriteSheet characterSheet = new SpriteSheet(ImageLoader.LoadImage("/textures/player_walk.png"),64,64);
        SpriteSheet characterAttackSheet = new SpriteSheet(ImageLoader.LoadImage("/textures/player_attack.png"),192,186);
        SpriteSheet werewolf       = new SpriteSheet(ImageLoader.LoadImage("/textures/werewolf.png"),64,64);
        SpriteSheet witch          = new SpriteSheet(ImageLoader.LoadImage("/textures/witch_walk.png"),64,64);
        SpriteSheet witch_attack   = new SpriteSheet(ImageLoader.LoadImage("/textures/witch_attack.png"),192,187);
        SpriteSheet caretaker      = new SpriteSheet(ImageLoader.LoadImage("/textures/caretaker.png"),64,64);
        SpriteSheet bridge         = new SpriteSheet(ImageLoader.LoadImage("/textures/bridge.jpg"),32,32);
        SpriteSheet swampTerrain   = new SpriteSheet(ImageLoader.LoadImage("/textures/swamp_tr.png"),32,32);
        SpriteSheet swampBridge    = new SpriteSheet(ImageLoader.LoadImage("/textures/swamp_bridge.png"),32,32);
        SpriteSheet bushes         = new SpriteSheet(ImageLoader.LoadImage("/textures/bushes.png"),32,32);
        SpriteSheet tombstones     = new SpriteSheet(ImageLoader.LoadImage("/textures/tombstones.png"),32,32);

        background=ImageLoader.LoadImage("/MenuState.jpg");
        pause=ImageLoader.LoadImage("/PauseState.jpg");
        deadState=ImageLoader.LoadImage("/DeadState.jpg");
        settingsBackground=ImageLoader.LoadImage("/SettingsState.jpg");
        infoBackground=ImageLoader.LoadImage("/InfoState.jpg");
        endStateBackground = ImageLoader.LoadImage("/EndState.jpg");

        hpBackground = ImageLoader.LoadImage("/textures/healthbarBackground.png");
        hpForeground = ImageLoader.LoadImage("/textures/healthbarForeground.png");
        enemyhpForeground = ImageLoader.LoadImage("/textures/enemyHealthbarForeground.png");
        hpBorder     = ImageLoader.LoadImage("/textures/healthbarBorder.png");

        MenuMusic   = new Sound("res/music/MenuMusic.mp3");
        PlayStateMusic   = new Sound("res/music/PlayStateMusic.mp3");
        Level1StateMusic = new Sound("res/music/Level1StateMusic.mp3");
        Level2StateMusic = new Sound("res/music/Level2StateMusic.mp3");
        Level3StateMusic = new Sound("res/music/Level3StateMusic.mp3");
        EndStateMusic    = new Sound("res/music/EndStateMusic.mp3");


        hoverSword=ImageLoader.LoadImage("/optionH.png");

        grass        =ImageLoader.LoadImage("/textures/grass.png");
        rock         =ImageLoader.LoadImage("/textures/rock.png");
        soil         =ImageLoader.LoadImage("/textures/dirt.png");
        water        =ImageLoader.LoadImage("/textures/water.png");
        bridge_left  = bridge.crop(0,0);
        bridge_right = bridge.crop(1,0);
        lava     =ImageLoader.LoadImage("/textures/lava.png");
        tree0   = ImageLoader.LoadImage("/textures/trees/tree0.png");
        tree1   = ImageLoader.LoadImage("/textures/trees/tree1.png");
        tree2   = ImageLoader.LoadImage("/textures/trees/tree2.png");
        tree3   = ImageLoader.LoadImage("/textures/trees/tree3.png");
        tree4   = ImageLoader.LoadImage("/textures/trees/tree4.png");
        tree5   = ImageLoader.LoadImage("/textures/trees/tree5.png");
        tree6   = ImageLoader.LoadImage("/textures/trees/tree6.png");
        tree7   = ImageLoader.LoadImage("/textures/trees/tree7.png");
        log     = ImageLoader.LoadImage("/textures/log.png");
        cave    = ImageLoader.LoadImage("/textures/cave.png");
        house    = ImageLoader.LoadImage("/textures/house.png");
        bush1   = bushes.crop(0,0);
        bush2   = bushes.crop(1,0);
        medallion=ImageLoader.LoadImage("/textures/medallion.png");
        cross   = tombstones.crop(0,0);
        tombstone = tombstones.crop(1,0);
        boulder   = ImageLoader.LoadImage("/textures/boulder.png");
        wood   = ImageLoader.LoadImage("/textures/wood.png");
        fireOn  = ImageLoader.LoadImage("/textures/fire_on.png");
        lily = ImageLoader.LoadImage("/textures/lily.png");

        swampSoil  = swampTerrain.crop(0,0);
        swampWater = swampTerrain.crop(2,0);
        swampBridgeHorizontal=swampBridge.crop(1,0);
        swampBridgeVertical=swampBridge.crop(0,0);

        swampLog   = ImageLoader.LoadImage("/textures/swamp_log.png");

        playerLeft  = new BufferedImage[9];
        playerRight = new BufferedImage[9];
        playerUp    = new BufferedImage[9];
        playerDown  = new BufferedImage[9];
        for(int i=0;i<9;i++)
            playerLeft[i] = characterSheet.crop(i, 1);
        for(int i=0;i<9;i++)
            playerRight[i] = characterSheet.crop(i, 3);
        for(int i=0;i<9;i++)
            playerUp[i] = characterSheet.crop(i,0);
        for(int i=0;i<9;i++)
            playerDown[i] = characterSheet.crop(i,2);

        playerAttackLeft  = new BufferedImage[6];
        playerAttackRight = new BufferedImage[6];
        playerAttackUp    = new BufferedImage[6];
        playerAttackDown  = new BufferedImage[6];
        for(int i=0;i<6;i++)
            playerAttackLeft[i] = characterAttackSheet.crop(i, 1);
        for(int i=0;i<6;i++)
            playerAttackRight[i] = characterAttackSheet.crop(i, 3);
        for(int i=0;i<6;i++)
            playerAttackUp[i] = characterAttackSheet.crop(i,0);
        for(int i=0;i<6;i++)
            playerAttackDown[i] = characterAttackSheet.crop(i,2);

        werewolfLeft  = new BufferedImage[9];
        werewolfRight = new BufferedImage[9];
        werewolfUp    = new BufferedImage[9];
        werewolfDown  = new BufferedImage[9];
        for(int i=0;i<9;i++)
            werewolfLeft[i] = werewolf.crop(i, 1);
        for(int i=0;i<9;i++)
            werewolfRight[i] = werewolf.crop(i, 3);
        for(int i=0;i<9;i++)
           werewolfUp[i] = werewolf.crop(i,0);
        for(int i=0;i<9;i++)
            werewolfDown[i] = werewolf.crop(i,2);

        werewolfAttackLeft  = new BufferedImage[6];
        werewolfAttackRight = new BufferedImage[6];
        werewolfAttackUp    = new BufferedImage[6];
        werewolfAttackDown  = new BufferedImage[6];
        for(int i=5;i>=0;i--)
            werewolfAttackLeft[i] = werewolf.crop(i, 5);
        for(int i=5;i>=0;i--)
            werewolfAttackRight[i] = werewolf.crop(i, 7);
        for(int i=5;i>=0;i--)
            werewolfAttackUp[i] = werewolf.crop(i,4);
        for(int i=5;i>=0;i--)
            werewolfAttackDown[i] = werewolf.crop(i,6);

        witchLeft   = new BufferedImage[9];
        witchRight  = new BufferedImage[9];
        witchUp     = new BufferedImage[9];
        witchDown   = new BufferedImage[9];
        witchAttack = new BufferedImage[4];
        for(int i=0;i<9;i++)
            witchLeft[i] = witch.crop(i, 1);
        for(int i=0;i<9;i++)
            witchRight[i] = witch.crop(i, 3);
        for(int i=0;i<9;i++)
            witchUp[i] = witch.crop(i,0);
        for(int i=0;i<9;i++)
            witchDown[i] = witch.crop(i,2);

        witchAttack[0] = witch_attack.crop(4,2);
        witchAttack[1] = witch_attack.crop(4,1);
        witchAttack[2] = witch_attack.crop(4,3);
        witchAttack[3] = witch_attack.crop(4,0);

        caretakerLeft   = new BufferedImage[9];
        caretakerRight  = new BufferedImage[9];
        caretakerUp     = new BufferedImage[9];
        caretakerDown   = new BufferedImage[9];
        for(int i=0;i<9;i++)
            caretakerLeft[i] = caretaker.crop(i, 5);
        for(int i=0;i<9;i++)
            caretakerRight[i] = caretaker.crop(i, 7);
        for(int i=0;i<9;i++)
            caretakerUp[i] = caretaker.crop(i,4);
        for(int i=0;i<9;i++)
            caretakerDown[i] = caretaker.crop(i,6);

        caretakerAttackLeft  = new BufferedImage[6];
        caretakerAttackRight = new BufferedImage[6];
        caretakerAttackUp    = new BufferedImage[6];
        caretakerAttackDown  = new BufferedImage[6];
        for(int i=0;i<6;i++)
            caretakerAttackLeft[i] = caretaker.crop(i, 1);
        for(int i=0;i<6;i++)
            caretakerAttackRight[i] = caretaker.crop(i, 3);
        for(int i=0;i<6;i++)
            caretakerAttackUp[i] = caretaker.crop(i,0);
        for(int i=0;i<6;i++)
            caretakerAttackDown[i] = caretaker.crop(i,2);
    }

    public static boolean qSecondElapsed()
    {
        if(!q_passed)
        {
            q_then= System.nanoTime();
            q_passed=true;
        }
        q_now=System.nanoTime();
        if(q_now-q_then>=1000000000/4)
        {
            q_passed=false;
            return true;
        }
        return false;
    }

    public static boolean PlayerAttackTimeElapsed()
    {
        if(!player_passed)
        {
            player_then= System.nanoTime();
            player_passed=true;
        }
        player_now=System.nanoTime();
        if(player_now-player_then>=1000000000/2.7)
        {
            player_passed=false;
            return true;
        }
        return false;
    }

    public static boolean EnemyAttackTimeElapsed()
    {
        if(!enemy_passed)
        {
            enemy_then= System.nanoTime();
            enemy_passed=true;
        }
        enemy_now=System.nanoTime();
        if(enemy_now-enemy_then>=1000000000/2.7)
        {
            enemy_passed=false;
            return true;
        }
        return false;
    }
}