package Game.States;

import Game.Graphics.Assets;
import Game.RefLinks;

import java.awt.*;

public class InfoState extends State
{
    public InfoState(RefLinks refLink)
    {
        super(refLink);
    }

    @Override
    public void Update(){

        if(refLink.GetMouseManager().getMouseY()>=855 && refLink.GetMouseManager().getMouseY()<=910)
        {
            if(refLink.GetMouseManager().getMouseX()>=585 && refLink.GetMouseManager().getMouseX()<=784)
            {
                if(refLink.GetMouseManager().leftClickPressed()) {
                    State.SetState(refLink.GetGame().getMenuState());
                    try {
                        Thread.sleep(500);
                    }
                    catch (InterruptedException e)
                    {
                        System.err.println("Error: InfoState: Thread Sleep");
                    }
                }
            }
        }
    }

    @Override
    public void Draw(Graphics g)
    {
        g.drawImage(Assets.infoBackground,0,0,refLink.GetWidth(),refLink.GetHeight(),null);
    }
}