# Simulation of a modified trust game using a reputation score

This is a simulation used in the project 'Analysis and simulation of a modified trust game using a reputation score'. It tries to simulate an online trading platform where users of various strategies interact with each other. The core idea is to study the effects of various reputation scores.

## Disclaimer

This simulation is **NOT user friendly** but may help as a framework for somebody who would like to play around with this model. Furthermore, the code was not intended to be published and hence may not be easily readable and may contain many commented-out lines.

## Configurations

Many configurations can be done within RunGame.java which is also contains the main-method. However, the variables are poorly documented and changes may have unexpected side effects. For users with a basic knowledge of Java, it should be possible to add new strategies, modify existing ones and change the population distribution.
Furthermore, the parameters of the trust game, capitals of the players, error rates of the players and a lot more can be modified. However, all these changes require a basic understanding of Java and a basic understanding of our code.
This repository also contains many classes which were not used in our paper. Those classes may or may not be functional in the current version.

## Some translations
Here are the relevant classes used in our paper:

* Main -> RunGame.java (calls a lot of constructors)

* The Trust Game -> GameInfo.java (configuration through the constructor)

* the core of the simulation -> StaticEvolutionManager (configuration through the constructor)

* Honest Player -> AlwaysCooperate.java
* Rational Player -> HomoOeconomicus.java
* Random Player -> TruthfulRandom.java (though, they are not truthful)
* Malicious Player -> MafiaDefect.java

* Without Reputation Score -> NoFeedbackSystem.java
* Objective Reputation Score -> ObjFeedbackSystem.java
* Reported Reputation Score -> PureFeedbackSystem.java
* Weighted Reputation Score -> WeightedFeedbackSystem.java
* Selective Reputation Score -> SelectiveFeedbackSystem.java
* Skewed Reputation Score -> TransformedFeedbackSystem.java

* Statistical Analysis -> Utils.java
