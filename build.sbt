name := "Agent-Based Analysis of Inovation in Artificial Products Market"

version := "1.0"

libraryDependencies  ++= Seq(
  // Last snapshot
  "org.scalanlp" %% "breeze" % "latest.integration",

  // Native libraries are not included by default. add this if you want them (as of 0.7)
  // Native libraries greatly improve performance, but increase jar sizes.
  // It also packages various blas implementations, which have licenses that may or may not
  // be compatible with the Apache License. No GPL code, as best I know.
  "org.scalanlp" %% "breeze-natives" % "0.13",

  // The visualization library is distributed separately as well.
  // It depends on LGPL code.
  "org.scalanlp" %% "breeze-viz" % "0.13"

)
resolvers ++= Seq(
  // other resolvers here
  // if you want to use snapshot builds (currently 0.12-SNAPSHOT), use this.
  "Sonatype Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/",
  "Sonatype Releases" at "https://oss.sonatype.org/content/repositories/releases/"
)
scalaVersion := "2.12.1"

retrieveManaged := true

makePomConfiguration := makePomConfiguration.value.copy(file = new File("pom.xml"))
