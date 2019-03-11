package com.github.nebtrx.typeclasses

import cats.effect.IO
import com.github.nebtrx.common.domain.models.{EntityId, Order, User, UserSummary}
import com.github.nebtrx.typeclasses.application.{Console, Logging, Service}
import com.github.nebtrx.typeclasses.domain.repositories.{OrderRepositoryAlg, UserRepositoryAlg}

package object implicits {

  implicit val userRepositoryIO: UserRepositoryAlg[IO] = new UserRepositoryAlg[IO] {
    override def getUser(id: EntityId): IO[Option[User]] = ???
  }
  implicit val orderRepositoryIO: OrderRepositoryAlg[IO] = new OrderRepositoryAlg[IO] {
    override def getOrder(id: EntityId): IO[Option[Order]] = ???

    override def getOrderByUser(userId: EntityId): IO[Option[Order]] = ???
  }

  implicit val serviceIO: Service[IO] = new Service[IO] {
    override def getUserSummary(id: EntityId): IO[Option[UserSummary]] = ???
  }

  implicit val consoleIO: Console[IO] = new Console[IO] {
    override def putStrLn(line: String): IO[Unit] = ???

    override def getStrLn: IO[String] = ???
  }

  implicit val loggingIO: Logging[IO] = new Logging[IO] {
    override def debug(content: String): IO[Unit] = ???
  }
}
