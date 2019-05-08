import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Template for a side-scrolling platform game.
 * 
 * @author R. Gordon
 * @version May 8, 2019
 */
public class SideScrollingWorld extends World
{
    /**
     * Instance variables
     * 
     * These are available for use in any method below.
     */    
    // Tile size in pixels for world elements (blocks, clouds, etc)
    private static final int TILE_SIZE = 32;

    // World size constants
    private static final int VISIBLE_WIDTH = 640;
    private static final int VISIBLE_HEIGHT = 480;
    private static final int HALF_VISIBLE_WIDTH = VISIBLE_WIDTH / 2;
    private static final int HALF_VISIBLE_HEIGHT = VISIBLE_HEIGHT / 2;
    private static final int WORLD_WIDTH = VISIBLE_WIDTH * 3;

    // Camera edges
    int leftCameraEdge;
    int rightCameraEdge;

    // Hero
    Hero theHero;

    // Track whether game is on
    private boolean isGameOver;

    // Get access to the level map
    private LevelMap level;

    /**
     * Constructor for objects of class SideScrollingWorld.
     */
    public SideScrollingWorld()
    {    
        // Create a new world with 640x480 cells with a cell size of 1x1 pixels.
        // Final argument of 'false' means that actors in the world are not restricted to the world boundary.
        // See: https://www.greenfoot.org/files/javadoc/greenfoot/World.html#World-int-int-int-boolean-
        super(VISIBLE_WIDTH, VISIBLE_HEIGHT, 1, false);

        // Set up the starting scene
        setup();

        // Camera edges (what part of world is showing right now
        leftCameraEdge = 0;
        rightCameraEdge = VISIBLE_WIDTH;

        // Game on
        isGameOver = false;

        // Access the level map
        level = new LevelMap();
    }

    /**
     * Set up the starting scene.
     */
    private void setup()
    {
        addHero();
        addGroundAtBottom();
    }

    /**
     * Act
     * 
     * This method is called approximately 60 times per second.
     */
    public void act()
    {
        if (!isGameOver)
        {
            updateCameraEdges();
        }
    }

    /**
     * Keep track of the visible range of the world, based on hero movement.
     */
    public void updateCameraEdges()
    {
        leftCameraEdge = theHero.getX() - HALF_VISIBLE_WIDTH;
        rightCameraEdge = theHero.getX() + HALF_VISIBLE_WIDTH;
    }

    /**
     * Add the hero to the world.
     */
    private void addHero()
    {
        // Initial horizontal position
        int initialX = getWidth() - 5 * getWidth() / 6;

        // Instantiate the hero object
        theHero = new Hero(initialX);

        // Add hero in bottom left corner of screen
        addObject(theHero, initialX, getHeight() / 4 * 3);
    }

    /**
     * Add blocks to create the ground to walk on at bottom of screen.
     */
    private void addGroundAtBottom()
    {
        // How many tiles will cover the bottom of the screen?
        int tilesToCreate = getWidth() / TILE_SIZE;

        // Loop to create and add the tile objects
        for (int i = 0; i < tilesToCreate; i += 1)
        {
            // Add ground objects at bottom of screen
            // NOTE: Actors are added based on their centrepoint, so the math is a bit trickier.
            int x = i * TILE_SIZE + TILE_SIZE / 2;
            int y = getHeight() - TILE_SIZE / 2;

            // Create a ground tile
            Ground groundTile = new Ground();

            // Add the objects
            addObject(groundTile, x, y);
        }
    }

    /**
     * Return half of the visible width of the world.
     */
    public int getHalfVisibleWidth()
    {
        return HALF_VISIBLE_WIDTH;
    }

    /**
     * Return an object reference to the hero.
     */
    public Hero getHero()
    {
        return theHero;
    }

    /**
     * Set game over
     */
    public void setGameOver()
    {
        isGameOver = true;
    }

    /**
     * Add tiles from the level map into the world, as needed between given horizontal range.
     */
    public void checkAddTiles(int heroX, int heroSpeed)
    {
        int leftX = heroX + HALF_VISIBLE_WIDTH;
        int rightX = leftX + heroSpeed;
        System.out.println("leftX is" + leftX);
        System.out.println("rightX is" + rightX);

        // Loop through all the tiles in the map, add any that are within the range given
        for (int i = 0; i < level.COUNT_OF_TILES; i += 1)
        {
            System.out.println("Looping through tiles");
            if (level.tileX[i] >= leftX && level.tileX[i] < rightX)
            {
                // Add this tile to the world
                if (level.tileType[i] == level.TILE_GROUND)
                {
                    System.out.println("Creating ground at (" + level.tileX[i] + ", " + level.tileY[i] + ")");
                    Ground ground = new Ground();
                    addObject(ground, level.tileX[i], level.tileY[i]);
                }
                else if (level.tileType[i] == level.TILE_METAL_PLATE)
                {
                    System.out.println("Creating metal plate at (" + level.tileX[i] + ", " + level.tileY[i] + ")");
                    MetalPlate metalPlate = new MetalPlate();
                    addObject(metalPlate, level.tileX[i], level.tileY[i]);
                }
            }
        }
    }
}

