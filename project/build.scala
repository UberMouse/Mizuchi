import sbt._
import sbt.Keys._
import spray.revolver.RevolverPlugin.Revolver
import play.twirl.sbt.SbtTwirl

object MediaFileManagerBuild extends Build {
  lazy val root = project.in(file(".")).aggregate(manager, processor).dependsOn(manager, processor).settings(net.virtualvoid.sbt.graph.Plugin.graphSettings: _*).enablePlugins(SbtTwirl)
  lazy val common = Project(id = "common", base = file("common")).settings(net.virtualvoid.sbt.graph.Plugin.graphSettings: _*)
  lazy val manager = Project(id = "manager", base = file("manager")).dependsOn(common, processor).settings(net.virtualvoid.sbt.graph.Plugin.graphSettings: _*).enablePlugins(SbtTwirl)
  lazy val processor = Project(id = "processor", base = file("processor")).settings(net.virtualvoid.sbt.graph.Plugin.graphSettings: _*).dependsOn(common)
  lazy val hummingbird = Project(id = "hummingbird", base = file("hummingbird")).settings(net.virtualvoid.sbt.graph.Plugin.graphSettings: _*)


  Revolver.settings
  javaOptions in Revolver.reStart := Seq("-Xdebug", "-Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5005")
}
