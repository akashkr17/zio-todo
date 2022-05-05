//package edu.knoldus
//
//class Polymorphic {
//  trait Console[F[_]] {
//    def putStrLn(line: String): F[Unit]
//
//    val getStrLn: F[String]
//  }
//  object Console {
//    def apply[F[_]](implicit F: Console[F]): Console[F] = F
//  }
//
//  implicit val ConsoleIO: Console[IO] = new Console[IO] {
//    def putStrLn(line: String): Task[Unit] =
//      IO.effect(println(line))
//
//    val getStrLn: IO[String] =
//      IO.effect(scala.io.StdIn.readLine())
//  }
//
//  def program[F[_]: Console: Monad]: F[String] =
//    for {
//      _    <- Console[F].putStrLn("Good morning, what's your name?")
//      name <- Console[F].getStrLn
//      _    <- Console[F].putStrLn(s"Great to meet you, $name")
//    } yield name
//
//  case class TestData(input: List[String], output: List[String])
//  case class TestIO[A](run: TestData => (TestData, A)) { s =>
//    def map[B](f: A => B): TestIO[B] = flatMap(a => TestIO.value(f(a)))
//    def flatMap[B](f: A => TestIO[B]): TestIO[B] =
//      TestIO(d =>
//        (s run d) match { case (d, a) => f(a) run d })
//  }
//  object TestIO {
//    def value[A](a: => A): TestIO[A] = TestIO(d => (d, a))
//  }
//
//  val programTest: TestIO[String] = program[TestIO]
//}
