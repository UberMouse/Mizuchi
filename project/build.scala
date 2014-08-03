import sbt._
import sbt.Keys._
import spray.revolver.RevolverPlugin.Revolver
import play.twirl.sbt.SbtTwirl

object MediaFileManagerBuild extends Build {
  lazy val root = project.in(file(".")).aggregate(manager, processor).settings(net.virtualvoid.sbt.graph.Plugin.graphSettings: _*)
  lazy val common = uri("common")
  lazy val manager = Project(id = "manager", base = file("manager")).dependsOn(common, processor).settings(net.virtualvoid.sbt.graph.Plugin.graphSettings: _*)
  lazy val processor = Project(id = "processor", base = file("processor")).dependsOn(common).settings(net.virtualvoid.sbt.graph.Plugin.graphSettings: _*)

  root.enablePlugins(SbtTwirl)


  Revolver.settings
}
