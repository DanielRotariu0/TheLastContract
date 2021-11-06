package Game.Tiles;

public class TileFactory {

    public Tile createTile(int id)
    {
        return switch (id) {
            case 0  -> new GrassTile(0);
            case 1  -> new RockTile(1);
            case 2  -> new WaterTile(2);
            case 3  -> new SoilTile(3);
            case 4  -> new BridgeLeftTile(4);
            case 5  -> new BridgeRightTile(5);
            case 6  -> new LavaTile(6);
            case 7  -> new SwampSoilTile(7);
            case 8  -> new SwampWaterTile(8);
            case 9  -> new SwampBridgeHorizontalTile(9);
            case 10 -> new SwampBridgeVerticalTile(10);
            default -> null;
        };
    }
}