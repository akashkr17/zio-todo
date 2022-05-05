package edu.knoldus

import java.io.IOException

import zio._

object ServiceMain extends ZIOAppDefault  {
  
  override def run: ZIO[Has[Console], IOException, Unit] = newApp

  val newApp: ZIO[Has[Console], IOException, Unit] = for {
    counter <- Ref.make(0)
    _ <- ZIO.foreachPar_(1 to 100).toList {
      _ =>
        counter.updateAndGet(_ + 1)
          .flatMap(reqNumber => Console.printLine(s"request number: $reqNumber"))
    }
    reqCounts <- counter.get
    _ <- Console.printLine(s"total requests performed: $reqCounts")
  } yield ()
//  val newApp: ZIO[Has[Logging], Nothing, Unit] = for {
//    logger <-  ZIO.service[Logging]
//    - <- logger.log("Hello World")
//  } yield ()
////  import edu.knoldus.Logging._
//
//  val myApp: ZIO[Any, Nothing, Unit] = newApp.inject{
//    LoggingLive.layer
//  }
}
