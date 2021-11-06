package Game.GameWindow;

import javax.swing.*;
import java.awt.*;

public class GameWindow
{
    private JFrame  WindowFrame;
    private final String  WindowTitle;
    private final int     WindowWidth;
    private final int     WindowHeight;

    private Canvas  canvas;

    public GameWindow(String title, int width, int height){
        WindowTitle    = title;
        WindowWidth    = width;
        WindowHeight   = height;
        WindowFrame    = null;
    }


    public void BuildGameWindow()
    {
        if(WindowFrame != null)
        {
            return;
        }

        WindowFrame = new JFrame(WindowTitle);
        WindowFrame.setSize(WindowWidth, WindowHeight);
        WindowFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        WindowFrame.setResizable(false);
        WindowFrame.setLocationRelativeTo(null);
        WindowFrame.setVisible(true);
        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(WindowWidth, WindowHeight));
        canvas.setMaximumSize(new Dimension(WindowWidth, WindowHeight));
        canvas.setMinimumSize(new Dimension(WindowWidth, WindowHeight));
        WindowFrame.add(canvas);
        WindowFrame.pack();
    }

    public int GetWindowWidth()
    {
        return WindowWidth;
    }

    public int GetWindowHeight()
    {
        return WindowHeight;
    }

    public Canvas GetCanvas()
    {
        return canvas;
    }

    public JFrame GetWindowFrame()
    {
        return WindowFrame;
    }
}
