
val commonSettings: List[SettingsDefinition] = List(
  inThisBuild(
    List(
      organization := "no.finn",
      scalaVersion := "2.13.1",
      version := "0.1.0-SNAPSHOT"
    )),
  libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.8" % Test,
  libraryDependencies += "org.typelevel" %% "cats-effect" % "2.0.0"
)

lazy val common = project.in(file("Common")).settings(commonSettings: _*).settings(name := "common")
lazy val examples = project.in(file("Examples")).settings(commonSettings: _*).settings(name := "examples")

def opg(navn: String) =
  Project(navn, file(navn))
    .settings(commonSettings: _*)
    .settings(name := navn, mainClass in (Compile, run) := Some("no.finn.Main"))
    .dependsOn(common % "test->test;compile->compile")

lazy val Task0 = opg("Task0")
lazy val Task1 = opg("Task1")
lazy val Task2 = opg("Task2")
lazy val Task3 = opg("Task3")
lazy val Task4 = opg("Task4")
lazy val Task5 = opg("Task5")
lazy val Task6 = opg("Task6")
