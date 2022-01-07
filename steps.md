
We will make this project in a few incremental steps to manage complexity.

# Step 1: Player

Firstly, we want to have the basics of the player character: their avatar and basic keyboard movement on the screen.

We want to be able to support multiple players at once, so we take that into account from the start. 

We will make a single Player model that tracks all basic player info: name, health, position, velocity, ...
Because we know we will later have other objects in the game, we will already abstract out a Vector2 class to represent positions and velocities/movement.

We also have a corresponding PlayerAvatarView view that visualizes the avatar, and a PlayerStatsView that shows health/name/etc. 
Thus showing that the Model-View relationship does not **have** to be 1-to-1.

In the FXML setup, we have a top-level Container, having as children the health/name visualizers (playerList), 
as well as the main levelContainer that we will use to render everything of the game itself (avatars, level geometry, pickups, enemies, ...)

We will then also abstract out a KeyboardInput class so we can have 2 individual players using the same keyboard (but different keys) easily.
Since the JavaFX keyPressed event doesn't work very well with multiple simultaneous keys, we track our own pressed states with keyPressed/keyReleased. 

Finally, we will have a GameLoop TimerTask that updates the main GameViewController on each Tick.

Time needed to complete this step: 80 minutes

Visual rend result: see /recordings/step1_players.gif

# Step 2: Enemy

Secondly, we want to make an Enemy that shoots at us. 

As such, we will need an Enemy model that tracks basic info, as well as a Bullet model.
Additionally, we need EnemyView and BulletView classes. 

Thinking about this, we can see that the Enemy and Player would share a large amount of logic...
Both have position, velocity, health, will shoot bullets, etc. 
The main difference will be how they are controlled (keyboard vs some AI logic).
As such, we can create new Model/View base classes called Spaceship/SpaceshipView that represent both.
We then also generalize KeyboardInput to SpaceshipInput, so we can have an AIInput on top as well.

Now, we encounter a problem: we want both the Player and Enemy to be able to shoot bullets.
Shooting bullets involves creating not just the Bullet Model, but also the Bullet View, so we can't just do this inside the Enemy/Player Model, nor the View classes...
As such, we add a new SpaceshipController that will help us guide everything.

We know we will want multiple different types of Enemies and Bullets down the line, so we already want to decide here how to handle that.
To fit with the basic course and teaching inheritance, we will have specific Enemies and Bullets inherit from their base classes. 
This will be the case both for the Model and View aspects, as well as Input. 
You can see examples of this in the AIInput_UpDown/Stationary and EnemyView_Frigate/Dreadnought.

Time needed to complete this step: 140 minutes

Visual rend result: see /recordings/step2_enemies.gif

# Step 3: Bullets

While we already had bullets in the previous Step, they were simple and only visual things, not actually causing damage.

To make the Bullets hit, we also need to implement some basic collision detection logic. 
We will not put this on either Player, Enemy or even Bullet, but rather have a separate subsystem for this to keep responsibilities clearly separated.
In the previous Step, we had the SpaceshipController create the Bullets itself, but that's somewhat inflexible.
For example, what happens to existing bullets if a ship (and thus its SpaceshipController) is destroyed?
Instead, we use the BulletController to create, move and track the bullets. 

We will also be clever about how we calculate collisions, since not everything collides with everything else.
Enemy bullets collide with Player ships and vice versa, but nothing else. 

We do Collisions in the Views, not the Models, since the Models don't track the actual size of the objects (only their position/velocity).
This gives us more flexibility in shaping the Views later on or to implement non-rectangular collisions later (for example, more finegrained triangle hit boxes).

To make the collision system flexible, we use a single Collidable interface. 
This will make it easy to later also add things like pickups or obstacles (for example asteroids flying through space).


Secondly, we also want bullets that explode or that go offscreen to be properly removed.
If not, they will stay around, taking up memory and processing time. 
That doesn't matter much at the start, but can lead to severe problems with many bullets/objects if the game goes long.


Thirdly, we also want some more complex bullets that don't just move in a straight line 
(for example, that auto-aim towards the player's position at launch, or that follow sinusoid lines)
So we create some subclasses of Bullets and make it so that different ships can fire different types of bullets 
by using the new BulletGenerator class.


Time needed to complete this step: 250 minutes (mainly due to quite some design changes throughout)

Visual end result: see /recordings/step3_bullets.gif
Visual end result stress test: see /recordings/step3_bullets_stress.gif

# Step 4: Graphics

Now that we have the basics of the game mechanics working, we want to make the game look a bit better.

There are tons of websites where you can find free to use game assets.
I used the following sets:
https://opengameart.org/content/space-shooter-redux
https://opengameart.org/content/space-shooter-extension-250
https://opengameart.org/content/explosion-effect-pixel-art

Now, we are going to re-use the same image a lot (especially for the bullets/explosions),
so we don't want to be loading the image from the hard disk each time it's needed.

Instead, we want to have a central ImageController that loads them just once, 
and we can then re-use them when needed in the different Views.

We don't want to have to pass the ImageController in the constructor of all our views, that would be tedious.
Instead, we will make it a static class with a static list of images, so we can access it anywhere more easily.
Normally, you would use something called a "Singleton" for this, but since this is an introductory course, 
we keep it simpler for now.

We also add a gently scrolling background.

Time needed to complete this step: 105 minutes (most of it searching and editing images)

Visual end result: see /recordings/step4_graphics.gif
Visual end result stress test: see /recordings/step4_graphics_stress.gif


Total time to get to this result: 575 minutes or 9.5 hours