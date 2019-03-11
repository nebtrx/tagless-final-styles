package com.github.nebtrx.typeclasses

import cats.Monad
import cats.effect.{ExitCode, IO, IOApp, Sync}
import com.github.nebtrx.typeclasses.application.{Console, Logging, Service}
import com.github.nebtrx.typeclasses.domain.repositories.{OrderRepositoryAlg, UserRepositoryAlg}
import cats.implicits._
//import com.github.nebtrx.common.domain.models.{EntityId, Order, User, UserSummary}


class Program extends IOApp {
  // Typeclasses approach
  def program[F[_] : Monad : Sync : Console : Logging : Service : UserRepositoryAlg : OrderRepositoryAlg]: F[Unit] = {

    for {
      _ <- Console[F].putStrLn("Good morning, what is your name?")
      name <- Console[F].getStrLn
      _ <- Service[F].getUserSummary("userId")
      _ <- Logging.apply[F].debug(s"Saved $name to configuration")
      _ <- Console[F].putStrLn(s"Good to meet you, $name!")
    } yield ()
  }

//  // Typeclasses env approach
//  trait Env[F[_]]
//    extends Console[F]
//      with Logging[F]
//      with Service[F]
//      with UserRepositoryAlg[F]
//      with OrderRepositoryAlg[F]
//
//  object Env {
//
//    implicit def env[F[_] : Logging : Console : Service : UserRepositoryAlg : OrderRepositoryAlg]: Env[F] = new Env[F] {
//      override def getUserSummary(id: EntityId): F[Option[UserSummary]] = Service[F].getUserSummary(id)
//
//      override def debug(content: String): F[Unit] = Logging[F].debug(content)
//
//      override def getUser(id: EntityId): F[Option[User]] = UserRepositoryAlg[F].getUser(id)
//
//      override def putStrLn(line: String): F[Unit] = Console[F].putStrLn(line)
//
//      override def getStrLn: F[String] = Console[F].getStrLn
//
//      override def getOrder(id: EntityId): F[Option[Order]] = OrderRepositoryAlg[F].getOrder(id)
//
//      override def getOrderByUser(userId: EntityId): F[Option[Order]] = OrderRepositoryAlg[F].getOrderByUser(userId)
//    }
//  }

//  def program2[F[_] : Monad : Sync : Env]: F[Unit] = {
//
//    val env = Env[F]
//    implicitly[Monad[F]]
//    implicitly[Sync[F]]
//    implicitly[Console[F]]
//    implicitly[Logging[F]]
//    implicitly[Service[F]]
//    implicitly[UserRepositoryAlg[F]]
//    implicitly[OrderRepositoryAlg[F]]
//
//    for {
//      _ <- env.putStrLn("Good morning, what is your name?")
//      name <- Env[F].getStrLn
//      _ <- Env[F].getUserSummary("userId")
//      _ <- Env.apply[F].debug(s"Saved $name to configuration")
//      _ <- Env[F].putStrLn(s"Good to meet you, $name!")
//    } yield ()
//  }

  // Run

  override def run(args: List[String]): IO[ExitCode] = {
    import com.github.nebtrx.typeclasses.implicits._

    program[IO].map(_ => ExitCode.Success)
  }
}
