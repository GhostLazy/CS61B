package byog.lab5;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    private static final int WIDTH = 60;
    private static final int HEIGHT = 60;

    public static void addHexagon(TETile[][] world, int xPos, int yPos, int s, TETile T) {
        /* record half of height for each column **/
        int halfHeight = 1;
        /* overwrite tile at each position **/
        for (int i = 0; i < 3 * s - 2; i++) {
            for (int j = 1 - halfHeight; j < 1 + halfHeight; j++) {
                world[xPos + i][yPos + j] = T;
            }
            /* update half of height for each column **/
            if (i < s - 1) {
                halfHeight += 1;
            } else if (i > 2 * s - 3) {
                halfHeight -= 1;
            }
        }
    }

    private static TETile randomTile() {
        Random random = new Random();
        int tileNum = random.nextInt(8);
        return switch (tileNum) {
            case 0 -> Tileset.WALL;
            case 1 -> Tileset.FLOWER;
            case 2 -> Tileset.TREE;
            case 3 -> Tileset.SAND;
            case 4 -> Tileset.MOUNTAIN;
            case 5 -> Tileset.GRASS;
            case 6 -> Tileset.FLOOR;
            case 7 -> Tileset.WATER;
            default -> Tileset.NOTHING;
        };
    }

    public static void tesselationNineteenHexagon(TETile[][] world, int xPos, int yPos, int s, int totalS) {
        int num = totalS;
        for (int i = 0; i < 2 * totalS - 1; i++) {
            for (int j = 0; j < num; j++) {
                TETile tile = randomTile();
                addHexagon(world, xPos + (2 * s - 1) * i , yPos + (2 * s) * j - s * (num - totalS), s, tile);
            }
            if (i < totalS - 1) {
                num += 1;
            } else {
                num -= 1;
            }
        }
    }

    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        TETile[][] world = new TETile[WIDTH][HEIGHT];
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                world[i][j] = Tileset.NOTHING;
            }
        }

        tesselationNineteenHexagon(world, 10, 25, 3, 4);

        ter.renderFrame(world);
    }
}
