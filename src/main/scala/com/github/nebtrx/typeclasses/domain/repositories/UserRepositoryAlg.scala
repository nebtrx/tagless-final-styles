package com.github.nebtrx.typeclasses.domain.repositories

import com.github.nebtrx.common.domain.models.{EntityId, User}
import simulacrum.typeclass
import scala.language.implicitConversions

@typeclass trait UserRepositoryAlg[F[_]] {
  def getUser(id: EntityId): F[Option[User]]
}
