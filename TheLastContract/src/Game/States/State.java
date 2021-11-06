package Game.States;

import java.awt.*;

import Game.RefLinks;

public abstract class State
{
    private static State previousState  = null;
    private static State currentState   = null;
    protected final RefLinks refLink;
    public State(RefLinks refLink)
    {
        this.refLink = refLink;
    }

    public static void SetState(State state)
    {
        previousState = currentState;
        currentState = state;
    }

    public static State GetState()
    {
        return currentState;
    }

    public static State GetPreviousState() { return previousState; }

    public abstract void Update();

    public abstract void Draw(Graphics g);
}