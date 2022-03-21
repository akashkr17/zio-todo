package edu.knoldus

import zio._

object Zioeffect extends ZIOAppDefault {
  override def run: ZIO[zio.ZEnv with Has[ZIOAppArgs], Any, Any] = res
  val zoption: IO[Option[Nothing], Int] = ZIO.fromOption(Some(2))

  case class User(id: String,teamId: String)
  case class Team()
  val maybeId: IO[Option[Nothing], String] = ZIO.fromOption(Some("abc123"))
  def getUser(userId: String): IO[Throwable, Option[User]] = ???
  def getTeam(teamId: String): IO[Throwable, Team] = ???


  val result: IO[Throwable, Option[(User, Team)]] = (for {
    id   <- maybeId
    user <- getUser(id).some

    team <- getTeam(user.teamId).asSomeError
  } yield (user, team)).optional

  val zeither: IO[Nothing, String] = ZIO.fromEither(Right("Success!"))

  import scala.util.Try

  val ztry: Task[Int] = ZIO.fromTry(Try(42 / 0))

  val zfun: URIO[Int, Int] =
    ZIO.fromFunction((i: Int) => i * i)

  import scala.concurrent.Future

  lazy val future = Future.successful("Hello!")

  val zfuture: Task[String] =
    ZIO.fromFuture { implicit ec =>
      future.map(_ => "Goodbye!")
    }
  val res: ZIO[Has[Console], Throwable, Unit] = for{
    z <- zfuture
    _ <- Console.printLine(z)
  } yield ()
}
