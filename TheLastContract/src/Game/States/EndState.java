package Game.States;

import Game.Graphics.Assets;
import Game.Entities.Player;
import Game.RefLinks;

import java.awt.*;
import java.sql.SQLException;

public class EndState extends State
{
    protected static int highestScore;
    public EndState(RefLinks refLink){
        super(refLink);
        try {
            Assets.EndStateMusic.setVolume(refLink.GetDatabase().getGameVolume()/200.);
        }
        catch (SQLException e)
        {
            System.err.println("Error: EndState: GameVolume.");
        }
    }

    @Override
    public void Update() {
        if(refLink.GetMouseManager().getMouseY()>=800 && refLink.GetMouseManager().getMouseY()<=875)
        {
            if(refLink.GetMouseManager().getMouseX()>=572 && refLink.GetMouseManager().getMouseX()<=830)
            {
                if(refLink.GetMouseManager().leftClickPressed()) {
                    System.exit(0);
                }
            }
        }
    }

    @Override
    public void Draw(Graphics g) {
        g.setColor(Color.white);
        g.setFont(new Font("Dosis", Font.PLAIN, 64));
        g.drawImage(Assets.endStateBackground,0,0,refLink.GetWidth(),refLink.GetHeight(),null);
        g.drawString(Integer.toString(Player.getInstance(refLink,0,0).GetScore()),660,550);
        g.drawString(Integer.toString(highestScore),660,640);
    }
}