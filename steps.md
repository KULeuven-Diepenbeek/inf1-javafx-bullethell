
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

