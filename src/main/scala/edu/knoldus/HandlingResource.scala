package edu.knoldus
import zio._

object HandlingResource extends ZIOAppDefault {
  val finalizer: UIO[Unit] =
    UIO.effectTotal(println("Finalizing!"))
  // finalizer: UIO[Unit] = zio.ZIO$EffectTotal@6fde673b

  val finalized: IO[String, Unit] =
    IO.fail("Failed!").ensuring(finalizer)
  // finalized: IO[String, Unit] = zio.ZIO$CheckInterrupt@38d5ec27


  //bracket
//  val groupedFileData: IO[IOException, Unit] =
//    openFile("data.json").bracket(closeFile(_)) { file =>
//      for {
//        data    <- decodeData(file)
//        grouped <- groupData(data)
//      } yield grouped
//    }
  override def run: ZIO[zio.ZEnv with Has[ZIOAppArgs], Any, Any] = finalizer
}
