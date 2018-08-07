# Mega Galaxian

## A Java-based arcade game
I built Mega Galaxian for a project in a CS class in HS. It was
probably the biggest Java project I'd done at the time and
definitely the most involving graphics. I submitted it as soon as
I got it to run well and look good - and since then, I've identified
quite a few bugs that I may get around to fixing later on. Finally,
I've also thought of a few additional features that would elevate
the gaming experience and add some variety. Both the bug list and
feature list are below the build instructions, and I wrote them out
from memory so let me know if there are more bugs/other cool features!

### Build Instructions:
The "Mega Galaxian" directory is an exported Eclipse project. You could
import the whole file into Eclipse as a project, and it would run. The
directory also contains all the source files inside **src** and all the
resources so you could pretty much use any Java set-up you wanted! The
build path specifies Java 1.7 I believe but as long as its 1.7 or above
everything should work great!

### Bugs:
1. Levels and Points can only show 1 of a single digit (due to a
confusing choice to use images instead of actual strings for the
counters
2. The player is not limited in any direction - they can move outside
the screen a significant distance (probably arbitrarily far) and still
fire bullets. The player's bullets will be tracked even outside the
screen since they are only removed after crossing the top edge.
3. Occasionally, an enemy will spawn right outisde the viewable screen
to the right (because I randomly generate an initial x-position on the
top of the window for each enemy, and forgot to account for the fact
that said position is on the top-left of the image. Thus, the level
won't end unless the players goes off the edge of the screen to the
right and fires a bullet to kill that enemy.

### New Features I want to add
##### (In no particular order)
1. Randomly spawning scrolling obstacles (you'll notice the image file
in the directory) that kill on impact (both enemies and player) and cannot be destroyed.
2. Boss-fight every 10 levels, where massive single enemy progressively
has greater health and shoots multiple multi-directional bullets at a time.
3. High-score tracking!
4. Variety of enemy sprites (alternate already exists in directory,
add/design more?)
