import AssemblyKeys._

seq(assemblySettings: _*)

name := "bootstrap"

version := "0.2"

organization :="fr.janalyse"

organizationHomepage := Some(new URL("http://www.janalyse.fr"))

scalaVersion := "2.10.3"

crossScalaVersions := Seq("2.10", "2.10.1", "2.10.2", "2.10.3")

libraryDependencies += "org.scalatest" % "scalatest_2.10" % "2.0" % "test"

libraryDependencies <++=  scalaVersion { sv =>
   ("org.scala-lang" % "scala-swing" % sv) ::
   ("org.scala-lang" % "jline"           % sv  % "compile") ::
   ("org.scala-lang" % "scala-compiler"  % sv  % "compile") ::
   ("org.scala-lang" % "scalap"          % sv  % "compile") ::
   ("org.scala-lang" % "scala-swing"     % sv  % "compile") ::Nil   
}

libraryDependencies += "junit" % "junit" % "4.10" % "test"


mainClass in assembly := Some("fr.janalyse.script.Bootstrap")

jarName in assembly := "bootstrap.jar"

mergeStrategy in assembly <<= (mergeStrategy in assembly) { (old) =>
  {
    case PathList("org", "fusesource", xs @ _*) => MergeStrategy.first
    case PathList("META-INF", xs @ _*) => MergeStrategy.discard
    case PathList("rootdoc.txt") => MergeStrategy.discard
    case x => old(x)
  }
}
