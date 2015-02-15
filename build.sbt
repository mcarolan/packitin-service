organization  := "com.example"

version       := "0.1"

scalaVersion  := "2.11.2"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

libraryDependencies ++= {
  val akkaV = "2.3.6"
  val sprayV = "1.3.2"
  Seq(
    "io.spray"            %%  "spray-can"     % sprayV,
    "io.spray"            %%  "spray-routing" % sprayV,
    "io.spray"            %%  "spray-testkit" % sprayV  % "test",
    "com.typesafe.akka"   %%  "akka-actor"    % akkaV,
    "com.typesafe.akka"   %%  "akka-testkit"  % akkaV   % "test",
    "com.typesafe.slick" %% "slick" % "2.1.0",
    "mysql" % "mysql-connector-java" % "5.1.29",
    "com.h2database" % "h2" % "1.3.175" % "test",
    "com.googlecode.flyway" % "flyway-core" % "2.3.1",
    "org.scalaz" %% "scalaz-core" % "7.1.0",
    "com.typesafe" % "config" % "1.2.1",
    "org.typelevel" %% "scalaz-specs2" % "0.3.0" % "test"
  )
}

Revolver.settings
