
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