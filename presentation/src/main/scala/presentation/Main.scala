package presentation

import application.UserUsecase

object Main {
  def main(args: Array[String]): Unit = {
    println("hello world")

    println(UserUsecase.create(args.head))
  }

  // TODO: サークル機能を作る
}
