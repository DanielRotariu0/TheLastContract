package Game;

import java.sql.*;

public class DBHandler {
    Connection c;
    Statement stmt;
    ResultSet rs;

    public DBHandler()
    {
        try
        {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:database.db");
            stmt = c.createStatement();
        }
        catch (Exception e)
        {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    public void updateSave(float playerX, float playerY, int playerLife, int playerScore, int current_lvl, int lvl_progress) throws SQLException {
        String instruction="UPDATE SAVES set PLAYER_X ="+playerX+" WHERE ID=1;";
        stmt.executeUpdate(instruction);
        instruction="UPDATE SAVES set PLAYER_Y ="+playerY+" WHERE ID=1;";
        stmt.executeUpdate(instruction);
        instruction="UPDATE SAVES set PLAYER_LIFE ="+playerLife+" WHERE ID=1;";
        stmt.executeUpdate(instruction);
        instruction="UPDATE SAVES set PLAYER_SCORE ="+playerScore+" WHERE ID=1;";
        stmt.executeUpdate(instruction);
        instruction="UPDATE SAVES set CURRENT_LEVEL ="+current_lvl+" WHERE ID=1;";
        stmt.executeUpdate(instruction);
        instruction="UPDATE SAVES set LEVEL_PROGRESS ="+lvl_progress+" WHERE ID=1;";
        stmt.executeUpdate(instruction);
    }

    public float getPlayerX() throws SQLException {
        rs=stmt.executeQuery("SELECT * FROM SAVES;");
        return rs.getInt("PLAYER_X");
    }

    public float getPlayerY() throws SQLException {
        rs=stmt.executeQuery("SELECT * FROM SAVES;");
        return rs.getInt("PLAYER_Y");
    }

    public int getPlayerLife() throws SQLException {
        rs=stmt.executeQuery("SELECT * FROM SAVES;");
        return rs.getInt("PLAYER_LIFE");
    }

    public int getPlayerScore() throws SQLException {
        rs=stmt.executeQuery("SELECT * FROM SAVES;");
        return rs.getInt("PLAYER_SCORE");
    }

    public int getCurrentLevel() throws SQLException {
        rs=stmt.executeQuery("SELECT * FROM SAVES;");
        return rs.getInt("CURRENT_LEVEL");
    }

    public int getLevelProgress() throws SQLException {
        rs=stmt.executeQuery("SELECT * FROM SAVES;");
        return rs.getInt("LEVEL_PROGRESS");
    }

    public int getHighestScore() throws SQLException {
        rs=stmt.executeQuery("SELECT * FROM SETTINGS;");
        return rs.getInt("HIGHEST_SCORE");
    }

    public void updateHighestScore(int score) throws SQLException {
        int highestScore=getHighestScore();
        if(score>highestScore) {
            String instruction = "UPDATE SETTINGS set HIGHEST_SCORE =" + score + " WHERE ID=1;";
            stmt.executeUpdate(instruction);
        }
    }

    public void updateDifficulty(int difficulty) throws SQLException {
        String instruction="UPDATE SETTINGS set DIFFICULTY ="+difficulty+" WHERE ID=1;";
        stmt.executeUpdate(instruction);
    }

    public void updateMenuMusicVolume(int volume) throws  SQLException {
        String instruction="UPDATE SETTINGS set MENU_MUSIC_VOLUME ="+volume+" WHERE ID=1;";
        stmt.executeUpdate(instruction);
    }

    public void updateGameMusicVolume(int volume) throws  SQLException {
        String instruction="UPDATE SETTINGS set GAME_MUSIC_VOLUME ="+volume+" WHERE ID=1;";
        stmt.executeUpdate(instruction);
    }

    public void updateAutoRetry(int auto_retry) throws  SQLException {
        String instruction="UPDATE SETTINGS set AUTO_RETRY ="+auto_retry+" WHERE ID=1;";
        stmt.executeUpdate(instruction);
    }

    public int getDifficulty() throws SQLException {
        rs=stmt.executeQuery("SELECT * FROM SETTINGS;");
        return rs.getInt("DIFFICULTY");
    }

    public int getMenuVolume() throws SQLException {
        rs=stmt.executeQuery("SELECT * FROM SETTINGS;");
        return rs.getInt("MENU_MUSIC_VOLUME");
    }

    public int getGameVolume() throws SQLException {
        rs=stmt.executeQuery("SELECT * FROM SETTINGS;");
        return rs.getInt("GAME_MUSIC_VOLUME");
    }

    public int getAutoRetry() throws SQLException {
        rs=stmt.executeQuery("SELECT * FROM SETTINGS;");
        return rs.getInt("AUTO_RETRY");
    }
}