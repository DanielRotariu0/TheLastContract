package Game.States;

import Game.Graphics.Assets;
import Game.RefLinks;

import java.awt.*;
import java.sql.SQLException;

public class SettingsState extends State
{
    public SettingsState(RefLinks refLink){
        super(refLink);
    }

    @Override
    public void Update(){
        if(refLink.GetMouseManager().getMouseY()>=754 && refLink.GetMouseManager().getMouseY()<=882)
        {
            if(refLink.GetMouseManager().getMouseX()>=560 && refLink.GetMouseManager().getMouseX()<=726)
            {
                if(refLink.GetMouseManager().leftClickPressed()) {
                    State.SetState(refLink.GetGame().getMenuState());
                }
            }
        }

        if(refLink.GetMouseManager().getMouseX()>=910 && refLink.GetMouseManager().getMouseX()<=1154) {
            try {
                if (refLink.GetMouseManager().getMouseY() >= 386 && refLink.GetMouseManager().getMouseY() <= 424) {
                    if (refLink.GetMouseManager().leftClickPressed()) {
                        if (refLink.GetDatabase().getDifficulty() == 4) {
                            refLink.GetDatabase().updateDifficulty(1);
                        } else {
                            refLink.GetDatabase().updateDifficulty(refLink.GetDatabase().getDifficulty() + 1);
                        }
                        Thread.sleep(100);
                    }
                }
                if (refLink.GetMouseManager().getMouseY() >= 482 && refLink.GetMouseManager().getMouseY() <= 516) {
                    if (refLink.GetMouseManager().leftClickPressed()) {
                        if (refLink.GetDatabase().getMenuVolume() == 100) {
                            refLink.GetDatabase().updateMenuMusicVolume(0);
                        } else {
                            refLink.GetDatabase().updateMenuMusicVolume(refLink.GetDatabase().getMenuVolume() + 10);
                        }
                        Assets.MenuMusic.setVolume(refLink.GetDatabase().getMenuVolume()/200.);
                        Thread.sleep(100);
                    }
                }
                if (refLink.GetMouseManager().getMouseY() >= 570 && refLink.GetMouseManager().getMouseY() <= 608) {
                    if (refLink.GetMouseManager().leftClickPressed()) {
                        if (refLink.GetDatabase().getGameVolume() == 100) {
                            refLink.GetDatabase().updateGameMusicVolume(0);
                        } else {
                            refLink.GetDatabase().updateGameMusicVolume(refLink.GetDatabase().getGameVolume() + 10);
                        }
                        Assets.PlayStateMusic.setVolume(refLink.GetDatabase().getGameVolume()/200.);
                        Thread.sleep(100);
                    }
                }
                if (refLink.GetMouseManager().getMouseY() >= 662 && refLink.GetMouseManager().getMouseY() <= 700) {
                    if (refLink.GetMouseManager().leftClickPressed()) {
                        if (refLink.GetDatabase().getAutoRetry() == 1) {
                            refLink.GetDatabase().updateAutoRetry(0);
                        } else {
                            refLink.GetDatabase().updateAutoRetry(1);
                        }
                        Thread.sleep(100);
                    }
                }
            }
            catch(SQLException e)
            {
                System.err.println("Error: SettingsState: Update Database.");
            }
            catch(InterruptedException e)
            {
                System.err.println("Error: SettingsState: Thread Sleep.");
            }
        }
    }

    @Override
    public void Draw(Graphics g) {
        g.drawImage(Assets.settingsBackground,0,0,refLink.GetWidth(),refLink.GetHeight(),null);
        g.setColor(Color.lightGray);
        g.setFont(new Font("Dosis", Font.PLAIN, 48));
        try {
            switch (refLink.GetDatabase().getDifficulty()) {
                case 1 -> g.drawString("Easy", 520, 422);
                case 2 -> g.drawString("Medium", 520, 422);
                case 3 -> g.drawString("Hard", 520, 422);
                case 4 -> g.drawString("Impossible", 520, 422);
            }
            g.drawString(Integer.toString(refLink.GetDatabase().getMenuVolume()), 674, 514);
            g.drawString(Integer.toString(refLink.GetDatabase().getGameVolume()), 684, 606);
            if (refLink.GetDatabase().getAutoRetry() == 0)
                g.drawString("No", 516, 696);
            else {
                g.drawString("Yes", 516, 696);
            }
        }
        catch (SQLException e)
        {
            System.err.println("Error: SettingsState: Load from Database.");
        }
    }
}