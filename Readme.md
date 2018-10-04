#### Running
- Run `sbt` in console
- ...Wait..
- Do `TaskN/compile` to compile
- Do `TaskN/run` to run
- Do `TaskN/test` to run tests

#### Task 0: Get started
- We have a small service for inputing and outputing Ads.
- It uses the console to select mode (input, output, exit).
- We "mock" a database using a mutable Map

#### Task 1: Adt
Implement Modus in AdServer as an ADT Sum Type

#### Task 2: General Logic
Implement an ADT for two kinds of Ad
- CarAd, with fields regNr (registration number) and price
- JobAd, with fields company and monthly salary

Make sure that we can output Ads to the console in the same format as it is input

#### Task 3: Options
Get rid of UnknownAdType. Do this by using Option

#### Task 4: Eithers
Switch previous tasks use of Options in favour of Either with a custom Error-ADT

#### Task 5: Recursion
Remove state in AdServer by making run tail-recursive

#### Task 6: IO
Wrap up all console-side effects in IO, and compose the whole program as an IO
(Bonus, wrap up all "Database"-read/write in IOs to)
