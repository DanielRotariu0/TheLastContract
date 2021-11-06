package Game;

import Game.GameWindow.GameWindow;
import Game.Graphics.Assets;
import Game.Input.KeyManager;
import Game.Input.MouseManager;
import Game.States.*;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game implements Runnable
{
    private final GameWindow      Window;
    private boolean         runState;
    private Thread          gameThread;
    private BufferStrategy  bs;
    private final DBHandler database;

    private Graphics        g;

    private State playState;
    private State menuState;
    private State settingsState;
    private State infoState;
    private State pauseState;
    private State level1State;
    private State level2State;
    private State level3State;
    private State endState;

    private final KeyManager keyManager;
    private final MouseManager mouseManager;
    private RefLinks refLink;

    public Game(String title, int width, int height) {
        Window = new GameWindow(title, width, height);
        runState = false;
        keyManager = new KeyManager();
        mouseManager= new MouseManager();
        database = new DBHandler();
    }

    private void InitGame(){
        Window.BuildGameWindow();
        Window.GetWindowFrame().addKeyListener(keyManager);
        Window.GetWindowFrame().addMouseListener(mouseManager);
        Window.GetWindowFrame().addMouseMotionListener(mouseManager);
        Window.GetCanvas().addMouseListener(mouseManager);
        Window.GetCanvas().addMouseMotionListener(mouseManager);

        Assets.Init();
        refLink         = new RefLinks(this);
        playState       = new PlayState(refLink);
        menuState       = new MenuState(refLink);
        settingsState   = new SettingsState(refLink);
        infoState       = new InfoState(refLink);
        pauseState      = new PauseState(refLink);
        level1State     = new Level1State(refLink);
        level2State     = new Level2State(refLink);
        level3State     = new Level3State(refLink);
        endState        = new EndState(refLink);
        State.SetState(menuState);
    }

    public void run()
    {
        InitGame();

        long oldTime = System.nanoTime();
        long curentTime;

        final int framesPerSecond   = 60;
        final double timeFrame      = 1000000000f / framesPerSecond;

        while (runState)
        {
            curentTime = System.nanoTime();
            if((curentTime - oldTime) > timeFrame)
            {
                Update();
                Draw();
                oldTime = curentTime;
            }
        }
    }

    public synchronized void StartGame()
    {
        if(!runState)
        {
            runState = true;
            gameThread = new Thread(this);
            gameThread.start();
        }
    }

    private void Update()
    {
        keyManager.Update();
        if(State.GetState() != null)
        {
            State.GetState().Update();
            Window.GetWindowFrame().requestFocusInWindow();
        }
    }

    private void Draw() {
        bs = Window.GetCanvas().getBufferStrategy();
        if(bs == null)
        {
            try
            {
                Window.GetCanvas().createBufferStrategy(3);
                return;
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        g = bs.getDrawGraphics();
        g.clearRect(0, 0, Window.GetWindowWidth(), Window.GetWindowHeight());

            if(State.GetState() != null)
            {
                State.GetState().Draw(g);
            }
        bs.show();
        g.dispose();
    }

    public int GetWidth()
    {
        return Window.GetWindowWidth();
    }

    public int GetHeight()
    {
        return Window.GetWindowHeight();
    }

    public KeyManager GetKeyManager()
    {
        return keyManager;
    }

    public MouseManager GetMouseManager(){ return mouseManager;}

    public State getPlayState()
    {
        return playState;
    }

    public State getMenuState()
    {
        return menuState;
    }

    public State getInfoState() { return infoState; }

    public State getSettingsState() { return settingsState; }

    public State getPauseState() { return pauseState; }

    public State getLevel1State() { return level1State; }

    public State getLevel2State() { return level2State; }

    public State getLevel3State() { return level3State; }

    public State getEndState() { return endState; }

    public DBHandler getDatabase() { return database; }
}