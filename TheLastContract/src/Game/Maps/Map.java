package Game.Maps;

import Game.Entities.*;
import Game.Entities.Bushes.Bush1;
import Game.Entities.Bushes.Bush2;
import Game.Entities.Bushes.Lily;
import Game.Entities.Medallion.MedallionManager;
import Game.Entities.Other.*;
import Game.Entities.Trees.*;
import Game.Exceptions.EmptyWorldFileException;
import Game.Exceptions.UnknownTileException;
import Game.RefLinks;
import Game.Tiles.*;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Map
{
    private final RefLinks refLink;
    private int width;
    private int height;
    public int [][] tiles;
    private final EntityManager entityManager;
    private MedallionManager medallionManager;
    private boolean clear=true;

    public Map(RefLinks refLink, int mapNumber) {
        this.refLink = refLink;

        entityManager = new EntityManager(refLink, Player.getInstance(refLink, 0, 0));
        switch (mapNumber){
        case 0 -> {
            entityManager.addEntity(new Tree0(refLink, 17, 25));
            entityManager.addEntity(new Tree0(refLink, 32, 18));
            entityManager.addEntity(new Tree0(refLink, 6, 9));
            entityManager.addEntity(new Tree0(refLink, 9, 14));
            entityManager.addEntity(new Tree0(refLink, 26, 2));
            entityManager.addEntity(new Tree0(refLink, 13, 7));
            entityManager.addEntity(new Tree1(refLink, 11, 24));
            entityManager.addEntity(new Tree1(refLink, 21, 21));
            entityManager.addEntity(new Tree1(refLink, 25, 26));
            entityManager.addEntity(new Tree1(refLink, 26, 16));
            entityManager.addEntity(new Tree1(refLink, 29, 17));
            entityManager.addEntity(new Tree1(refLink, 1, 11));
            entityManager.addEntity(new Tree1(refLink, 7, 1));
            entityManager.addEntity(new Tree1(refLink, 32, 2));
            entityManager.addEntity(new Tree1(refLink, 9, -2));
            entityManager.addEntity(new Tree1(refLink, 14, -1));
            entityManager.addEntity(new Tree2(refLink, 33, 24));
            entityManager.addEntity(new Tree2(refLink, 30, 26));
            entityManager.addEntity(new Tree2(refLink, 36, 16));
            entityManager.addEntity(new Tree2(refLink, 21, 26));
            entityManager.addEntity(new Tree2(refLink, 15, 19));
            entityManager.addEntity(new Tree2(refLink, 12, 26));
            entityManager.addEntity(new Tree2(refLink, 8, 26));
            entityManager.addEntity(new Tree2(refLink, 13, 11));
            entityManager.addEntity(new Tree2(refLink, 1, 6));
            entityManager.addEntity(new Tree2(refLink, 17, 1));
            entityManager.addEntity(new Tree2(refLink, 21, -2));
            entityManager.addEntity(new Tree2(refLink, 25, -3));
            entityManager.addEntity(new Tree2(refLink, 31, 7));
            entityManager.addEntity(new Tree2(refLink, 36, 6));
            entityManager.addEntity(new Tree2(refLink, 21, 4));
            entityManager.addEntity(new Tree3(refLink, 25, 22));
            entityManager.addEntity(new Tree3(refLink, 36, 21));
            entityManager.addEntity(new Tree3(refLink, 17, 8));
            entityManager.addEntity(new Tree3(refLink, 25, 7));
            entityManager.addEntity(new Tree3(refLink, 28, -3));
            entityManager.addEntity(new Tree3(refLink, 35, -3));
            entityManager.addEntity(new Tree4(refLink, 19, 16));
            entityManager.addEntity(new Tree4(refLink, 2, 16));
            entityManager.addEntity(new Tree4(refLink, 35, 27));

            entityManager.addEntity(new Log(refLink, 11, 1));
            entityManager.addEntity(new Log(refLink, 30, 7));
            entityManager.addEntity(new Log(refLink, 7, 16));
            entityManager.addEntity(new Log(refLink, 31, 24));
            entityManager.addEntity(new Log(refLink, 28, 29));

            entityManager.addEntity(new Bush1(refLink, 20, 30));
            entityManager.addEntity(new Bush1(refLink, 16, 24));
            entityManager.addEntity(new Bush1(refLink, 26, 21));
            entityManager.addEntity(new Bush1(refLink, 38, 26));
            entityManager.addEntity(new Bush1(refLink, 1, 16));
            entityManager.addEntity(new Bush1(refLink, 1, 23));
            entityManager.addEntity(new Bush1(refLink, 8, 7));
            entityManager.addEntity(new Bush1(refLink, 16, 5));
            entityManager.addEntity(new Bush1(refLink, 20, 4));
            entityManager.addEntity(new Bush1(refLink, 36, 10));

            entityManager.addEntity(new Bush2(refLink, 35, 30));
            entityManager.addEntity(new Bush2(refLink, 24, 28));
            entityManager.addEntity(new Bush2(refLink, 35, 23));
            entityManager.addEntity(new Bush2(refLink, 31, 25));
            entityManager.addEntity(new Bush2(refLink, 7, 19));
            entityManager.addEntity(new Bush2(refLink, 4, 6));
            entityManager.addEntity(new Bush2(refLink, 11, 3));
            entityManager.addEntity(new Bush2(refLink, 17, 10));
            entityManager.addEntity(new Bush2(refLink, 38, 5));
            entityManager.addEntity(new Bush2(refLink, 34, 1));

            entityManager.addEntity(new FireOn(refLink, 17, 29));
        }
        case 1 -> {
            entityManager.addEntity(new Cave(refLink, 8, 4));

            entityManager.addEntity(new Tree5(refLink, 1, 1));
            entityManager.addEntity(new Tree5(refLink, 1, 8));
            entityManager.addEntity(new Tree5(refLink, 13, -1));
            entityManager.addEntity(new Tree5(refLink, 20, -2));
            entityManager.addEntity(new Tree5(refLink, 16, 4));
            entityManager.addEntity(new Tree5(refLink, 24, 4));
            entityManager.addEntity(new Tree5(refLink, 20, 7));
            entityManager.addEntity(new Tree5(refLink, 29, 7));
            entityManager.addEntity(new Tree5(refLink, 25, 1));
            entityManager.addEntity(new Tree5(refLink, 35, 9));
            entityManager.addEntity(new Tree5(refLink, 31, 13));
            entityManager.addEntity(new Tree5(refLink, 7, 17));
            entityManager.addEntity(new Tree5(refLink, 1, 23));
            entityManager.addEntity(new Tree5(refLink, 13, 18));
            entityManager.addEntity(new Tree5(refLink, 20, 22));
            entityManager.addEntity(new Tree5(refLink, 25, 20));
            entityManager.addEntity(new Tree5(refLink, 30, 22));
            entityManager.addEntity(new Tree5(refLink, 12, 24));

            entityManager.addEntity(new Log(refLink, 20, 6));
            entityManager.addEntity(new Log(refLink, 3, 7));
            entityManager.addEntity(new Log(refLink, 17, 12));
            entityManager.addEntity(new Log(refLink, 25, 10));
            entityManager.addEntity(new Log(refLink, 5, 16));
            entityManager.addEntity(new Log(refLink, 29, 18));
            entityManager.addEntity(new Log(refLink, 18, 28));

            entityManager.addEntity(new Boulder(refLink, 22, 17));
            entityManager.addEntity(new Boulder(refLink, 7, 1));
            entityManager.addEntity(new Boulder(refLink, 8, 1));
            entityManager.addEntity(new Boulder(refLink, 12, 1));
            entityManager.addEntity(new Boulder(refLink, 5, 4));
            entityManager.addEntity(new Boulder(refLink, 15, 9));
            entityManager.addEntity(new Boulder(refLink, 6, 12));
            entityManager.addEntity(new Boulder(refLink, 18, 15));
            entityManager.addEntity(new Boulder(refLink, 23, 14));
            entityManager.addEntity(new Boulder(refLink, 2, 19));
            entityManager.addEntity(new Boulder(refLink, 11, 19));
            entityManager.addEntity(new Boulder(refLink, 6, 25));
            entityManager.addEntity(new Boulder(refLink, 9, 28));

            entityManager.addEntity(new FireOn(refLink, 13, 15));
        }
        case 2 -> {
            entityManager.addEntity(new Tree6(refLink, 2, 0));
            entityManager.addEntity(new Tree6(refLink, 9, 0));
            entityManager.addEntity(new Tree6(refLink, 20, -1));
            entityManager.addEntity(new Tree6(refLink, 37, 0));
            entityManager.addEntity(new Tree6(refLink, 14, 4));
            entityManager.addEntity(new Tree6(refLink, 24, 4));
            entityManager.addEntity(new Tree6(refLink, 6, 7));
            entityManager.addEntity(new Tree6(refLink, 16, 9));
            entityManager.addEntity(new Tree6(refLink, 35, 8));
            entityManager.addEntity(new Tree6(refLink, 31, 12));
            entityManager.addEntity(new Tree6(refLink, 22, 13));
            entityManager.addEntity(new Tree6(refLink, 1, 17));
            entityManager.addEntity(new Tree6(refLink, 1, 27));
            entityManager.addEntity(new Tree6(refLink, 31, 21));
            entityManager.addEntity(new Tree6(refLink, 30, 27));

            entityManager.addEntity(new SwampLog(refLink, 6, 3));
            entityManager.addEntity(new SwampLog(refLink, 14, 3));
            entityManager.addEntity(new SwampLog(refLink, 35, 4));
            entityManager.addEntity(new SwampLog(refLink, 21, 6));
            entityManager.addEntity(new SwampLog(refLink, 1, 7));
            entityManager.addEntity(new SwampLog(refLink, 13, 13));
            entityManager.addEntity(new SwampLog(refLink, 28, 18));
            entityManager.addEntity(new SwampLog(refLink, 6, 21));
            entityManager.addEntity(new SwampLog(refLink, 6, 30));
            entityManager.addEntity(new SwampLog(refLink, 27, 31));
            entityManager.addEntity(new SwampLog(refLink, 18, 25));
            entityManager.addEntity(new SwampLog(refLink, 10, 26));
            entityManager.addEntity(new SwampLog(refLink, 12, 20));

            entityManager.addEntity(new Lily(refLink, 27, 3));
            entityManager.addEntity(new Lily(refLink, 18, 4));
            entityManager.addEntity(new Lily(refLink, 6, 2));
            entityManager.addEntity(new Lily(refLink, 37, 7));
            entityManager.addEntity(new Lily(refLink, 28, 16));
            entityManager.addEntity(new Lily(refLink, 37, 23));
            entityManager.addEntity(new Lily(refLink, 36, 27));
            entityManager.addEntity(new Lily(refLink, 4, 26));
            entityManager.addEntity(new Lily(refLink, 6, 23));
            entityManager.addEntity(new Lily(refLink, 6, 17));
            entityManager.addEntity(new Lily(refLink, 1, 13));
            entityManager.addEntity(new Lily(refLink, 33, 19));

            entityManager.addEntity(new House(refLink, 24, 22));

            entityManager.addEntity(new Wood(refLink, 28, 28));
            entityManager.addEntity(new Wood(refLink, 29, 27));

            entityManager.addEntity(new FireOn(refLink, 24, 27));
        }
        case 3 -> {
            entityManager.addEntity(new Tree7(refLink, 0, 1));
            entityManager.addEntity(new Tree7(refLink, 11, 2));
            entityManager.addEntity(new Tree7(refLink, 17, -1));
            entityManager.addEntity(new Tree7(refLink, 22, -1));
            entityManager.addEntity(new Tree7(refLink, 29, -2));
            entityManager.addEntity(new Tree7(refLink, 35, 0));
            entityManager.addEntity(new Tree7(refLink, 37, 6));
            entityManager.addEntity(new Tree7(refLink, 37, 15));
            entityManager.addEntity(new Tree7(refLink, 5, 14));
            entityManager.addEntity(new Tree7(refLink, 1, 17));
            entityManager.addEntity(new Tree7(refLink, 6, 21));
            entityManager.addEntity(new Tree7(refLink, 6, 26));
            entityManager.addEntity(new Tree7(refLink, 2, 25));

            entityManager.addEntity(new Tombstone(refLink, 17, 8));
            entityManager.addEntity(new Tombstone(refLink, 20, 7));
            entityManager.addEntity(new Tombstone(refLink, 26, 7));
            entityManager.addEntity(new Tombstone(refLink, 23, 10));
            entityManager.addEntity(new Tombstone(refLink, 27, 13));
            entityManager.addEntity(new Tombstone(refLink, 14, 16));
            entityManager.addEntity(new Tombstone(refLink, 20, 16));
            entityManager.addEntity(new Tombstone(refLink, 23, 28));
            entityManager.addEntity(new Tombstone(refLink, 29, 26));
            entityManager.addEntity(new Tombstone(refLink, 16, 20));
            entityManager.addEntity(new Tombstone(refLink, 23, 22));
            entityManager.addEntity(new Cross(refLink, 30, 8));
            entityManager.addEntity(new Cross(refLink, 17, 11));
            entityManager.addEntity(new Cross(refLink, 31, 13));
            entityManager.addEntity(new Cross(refLink, 13, 20));
            entityManager.addEntity(new Cross(refLink, 19, 26));
            entityManager.addEntity(new Cross(refLink, 33, 22));
            entityManager.addEntity(new Cross(refLink, 35, 27));
            entityManager.addEntity(new Cross(refLink, 22, 12));
            entityManager.addEntity(new Cross(refLink, 25, 16));
            entityManager.addEntity(new Cross(refLink, 30, 19));
            entityManager.addEntity(new Cross(refLink, 26, 23));

            entityManager.addEntity(new FireOn(refLink, 24, 18));
        }
    }
        LoadWorld(mapNumber);
    }

    public  void Update()
    {
        entityManager.Update();
        if(!clear) {
            medallionManager.Update();
        }
    }

    public void Draw(Graphics g)
    {
        for(int y = 0; y < refLink.GetGame().GetHeight()/Tile.TILE_HEIGHT; y++)
        {
            for(int x = 0; x < refLink.GetGame().GetWidth()/Tile.TILE_WIDTH; x++)
            {
                GetTile(x, y).Draw(g, x * Tile.TILE_HEIGHT, y * Tile.TILE_WIDTH);
            }
        }
        if(!clear) {
            medallionManager.Draw(g);
        }
        entityManager.Draw(g);
    }

    public Tile GetTile(int x, int y)
    {
        if(x < 0 || y < 0 || x >= width || y >= height)
        {
            return Tile.grassTile;
        }
        Tile t = Tile.tiles[tiles[x][y]];
        if(t == null)
        {
            return Tile.rockTile;
        }
        return t;
    }

    public void LoadWorld(int mapNumber)
    {
        switch (mapNumber) {
            case 0  -> LoadWorldFromFile("res/Worlds/PlayStateMap.txt");
            case 1  -> LoadWorldFromFile("res/Worlds/Level1StateMap.txt");
            case 2  -> LoadWorldFromFile("res/Worlds/Level2StateMap.txt");
            case 3  -> LoadWorldFromFile("res/Worlds/Level3StateMap.txt");
        }
    }

    public void LoadWorldFromFile(String file)
    {
        width = 40;
        height = 32;
        tiles = new int[width][height];
        String content = null;
        Path filename;
        String[] tokens;
        int tileType;

        filename=Path.of(file);
        try {
            content = Files.readString(filename);
        }
        catch (IOException e){
            System.err.println("Error: Map: No such file.");
        }
        try{
            assert content != null;
            if(content.equals("")){
                for(int y = 0; y < height; y++)
                    for (int x = 0; x < width; x++)
                        tiles[x][y]  =DefaultMap(y,x);

                throw new EmptyWorldFileException();
            }
            else{
                tokens=content.split("\\s+");
                for(int y = 0; y < height; y++) {
                    for (int x = 0; x < width; x++) {
                        tileType = Integer.parseInt(tokens[x+y*width]);
                        try {
                            if (tileType < 0 || tileType > 10) {
                                tiles[x][y] = 0;
                                throw new UnknownTileException();
                            }
                            else
                                tiles[x][y] = tileType;
                        }
                        catch (UnknownTileException e)
                        {
                            System.err.println(e.getMessage());
                        }
                    }
                }
            }
        }
        catch (EmptyWorldFileException e)
        {
            System.err.println(e.getMessage());
        }
    }

    public int DefaultMap(int y, int x)
    {
        final int[][] map = {
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
        };

        return map[y][x];
    }
    public EntityManager getEntityManager() {
        return entityManager;
    }
    public MedallionManager getMedallionManager() {
        return medallionManager;
    }
    public void setMedallionManager() {
        medallionManager = new MedallionManager(refLink);
    }
    public void setClear(boolean value){
        clear=value;
    }
}