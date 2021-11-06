package Game.States;

import Game.Graphics.Assets;
import Game.RefLinks;

import java.awt.*;
import java.sql.SQLException;

public class MenuState extends State
{
    protected static boolean load=false;

    public MenuState(RefLinks refLink) {
        super(refLink);
        try {
            Assets.MenuMusic.setVolume(refLink.GetDatabase().getMenuVolume()/200.);
        }
        catch (SQLException e)
        {
            System.err.println("Error: MenuState music");
        }
        Assets.MenuMusic.play();

    }

    @Override
    public void Update(){


        if(refLink.GetMouseManager().getMouseX()>=140 && refLink.GetMouseManager().getMouseX()<=582)
        {
            if(refLink.GetMouseManager().getMouseY()>=492 && refLink.GetMouseManager().getMouseY()<=574)
            {
                if(refLink.GetMouseManager().leftClickPressed())
                {
                    Assets.MenuMusic.stop();
                    Assets.PlayStateMusic.play();
                    refLink.SetMap(PlayState.map);
                    State.SetState(refLink.GetGame().getPlayState());
                    try {
                        Thread.sleep(500);
                    }
                    catch (InterruptedException e)
                    {
                        System.err.println("Error: MenuState: Thread Sleep");
                    }
                }
            }

            if(refLink.GetMouseManager().getMouseY()>=577 && refLink.GetMouseManager().getMouseY()<=692)
            {
                if(refLink.GetMouseManager().leftClickPressed())
                {
                    Assets.MenuMusic.stop();
                    Load();
                    try {
                        Thread.sleep(500);
                    }
                    catch (InterruptedException e)
                    {
                        System.err.println("Error: MenuState: Thread Sleep");
                    }
                }
            }


            if(refLink.GetMouseManager().getMouseY()>=709 && refLink.GetMouseManager().getMouseY()<=823)
            {
                if(refLink.GetMouseManager().leftClickPressed())
                {
                    State.SetState(refLink.GetGame().getSettingsState());
                }

            }

            if(refLink.GetMouseManager().getMouseY()>=842 && refLink.GetMouseManager().getMouseY()<=890)
            {
                if(refLink.GetMouseManager().leftClickPressed())
                {
                    System.exit(0);
                }
            }
        }

        else if(refLink.GetMouseManager().getMouseX()>=1100 && refLink.GetMouseManager().getMouseX()<=1230)
        {
            if(refLink.GetMouseManager().getMouseY()>=804 && refLink.GetMouseManager().getMouseY()<=880)
            {
                if(refLink.GetMouseManager().leftClickPressed())
                {
                    State.SetState(refLink.GetGame().getInfoState());
                }
            }
        }
    }

    @Override
    public void Draw(Graphics g)
    {
        g.drawImage(Assets.background,0,0,refLink.GetWidth(),refLink.GetHeight(),null);
        if(refLink.GetMouseManager().getMouseX()>=140 && refLink.GetMouseManager().getMouseX()<=582)
        {
            if(refLink.GetMouseManager().getMouseY()>=492 && refLink.GetMouseManager().getMouseY()<=574)
            {
                g.drawImage(Assets.hoverSword,596,500,381,100,null);
            }
            if(refLink.GetMouseManager().getMouseY()>=576 && refLink.GetMouseManager().getMouseY()<=693)
            {
                g.drawImage(Assets.hoverSword,336,600,381,100,null);
            }

            if(refLink.GetMouseManager().getMouseY()>=709 && refLink.GetMouseManager().getMouseY()<=823)
            {
                g.drawImage(Assets.hoverSword,466,709,381,100,null);
            }

            if(refLink.GetMouseManager().getMouseY()>=842 && refLink.GetMouseManager().getMouseY()<=890)
            {
                g.drawImage(Assets.hoverSword,300,812,381,100,null);
            }
        }
    }

    public void Load()
    {
        load=true;
        try {
            int current_lvl  = refLink.GetDatabase().getCurrentLevel();
            int lvl_progress = refLink.GetDatabase().getLevelProgress();

            switch (lvl_progress)
            {
                case 1 -> {
                    Level1State.wasBossDefeated=true;
                }
                case 2 -> {
                    Level1State.wasBossDefeated=true;
                    Level2State.wasBossDefeated=true;
                }
                case 3 -> {
                    Level1State.wasBossDefeated=true;
                    Level2State.wasBossDefeated=true;
                    Level3State.wasBossDefeated=true;
                    Level3State.wasBoss2Defeated=true;
                }
            }
            switch (current_lvl){
                case 0 -> {
                    refLink.SetMap(PlayState.map);
                    State.SetState(refLink.GetGame().getPlayState());
                    Assets.PlayStateMusic.play();
                }
                case 1 -> {
                    refLink.SetMap(Level1State.level1);
                    State.SetState(refLink.GetGame().getLevel1State());
                    Assets.PlayStateMusic.play();
                }
                case 2 -> {
                    refLink.SetMap(Level2State.level2);
                    State.SetState(refLink.GetGame().getLevel2State());
                    if(Level2State.wasBossDefeated)
                        Assets.PlayStateMusic.play();
                    else
                        Assets.Level2StateMusic.play();
                }
                case 3 -> {
                    refLink.SetMap(Level3State.level3);
                    State.SetState(refLink.GetGame().getLevel3State());
                    Assets.PlayStateMusic.play();
                }
            }

        }
        catch (SQLException e)
        {
            System.err.print("Error: MenuState: Load");
        }
    }

    public static void setLoad(boolean flag)
    {
        load=flag;
    }
    public static boolean getLoad()
    {
        return load;
    }
}