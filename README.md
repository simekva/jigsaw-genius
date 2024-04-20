# JigsawGenius

Welcome to the repository for the *JigsawGenius* Android Game. This project was developed for the course **TDT4240 Programvarearkitektur**, for the Spring of 2024. 

## Description of game

JigsawGenius is a strategy tile-placing game, where the main objective is to score as many points as possible. A player can score points by placing hexagonal tiles next to eachother. Each tile has 6 sides with a random **Biome** on each side. These biomes include *plains*, *desert*, *forest*, *village* and *field*. The way you score points is to match up a biome on your tile, with the same biome on an already placed tile. Since a tile has 6 sides, it gives the player the opportunity to match up to 6 sides on one tile placed. This would reward the player with the maximal amount of points. 

You can play the game in SinglePlayer- or MultiPlayerMode. In SinglePlayer, you try to get the highest score possible. While in MultiPlayer, you battle against a friend to get the highest score between the two of you.



## Structure of the code



### Visualisation of folders and files

Here is the folder structure for JigsawGenius:

```
|__artwork
|
|__main
|    |
|    |__android
|    |     |
|    |     |__src\com\gdx\jigsawgenius
|    |                   |
|    |                   |__AndroidLauncher.java
|    |__assets
|    |     |__images/figures
|    |
|    |__core
|    |    |
|    |    |__src\com\gdx\jigsawgenius
|    |    |               |
|    |    |
|    |    |
|    |    |_JigsawGenius.java
|    |    
|    |
|    |__desktop
|    |     |
|    |     |__src\com\gdx\jigsawgenius
|    |                   |
|    |                   |__DesktopLauncher.java
|    |
|    |__gradle
|    |     |__gradle.wrapper.jar
|    |
|    |__ios
|    |   |
|    |   |__src\com\gdx\jigsawgenius
|    |                   |
|    |                   |__IOSLauncher.java
|    |
|    |__build.gradle
|
|__README.md
```

### Description

The *main\core\JigsawGenius.java* is our main class being called in one of the launcher classes. This file is what initializes the whole game.




## How to run/compile the project

There are several ways a player can download to try our game. One can simply *Download ZIP* to get all the raw code, or *Clone* the project using HTTPS or SSH. From there navigate to one of the *Launchers* to start the game from the code. 


### Launchers
In the initial **gradle** build, we decided to include launchers for Android, Desktop and IOS devices. Since the assignment was to make a game for android, nothing is configured for a ios. The possibility to modify this launcher is still there, if further development is wanted. 

