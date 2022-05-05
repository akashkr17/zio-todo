package edu.knoldus
import java.io.IOException

import zio._

object TestingEffect extends ZIOAppDefault {
  val square: ZIO[Int, Nothing, Int] =
    for {
      env <- ZIO.environment[Int]

    } yield env * env
  val result: UIO[Int] = square.provide(42)

  override def run: ZIO[zio.ZEnv with Has[ZIOAppArgs], Any, Any] = ???
}
