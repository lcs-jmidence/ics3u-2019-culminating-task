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
    // TO STUDENTS: Modify if your game's tiles have different dimensions
    private static final int TILE_SIZE = 32;
    private static final int HALF_TILE_SIZE = TILE_SIZE / 2;

    // World size constants
    // TO STUDENTS: Modify only if you're sure
    //              Should be a resolution that's a multiple of TILE_SIZE
    private static final int VISIBLE_WIDTH = 480;
    private static final int VISIBLE_HEIGHT = 640;

    // Additional useful constants based on world size
    public static final int HALF_VISIBLE_WIDTH = VISIBLE_WIDTH / 2;
    private static final int HALF_VISIBLE_HEIGHT = VISIBLE_HEIGHT / 2;

    // Defining the boundaries of the scrollable world
    // TO STUDENTS: Modify SCROLLABLE_WIDTH if you wish to have a longer level
    public static final int SCROLLABLE_WIDTH = VISIBLE_WIDTH * 3;
    private static final int SCROLLABLE_HEIGHT = VISIBLE_HEIGHT;

    // Hero
    Taizo theHero;

    // Track whether game is on
    private boolean isGameOver;
    
    // Background music
    GreenfootSound backgroundMusic = new GreenfootSound("ThemeSong.mp3");

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

        // Game on  
        isGameOver = false;
        prepare();
        
        // Play the music
        backgroundMusic.playLoop();
    }

    /**
     * Set up the entire world.
     */
    private void setup()
    {
        // Add the necessary methods at the beginning
        topBorder();
        bottomBorder();
        leftBorder();
        rightBorder();
        addGround();
        addHero();
    }

    /**
     * Add blocks to create the ground to walk on at bottom-left of scrollable world.
     */

    private void topBorder()
    {
        // Make the tiles the width of the world
        final int tilesToCreate = getWidth();

        // Loop to create and add the tile objects
        for (int i = 0; i < tilesToCreate; i += 1)
        {
            // Add MetalPlates objects at the top of the screen.
            int x = i * TILE_SIZE + HALF_TILE_SIZE;
            int y = HALF_TILE_SIZE;

            // Create a MetalPlate tile
            MetalPlate metalBorder = new MetalPlate(x, y);

            // Add the objects
            addObject(metalBorder, x, y);
        }
    }

    private void bottomBorder()
    {
        // Make the tiles the width of the world
        final int tilesToCreate = getWidth();

        // Loop to create and add the tile objects
        for (int i = 0; i < tilesToCreate; i += 1)
        {
            // Add MetalPlates objects at bottom of screen.
            int x = i * TILE_SIZE + HALF_TILE_SIZE;
            int y = getHeight() - HALF_TILE_SIZE;

            // Create a MetalPlate tile
            MetalPlate metalBorder = new MetalPlate(x, y);

            // Add the objects
            addObject(metalBorder, x, y);
        }
    }

    private void leftBorder()
    {
        // Make the tiles the width of the world
        final int tilesToCreate = getHeight();

        // Loop to create and add the tile objects
        for (int i = 0; i < tilesToCreate; i += 1)
        {
            // Add MetalPlates objects at bottom of screen.
            int x = HALF_TILE_SIZE;
            int y = i * TILE_SIZE + HALF_TILE_SIZE;

            // Create a MetalPlate tile
            MetalPlate metalBorder = new MetalPlate(x, y);

            // Add the objects
            addObject(metalBorder, x, y);
        }
    }

    private void rightBorder()
    {
        // Make the tiles the width of the world
        final int tilesToCreate = getHeight();

        // Loop to create and add the tile objects
        for (int i = 0; i < tilesToCreate; i += 1)
        {
            // Add MetalPlates objects at bottom of screen.
            int x = SCROLLABLE_WIDTH - HALF_TILE_SIZE;
            int y = i * TILE_SIZE + HALF_TILE_SIZE;

            // Create a MetalPlate tile
            MetalPlate metalBorder = new MetalPlate(x, y);

            // Add the objects
            addObject(metalBorder, x, y);
        }
    }

    /**
     * Act
     * 
     * This method is called approximately 60 times per second.
     */
    public void act()
    {
    }

    /**
     * Add the hero to the world.
     */
    private void addHero()
    {
        // Initial horizontal position
        int initialX = TILE_SIZE * 3;

        // Instantiate the hero object
        theHero = new Taizo(initialX);

        // Add hero in bottom left corner of screen
        addObject(theHero, initialX, getHeight() / 4 * 3);
    }

    /**
     * Add blocks to create the ground to walk on at top-right of scrollable world.
     */
    private void addGround()
    {
        // Constants to control dimensions of the ground at end of world
        final int COUNT_OF_GROUND = 39;
        final int GROUND_BELOW_COLUMNS = COUNT_OF_GROUND;
        final int GROUND_BELOW_ROWS = 18;
        final int COUNT_OF_GROUND_BELOW = GROUND_BELOW_COLUMNS * GROUND_BELOW_ROWS;
        
        // Instead of having 2 sets of ground, just have one big section
        for (int i = 0; i < GROUND_BELOW_COLUMNS; i += 1)
        {
            for (int j = 0; j < GROUND_BELOW_ROWS; j += 1)
            {
                // Position in wider scrollable world
                int x = i * TILE_SIZE + 5 * TILE_SIZE + HALF_TILE_SIZE;
                int y = HALF_TILE_SIZE + TILE_SIZE * (j + 1);

                // Create object and add it
                Ground ground = new Ground(x, y);
                addObject(ground, x, y);
            }
        }
    }

    /**
     * Return an object reference to the hero.
     */
    public Taizo getHero()
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
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        Pooka pooka = new Pooka();
        addObject(pooka,81,77);
    }
}

