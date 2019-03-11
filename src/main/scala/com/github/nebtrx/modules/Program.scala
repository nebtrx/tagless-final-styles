package com.github.nebtrx.modules

import cats.Monad
import cats.effect.{ExitCode, IO, IOApp, Sync}
import cats.implicits._
import com.github.nebtrx.modules.application.{Console, ConsoleLive, Logging, LoggingLive, ServiceLive}
import com.github.nebtrx.modules.domain.repositories.{OrderRepositoryAlg, UserRepositoryAlg}

class Program extends IOApp {
  def program[F[_] : Monad: Sync]: F[Unit] = {
    val userRepo: UserRepositoryAlg[F] = ???
    val orderRepo: OrderRepositoryAlg[F] = ???
    val service: ServiceLive[F] = ServiceLive(userRepo, orderRepo)
    val console: Console[F] = ConsoleLive[F]
    val logging: Logging[F] = LoggingLive[F]

    for {
      _ <- console.putStrLn("Good morning, what is your name?")
      name <- console.getStrLn
      _ <- service.getUserSummary("userId")
      _ <- logging.debug(s"Saved $name to configuration")
      _ <- console.putStrLn(s"Good to meet you, $name!")
    } yield ()
  }

  override def run(args: List[String]): IO[ExitCode] = {
    program[IO].map(_ => ExitCode.Success)
  }
}
