# Mega Galaxian

## A Java-based arcade game
I built Mega Galaxian for a project in a CS class in HS. It was
probably the biggest Java project I'd done at the time and
definitely the most involving graphics. I submitted it as soon as
I got it to run well and look good - and since then, I've identified
quite a few bugs. I've also  thought of a few additional features
that would elevate the gaming experience and add some variety. As I
get the time and motivation, I'll be working through this project to
fix the issues and add the features, and I'll be updating the list accordingly.
Both the bug list and feature list are below the build instructions,
and I wrote them out from memory so let me know if there are more
bugs or you think of other cool features if you decide to check it out!

### Build Instructions:
The "Mega Galaxian" directory is an exported Eclipse project. You could
import the whole file into Eclipse as a project, and it would run. The
directory also contains all the source files inside `src\` and all the
resources so you could pretty much use any Java set-up you wanted! 
The project requires Java 7 or above.

### Issues:
1. Levels and Points can only show 1 of a single digit (Points: 50
displays fine, but Points: 55 would display as Points: 5). This is
due to me utilizing .png's of each digit to display numbers rather
than using an appropriate font. Can be fixed by: 
    * making 2 icons for each digit for Levels, and 3 icons for 
    each digit for Points.
    * replacing counter with an appropriate font and using strings,
    which is obviously better but would require re-writing the whole
    interface for visual alignment
2. The player is not limited in any direction - they can move outside
the screen a significant distance (probably arbitrarily far) and still
fire bullets. The player's bullets will be tracked even outside the
screen since they are only removed after crossing the top edge. Fix is
simple - don't allow player's movement if they are at the edges of the
screen. Fix #3 before fixing #2, or you could be randomly stuck on a
level with a hidden enemy and not be able to kill it.
3. Occasionally, an enemy will spawn right outisde the viewable screen
to the right. Issue is due to the x/y position of the enemy being based
on their top-left corner, and random generation of x-position being
between 0 and window-width. Thus, sometimes an enemy spawns completely
outside the screen bounds, and can neither be seen nor has its movement
calculations performed to move inside the window. Fix is to adjust the
range of the possible x-positions to be `window-width - enemy-width`.

### New Features I want to add
##### (In no particular order)
1. Randomly spawning scrolling obstacles (you'll notice the image file
in the directory and Obstacle class file) that kill on impact (both 
enemies and player) and cannot be destroyed. 2 new random number
generators needed:
    1. Calculates the x-position of the obstacle
    2. Calculates if an obstacle should be generated (frequency of 
    obstacles should be about 10-15%)
Also, will require a new ArrayList to store Obstacle objects, and
code in the drawing loop to draw them.
2. Boss-fight every 10 levels, where massive single enemy progressively
has greater health and shoots multiple multi-directional bullets at a time.
Possible steps to create feature:
    * Create Boss class that inherits from Enemy, adds necessary methods
    like changing health based on level and firing multi-directionally
    * Add new game loop logic to do no new enemy generation for Boss
    levels
    * Add Boss-drawing code in draw loop when in a Boss level
3. High-score tracking! This could be don every simply by maintaining
a txt file in the directory that lists the top 10 scores and submitted
usernames for the players. As above, steps to create this:
    * Score class that records integer score and String username, and
    methods to reading Score objects from txt file and writing Score objects
    into txt file
    * Text field on game end to enter a username if new high score is reached
    * Add an all-time high score display to game window
4. Variety of enemy sprites (alternate already exists in directory,
add/design more?)
    * Could be a matter of simply alternating whether an enemy icon displays
    png1 or png2 or any other enemy designs
    * Could use another random generator to make it less deterministic
    
### Good Luck and hope you enjoy Mega Galaxian!
