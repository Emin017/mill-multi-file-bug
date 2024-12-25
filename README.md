# mill-multi-file-bug
To reproduce the bug, run `mill run` and it output:
```shell
$ mill run
[build.sc-64/68] compile
[build.sc-64] [info] compiling 1 Scala source to /home/nixos/Workbench/mill-multi-file-bug/out/mill-build/compile.dest/classes ...
[build.sc-64] [error] /home/nixos/Workbench/mill-multi-file-bug/build.sc:2:8: object build is not a member of package build_.foo
[build.sc-64] [error] import build_.foo.{build => buildModule}
[build.sc-64] [error]        ^
[build.sc-64] [error] /home/nixos/Workbench/mill-multi-file-bug/build.sc:9:29: not found: value buildModule
[build.sc-64] [error]       "MY_SCALA_VERSION" -> buildModule.build.myScalaVersion,
[build.sc-64] [error]                             ^
[build.sc-64] [error] two errors found
[68/68] ============================== run ============================== 2s
1 tasks failed
compile Compilation failed
```
