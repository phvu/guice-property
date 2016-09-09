name := "guice-properties"

version := "1.0"

scalaVersion := "2.11.8"

mainClass in assembly := Some("phvu.Main")

libraryDependencies ++= Seq(
  "com.google.inject" % "guice" % "4.1.0",
  "org.scalatest" %% "scalatest" % "3.0.0" % "test"
)