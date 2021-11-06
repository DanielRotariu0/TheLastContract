package Game.States;

import Game.Graphics.Assets;
import Game.RefLinks;

import java.awt.*;
import java.sql.SQLException;

public class PauseState extends PlayState {

    public PauseState(RefLinks refLink) {
        super(refLink);
    }

    @Override
    public void Update(){
        if(refLink.GetMouseManager().getMouseY()>=556 && refLink.GetMouseManager().getMouseY()<=625)
            if (refLink.GetMouseManager().getMouseX() >= 590 && refLink.GetMouseManager().getMouseX() <= 712) {
                if (refLink.GetMouseManager().leftClickPressed()) {
                    Level3State.level3.LoadWorld(3);
                    Level2State.level2.LoadWorld(2);
                    Level1State.level1.LoadWorld(1);
                    resetLevels();
                    retry();
                    Assets.PlayStateMusic.stop();
                    Assets.Level1StateMusic.stop();
                    Assets.Level2StateMusic.stop();
                    Assets.Level3StateMusic.stop();
                    Assets.MenuMusic.play();
                    State.SetState(refLink.GetGame().getMenuState());
                    try {
                        Thread.sleep(500);
                    }
                    catch (InterruptedException e)
                    {
                        System.err.println("Error: PauseState: Thread Sleep");
                    }
                }
            }
        if(refLink.GetMouseManager().getMouseY()>=302 && refLink.GetMouseManager().getMouseY()<=376)
            if (refLink.GetMouseManager().getMouseX() >= 506 && refLink.GetMouseManager().getMouseX() <= 790) {
                if (refLink.GetMouseManager().leftClickPressed()) {
                    State.SetState(State.GetPreviousState());
                    try {
                        Thread.sleep(500);
                    }
                    catch (InterruptedException e)
                    {
                        System.err.println("Error: PauseState: Thread Sleep");
                    }
                }
            }

        if (refLink.GetMouseManager().getMouseX() >= 565 && refLink.GetMouseManager().getMouseX() <= 730)
            if (refLink.GetMouseManager().getMouseY() >= 436 && refLink.GetMouseManager().getMouseY() <= 496)
                if (refLink.GetMouseManager().leftClickPressed()){
                    try {
                        Thread.sleep(500);
                        if(canSave)
                            Save();
                    }
                    catch (InterruptedException e)
                    {
                        System.err.println("Error: PauseState: Thread Sleep");
                    }
                }
    }

    @Override
    public void Draw(Graphics g){
        g.drawImage(Assets.pause,0,0,refLink.GetWidth(),refLink.GetHeight(),null);
        if(!canSave)
        {
            g.setColor(Color.WHITE);
            g.setFont(new Font("Dosis", Font.PLAIN, 48));
            g.drawString("You can't save while in a fight.",354,716);
        }


        if(refLink.GetMouseManager().getMouseX()>=590 && refLink.GetMouseManager().getMouseX()<=712)
            if(refLink.GetMouseManager().getMouseY()>=556 && refLink.GetMouseManager().getMouseY()<=625)
            {
                g.drawImage(Assets.hoverSword,720,546,381,100,null);
            }

        if(refLink.GetMouseManager().getMouseX()>=565 && refLink.GetMouseManager().getMouseX()<=730)
            if(refLink.GetMouseManager().getMouseY()>=436 && refLink.GetMouseManager().getMouseY()<=496)
            {
                g.drawImage(Assets.hoverSword,740,430,381,100,null);
            }

        if(refLink.GetMouseManager().getMouseX()>=506 && refLink.GetMouseManager().getMouseX()<=790)
            if(refLink.GetMouseManager().getMouseY()>=302 && refLink.GetMouseManager().getMouseY()<=376)
            {
                g.drawImage(Assets.hoverSword,800,306,381,100,null);
            }

    }

    protected void Save() {
        int lvl_progress=0;
        int current_lvl=0;

        if(Level1State.wasBossDefeated)
            lvl_progress=1;
        if(Level2State.wasBossDefeated)
            lvl_progress=2;
        if(Level3State.wasBossDefeated && Level3State.wasBoss2Defeated)
            lvl_progress=3;

        if (State.GetPreviousState() == refLink.GetGame().getLevel1State())
            current_lvl=1;
        if (State.GetPreviousState() == refLink.GetGame().getLevel2State())
            current_lvl=2;
        if (State.GetPreviousState() == refLink.GetGame().getLevel3State())
            current_lvl=3;

        try {
            refLink.GetDatabase().updateSave(player.GetX(), player.GetY(), player.GetActualLife(), player.GetScore(), current_lvl, lvl_progress);
        }
        catch(SQLException e){
            System.err.print("Error: PauseState: Save");
        }
    }
}