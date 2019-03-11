package com.github.nebtrx.typeclasses.domain.repositories

import com.github.nebtrx.common.domain.models.{EntityId, Order}
import simulacrum.typeclass
import scala.language.implicitConversions


@typeclass trait OrderRepositoryAlg[F[_]] {
  def getOrder(id: EntityId): F[Option[Order]]

  def getOrderByUser(userId: EntityId): F[Option[Order]]
}
