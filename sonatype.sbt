// Your profile name of the sonatype account. The default is the same with the organization value
sonatypeProfileName := "com.github.jirotubuyaki"

// To sync with Maven central, you need to supply the following information:
publishMavenStyle := true

// License of your choice
organization := "com.github.jirotubuyaki"
licenses := Seq("APL2" -> url("http://www.apache.org/licenses/LICENSE-2.0.txt"))
homepage := Some(url("https://jirotubuyaki.github.io/"))
scmInfo := Some(
  ScmInfo(
    url("https://github.com/jirotubuyaki/AgentMarketModel"),
    "scm:git@github.com:jirotubuyaki/AgentMarketModel.git"
  )
)
developers := List(
  Developer(id = "1",name = "Masashi OKADA", email = "okadaalgorithm@gmail.com", url = url("https://jirotubuyaki.github.io/"))
)
