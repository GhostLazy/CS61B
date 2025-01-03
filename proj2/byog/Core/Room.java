package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Room {
    private static final Random random = new Random();
    private static int count = 0;
    public static List<int[]> roomInfos = new ArrayList<>();

    private static boolean isValidPosition(TETile[][] world, int xPos, int yPos, int width, int height) {
        return xPos > 0 && yPos > 0 && xPos + width < world.length - 1 && yPos + height < world[0].length - 1;
    }

    private static boolean isValidRoom(TETile[][] world, int xPos, int yPos, int width, int height) {
        if (!isValidPosition(world, xPos, yPos, width, height)) {
            return false;
        }
        for (int[] roomInfo : roomInfos) {
            if (xPos + width >= roomInfo[0] && yPos + height >= roomInfo[1]
                    && xPos <= roomInfo[0] + roomInfo[2] && yPos <= roomInfo[1] + roomInfo[3]) {
                return false;
            }
        }
        return true;
    }

    private static void addOneRoom(TETile[][] world) {
        int xPos = random.nextInt(world.length);
        int yPos = random.nextInt(world[0].length);
        int width = random.nextInt(5) + 5;
        int height = random.nextInt(3) + 5;
        if (!isValidRoom(world, xPos, yPos, width, height)) {
            return;
        }
        for (int i = xPos; i < xPos + width; i++) {
            for (int j = yPos; j < yPos + height; j++) {
                if (i == xPos || i == xPos + width - 1 || j == yPos || j == yPos + height - 1) {
                    world[i][j] = Tileset.WALL;
                } else {
                    world[i][j] = Tileset.FLOOR;
                }
            }
        }
        roomInfos.add(new int[] {xPos, yPos, width, height});
        count += 1;
    }

    public static void addRooms(TETile[][] world) {
        int rooms = random.nextInt(5) + 12;
        while (count < rooms) {
            addOneRoom(world);
        }
    }
}
