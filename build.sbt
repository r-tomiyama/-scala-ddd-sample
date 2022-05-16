import Dependencies._

ThisBuild / scalaVersion := "2.12.8"
ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / organization := "com.example"
ThisBuild / organizationName := "example"

lazy val commonSettings = Seq(
  scalaVersion := "2.12.8",
  libraryDependencies += scalaTest % Test,
  libraryDependencies += "com.softwaremill.macwire" %% "macros" % "2.5.6" % "provided",
  libraryDependencies += "org.mockito" % "mockito-core" % "4.4.0" % "test"
  )

lazy val root = (project in file("."))
  .aggregate(
    presentation,
    application,
    domainService,
    infrastructure,
    domainModel
  )
  .dependsOn(
    presentation,
    application,
    domainService,
    infrastructure,
    domainModel
  )

lazy val presentation = (project in file("presentation"))
  .settings(commonSettings)
  .dependsOn(application)

lazy val application = (project in file("application"))
  .settings(commonSettings)
  .dependsOn(domainService, infrastructure, domainModel)

lazy val domainService = (project in file("domainService"))
  .settings(commonSettings)
  .dependsOn(infrastructure, domainModel)

lazy val infrastructure = (project in file("infrastructure"))
  .settings(commonSettings)
  .dependsOn(domainModel)

lazy val domainModel = (project in file("domainModel"))
  .settings(commonSettings)

