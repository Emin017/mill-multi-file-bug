import mill._, scalalib._
import $file.foo.{build => buildModule}
import $file.foo.{common => commonModule}

object `package` extends RootModule with ScalaModule {
  def scalaVersion = "2.13.14"
  def forkEnv = T {
    Map(
      "MY_SCALA_VERSION" -> buildModule.build.myScalaVersion,
      "MY_PROJECT_VERSION" -> commonModule.common.myProjectVersion
    )
  }
}
