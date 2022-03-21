package edu.knoldus

import zio._

object AsyncMain extends ZIOAppDefault {
  case class User()
  case class AuthError()
  object legacy {
    def login(
               onSuccess: User => Unit,
               onFailure: AuthError => Unit): Unit = ???
  }

  val login: ZIO[Any, AuthError, User] =
    ZIO.async[Any, AuthError, User] { callback =>
      legacy.login(
        user => callback(IO.succeed(user)),
        err  => callback(IO.fail(err))
      )
    }

  override def run: ZIO[Any, AuthError, User] = login
}
