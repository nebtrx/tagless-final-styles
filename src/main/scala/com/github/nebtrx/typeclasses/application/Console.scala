package com.github.nebtrx.typeclasses.application

import simulacrum.typeclass
import scala.language.implicitConversions

@typeclass trait Console[F[_]] {
  def putStrLn(line: String): F[Unit]

  def getStrLn: F[String]
}
