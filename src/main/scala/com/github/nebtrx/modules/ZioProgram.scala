package com.github.nebtrx.modules

import cats.Monad
import cats.effect.Sync
import cats.implicits._
import com.github.nebtrx.modules.application.{Console, ConsoleLive, Logging, LoggingLive, ServiceLive}
import com.github.nebtrx.modules.domain.repositories.{OrderRepositoryAlg, UserRepositoryAlg}
import scalaz.zio.internal.{Platform, PlatformLive}
import scalaz.zio.Task
import scalaz.zio.interop.catz._
import scalaz.zio.Runtime

import scala.concurrent.ExecutionContext


trait AppRuntime extends App with Runtime[Unit] {

  override val Platform: Platform       = PlatformLive.fromExecutionContext(ExecutionContext.global)
  override val Environment: Unit        = ()
}


object ZioProgram extends AppRuntime {

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

  unsafeRun(program[Task].either.map(_.fold(_ => 1, _ => 0)))
}
