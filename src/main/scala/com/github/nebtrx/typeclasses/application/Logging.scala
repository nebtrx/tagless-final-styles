package com.github.nebtrx.typeclasses.application

import simulacrum.typeclass
import scala.language.implicitConversions

@typeclass trait Logging[F[_]] {
  def debug(content: String): F[Unit]
}
