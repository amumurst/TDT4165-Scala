#### Setup for Intellij
- Install the scala plugin (Preferences -> Plugins -> Search 'scala' -> Restart Intellij)
- Open project in Intellij
- Choose project JDK 1.8 (only tested on java8), leave rest as defaults

#### Running
- Run `sbt` in console or use sbt shell tab in intellij 
- ...Wait..
- Do `TaskN/compile` to compile
- Do `TaskN/run` to run
- Do `TaskN/test` to run tests

#### Prelude 
Read: Syntax

#### Task 0: Introduction
- We have a small service for inputting and outputting Ads.
- It uses the console to select mode (input, output, exit).
- We "mock" a database using a mutable Map

#### Task 1: ADT
Read: ADTExample
Problem: The modus variable in AdServer is implemented as a string and such has an infinite domain
Task: Replace it with an ADT Sum Type with a limited domain.

#### Task 2: Modeling
Read: ModelingExample
Problem: The database accepts strings and not product types
Task: Implement an ADT for two kinds of Ad
        - CarAd, with fields regNr (registration number) and price
        - JobAd, with fields company and monthly salary
Make sure we can output Ads to the console in the same format as it is input

#### Task 3: Option
Read: OptionExample
Problem: UnknownAdType is not meaningful in the Ad type 
Task: Get rid of UnknownAdType. Do this by using Option

#### Task 4: Either
Read: EitherExample
Problem: The user gets no feedback on what went wrong if we could not handle the input
Task: Switch previous tasks use of Options in favour of Either with a custom Error-ADT

#### Task 5: Recursion
Read: RecursionExample
Problem: The app uses a while loop mutating a var. Eg it has state.
Task: Remove state in AdServer by making the run method tail-recursive

#### Task 6: IO
Read: IOExample
Problem: There are untracked side effects in accessing console and database
Task: Wrap up console effects in IO and compose program as IO. 
(Bonus, wrap up all "Database"-read/write in IOs to)
(Bonus2, create list of modes to present user)