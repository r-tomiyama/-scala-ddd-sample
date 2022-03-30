package presentation

import application.UserUsecase

object Main {
  def main(args: Array[String]): Unit = {
    println("hello world")

    val userUsecase = UserUsecase()
    println(userUsecase.create(args.head))
  }

  // TODO: サークル機能を作る
}
