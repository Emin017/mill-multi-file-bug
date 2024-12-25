import mill._, scalalib._
import $file.foo.{build => buildModule}
import $file.foo.{common => commonModule}

object bar extends ScalaModule {
  def scalaVersion = "2.13.14"
  def sources = Task.Sources {
    super.sources() ++ Seq(PathRef(millSourcePath / "bar"))
  }
  def forkEnv = T {
    Map(
      "MY_SCALA_VERSION" -> buildModule.build.myScalaVersion,
      "MY_PROJECT_VERSION" -> commonModule.common.myProjectVersion
    )
  }
}
