package com.github.nebtrx.modules.application

import cats.Monad
import cats.effect.Sync
import com.github.nebtrx.common.domain.models.{EntityId, UserSummary}
import com.github.nebtrx.modules.domain.repositories.{OrderRepositoryAlg, UserRepositoryAlg}

trait Service[F[_]] {
  def getUserSummary(id: EntityId): F[Option[UserSummary]]
}

class ServiceLive[F[_] : Monad: Sync](userRepo: UserRepositoryAlg[F],
                                      oderRepo: OrderRepositoryAlg[F])
  extends Service[F] {

  override def getUserSummary(id: EntityId): F[Option[UserSummary]] = ???
}

object ServiceLive {
  def apply[F[_] : Monad: Sync](userRepo: UserRepositoryAlg[F], oderRepo: OrderRepositoryAlg[F]): ServiceLive[F] =
    new ServiceLive(userRepo, oderRepo)
}
