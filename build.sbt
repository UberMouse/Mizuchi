import play.PlayScala

name := """Mizuchi"""

version := "0.1"

lazy val mizuchi = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
  jdbc,
  cache,
  ws,
  "org.xerial"          % "sqlite-jdbc"    % "3.7.2",
  "com.typesafe.play"   %% "play-slick"    % "0.8.0-M1",
  "org.scaldi"          %% "scaldi-play"   % "0.4",
  "io.argonaut"         %% "argonaut"      % "6.0.4",
  "org.scalatestplus"   %% "play"          % "1.1.0" % "test"
)
