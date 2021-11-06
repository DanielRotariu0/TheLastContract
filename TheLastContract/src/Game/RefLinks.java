package Game;

import Game.Input.MouseManager;
import Game.Maps.Map;

import Game.Input.KeyManager;

public class RefLinks
{
    private final Game game;
    private Map map;

    public RefLinks(Game game)
    {
        this.game = game;
    }

    public KeyManager GetKeyManager()
    {
        return game.GetKeyManager();
    }

    public MouseManager GetMouseManager(){ return game.GetMouseManager();}

    public int GetWidth()
    {
        return game.GetWidth();
    }

    public int GetHeight()
    {
        return game.GetHeight();
    }

    public Game GetGame()
    {
        return game;
    }

    public Map GetMap()
    {
        return map;
    }

    public void SetMap(Map map)
    {
        this.map = map;
    }

    public DBHandler GetDatabase() { return game.getDatabase(); }
}