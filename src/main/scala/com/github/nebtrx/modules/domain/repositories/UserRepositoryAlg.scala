package com.github.nebtrx.modules.domain.repositories

import com.github.nebtrx.common.domain.models.{EntityId, User}

trait UserRepositoryAlg[F[_]] {
  def getUser(id: EntityId): F[Option[User]]
}
