package com.github.nebtrx.modules.application

trait Logging[F[_]] {
  def debug(content: String): F[Unit]
}

class LoggingLive[F[_]]  extends  Logging[F] {
  override def debug(content: String): F[Unit] = ???
}

object LoggingLive {
  def apply[F[_]]: LoggingLive[F] = new LoggingLive[F]()
}
