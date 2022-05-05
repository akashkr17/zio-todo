package edu.knoldus
import java.io.IOException

import zio._

object Concurrency extends ZIOAppDefault {
  override def run: ZIO[zio.ZEnv with Has[ZIOAppArgs], Any, Any] = zipp

  def fib(n: Long): UIO[Long] = UIO {
    if (n <= 1) UIO.succeed(n)
    else fib(n - 1).zipWith(fib(n - 2))(_ + _)
  }.flatten

  val fib100Fiber: ZIO[Any, Nothing, Fiber.Runtime[Nothing, Long]] =
    for {
      fiber <- fib(100).fork
    } yield fiber

  for {
    fiber   <- IO.succeed("Hi!").fork
    message <- fiber.join
  } yield message

  for {
    fiber <- IO.succeed("Hi!").fork
    exit  <- fiber.await
  } yield exit

  for {
    fiber <- IO.succeed("Hi!").forever.fork
    exit  <- fiber.interrupt
  } yield exit

  val zipp: ZIO[Has[Console], IOException, (String, String)] = for {
    fiber1 <- IO.succeed("Hi!").fork
    fiber2 <- IO.succeed("Bye!").fork
    fiber   = fiber1.zip(fiber2)
    tuple  <- fiber.join
    _ <- Console.printLine(tuple)
  } yield tuple
}
