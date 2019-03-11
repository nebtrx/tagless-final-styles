package com.github.nebtrx.common.domain.models

case class Order(id: EntityId, userId: EntityId, items: List[Product])
