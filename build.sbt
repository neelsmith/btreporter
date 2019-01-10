name := "btreporter"
name := "HMT CEX builder"

crossScalaVersions  := Seq("2.11.8", "2.12.4")
scalaVersion := (crossScalaVersions ).value.last


organization := "io.github.neelsmith"
version := "2.4.0"
licenses += ("GPL-3.0",url("https://opensource.org/licenses/gpl-3.0.html"))


libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "3.0.1" % "test",
  "net.liftweb" %% "lift-json" % "3.1.0",
  "com.github.nscala-time" %% "nscala-time" % "2.16.0"
)

tutTargetDirectory := file("docs")
tutSourceDirectory := file("tut")

enablePlugins(TutPlugin)
