name          := "MediaFileManager"

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
    "com.typesafe.slick"  %% "slick"         % "2.1.0-RC3",
    "com.h2database"      %  "h2"            % "1.3.174"
  )
}
