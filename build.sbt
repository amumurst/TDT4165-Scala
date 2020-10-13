val commonSettings: List[SettingsDefinition] = List(
  inThisBuild(
    List(
      organization := "no.finn",
      scalaVersion := "2.13.3",
      version := "0.1.0-SNAPSHOT"
    )),
  libraryDependencies += "org.scalatest" %% "scalatest"   % "3.2.2" % Test,
  libraryDependencies += "org.typelevel" %% "cats-effect" % "2.2.0"
)

lazy val common = project.in(file("Common")).settings(commonSettings: _*).settings(name := "common")
lazy val examples =
  project.in(file("Examples")).settings(commonSettings: _*).settings(name := "examples")

def opg(number: Int) = {
  val navn = s"task$number"
  Project(navn, file(navn))
    .settings(commonSettings: _*)
    .settings(name := navn, mainClass in (Compile, run) := Some("no.finn.Main"))
    .dependsOn(common % "test->test;compile->compile")
}

lazy val Task0 = opg(0)
lazy val Task1 = opg(1)
lazy val Task2 = opg(2)
lazy val Task3 = opg(3)
lazy val Task4 = opg(4)
lazy val Task5 = opg(5)
lazy val Task6 = opg(6)

lazy val root =
  project.in(file(".")).aggregate(common, examples, Task0, Task1, Task2, Task3, Task4, Task5, Task6)
