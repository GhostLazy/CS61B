package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class Hallway {
    public static List<int[]> roomInfos = Room.roomInfos;
    private static final Random random = new Random();

    private static int manhattanDistance(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }

    private static int centerX(int[] room) {
        return room[0] + room[2] / 2;
    }

    private static int centerY(int[] room) {
        return room[1] + room[3] / 2;
    }

    private static int minDisRoom(int roomIdx) {
        int minDis = Integer.MAX_VALUE;
        int result = 0;
        for (int i = roomIdx + 1; i < roomInfos.size(); i++) {
            int temDis = manhattanDistance(centerX(roomInfos.get(roomIdx)), centerY(roomInfos.get(roomIdx)),
                    centerX(roomInfos.get(i)), centerY(roomInfos.get(i)));
            if (temDis < minDis) {
                minDis = temDis;
                result = i;
            }
        }
        return result;
    }

    private static void addHallwayX(TETile[][] world, int[] room1, int[] room2) {
        if (room1[0] + room1[2] - 1 < room2[0]) {
            int start = random.nextInt(room1[3] - 2) + 1;
            world[room1[0] + room1[2] - 1][room1[1] + start] = Tileset.FLOOR;
            for (int i = room1[0] + room1[2]; i <= room2[0]; i++) {
                world[i][room1[1] + start - 1] = Tileset.WALL;
                world[i][room1[1] + start] = Tileset.FLOOR;
                world[i][room1[1] + start + 1] = Tileset.WALL;
            }
        } else {
            int start = random.nextInt(room1[3] - 2) + 1;
            world[room1[0]][room1[1] + start] = Tileset.FLOOR;
            for (int i = room1[0] - 1; i >= room2[0] + room2[2] - 1; i--) {
                world[i][room1[1] + start - 1] = Tileset.WALL;
                world[i][room1[1] + start] = Tileset.FLOOR;
                world[i][room1[1] + start + 1] = Tileset.WALL;
            }
        }
    }

    private static void addHallwayY(TETile[][] world, int[] room1, int[] room2) {
        if (room1[1] + room1[3] - 1 < room2[1]) {
            int start = random.nextInt(room1[2] - 2) + 1;
            world[room1[0] + start][room1[1] + room1[3] - 1] = Tileset.FLOOR;
            for (int i = room1[1] + room1[3]; i <= room2[1]; i++) {
                world[room1[0] + start - 1][i] = Tileset.WALL;
                world[room1[0] + start][i] = Tileset.FLOOR;
                world[room1[0] + start + 1][i] = Tileset.WALL;
            }
        } else {
            int start = random.nextInt(room1[2] - 2) + 1;
            world[room1[0] + start][room1[1]] = Tileset.FLOOR;
            for (int i = room1[1] - 1; i >= room2[0] + room2[2] - 1; i--) {
                world[room1[0] + start - 1][i] = Tileset.WALL;
                world[room1[0] + start][i] = Tileset.FLOOR;
                world[room1[0] + start + 1][i] = Tileset.WALL;
            }
        }
    }

    public static void addHallways(TETile[][] world) {
        int idx = 0;
        for (int[] room : roomInfos) {
            addHallwayX(world, room, roomInfos.get(minDisRoom(idx)));
            addHallwayY(world, room, roomInfos.get(minDisRoom(idx)));
            System.out.println(Arrays.toString(room));
            idx++;
        }
    }
}
