package com.github.nebtrx.modules.domain.repositories

import com.github.nebtrx.common.domain.models.{EntityId, Order}

trait OrderRepositoryAlg[F[_]] {
  def getOrder(id: EntityId): F[Option[Order]]

  def getOrderByUser(userId: EntityId): F[Option[Order]]
}
