//package edu.knoldus
//
//import java.io.{FileNotFoundException, IOException}
//
//import zio._
//
//object Zioeffect extends ZIOAppDefault {
//  override def run: ZIO[zio.ZEnv with Has[ZIOAppArgs], Any, Any] = res
//  val zoption: IO[Option[Nothing], Int] = ZIO.fromOption(Some(2))
//
//  case class User(id: String,teamId: String)
//  case class Team()
//  val maybeId: IO[Option[Nothing], String] = ZIO.fromOption(Some("abc123"))
//  def getUser(userId: String): IO[Throwable, Option[User]] = ???
//  def getTeam(teamId: String): IO[Throwable, Team] = ???
//
//
//  val result: IO[Throwable, Option[(User, Team)]] = (for {
//    id   <- maybeId
//    user <- getUser(id).some
//
//    team <- getTeam(user.teamId).asSomeError
//  } yield (user, team)).optional
//
//  val zeither: IO[Nothing, String] = ZIO.fromEither(Right("Success!"))
//
//  import scala.util.Try
//
//  val ztry: Task[Int] = ZIO.fromTry(Try(42 / 0))
//
//  val zfun: URIO[Int, Int] =
//    ZIO.fromFunction((i: Int) => i * i)
//
//  import scala.concurrent.Future
//
//  lazy val future = Future.successful("Hello!")
//
//  val zfuture: Task[String] =
//    ZIO.fromFuture { implicit ec =>
//      future.map(_ => "Goodbye!")
//    }
//
//  import scala.io.StdIn
//
//  val getStrLn: Task[String] =
//    ZIO.effect(StdIn.readLine())
//
//
//  val succeeded: UIO[Int] = IO.succeed(21).map(_ * 2)
//  val failed: IO[Exception, Unit] =
//    IO.fail("No no!").mapError(msg => new Exception(msg))
//
//  val zipped: UIO[(String, Int)] =
//    ZIO.succeed("4").zip(ZIO.succeed(2))
//
//  val zEitherHandle : UIO[Either[String, Int]] =
//    IO.fail("Uh oh!").either
////
//  val z: IO[IOException, Array[Byte]] =
//    openFile("primary.json").catchAll(_ =>
//      openFile("backup.json"))
//
//  val data: IO[IOException, Array[Byte]] =
//    openFile("primary.data").catchSome {
//      case _ : FileNotFoundException =>
//        openFile("backup.data")
//    }
////val primaryOrBackupData: IO[IOException, Array[Byte]] =
////openFile("primary.data").orElse(openFile("backup.data"))
//
//
//lazy val DefaultData: Array[Byte] = Array(0, 0)
//
//  val primaryOrDefaultData: UIO[Array[Byte]] =
//    openFile("primary.data").fold(
//      _    => DefaultData,
//      data => data)
//
//  val urls: UIO[Content] =
//    readUrls("urls.json").foldM(
//      error   => IO.succeed(NoContent(error)),
//      success => fetchContent(success)
//    )
//
////  import zio.clock._
////
////  val retriedOpenFile: ZIO[Clock, IOException, Array[Byte]] =
////    openFile("primary.data").retry(Schedule.recurs(5))
//  val res: ZIO[Has[Console], Throwable, Unit] = for{
//    z <- zfuture
//    _ <- Console.printLine(z)
////    in <- getStrLn
////    _ <- Console.printLine(in)
//    suc <- succeeded
//    _ <- Console.printLine(suc)
//    zip <- zipped
//    _ <- Console.printLine(zip)
////    fail <- failed
////    _ <- Console.printLine(fail)
//        fail <- zEitherHandle
//        _ <- Console.printLine(fail)
//
//
//  } yield ()
//
//}
