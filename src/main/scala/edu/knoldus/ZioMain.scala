package edu.knoldus

import java.io.IOException

import zio._

import scala.io.Source

object ZioMain extends ZIOAppDefault {

  def run: ZIO[Has[Console], Throwable, Unit] = fileReader
  val flatMapApp: ZIO[Has[Console], IOException, Unit] = for {
    _ <- Console.printLine("Hello! What is your name?")
    n <- Console.readLine
    _ <- Console.printLine("Hello, " + n + ", good to meet you!")
  } yield ()

//  val zipApp: ZIO[Has[Console] with Has[Random], IOException, Unit] = for {
//    _ <- Console.printLine("Enter a new user name")
//    (uuid, username) <- Random.nextUUID zip Console.readLine
//    _ <- Console.printLine("Hello, " + username + ", good to meet you!")
//  } yield ()

  val resourceApp: ZIO[Any, Throwable, String] = ZIO.acquireReleaseWith(
    ZIO.attemptBlocking(Source.fromFile("file.txt"))
  )(file => ZIO.attempt(file.close()).orDie) { file =>
    ZIO.attemptBlocking(file.getLines().mkString("\n"))
  }
  val fileReader: ZIO[Has[Console], Throwable, Unit] = for {
    data <- resourceApp
    _ <- Console.printLine(data)
  } yield ()

//  val myApp: ZIO[Has[Console], IOException, Unit] = {
//    Console.printLine("Hello Zio"
  //    )
//
//  }
}
