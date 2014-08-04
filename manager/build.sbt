import spray.revolver.RevolverPlugin._

name          := "show-manager"

version       := "0.1"

scalaVersion  := "2.11.0"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

libraryDependencies ++= {
  val akkaV = "2.3.2"
  val sprayV = "1.3.1"
  Seq(
    "io.spray"            %% "spray-can"     % sprayV,
    "io.spray"            %% "spray-routing" % sprayV,
    "io.spray"            %% "spray-testkit" % sprayV  % "test",
    "io.spray"            %% "spray-json"    % "1.2.6",
    "com.typesafe.akka"   %% "akka-actor"    % akkaV,
    "com.typesafe.akka"   %% "akka-testkit"  % akkaV   % "test",
    "org.scaldi"          %% "scaldi-akka"   % "0.4",
    "org.scalatest"       %% "scalatest"     % "2.2.1" % "test",
    "org.xerial"          %  "sqlite-jdbc"   % "3.7.2",
    "org.slf4j"           %  "slf4j-api"     % "1.7.7",
    "org.slf4j"           %  "slf4j-simple"  % "1.7.7"
  )
}

Revolver.settings
