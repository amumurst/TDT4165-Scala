## About
This repo is a basis of a guest lecture held at NTNU Trondheim in the course TDT4165 Programming Languages.

The goal is to show of as much as possible of Scala's base features in 2 hours and give motivations for why
thinking of types and domains is useful and important.

## Scala at FINN.no
Finn has been deploying scala for at least 8 years now.
Most of the teams using it is embracing the more functional style.
Some things we really like:
- Errors at compile time instead of at run time
- Immutability first - approach
- Incredibly powerful type system
- Concise and regular language structure
- Very nice library support for doing Functional Programming
- Leverage existing tools from JVM eco system
- Ability to drop down to procedural when desired

## Mini-FINN
A barebone classified ads service using the console

#### Setup for Intellij
- Install the scala plugin (Preferences -> Plugins -> Search 'scala' -> Restart Intellij)
- Open project in Intellij
- Choose project JDK 1.8 (only tested on java8), leave rest as defaults

#### Running
- Press the play button on a main class (AdServer in a sub-module)

### Prelude 
*Read*: Syntax

### Task 0: Introduction
- We have a small service for inputting and outputting Ads.
- It uses the console to select mode (input, output, exit).
- We "mock" a database using a mutable Map

### Task 1: ADT
*Read*: ADTExample

*Problem*: The modus variable in AdServer is implemented as a string and such has an infinite domain

*Task:* Replace it with an ADT Sum Type with a limited domain.

### Task 2: Modeling
*Read*: ModelingExample

*Problem*: The database accepts strings and not product types

*Task:* Implement an ADT for two kinds of Ad

- CarAd, with fields regNr (registration number) and price
- JobAd, with fields company and monthly salary

Make sure we can output Ads to the console in the same format as it is input

### Task 3: Option
*Read*: OptionExample

*Problem*: UnknownAdType is not meaningful in the Ad type
 
*Task:* Get rid of UnknownAdType. Do this by using the Option type

### Task 4: Either
*Read*: EitherExample

*Problem*: The user gets no feedback on what went wrong if we could not handle the input

*Task:* Switch previous tasks use of Options in favour of Either with a custom Error-ADT

### Task 5: Recursion
*Read*: RecursionExample

*Problem*: The app uses a while loop mutating a var. Eg it has state.

*Task:* Remove state in AdServer by making the run method tail-recursive

### Task 6: IO
*Read*: IOExample

*Problem*: There are untracked side effects in accessing console and database

*Task:* Wrap up console effects in IO and compose program as IO. 

*Bonus*: wrap up all "Database"-read/write in IOs to)

*Bonus2*: create list of modes to present user