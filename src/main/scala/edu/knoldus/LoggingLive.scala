//package edu.knoldus
//
//import java.io.IOException
//
//import zio._
//
//case class LoggingLive(console: Console, clock: Clock) extends Logging {
//  override def log(line: String): UIO[Unit] = for {
//    current <- clock.currentDateTime
//    _ <- console.printLine(s"$current--$line").orDie
//} yield ()
//}
//object LoggingLive {
//  val layer: URLayer[Has[Console] with Has[Clock], Has[LoggingLive]] = (LoggingLive(_,_)).toLayer
//}