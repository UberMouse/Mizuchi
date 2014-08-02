import sbt._
import Keys._
import spray.revolver.RevolverPlugin.Revolver
import play.twirl.sbt.SbtTwirl

object MediaFileManagerBuild extends Build {
    lazy val root = project.in(file(".")).aggregate(manager, processor)
    lazy val manager = Project(id = "manager", base = file("manager")) dependsOn processor
    lazy val processor = uri("processor")

    root.enablePlugins(SbtTwirl)

    name           := "MediaFileManager"
    version        := "0.1"
    scalaVersion   := "2.10.3"
    scalacOptions  := Seq("-unchecked", "-deprecation", "-encoding", "utf8")
    Revolver.settings
}
