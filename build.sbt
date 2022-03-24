import Dependencies._

ThisBuild / scalaVersion := "2.12.8"
ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / organization := "com.example"
ThisBuild / organizationName := "example"

lazy val commonSettings = Seq(
  scalaVersion := "2.12.8",
  libraryDependencies += scalaTest % Test
  )

lazy val root = (project in file("."))
  .aggregate(
    presentation,
    application,
    domain,
    infrastructure
  )

lazy val presentation = (project in file("presentation"))
  .settings(commonSettings)
  .dependsOn(application)

lazy val application = (project in file("application"))
  .settings(commonSettings)
  .dependsOn(domain, infrastructure)

lazy val domain = (project in file("domain"))
  .settings(commonSettings)

lazy val infrastructure = (project in file("infrastructure"))
  .settings(commonSettings)
