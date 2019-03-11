package com.github.nebtrx.modules.application

trait Console[F[_]] {
  def putStrLn(line: String): F[Unit]

  val getStrLn: F[String]
}

class ConsoleLive[F[_]] extends Console[F]{
  override def putStrLn(line: String): F[Unit] = ???

  override val getStrLn: F[String] = ???
}

object ConsoleLive {
  def apply[F[_]]: ConsoleLive[F] = new ConsoleLive[F]()
}
