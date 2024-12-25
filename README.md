# mill-multi-file-bug
To reproduce the bug, run `mill bar.runMain hello.Hello` and it output:
```shell
$ mill bar.runMain hello.Hello
[build.sc-64/68] compile
[build.sc-64] [info] compiling 1 Scala source to /home/nixos/Workbench/mill-multi-file-bug/out/mill-build/compile.dest/classes ...
[build.sc-64] [error] /home/nixos/Workbench/mill-multi-file-bug/build.sc:2:8: object build is not a member of package build_.foo
[build.sc-64] [error] import build_.foo.{build => buildModule}
[build.sc-64] [error]        ^
[build.sc-64] [error] /home/nixos/Workbench/mill-multi-file-bug/build.sc:12:29: not found: value buildModule
[build.sc-64] [error]       "MY_SCALA_VERSION" -> buildModule.build.myScalaVersion,
[build.sc-64] [error]                             ^
[build.sc-64] [error] two errors found
[68/68] ============================== bar.runMain hello.Hello ============================== 60s
1 tasks failed
compile Compilation failed
```
It seems that the `build.sc` file is not being imported correctly, but the `common.sc` file is being imported correctly.

If we comment out the line `import build_.foo.{build => buildModule}` in `build.sc`:
```scala
import mill._, scalalib._
// import $file.foo.{build => buildModule}
import $file.foo.{common => commonModule}

object bar extends ScalaModule {
  def scalaVersion = "2.13.14"
  def sources = Task.Sources {
    super.sources() ++ Seq(PathRef(millSourcePath / "bar"))
  }
  def forkEnv = T {
    Map(
      "MY_SCALA_VERSION" -> "2.13.14",
      "MY_PROJECT_VERSION" -> commonModule.common.myProjectVersion
    )
  }
}
```
It can be compiled and run successfully:
```shell
$ mill bar.runMain hello.Hello
[68/68] bar.runMain
[68] Hello, world
[68/68] ============================== bar.runMain hello.Hello ==============================
```
