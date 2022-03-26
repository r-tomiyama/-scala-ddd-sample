package presentation

import application.UserUsecase

object Main {
  def main(args: Array[String]): Unit = {
    println("hello world")

    val userUsecase = UserUsecase()
    userUsecase.create(args.head)
  }
}
