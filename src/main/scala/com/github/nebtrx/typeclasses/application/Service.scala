package com.github.nebtrx.typeclasses.application

import com.github.nebtrx.common.domain.models.{EntityId, UserSummary}
import simulacrum.typeclass
import scala.language.implicitConversions

@typeclass trait Service[F[_]] {
  def getUserSummary(id: EntityId): F[Option[UserSummary]]
}

